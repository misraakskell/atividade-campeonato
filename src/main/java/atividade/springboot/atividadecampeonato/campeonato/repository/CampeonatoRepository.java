package atividade.springboot.atividadecampeonato.campeonato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import atividade.springboot.atividadecampeonato.campeonato.domain.Campeonato;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Integer> {
}
