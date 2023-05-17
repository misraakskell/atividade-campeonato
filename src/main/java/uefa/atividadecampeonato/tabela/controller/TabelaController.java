package uefa.atividadecampeonato.tabela.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uefa.atividadecampeonato.tabela.domain.Tabela;
import uefa.atividadecampeonato.tabela.requests.TabelaPostRequestBody;
import uefa.atividadecampeonato.tabela.requests.TabelaPutRequestBody;
import uefa.atividadecampeonato.tabela.service.TabelaService;

import javax.validation.Valid;
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
    public ResponseEntity<Tabela> save(@RequestBody @Valid TabelaPostRequestBody tabelaPostRequestBody) {
        return new ResponseEntity<>(tabelaService.save(tabelaPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        tabelaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody TabelaPutRequestBody tabelaPutRequestBody) {
        tabelaService.replace(tabelaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
