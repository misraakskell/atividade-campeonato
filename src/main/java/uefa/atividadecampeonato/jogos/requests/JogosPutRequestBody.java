package uefa.atividadecampeonato.jogos.requests;

import lombok.Builder;
import lombok.Data;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;
import uefa.atividadecampeonato.times.domain.Times;

@Data
@Builder
public class JogosPutRequestBody {
    private int idJogo;
    private Times timeMandante;
    private Times timeVisitante;
    private Campeonato campeonato;
    private long golsMandante;
    private long golsVisitante;
}
