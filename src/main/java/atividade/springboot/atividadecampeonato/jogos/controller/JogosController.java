package atividade.springboot.atividadecampeonato.jogos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import atividade.springboot.atividadecampeonato.jogos.domain.Jogos;
import atividade.springboot.atividadecampeonato.jogos.requests.JogosPutRequestBody;
import atividade.springboot.atividadecampeonato.jogos.service.JogosService;

import java.util.List;

@RestController
@RequestMapping("jogos")
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
    public ResponseEntity<Jogos> save(@RequestBody @Valid Jogos jogos) {
        return new ResponseEntity<>(jogosService.save(jogos), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        jogosService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> replace(@RequestBody JogosPutRequestBody jogosPutRequestBody) {
        jogosService.replace(jogosPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
