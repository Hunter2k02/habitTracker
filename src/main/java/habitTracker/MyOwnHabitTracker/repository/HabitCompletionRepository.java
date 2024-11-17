package habitTracker.MyOwnHabitTracker.repository;

import habitTracker.MyOwnHabitTracker.model.HabitCompletion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitCompletionRepository extends JpaRepository<HabitCompletion, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM HabitCompletion h WHERE h.habitId = :habitId AND h.date = :date")
    void deleteByHabitIdAndDate(@Param("habitId") Integer habitId, @Param("date") String date);
}
