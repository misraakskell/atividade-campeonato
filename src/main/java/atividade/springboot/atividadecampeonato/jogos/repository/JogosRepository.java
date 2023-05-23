package atividade.springboot.atividadecampeonato.jogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import atividade.springboot.atividadecampeonato.jogos.domain.Jogos;

public interface JogosRepository extends JpaRepository<Jogos, Integer> {
}
