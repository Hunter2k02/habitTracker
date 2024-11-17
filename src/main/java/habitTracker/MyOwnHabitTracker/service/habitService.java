package habitTracker.MyOwnHabitTracker.service;

import habitTracker.MyOwnHabitTracker.exceptionHandler.HabitNotFoundException;
import habitTracker.MyOwnHabitTracker.model.Habit;
import habitTracker.MyOwnHabitTracker.repository.HabitRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class HabitService {

    HabitRepository repository;
    HabitCompletionService habitCompletionService;
    public HabitService(HabitRepository repository, HabitCompletionService habitCompletionService) {
        this.repository = repository;
        this.habitCompletionService = habitCompletionService;
    }

    public List<Habit> getHabits() {
        return repository.findAll();
    }

    public ResponseEntity<?> addHabit(Habit habit) {
        try {
            repository.save(habit);
            return ResponseEntity.ok("Habit added successfully!");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: This habit already exists or violates data constraints.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    public ResponseEntity<?> deleteHabit(Habit habit) {
        repository.delete(habit);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> isHabitCompleted(Integer id, Map<String, Boolean> payload) {

        Boolean isCompletedToday = payload.get("isCompletedToday");

        Habit habit = repository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found with ID: " + id));

        if (isCompletedToday) {
            habit.setTimesCompleted(habit.getTimesCompleted() + 1);
            habitCompletionService.saveAHabitCompletion(id);
        } else if (habit.getTimesCompleted() > 0) {
            habit.setTimesCompleted(habit.getTimesCompleted() - 1);
            habitCompletionService.deleteAHabitCompletion(id);
        }


        habit.setIsCompleted(isCompletedToday);
        repository.save(habit);

        return ResponseEntity.ok().build();
    }



}
