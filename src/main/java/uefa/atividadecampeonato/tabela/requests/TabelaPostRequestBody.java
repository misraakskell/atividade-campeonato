package uefa.atividadecampeonato.tabela.requests;

import lombok.Data;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;
import uefa.atividadecampeonato.times.domain.Times;

@Data
public class TabelaPostRequestBody {
    private Campeonato campeonato;
    private Times times;
    private int pontuacao;
    private int qntdDeVitorias;
    private int qntdDeDerrotas;
    private int qntdDeGolsFeitos;
    private int qntdDeGolsSofridos;
}
