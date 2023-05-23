package atividade.springboot.atividadecampeonato.campeonato.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CampeonatoPutRequestBody {

    private int ano;
    private int idCamp;
    private String nome;
    private boolean status;
    private boolean oficial;
}
