package atividade.springboot.atividadecampeonato.campeonato.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import atividade.springboot.atividadecampeonato.campeonato.domain.Campeonato;
import atividade.springboot.atividadecampeonato.campeonato.repository.CampeonatoRepository;
import atividade.springboot.atividadecampeonato.campeonato.requests.CampeonatoPutRequestBody;
import atividade.springboot.atividadecampeonato.exception.BadRequestException;

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

    public Campeonato save(Campeonato campeonato) {
        return campeonatoRepository.save(campeonato);
    }

    public void delete(int id) {
        campeonatoRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(CampeonatoPutRequestBody campeonatoPutRequestBody) {
        Campeonato savedCampeonato = findByIdOrThrowBadRequestException(campeonatoPutRequestBody.getIdCamp());
        savedCampeonato.setIdCamp(campeonatoPutRequestBody.getIdCamp());
        savedCampeonato.setNome(campeonatoPutRequestBody.getNome());
        savedCampeonato.setAno(campeonatoPutRequestBody.getAno());
        savedCampeonato.setStatus(campeonatoPutRequestBody.isStatus());
        savedCampeonato.setOficial(campeonatoPutRequestBody.isOficial());
        campeonatoRepository.save(savedCampeonato);
    }
}
