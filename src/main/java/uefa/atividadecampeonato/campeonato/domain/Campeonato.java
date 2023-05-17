package uefa.atividadecampeonato.campeonato.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uefa.atividadecampeonato.jogos.domain.Jogos;
import uefa.atividadecampeonato.tabela.domain.Tabela;
import uefa.atividadecampeonato.times.domain.Times;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Campeonato {

    private int ano;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCamp;

    private String nome;
    private boolean status;
    private boolean oficial; //se não amistoso

    @OneToMany
    @JoinColumn(name = "ID_TIMES")
    private List<Times> times;

    @OneToMany
    @JoinColumn(name = "ID_JOGOS", nullable = false)
    private List<Jogos> jogos;

    @OneToOne
    @JoinColumn(name = "ID_TABELA")
    private Tabela tabela;
}
