package uefa.atividadecampeonato.jogos.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;
import uefa.atividadecampeonato.tabela.domain.Tabela;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Jogos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJogo;

    private int timeMandante;
    private int timeVisitante;
    private int golsMandante;
    private int golsVisitante;
    private int campeonato;

    @ManyToOne
    @JoinColumn(name = "ID-TABELA", nullable = false)
    private Tabela tabela;

}
