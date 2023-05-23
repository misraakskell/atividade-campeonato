package atividade.springboot.atividadecampeonato.jogos.requests;

import lombok.Builder;
import lombok.Data;
import atividade.springboot.atividadecampeonato.times.domain.Times;
import atividade.springboot.atividadecampeonato.campeonato.domain.Campeonato;

@Data
@Builder
public class JogosPutRequestBody {
    private int idJogo;
    private Times timeMandante;
    private Times timeVisitante;
    private Campeonato campeonato;
    private int golsMandante;
    private int golsVisitante;
}
