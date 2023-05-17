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

    @Column(name = "Placar casa")
    private Integer golsMandante;

    @Column(name = "Placar visita")
    private Integer golsVisitante;

    //    {
//        "timeMandante": "Fortaleza FC",
//            "timeVisitante": "Paysandu SC",
//            "Campeonato": "Campeonato Brasileiro",
//            "golsMandante": 3,
//            "golsVisitante": 2
//    }

}
