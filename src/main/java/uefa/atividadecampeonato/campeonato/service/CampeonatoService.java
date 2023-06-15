package uefa.atividadecampeonato.campeonato.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;
import uefa.atividadecampeonato.campeonato.repository.CampeonatoRepository;
import uefa.atividadecampeonato.campeonato.requests.CampeonatoPutRequestBody;
import uefa.atividadecampeonato.exception.BadRequestException;
import uefa.atividadecampeonato.tabela.domain.Tabela;
import uefa.atividadecampeonato.tabela.repository.TabelaRepository;
import uefa.atividadecampeonato.times.domain.Times;
import uefa.atividadecampeonato.times.service.TimesService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CampeonatoService {

    private final CampeonatoRepository campeonatoRepository;
    private final TabelaRepository tabelaRepository;
    private final TimesService timesService;

    @Autowired
    public CampeonatoService(CampeonatoRepository campeonatoRepository, TabelaRepository tabelaRepository, TimesService timesService) {
        this.campeonatoRepository = campeonatoRepository;
        this.tabelaRepository = tabelaRepository;
        this.timesService = timesService;
    }

    public List<Campeonato> listAll() {
        return campeonatoRepository.findAll();
    }

    public Campeonato findByIdOrThrowBadRequestException(int id) {
        return campeonatoRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Campeonato not found"));
    }

    public Campeonato save(Campeonato campeonato) {
        verificaNovoCamp(campeonato);
        return campeonatoRepository.save(campeonato);
    }

    public void delete(int id) {
        campeonatoRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(CampeonatoPutRequestBody campeonatoPutRequestBody) {
        Campeonato savedCampeonato = findByIdOrThrowBadRequestException(campeonatoPutRequestBody.getIdCamp());
        savedCampeonato.setIdCamp(campeonatoPutRequestBody.getIdCamp());
        campeonatoRepository.save(savedCampeonato);
    }

    public void tabelaParaTime(CampeonatoPutRequestBody campeonatoPutRequestBody, Campeonato campeonato) {
        campeonatoPutRequestBody.getIdTimes().forEach(idTime -> {
            Tabela tabela1 = criaTabela(campeonato, timesService.findByIdOrThrowBadRequestException(idTime));
            tabelaRepository.save(tabela1);
        });
    }

    public Tabela criaTabela(Campeonato campeonato, Times times) {
        Tabela tabela = new Tabela();
        tabela.setCampeonato(campeonato);
        tabela.setTimes(times);
        tabela.setPontuacao(0);
        tabela.setQntdDeVitorias(0);
        tabela.setQntdDeEmpates(0);
        tabela.setQntdDeDerrotas(0);
        tabela.setQntdDeGolsFeitos(0);
        tabela.setQntdDeGolsSofridos(0);
        return tabela;
    }

    public void inicia(CampeonatoPutRequestBody campeonatoPutRequestBody) {
        verificaInicio(campeonatoPutRequestBody);
        Campeonato campeonato1 = this.findByIdOrThrowBadRequestException(campeonatoPutRequestBody.getIdCamp());
        campeonato1.setIniciado(true);
        campeonatoRepository.save(campeonato1);
        tabelaParaTime(campeonatoPutRequestBody, campeonato1);
    }

    public void finaliza(int id) {
        verificaFinal(id);
        Campeonato campeonato = this.findByIdOrThrowBadRequestException(id);
        campeonato.setIniciado(false);
        campeonato.setFinalizado(true);
        campeonatoRepository.save(campeonato);
    }

    public void verificaFinal(int id) {
        seNaoIniciadoOuFinalizado(id);
        verificaNumeroJogos(id);
    }

    public void verificaNovoCamp(Campeonato campeonato) {
        validaCriacao(campeonato);
        validaAno(campeonato);
        statusAtual(campeonato);
    }

    public void verificaInicio(CampeonatoPutRequestBody campeonatoPutRequestBody) {
        verificaInicioOuFinal(campeonatoPutRequestBody);
        qntdMinima(campeonatoPutRequestBody);
        verificaTimeRepetido(campeonatoPutRequestBody);
    }

    public void verificaInicioOuFinal(CampeonatoPutRequestBody campeonatoPutRequestBody) {
        if (campeonatoRepository.findByIniciado(campeonatoPutRequestBody.getIdCamp()) == 1 ||
                campeonatoRepository.findByFinalizado(campeonatoPutRequestBody.getIdCamp()) == 1) {
            throw new RuntimeException("Campeonato já foi iniciado ou finalizado");
        }
    }

    public void seNaoIniciadoOuFinalizado(int id) {
        if (campeonatoRepository.findByFinalizado(id) == 1 || (campeonatoRepository.findByFinalizado(id) == 1
                && campeonatoRepository.findByIniciado(id) == 0)) {
            throw new RuntimeException("O campeonato não foi iniciado ou já foi finalizado");
        }
    }

    public void nenhumDosDois(int id) {
        if (campeonatoRepository.findByIniciado(id) == 0 && campeonatoRepository.findByFinalizado(id) == 0) {
            throw new RuntimeException("O campeonato não foi iniciado nem finalizado");
        }
    }

    public void statusAtual(Campeonato campeonato) {
        if (campeonato.isIniciado() || campeonato.isFinalizado()) {
            throw new RuntimeException("O campeonato não pode ter sido iniciado nem finalizado");
        }
    }

    public void validaCriacao(Campeonato campeonato) {
        if (campeonatoRepository.existsByNomeAndAno(campeonato.getNome(), campeonato.getAno())) {
            throw new RuntimeException("O campeonato já foi criado");
        }
    }

    public void validaAno(Campeonato campeonato) {
        LocalDateTime now = LocalDateTime.now();

        if (campeonato.getAno() < now.getYear()) {
            throw new RuntimeException("A data de criação tem que ser superior a de hoje");
        }
    }

    public void qntdMinima(CampeonatoPutRequestBody campeonatoPutRequestBody) {
        if (campeonatoPutRequestBody.getIdTimes().size() < 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possivel criar um campeonato com menos de dois times");
        }
    }

    public void verificaTimeRepetido(CampeonatoPutRequestBody campeonatoPutRequestBody) { //estava no jogos
        Set<Integer> set = new HashSet<>();

        campeonatoPutRequestBody.getIdTimes().forEach(idTime -> {
            if (!set.add(idTime)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um dos times já foi inserido");
            }
        });
    }

    public void verificaNumeroJogos(int id){
        int partidas = tabelaRepository.qntdDeTimes(id) * (tabelaRepository.qntdDeTimes(id) - 1);
        if(campeonatoRepository.numeroJogos(id) != partidas){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os times precisam ter jogados no minimo duas vezes");
        }
    }

}