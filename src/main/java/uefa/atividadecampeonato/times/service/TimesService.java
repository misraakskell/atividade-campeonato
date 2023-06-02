package uefa.atividadecampeonato.times.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uefa.atividadecampeonato.exception.BadRequestException;
import uefa.atividadecampeonato.times.domain.Times;
import uefa.atividadecampeonato.times.repository.TimesRepository;
import uefa.atividadecampeonato.times.requests.TimesPutRequestBody;

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

    public void verificaTime(Times times){
        if(timesRepository.existeTimeNome(times.getNome())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O time já existe");
        }
    }
}
