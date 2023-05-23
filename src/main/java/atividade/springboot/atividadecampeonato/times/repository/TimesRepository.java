package atividade.springboot.atividadecampeonato.times.repository;


import atividade.springboot.atividadecampeonato.times.domain.Times;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TimesRepository extends JpaRepository<Times, Integer> {
}
