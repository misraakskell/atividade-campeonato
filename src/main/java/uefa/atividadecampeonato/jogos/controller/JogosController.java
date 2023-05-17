package uefa.atividadecampeonato.jogos.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uefa.atividadecampeonato.jogos.domain.Jogos;
import uefa.atividadecampeonato.jogos.requests.JogosPostRequestBody;
import uefa.atividadecampeonato.jogos.requests.JogosPutRequestBody;
import uefa.atividadecampeonato.jogos.service.JogosService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("campeonato")
@Log4j2
@RequiredArgsConstructor
public class JogosController {

    private final JogosService jogosService;

    @GetMapping
    public ResponseEntity<List<Jogos>> list() {
        return ResponseEntity.ok(jogosService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Jogos> findById(@PathVariable int id) {
        return ResponseEntity.ok(jogosService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Jogos> save(@RequestBody @Valid JogosPostRequestBody jogosPostRequestBody) {
        return new ResponseEntity<>(jogosService.save(jogosPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        jogosService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody JogosPutRequestBody jogosPutRequestBody) {
        jogosService.replace(jogosPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
