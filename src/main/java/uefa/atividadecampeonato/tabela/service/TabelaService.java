package uefa.atividadecampeonato.tabela.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uefa.atividadecampeonato.exception.BadRequestException;
import uefa.atividadecampeonato.tabela.domain.Tabela;
import uefa.atividadecampeonato.tabela.mapper.TabelaMapper;
import uefa.atividadecampeonato.tabela.repository.TabelaRepository;
import uefa.atividadecampeonato.tabela.requests.TabelaPostRequestBody;
import uefa.atividadecampeonato.tabela.requests.TabelaPutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TabelaService {

    private final TabelaRepository tabelaRepository;

    public List<Tabela> listAll() {
        return tabelaRepository.findAll();
    }

    public Tabela findByIdOrThrowBadRequestException(int id) {
        return tabelaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Tabela not found"));
    }

    public Tabela save(TabelaPostRequestBody tabelaPostRequestBody) {
        return tabelaRepository.save(TabelaMapper.INSTANCE.toTabela(tabelaPostRequestBody));
    }

    public void delete(int id) {
        tabelaRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(TabelaPutRequestBody tabelaPutRequestBody) {
        Tabela savedTabela = findByIdOrThrowBadRequestException(tabelaPutRequestBody.getIdTabela());
        Tabela tabela = TabelaMapper.INSTANCE.toTabela(tabelaPutRequestBody);
        tabela.setIdTabela(savedTabela.getIdTabela());
        tabelaRepository.save(tabela);
    }
}
