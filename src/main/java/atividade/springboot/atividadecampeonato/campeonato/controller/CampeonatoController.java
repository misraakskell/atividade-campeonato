package atividade.springboot.atividadecampeonato.campeonato.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import atividade.springboot.atividadecampeonato.campeonato.domain.Campeonato;
import atividade.springboot.atividadecampeonato.campeonato.requests.CampeonatoPutRequestBody;
import atividade.springboot.atividadecampeonato.campeonato.service.CampeonatoService;

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

}
