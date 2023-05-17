package uefa.atividadecampeonato.times.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uefa.atividadecampeonato.exception.BadRequestException;
import uefa.atividadecampeonato.times.domain.Times;
import uefa.atividadecampeonato.times.mapper.TimesMapper;
import uefa.atividadecampeonato.times.repository.TimesRepository;
import uefa.atividadecampeonato.times.requests.TimesPostRequestBody;
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

    public Times save(TimesPostRequestBody timesPostRequestBody) {
        return timesRepository.save(TimesMapper.INSTANCE.toTimes(timesPostRequestBody));
    }

    public void delete(int id) {
        timesRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(TimesPutRequestBody timesPutRequestBody) {
        Times savedTimes = findByIdOrThrowBadRequestException(timesPutRequestBody.getIdTime());
        Times times = TimesMapper.INSTANCE.toTimes(timesPutRequestBody);
        times.setIdTime(savedTimes.getIdTime());
        timesRepository.save(times);
    }
}
