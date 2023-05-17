package uefa.atividadecampeonato.jogos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uefa.atividadecampeonato.exception.BadRequestException;
import uefa.atividadecampeonato.jogos.domain.Jogos;
import uefa.atividadecampeonato.jogos.mapper.JogosMapper;
import uefa.atividadecampeonato.jogos.repository.JogosRepository;
import uefa.atividadecampeonato.jogos.requests.JogosPostRequestBody;
import uefa.atividadecampeonato.jogos.requests.JogosPutRequestBody;

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

    public Jogos save(JogosPostRequestBody jogosPostRequestBody) {
        return jogosRepository.save(JogosMapper.INSTANCE.toJogos(jogosPostRequestBody));
    }

    public void delete(int id) {
        jogosRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(JogosPutRequestBody jogosPutRequestBody) {
        Jogos savedJogos = findByIdOrThrowBadRequestException(jogosPutRequestBody.getIdJogo());
        Jogos jogos = JogosMapper.INSTANCE.toJogos(jogosPutRequestBody);
        jogos.setIdJogo(savedJogos.getIdJogo());
        jogosRepository.save(jogos);
    }
}
