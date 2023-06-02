package uefa.atividadecampeonato.jogos.requests;

import lombok.Builder;
import lombok.Data;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;
import uefa.atividadecampeonato.times.domain.Times;

import java.util.Date;

@Data
@Builder
public class JogosPutRequestBody {
    private int idJogo;
    private int timeMandante;
    private int timeVisitante;
    private int campeonato;
    private int golsMandante;
    private int golsVisitante;
    private Date dataJogo;
}
