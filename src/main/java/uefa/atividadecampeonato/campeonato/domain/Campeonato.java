package uefa.atividadecampeonato.campeonato.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Campeonato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCamp;

    @Column(name = "Ano")
    @NotNull
    private int ano;

    @NotEmpty(message = "O campeonato precisa de um nome")
    @Column(name = "Nome")
    private String nome;

    @Column(name = "Status")
    private boolean status; //se não finalizado

    @Column(name = "Oficial")
    private boolean oficial; //se não amistoso

//    @OneToMany
//    @JoinColumn(name = "ID_TIMES")
//    private List<Times> times;
//
//    @OneToMany
//    @JoinColumn(name = "ID_JOGOS", nullable = false)
//    private Jogos jogos;

//    @OneToOne
//    @JoinColumn(name = "ID_TABELA")
//    private Tabela tabela;
}
