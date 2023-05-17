package uefa.atividadecampeonato.campeonato.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;
import uefa.atividadecampeonato.campeonato.requests.CampeonatoPostRequestBody;
import uefa.atividadecampeonato.campeonato.requests.CampeonatoPutRequestBody;
import uefa.atividadecampeonato.campeonato.service.CampeonatoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("campeonato")
@Log4j2
@RequiredArgsConstructor
public class CampeonatoController {

    private final CampeonatoService campeonatoService;

    @GetMapping
    public ResponseEntity<List<Campeonato>> list() {
        return ResponseEntity.ok(campeonatoService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Campeonato> findById(@PathVariable int id) {
        return ResponseEntity.ok(campeonatoService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Campeonato> save(@RequestBody @Valid CampeonatoPostRequestBody campeonatoPostRequestBody) {
        return new ResponseEntity<>(campeonatoService.save(campeonatoPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        campeonatoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = " /{id}")
    public ResponseEntity<Void> replace(@RequestBody CampeonatoPutRequestBody campeonatoPutRequestBody) {
        campeonatoService.replace(campeonatoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    {
//        "idCamp": 2,
//            "ano": 2018,
//            "nome": "Campeonato Brasileiro",
//            "status": true,
//            "oficial": true
//    }

}
