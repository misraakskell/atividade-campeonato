package uefa.atividadecampeonato.tabela.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Tabela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTabela;

    //private int time;
    private int pontuacao;
    private int qntdDeVitorias;
    private int qntdDeDerrotas;
    private int qntdDeGolsFeitos;
    private int qntdDeGolsSofridos;

}
