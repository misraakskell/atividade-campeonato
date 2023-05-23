package atividade.springboot.atividadecampeonato.tabela.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import atividade.springboot.atividadecampeonato.tabela.domain.Tabela;
import atividade.springboot.atividadecampeonato.tabela.requests.TabelaPutRequestBody;
import atividade.springboot.atividadecampeonato.tabela.service.TabelaService;

import java.util.List;

@RestController
@RequestMapping("tabela")
@Log4j2
@RequiredArgsConstructor
public class TabelaController {

    private final TabelaService tabelaService;

    @GetMapping
    public ResponseEntity<List<Tabela>> list() {
        return ResponseEntity.ok(tabelaService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Tabela> findById(@PathVariable int id) {
        return ResponseEntity.ok(tabelaService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Tabela> save(@RequestBody @Valid Tabela tabela) {
        return new ResponseEntity<>(tabelaService.save(tabela), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        tabelaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> replace(@RequestBody TabelaPutRequestBody tabelaPutRequestBody) {
        tabelaService.replace(tabelaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
