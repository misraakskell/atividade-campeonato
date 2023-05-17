package uefa.atividadecampeonato.tabela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uefa.atividadecampeonato.tabela.domain.Tabela;

public interface TabelaRepository extends JpaRepository<Tabela, Integer> {
}
