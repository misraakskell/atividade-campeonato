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
    @JoinColumn(name = "ID_CAMPEONATO")
    private Campeonato campeonato;

    @ManyToOne
    @JoinColumn(name = "ID_TIME")
    private Times times;

    @Column(name = "Pontuação")
    private int pontuacao;

    @Column(name = "Vitórias")
    private int qntdDeVitorias;

    @Column(name = "Derrotas")
    private int qntdDeDerrotas;

    @Column(name = "Gols feitos")
    private int qntdDeGolsFeitos;

    @Column(name = "Gols sofridos")
    private int qntdDeGolsSofridos;

}
