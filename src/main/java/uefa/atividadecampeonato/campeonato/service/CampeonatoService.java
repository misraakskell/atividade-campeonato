package uefa.atividadecampeonato.campeonato.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;
import uefa.atividadecampeonato.campeonato.mapper.CampeonatoMapper;
import uefa.atividadecampeonato.campeonato.repository.CampeonatoRepository;
import uefa.atividadecampeonato.campeonato.requests.CampeonatoPostRequestBody;
import uefa.atividadecampeonato.campeonato.requests.CampeonatoPutRequestBody;
import uefa.atividadecampeonato.exception.BadRequestException;

import java.util.List;

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

    public Campeonato save(CampeonatoPostRequestBody campeonatoPostRequestBody) {
        return campeonatoRepository.save(CampeonatoMapper.INSTANCE.toCampeonato(campeonatoPostRequestBody));
    }

    public void delete(int id) {
        campeonatoRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(CampeonatoPutRequestBody campeonatoPutRequestBody) {
        Campeonato savedCampeonato = findByIdOrThrowBadRequestException(campeonatoPutRequestBody.getIdCamp());
        Campeonato campeonato = CampeonatoMapper.INSTANCE.toCampeonato(campeonatoPutRequestBody);
        campeonato.setIdCamp(savedCampeonato.getIdCamp());
        campeonatoRepository.save(campeonato);
    }
}
