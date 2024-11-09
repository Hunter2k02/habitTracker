package habitTracker.MyOwnHabitTracker.service;

import habitTracker.MyOwnHabitTracker.model.Habit;
import org.springframework.stereotype.Service;
import habitTracker.MyOwnHabitTracker.repository.habitRepository;

import java.util.List;

@Service
public class habitService {
    habitRepository repository;

    public habitService(habitRepository repository) {
        this.repository = repository;
    }

    public List<Habit> getHabits() {
        return repository.findAll();
    }
    public Habit getHabit(int id) {
        return repository.findById(id).get();
    }
    public void addHabit(Habit habit) {
        repository.save(habit);
    }

}
