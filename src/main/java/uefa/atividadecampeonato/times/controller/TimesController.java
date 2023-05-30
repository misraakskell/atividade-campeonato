package uefa.atividadecampeonato.times.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uefa.atividadecampeonato.times.domain.Times;
import uefa.atividadecampeonato.times.requests.TimesPostRequestBody;
import uefa.atividadecampeonato.times.requests.TimesPutRequestBody;
import uefa.atividadecampeonato.times.service.TimesService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("times")
@Log4j2
@RequiredArgsConstructor
public class TimesController {

    private final TimesService timesService;

    @GetMapping
    public ResponseEntity<List<Times>> list() {
        return ResponseEntity.ok(timesService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Times> findById(@PathVariable int id) {
        return ResponseEntity.ok(timesService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Times> save(@RequestBody @Valid Times times) {
        return new ResponseEntity<>(timesService.save(times), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        timesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> replace(@RequestBody TimesPutRequestBody timesPutRequestBody) {
        timesService.replace(timesPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
