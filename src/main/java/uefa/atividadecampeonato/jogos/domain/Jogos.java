package uefa.atividadecampeonato.jogos.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;
import uefa.atividadecampeonato.times.domain.Times;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Jogos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJogo;

    @ManyToOne
    @JoinColumn(name = "ID_MANDANTE")
    private Times timeMandante;

    @ManyToOne
    @JoinColumn(name = "ID_VISITANTE")
    private Times timeVisitante;

    @ManyToOne
    @JoinColumn(name = "ID_CAMPEONATO")
    private Campeonato campeonato;

    private long golsMandante;
    private long golsVisitante;

}
