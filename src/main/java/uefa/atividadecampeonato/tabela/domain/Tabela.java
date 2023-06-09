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
    @Column(name = "id_tabela")
    private int idTabela;

    @ManyToOne
    @JoinColumn(name = "id_camp")
    private Campeonato campeonato;

    @ManyToOne
    @JoinColumn(name = "id_time")
    private Times times;

    @Column(name = "pontuacao")
    private long pontuacao;

    @Column(name = "qntd_de_vitorias")
    private long qntdDeVitorias;

    @Column(name = "qntd_de_derrotas")
    private long qntdDeDerrotas;

    @Column(name = "qntd_de_empates")
    private long qntdDeEmpates;

    @Column(name = "qntd_de_gols_feitos")
    private long qntdDeGolsFeitos;

    @Column(name = "qntd_de_gols_sofridos")
    private long qntdDeGolsSofridos;

}
