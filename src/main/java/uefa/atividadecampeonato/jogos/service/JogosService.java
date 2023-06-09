package uefa.atividadecampeonato.jogos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;
import uefa.atividadecampeonato.campeonato.repository.CampeonatoRepository;
import uefa.atividadecampeonato.campeonato.service.CampeonatoService;
import uefa.atividadecampeonato.exception.BadRequestException;
import uefa.atividadecampeonato.jogos.domain.Jogos;
import uefa.atividadecampeonato.jogos.repository.JogosRepository;
import uefa.atividadecampeonato.jogos.requests.JogosPutRequestBody;
import uefa.atividadecampeonato.tabela.domain.Tabela;
import uefa.atividadecampeonato.tabela.repository.TabelaRepository;
import uefa.atividadecampeonato.tabela.service.TabelaService;
import uefa.atividadecampeonato.times.service.TimesService;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JogosService {

    private final JogosRepository jogosRepository;
    private final TabelaRepository tabelaRepository;
    private final CampeonatoRepository campeonatoRepository;
    private final CampeonatoService campeonatoService;
    private final TimesService timesService;
    private final TabelaService tabelaService;

    public List<Jogos> listAll() {
        return jogosRepository.findAll();
    }

    public Jogos findByIdOrThrowBadRequestException(int id) {
        return jogosRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Jogo not found"));
    }

    public Jogos save(JogosPutRequestBody jogosPutRequestBody) {
        jogoNovo(jogosPutRequestBody);
        Jogos jogoSaved = new Jogos();

        jogoSaved.setTimeMandante(timesService.findByIdOrThrowBadRequestException(jogosPutRequestBody.getTimeMandante()).getIdTime());
        jogoSaved.setTimeVisitante(timesService.findByIdOrThrowBadRequestException(jogosPutRequestBody.getTimeVisitante()).getIdTime());
        jogoSaved.setGolsMandante(jogosPutRequestBody.getGolsMandante());
        jogoSaved.setGolsVisitante(jogosPutRequestBody.getGolsVisitante());
        jogoSaved.setDataJogo(jogosPutRequestBody.getDataJogo());
        jogoSaved.setCampeonato(jogosPutRequestBody.getCampeonato());
        vencedor(jogosPutRequestBody);
        return jogosRepository.save(jogoSaved);
    }

    public void delete(int id) {
        jogosRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(JogosPutRequestBody jogosPutRequestBody) {
        Jogos savedJogos = findByIdOrThrowBadRequestException(jogosPutRequestBody.getIdJogo());
        savedJogos.setIdJogo(jogosPutRequestBody.getIdJogo());
        savedJogos.setTimeMandante(jogosPutRequestBody.getTimeMandante());
        savedJogos.setTimeVisitante(jogosPutRequestBody.getTimeVisitante());
        savedJogos.setGolsMandante(jogosPutRequestBody.getGolsMandante());
        savedJogos.setGolsVisitante(jogosPutRequestBody.getGolsVisitante());
        savedJogos.setCampeonato(jogosPutRequestBody.getCampeonato());
        jogosRepository.save(savedJogos);
    }

    public void verificaInicioJogo(JogosPutRequestBody jogosPutRequestBody){ //necessario a mesma validação? igual do camp?
        inicioCamp(jogosPutRequestBody);
        verificaTimeCamp(jogosPutRequestBody);
    }

    public void jogoNovo(JogosPutRequestBody jogosPutRequestBody){
        if(Objects.nonNull(jogosPutRequestBody.getCampeonato())){
            verificaInicioJogo(jogosPutRequestBody);
            jogoExiste(jogosPutRequestBody);
        }
        dataDisponivel(jogosPutRequestBody);
        timeRepetido(jogosPutRequestBody);
        timeDisponivel(jogosPutRequestBody);
    }

    public void inicioCamp(JogosPutRequestBody jogosPutRequestBody){
        //campeonatoService.seNaoIniciadoOuFinalizado(jogosPutRequestBody.getCampeonato());
        if(campeonatoRepository.findByIniciado(jogosPutRequestBody.getCampeonato()) == 0 || campeonatoRepository.findByFinalizado(jogosPutRequestBody.getCampeonato()) == 1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campeonato não foi iniciado ou já foi finalizado");
        }
    }

    public void verificaTimeCamp(JogosPutRequestBody jogosPutRequestBody){
        if(tabelaRepository.campPorTabela(jogosPutRequestBody.getCampeonato(), jogosPutRequestBody.getTimeMandante()) == 0
            || tabelaRepository.campPorTabela(jogosPutRequestBody.getCampeonato(), jogosPutRequestBody.getTimeVisitante()) == 0){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um dos times não está no campeonato");
        }
    }

    public void jogoExiste(JogosPutRequestBody jogosPutRequestBody){
        if(jogosRepository.jogoExiste(jogosPutRequestBody.getCampeonato(), jogosPutRequestBody.getTimeMandante(),
                jogosPutRequestBody.getTimeVisitante()) == 1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um dos times já jogou como mandante");
        }
    }

    public void timeRepetido(JogosPutRequestBody jogosPutRequestBody){
        if(jogosPutRequestBody.getTimeMandante() == jogosPutRequestBody.getTimeVisitante()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um time não pode jogar com ele mesmo");
        }
    }

    public void timeDisponivel(JogosPutRequestBody jogosPutRequestBody){
        if(jogosRepository.jogoPorData(jogosPutRequestBody.getDataJogo(), jogosPutRequestBody.getTimeMandante(),
                jogosPutRequestBody.getTimeVisitante()) == 1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um dos times já possui jogo no mesmo dia");
        }
    }

    public void dataDisponivel(JogosPutRequestBody jogosPutRequestBody){
        Date dataEscolhida = new Date();
        if(jogosPutRequestBody.getDataJogo().compareTo(dataEscolhida) < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data tem que ser superior a de hoje");
        }
    }

    public void vitoriaMandante(JogosPutRequestBody jogosPutRequestBody){
        Tabela Mandante = tabelaRepository.timePorId(jogosPutRequestBody.getTimeMandante(), jogosPutRequestBody.getCampeonato());
        Tabela Visitante = tabelaRepository.timePorId(jogosPutRequestBody.getTimeVisitante(), jogosPutRequestBody.getCampeonato());

        Mandante.setPontuacao(Mandante.getPontuacao() + 3);
        Mandante.setQntdDeGolsFeitos(Mandante.getQntdDeGolsFeitos() + jogosPutRequestBody.getGolsMandante());
        Mandante.setQntdDeGolsSofridos(Mandante.getQntdDeGolsSofridos() + jogosPutRequestBody.getGolsVisitante());
        Mandante.setQntdDeVitorias(Mandante.getQntdDeVitorias() + 1);
        Mandante.setQntdDeEmpates(Mandante.getQntdDeEmpates());
        Mandante.setQntdDeDerrotas(Mandante.getQntdDeDerrotas());

        Visitante.setPontuacao(Visitante.getPontuacao());
        Visitante.setQntdDeGolsFeitos(Visitante.getQntdDeGolsFeitos() + jogosPutRequestBody.getGolsVisitante());
        Visitante.setQntdDeGolsSofridos(Visitante.getQntdDeGolsSofridos() + jogosPutRequestBody.getGolsMandante());
        Visitante.setQntdDeVitorias(Visitante.getQntdDeVitorias());
        Visitante.setQntdDeDerrotas(Visitante.getQntdDeDerrotas() + 1);
        Visitante.setQntdDeEmpates(Visitante.getQntdDeEmpates());

        tabelaService.save(Mandante);
        tabelaService.save(Visitante);
    }

    public void vitoriaVisitante(JogosPutRequestBody jogosPutRequestBody){
        Tabela Mandante = tabelaRepository.timePorId(jogosPutRequestBody.getTimeMandante(), jogosPutRequestBody.getCampeonato());
        Tabela Visitante = tabelaRepository.timePorId(jogosPutRequestBody.getTimeVisitante(), jogosPutRequestBody.getCampeonato());

        Mandante.setPontuacao(Mandante.getPontuacao());
        Mandante.setQntdDeGolsFeitos(Mandante.getQntdDeGolsFeitos() + jogosPutRequestBody.getGolsMandante());
        Mandante.setQntdDeGolsSofridos(Mandante.getQntdDeGolsSofridos() + jogosPutRequestBody.getGolsVisitante());
        Mandante.setQntdDeVitorias(Mandante.getQntdDeVitorias());
        Mandante.setQntdDeEmpates(Mandante.getQntdDeEmpates());
        Mandante.setQntdDeDerrotas(Mandante.getQntdDeDerrotas() + 1);

        Visitante.setPontuacao(Visitante.getPontuacao() + 3);
        Visitante.setQntdDeGolsFeitos(Visitante.getQntdDeGolsFeitos() + jogosPutRequestBody.getGolsVisitante());
        Visitante.setQntdDeGolsSofridos(Visitante.getQntdDeGolsSofridos() + jogosPutRequestBody.getGolsMandante());
        Visitante.setQntdDeVitorias(Visitante.getQntdDeVitorias() + 1);
        Visitante.setQntdDeDerrotas(Visitante.getQntdDeDerrotas());
        Visitante.setQntdDeEmpates(Visitante.getQntdDeEmpates());

        tabelaService.save(Mandante);
        tabelaService.save(Visitante);
    }

    public void empate(JogosPutRequestBody jogosPutRequestBody){
        Tabela Mandante = tabelaRepository.timePorId(jogosPutRequestBody.getTimeMandante(), jogosPutRequestBody.getCampeonato());
        Tabela Visitante = tabelaRepository.timePorId(jogosPutRequestBody.getTimeVisitante(), jogosPutRequestBody.getCampeonato());

        Mandante.setPontuacao(Mandante.getPontuacao() + 1);
        Mandante.setQntdDeGolsFeitos(Mandante.getQntdDeGolsFeitos() + jogosPutRequestBody.getGolsMandante());
        Mandante.setQntdDeGolsSofridos(Mandante.getQntdDeGolsSofridos() + jogosPutRequestBody.getGolsVisitante());
        Mandante.setQntdDeVitorias(Mandante.getQntdDeVitorias());
        Mandante.setQntdDeEmpates(Mandante.getQntdDeEmpates() + 1);
        Mandante.setQntdDeDerrotas(Mandante.getQntdDeDerrotas());

        Visitante.setPontuacao(Visitante.getPontuacao() + 1);
        Visitante.setQntdDeGolsFeitos(Visitante.getQntdDeGolsFeitos() + jogosPutRequestBody.getGolsVisitante());
        Visitante.setQntdDeGolsSofridos(Visitante.getQntdDeGolsSofridos() + jogosPutRequestBody.getGolsMandante());
        Visitante.setQntdDeVitorias(Visitante.getQntdDeVitorias());
        Visitante.setQntdDeDerrotas(Visitante.getQntdDeDerrotas());
        Visitante.setQntdDeEmpates(Visitante.getQntdDeEmpates() + 1);

        tabelaService.save(Mandante);
        tabelaService.save(Visitante);
    }

    public void vencedor(JogosPutRequestBody jogosPutRequestBody){
        if(jogosPutRequestBody.getGolsMandante() > jogosPutRequestBody.getGolsVisitante()){
            vitoriaMandante(jogosPutRequestBody);
        } else if (jogosPutRequestBody.getGolsMandante() == jogosPutRequestBody.getGolsVisitante()) {
            empate(jogosPutRequestBody);
        } else {
            vitoriaVisitante(jogosPutRequestBody);
        }
    }
}

//{
//    "timeMandante": 1,
//        "timeVisitante": 3,
//        "campeonato": 13,
//        "golsMandante": 0,
//        "golsVisitante": 0,
//        "dataJogo": "2023-05-10"
//}
