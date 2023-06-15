package uefa.atividadecampeonato.campeonato.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;
import uefa.atividadecampeonato.campeonato.requests.CampeonatoPutRequestBody;
import uefa.atividadecampeonato.campeonato.service.CampeonatoService;
import uefa.atividadecampeonato.jogos.service.JogosService;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("campeonato")
@Log4j2
@RequiredArgsConstructor
public class CampeonatoController {

    private final CampeonatoService campeonatoService;
    private final JogosService jogosService;

    @GetMapping
    public ResponseEntity<List<Campeonato>> list() {
        return ResponseEntity.ok(campeonatoService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Campeonato> findById(@PathVariable int id) {
        return ResponseEntity.ok(campeonatoService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Campeonato> save(@RequestBody @Valid Campeonato campeonato) {
        return new ResponseEntity<>(campeonatoService.save(campeonato), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        campeonatoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> replace(@RequestBody CampeonatoPutRequestBody campeonatoPutRequestBody) {
        campeonatoService.replace(campeonatoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}/inicia")
    public ResponseEntity<Object> iniciaCamp(@PathVariable("id") int id, @RequestBody CampeonatoPutRequestBody campeonatoPutRequestBody) {
        campeonatoService.inicia(campeonatoPutRequestBody);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}/finaliza")
    public ResponseEntity<Object> finalizaCamp(@PathVariable("id") int id) {
        campeonatoService.finaliza(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
