package habitTracker.MyOwnHabitTracker.repository;

import habitTracker.MyOwnHabitTracker.model.HabitCompletion;
import habitTracker.MyOwnHabitTracker.model.HabitForChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitCompletionRepository extends JpaRepository<HabitCompletion, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM HabitCompletion h WHERE h.habitId = :habitId AND h.date = :date")
    void deleteByHabitIdAndDate(@Param("habitId") Integer habitId, @Param("date") String date);
    @Modifying
    @Transactional
    @Query(value = """
        SELECT 
            habit_id, 
            SUBSTRING(date, 7, 4) AS year, 
            SUBSTRING(date, 4, 2) AS month, 
            COUNT(*) AS timesCompleted
        FROM 
            habit_completion
        WHERE habit_id = :habitId
        GROUP BY 
            habit_id, year, month
        ORDER BY 
            habit_id, year, month
    """, nativeQuery = true)
    List<Object[]> findAllByHabitId(Integer habitId);

}
