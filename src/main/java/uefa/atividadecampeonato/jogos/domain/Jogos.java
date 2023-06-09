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
    @Column(name = "id_jogo")
    private int idJogo;

    @Column(name = "time_mandante")
    private int timeMandante;

    @Column(name = "time_visitante")
    private int timeVisitante;

    @Column(name = "campeonato")
    private int campeonato;

    @Column(name = "gols_mandante")
    private long golsMandante;

    @Column(name = "gols_visitante")
    private long golsVisitante;

    @Column(name = "data_jogo")
    private Date dataJogo;
}
