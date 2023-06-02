package uefa.atividadecampeonato.jogos.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;
import uefa.atividadecampeonato.times.domain.Times;

import java.util.Date;

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
    private int campeonato;
    private long golsMandante;
    private long golsVisitante;
    private Date dataJogo;
}
