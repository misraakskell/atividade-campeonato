package uefa.atividadecampeonato.campeonato.service;

import javafx.scene.control.Tab;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;
import uefa.atividadecampeonato.campeonato.repository.CampeonatoRepository;
import uefa.atividadecampeonato.campeonato.requests.CampeonatoPutRequestBody;
import uefa.atividadecampeonato.exception.BadRequestException;
import uefa.atividadecampeonato.tabela.domain.Tabela;
import uefa.atividadecampeonato.times.domain.Times;
import uefa.atividadecampeonato.times.service.TimesService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CampeonatoService {

    private final CampeonatoRepository campeonatoRepository;

    public List<Campeonato> listAll() {
        return campeonatoRepository.findAll();
    }

    public Campeonato findByIdOrThrowBadRequestException(int id) {
        return campeonatoRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Campeonato not found"));
    }

    public Campeonato save(Campeonato campeonato) {
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

//    public void tabelaParaTime(CampeonatoPutRequestBody campeonatoPutRequestBody, Campeonato campeonato){
//        campeonatoPutRequestBody.getIdTimes().forEach(idTime -> {
//            Tabela tabela = criaTabela(campeonato, TimesService.findByIdOrThrowBadRequestException(idTime));
//            tabelaRepository.save(tabela);
//        });
//    }

    public Tabela criaTabela(Campeonato campeonato, Times times){
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

//        createTabelaPontForEachTime(dtoCampeonato, domainCampeonato);

    public void inicia(Campeonato campeonato){
        verificaNovoCamp(campeonato);
        Campeonato campeonato1 = this.findByIdOrThrowBadRequestException(campeonato.getIdCamp());
        campeonato1.setIniciado(true);
        campeonatoRepository.save(campeonato);
    }

    public void finaliza(int id){
        verificaFinal(id);
        Campeonato campeonato = this.findByIdOrThrowBadRequestException(id);
        campeonato.setIniciado(true);
        campeonato.setFinalizado(false);
        campeonatoRepository.save(campeonato);
    }

    public void verificaFinal(int id){
        verificaFinal(id);
    }

    public void verificaNovoCamp(Campeonato campeonato){
        validaCriacao(campeonato);
        validaAno(campeonato);
    }

    public void verificaInicioOuFinal(CampeonatoPutRequestBody campeonatoPutRequestBody){
        if(campeonatoRepository.campIniciado(campeonatoPutRequestBody.getIdCamp()) ||
            campeonatoRepository.campFinalizado(campeonatoPutRequestBody.getIdCamp())){
            throw new RuntimeException("Campeonato já foi iniciado ou finalizado");
        }
    }

    public void seNaoIniciadoOuFinalizado(int id){
        if(campeonatoRepository.campFinalizado(id) || (campeonatoRepository.campFinalizado(id)
                && !campeonatoRepository.campIniciado(id))) {
            throw new RuntimeException("O campeonato não foi iniciado ou já foi finalizado");
        }
    }

    public void nenhumDosDois(int id){
        if(campeonatoRepository.campIniciado(id) && campeonatoRepository.campFinalizado(id)){
            throw new RuntimeException("O campeonato não foi iniciado nem finalizado");
        }
    }

    public void validaCriacao(Campeonato campeonato){
        if (campeonatoRepository.verificaNomeAno(campeonato.getNome(), campeonato.getAno())){
            throw new RuntimeException("O campeonato já foi criado");
        }
    }

    public void validaAno(Campeonato campeonato){
        LocalDateTime now = LocalDateTime.now();

        if(campeonato.getAno() < now.getYear()){
            throw new RuntimeException("A data de criação tem que ser superior a de hoje");
        }
    }

    public void qntdMinima(CampeonatoPutRequestBody campeonatoPutRequestBody){
        if(campeonatoPutRequestBody.getIdTimes().size() < 2){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possivel criar um campeonato com menos de dois times");
        }
    }

    public void verificaTimeRepetido(CampeonatoPutRequestBody campeonatoPutRequestBody){ //estava no jogos
        Set<Long> set = new HashSet<>();
        campeonatoPutRequestBody.getIdTimes().forEach(idTime -> {
            if(!set.add(idTime)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um dos times já foi inserido");
            }
        });
    }

}

