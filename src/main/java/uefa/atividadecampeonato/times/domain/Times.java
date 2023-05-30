package uefa.atividadecampeonato.times.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Times {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTime;

    @NotEmpty(message = "O time precisa de um nome")
    private String nome;

}
