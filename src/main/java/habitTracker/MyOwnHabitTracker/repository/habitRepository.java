package habitTracker.MyOwnHabitTracker.repository;

import habitTracker.MyOwnHabitTracker.model.Habit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface HabitRepository extends JpaRepository<Habit, Integer> {


    @Modifying
    @Transactional
    @Query("UPDATE Habit h SET h.isCompleted = false")
    void resetAllCheckmarks();
}
