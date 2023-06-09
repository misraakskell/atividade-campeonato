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
                        "WHERE j.campeonato = :campeonato " +
                        "AND j.time_mandante = :timeMandante " +
                        "AND j.time_visitante = :timeVisitante")
    int jogoExiste(@Param("campeonato")int campeonato, @Param("timeMandante")int timeMandante,
                       @Param("timeVisitante")int timeVisitante);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM jogos j " +
                        "WHERE j.data_jogo = :dataJogo " +
                        "AND j.time_mandante = :timeMandante " +
                        "OR j.data_jogo = :dataJogo " +
                        "AND j.time_visitante = :timeVisitante " +
                        "OR j.data_jogo = :dataJogo " +
                        "AND j.time_visitante = :timeVisitante " +
                        "OR j.data_jogo = :dataJogo " +
                        "AND j.time_mandante = :timeMandante")
    int jogoPorData(@Param("dataJogo")Date dataJogo, @Param("timeMandante")int timeMandante,
                        @Param("timeVisitante")int timeVisitante); //testar
}




