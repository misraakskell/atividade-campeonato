package atividade.springboot.atividadecampeonato.tabela.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import atividade.springboot.atividadecampeonato.exception.BadRequestException;
import atividade.springboot.atividadecampeonato.tabela.domain.Tabela;
import atividade.springboot.atividadecampeonato.tabela.repository.TabelaRepository;
import atividade.springboot.atividadecampeonato.tabela.requests.TabelaPutRequestBody;

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

    public Tabela save(Tabela tabela) {
        return tabelaRepository.save(tabela);
    }

    public void delete(int id) {
        tabelaRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(TabelaPutRequestBody tabelaPutRequestBody) {
        Tabela savedTabela = findByIdOrThrowBadRequestException(tabelaPutRequestBody.getIdTabela());
        savedTabela.setIdTabela(tabelaPutRequestBody.getIdTabela());
        savedTabela.setCampeonato(tabelaPutRequestBody.getCampeonato());
        savedTabela.setTimes(tabelaPutRequestBody.getTimes());
        savedTabela.setPontuacao(tabelaPutRequestBody.getPontuacao());
        savedTabela.setQntdDeVitorias(tabelaPutRequestBody.getQntdDeVitorias());
        savedTabela.setQntdDeDerrotas(tabelaPutRequestBody.getQntdDeDerrotas());
        savedTabela.setQntdDeGolsFeitos(tabelaPutRequestBody.getQntdDeGolsFeitos());
        savedTabela.setQntdDeGolsSofridos(tabelaPutRequestBody.getQntdDeGolsSofridos());
        tabelaRepository.save(savedTabela);
    }
}
