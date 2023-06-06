package uefa.atividadecampeonato.times.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uefa.atividadecampeonato.times.domain.Times;

public interface TimesRepository extends JpaRepository<Times, Integer> {

    boolean findByNome(String nome);
}
