package habitTracker.MyOwnHabitTracker.service;

import habitTracker.MyOwnHabitTracker.model.Habit;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import habitTracker.MyOwnHabitTracker.repository.habitRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class habitService {
    habitRepository repository;

    public habitService(habitRepository repository) {
        this.repository = repository;
    }

    public List<Habit> getHabits() {
        return repository.findAll();
    }
    public void addHabit(Habit habit) {
        repository.save(habit);
    }
    public void deleteHabit(Habit habit) {
        repository.delete(habit);
    }
    public ResponseEntity<Void> incrementTimesCompleted(Integer id) {
        Optional<Habit> habitOptional = repository.findById(id);
        if (habitOptional.isPresent()) {
            Habit habit = habitOptional.get();
            habit.setTimesCompleted(habit.getTimesCompleted() + 1);
            repository.save(habit);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> isHabitCompleted(Integer id, Map<String, Boolean> payload) {

        Boolean isCompletedToday = payload.get("isCompletedToday");

        Habit habit = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));

        if (isCompletedToday) {
            habit.setTimesCompleted(habit.getTimesCompleted() + 1);
        } else if (habit.getTimesCompleted() > 0) {
            habit.setTimesCompleted(habit.getTimesCompleted() - 1);
        }


        habit.setIsCompleted(isCompletedToday);
        repository.save(habit);
        return ResponseEntity.ok().build();
    }

}
