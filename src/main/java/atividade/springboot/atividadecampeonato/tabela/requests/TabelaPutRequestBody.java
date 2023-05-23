package atividade.springboot.atividadecampeonato.tabela.requests;

import lombok.Builder;
import lombok.Data;
import atividade.springboot.atividadecampeonato.times.domain.Times;
import atividade.springboot.atividadecampeonato.campeonato.domain.Campeonato;

@Data
@Builder
public class TabelaPutRequestBody {
    private int idTabela;
    private Campeonato campeonato;
    private Times times;
    private int pontuacao;
    private int qntdDeVitorias;
    private int qntdDeDerrotas;
    private int qntdDeGolsFeitos;
    private int qntdDeGolsSofridos;
}
