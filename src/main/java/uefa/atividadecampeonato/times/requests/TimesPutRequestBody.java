package uefa.atividadecampeonato.times.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimesPutRequestBody {
    private int idTime;
    private String nome;
}
