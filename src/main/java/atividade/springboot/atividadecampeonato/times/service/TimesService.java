package atividade.springboot.atividadecampeonato.times.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import atividade.springboot.atividadecampeonato.exception.BadRequestException;
import atividade.springboot.atividadecampeonato.times.domain.Times;
import atividade.springboot.atividadecampeonato.times.repository.TimesRepository;
import atividade.springboot.atividadecampeonato.times.requests.TimesPutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimesService {

    private final TimesRepository timesRepository;

    public List<Times> listAll(){
        return timesRepository.findAll();
    }

    public Times findByIdOrThrowBadRequestException(int id) {
        return timesRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Time not Found"));
    }

    public Times save(Times times) {
        Times novoTime = new Times();
        novoTime.setNome(times.getNome());
        return timesRepository.save(novoTime);
    }

    public void delete(int id) {
        timesRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(TimesPutRequestBody timesPutRequestBody) {
        Times savedTimes = findByIdOrThrowBadRequestException(timesPutRequestBody.getIdTime());
        Times times = new Times();
        times.setIdTime(savedTimes.getIdTime());
        times.setNome(timesPutRequestBody.getNome());
        timesRepository.save(times);
    }
}
