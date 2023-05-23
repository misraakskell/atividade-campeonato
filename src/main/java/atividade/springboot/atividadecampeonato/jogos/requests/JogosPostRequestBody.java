package atividade.springboot.atividadecampeonato.jogos.requests;

import lombok.Data;
import atividade.springboot.atividadecampeonato.campeonato.domain.Campeonato;
import atividade.springboot.atividadecampeonato.times.domain.Times;

@Data
public class JogosPostRequestBody {
    private Times timeMandante;
    private Times timeVisitante;
    private Campeonato campeonato;
    private int golsMandante;
    private int golsVisitante;
}
