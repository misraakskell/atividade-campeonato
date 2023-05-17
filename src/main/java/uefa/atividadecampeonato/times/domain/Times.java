package uefa.atividadecampeonato.times.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;
import uefa.atividadecampeonato.tabela.domain.Tabela;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Times {
    @NotEmpty(message = "O time precisa de um nome")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTime;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "ID_TIMES", nullable = false)
    private Tabela tabela;


}
