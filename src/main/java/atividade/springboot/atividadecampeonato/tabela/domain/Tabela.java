package atividade.springboot.atividadecampeonato.tabela.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import atividade.springboot.atividadecampeonato.campeonato.domain.Campeonato;
import atividade.springboot.atividadecampeonato.times.domain.Times;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Tabela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTabela;

    @ManyToOne
    @JoinColumn
    private Campeonato campeonato;

    @ManyToOne
    @JoinColumn
    private Times times;

    @Column(name = "Pontuação")
    private int pontuacao;

    @Column(name = "Vitórias")
    private int qntdDeVitorias;

    @Column(name = "Derrotas")
    private int qntdDeDerrotas;

    @Column(name = "Gols_feitos")
    private int qntdDeGolsFeitos;

    @Column(name = "Gols_sofridos")
    private int qntdDeGolsSofridos;

}
