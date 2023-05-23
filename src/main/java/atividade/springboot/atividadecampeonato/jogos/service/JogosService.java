package atividade.springboot.atividadecampeonato.jogos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import atividade.springboot.atividadecampeonato.exception.BadRequestException;
import atividade.springboot.atividadecampeonato.jogos.domain.Jogos;
import atividade.springboot.atividadecampeonato.jogos.repository.JogosRepository;
import atividade.springboot.atividadecampeonato.jogos.requests.JogosPutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogosService {

    private final JogosRepository jogosRepository;

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
}
