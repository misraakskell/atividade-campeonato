package uefa.atividadecampeonato.campeonato.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CampeonatoPutRequestBody {

    private int idCamp;
    private List<Integer> idTimes;
}
