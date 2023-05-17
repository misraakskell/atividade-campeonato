package uefa.atividadecampeonato.jogos.requests;

import lombok.Data;

@Data
public class JogosPostRequestBody {
    private int timeMandante;
    private int timeVisitante;
    private int golsMandante;
    private int golsVisitante;
    private int campeonato;
}
