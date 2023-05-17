package uefa.atividadecampeonato.campeonato.requests;

import lombok.Data;

@Data
public class CampeonatoPostRequestBody {

    private int ano;
    private String nome;
    private boolean status;
    private boolean oficial;
}
