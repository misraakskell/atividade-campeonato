package uefa.atividadecampeonato.jogos.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JogosPutRequestBody {
    private int idJogo;
    private int timeMandante;
    private int timeVisitante;
    private int golsMandante;
    private int golsVisitante;
    private int campeonato;
}
