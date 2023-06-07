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
    int findByIniciado(@Param("idCamp") int idCamp);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM campeonato c " +
                    "WHERE c.id_camp = :idCamp " +
                    "AND c.finalizado = TRUE")
    int findByFinalizado(@Param("idCamp") int idCamp);

    boolean existsByNomeAndAno(String nome, int ano);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) " +
                    "FROM jogos j " +
                    "WHERE campeonato = :idCamp")
    int numeroJogos(@Param("idCamp")int idCamp);

}
