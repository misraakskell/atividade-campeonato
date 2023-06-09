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
    @Column(name = "id_camp")
    private int idCamp;

    @NotNull
    @Column(name = "ano")
    private int ano;

    @NotEmpty(message = "O campeonato precisa de um nome")
    @Column(name = "nome")
    private String nome;

    @Column(name = "iniciado")
    private boolean iniciado = false;

    @Column(name = "finalizado")
    private boolean finalizado = false;
}
