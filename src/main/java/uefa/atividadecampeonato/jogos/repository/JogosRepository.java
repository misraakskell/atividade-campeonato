package uefa.atividadecampeonato.jogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uefa.atividadecampeonato.jogos.domain.Jogos;

public interface JogosRepository extends JpaRepository<Jogos, Integer> {
}
