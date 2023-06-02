package uefa.atividadecampeonato.tabela.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;
import uefa.atividadecampeonato.times.domain.Times;

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

    @ManyToOne
    @JoinColumn
    private Campeonato campeonato;

    @ManyToOne
    @JoinColumn
    private Times times;
    private long pontuacao;
    private long qntdDeVitorias;
    private long qntdDeDerrotas;
    private long qntdDeEmpates;
    private long qntdDeGolsFeitos;
    private long qntdDeGolsSofridos;

}
