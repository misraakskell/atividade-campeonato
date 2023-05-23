package atividade.springboot.atividadecampeonato.tabela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import atividade.springboot.atividadecampeonato.tabela.domain.Tabela;

public interface TabelaRepository extends JpaRepository<Tabela, Integer> {
}
