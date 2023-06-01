package uefa.atividadecampeonato.campeonato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM campeonato c " +
                    "WHERE c.id_camp = :idCamp " +
                    "AND c.iniciado = TRUE")
    boolean campIniciado(@Param("idCamp") int idCamp);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM campeonato c " +
                    "WHERE c.id_camp = :idCamp " +
                    "AND c.finalizado = TRUE")
    boolean campFinalizado(@Param("idCamp") int idCamp);

    boolean verificaNomeAno(String nome, long ano);
}
