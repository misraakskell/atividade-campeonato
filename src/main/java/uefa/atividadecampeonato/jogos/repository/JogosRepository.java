package uefa.atividadecampeonato.jogos.repository;

import org.omg.CORBA.FREE_MEM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uefa.atividadecampeonato.jogos.domain.Jogos;

import java.util.Date;

public interface JogosRepository extends JpaRepository<Jogos, Integer> {


    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM jogos j " +
                        "WHERE j.id_campeonato = :campeonato " +
                        "AND j.id_mandante = :timeMandante " +
                        "AND j.id_visitante = :timeVisitante")
    boolean jogoExiste(@Param("campeonato")int campeonato, @Param("timeMandante")int timeMandante,
                       @Param("timeVisitante")int timeVisitante);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM jogos j " +
                        "WHERE j.data_jogo = :dataJogo " +
                        "AND j.id_mandante = :timeMandante " +
                        "OR j.data_jogo = :dataJogo " +
                        "AND j.id_visitante = :timeVisitante " +
                        "OR j.data_jogo = :dataJogo " +
                        "AND j.id_visitante = :timeVisitante " +
                        "OR j.data_jogo = :dataJogo " +
                        "AND j.id_mandante = :timeMandante")
    boolean jogoPorData(@Param("dataJogo")Date dataJogo, @Param("timeMandante")int timeMandante,
                        @Param("timeVisitante")int timeVisitante); //testar
}




