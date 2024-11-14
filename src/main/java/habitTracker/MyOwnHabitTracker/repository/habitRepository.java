package habitTracker.MyOwnHabitTracker.repository;

import habitTracker.MyOwnHabitTracker.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface habitRepository extends JpaRepository<Habit, Integer> {

    Optional<Habit> findByName(String name);
}
