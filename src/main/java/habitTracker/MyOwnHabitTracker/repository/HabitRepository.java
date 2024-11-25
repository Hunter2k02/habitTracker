package habitTracker.MyOwnHabitTracker.repository;

import habitTracker.MyOwnHabitTracker.model.Habit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface HabitRepository extends JpaRepository<Habit, Integer> {


    @Modifying
    @Transactional
    @Query("UPDATE Habit h SET h.isCompleted = false")
    void resetAllCheckmarks();

    @Query("SELECT h.id FROM Habit h WHERE h.name = :name")
    Integer findIdByName(@Param("name") String name);
}
