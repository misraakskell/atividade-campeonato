package uefa.atividadecampeonato.campeonato.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Campeonato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCamp;

    @NotNull
    private long ano;

    @NotEmpty(message = "O campeonato precisa de um nome")
    private String nome;

    private boolean iniciado; //se não finalizado

    private boolean finalizado; //se não amistoso

}
