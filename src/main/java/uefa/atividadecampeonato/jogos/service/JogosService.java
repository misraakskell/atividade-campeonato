package uefa.atividadecampeonato.jogos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;
import uefa.atividadecampeonato.campeonato.repository.CampeonatoRepository;
import uefa.atividadecampeonato.exception.BadRequestException;
import uefa.atividadecampeonato.jogos.domain.Jogos;
import uefa.atividadecampeonato.jogos.repository.JogosRepository;
import uefa.atividadecampeonato.jogos.requests.JogosPutRequestBody;
import uefa.atividadecampeonato.tabela.domain.Tabela;
import uefa.atividadecampeonato.tabela.repository.TabelaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogosService {

    private final JogosRepository jogosRepository;
    private final TabelaRepository tabelaRepository;
    private final CampeonatoRepository campeonatoRepository;

    public List<Jogos> listAll() {
        return jogosRepository.findAll();
    }

    public Jogos findByIdOrThrowBadRequestException(int id) {
        return jogosRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Jogo not found"));
    }

    public Jogos save(Jogos jogos) {
        return jogosRepository.save(jogos);
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

    public void verificaTimeCamp(JogosPutRequestBody jogosPutRequestBody){
        if(!(tabelaRepository.campPorTabela(jogosPutRequestBody.getCampeonato(), jogosPutRequestBody.getTimeMandante()))
            || !tabelaRepository.campPorTabela(jogosPutRequestBody.getCampeonato(), jogosPutRequestBody.getTimeVisitante())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um dos times não está no campeonato");
        }
    }

    public void jogoExiste(JogosPutRequestBody jogosPutRequestBody){
        if(jogosRepository.jogoExiste(jogosPutRequestBody.getCampeonato(), jogosPutRequestBody.getTimeMandante(),
                jogosPutRequestBody.getTimeVisitante())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um dos times já jogou como mandante");
        }
    }

    public void timeRepetido(JogosPutRequestBody jogosPutRequestBody){
        if(jogosPutRequestBody.getTimeMandante() == jogosPutRequestBody.getTimeVisitante()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um time não pode jogar com ele mesmo");
        }
    }

}
