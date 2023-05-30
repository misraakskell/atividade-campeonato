package uefa.atividadecampeonato.jogos.requests;

import lombok.Data;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;
import uefa.atividadecampeonato.times.domain.Times;

@Data
public class JogosPostRequestBody {
    private Times timeMandante;
    private Times timeVisitante;
    private Campeonato campeonato;
    private int golsMandante;
    private int golsVisitante;
}
