package uefa.atividadecampeonato.campeonato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Integer> {
}
