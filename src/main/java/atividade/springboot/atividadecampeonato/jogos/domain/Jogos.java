package atividade.springboot.atividadecampeonato.jogos.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import atividade.springboot.atividadecampeonato.campeonato.domain.Campeonato;
import atividade.springboot.atividadecampeonato.times.domain.Times;
import lombok.extern.jackson.Jacksonized;

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
    @JoinColumn(name = "timeMandante")
    private Times timeMandante;

    @ManyToOne
    @JoinColumn(name = "timeVisitante")
    private Times timeVisitante;

    @ManyToOne
    @JoinColumn(name = "campeonato")
    private Campeonato campeonato;

    @Column(name = "golsMandante")
    private int golsMandante;

    @Column(name = "golsVisitante")
    private int golsVisitante;


}

//{
//        "ano": 2023,
//        "timeMandante": "Manchester City",
//        "timeVisitante": "Chelsea FC",
//        "campeonato": "Premier League",
//        "golsMandante": 1,
//        "golsVisitante": 0
//}