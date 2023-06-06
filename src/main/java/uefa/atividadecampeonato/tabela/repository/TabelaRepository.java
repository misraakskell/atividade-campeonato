package uefa.atividadecampeonato.tabela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uefa.atividadecampeonato.tabela.domain.Tabela;

public interface TabelaRepository extends JpaRepository<Tabela, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM tabela t " +
                    "JOIN times t2 " +
                    "ON t2.id_time = t.id_times " +
                    "WHERE t2.id_time = :times " +
                    "AND t.id_campeonato = :campeonato")
    Tabela timePorId(@Param("times")String times, @Param("campeonato")String campeonato);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM tabela t " +
                        "WHERE t.id_camp = :campeonato " +
                        "AND t.id_time = :times")
    boolean campPorTabela(@Param("campeonato")int campeonato, @Param("times")int times);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) > 0 " +
                    "FROM tabela t " +
                        "WHERE id_camp = :campeonato")
    int qntdDeTimes(@Param("campeonato")int campeonato);
}