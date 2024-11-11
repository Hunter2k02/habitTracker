package habitTracker.MyOwnHabitTracker.controller;

import habitTracker.MyOwnHabitTracker.model.Habit;
import habitTracker.MyOwnHabitTracker.service.habitService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class habitController {

    habitService service;
    public habitController(habitService service) {
        this.service = service;
    }


    @GetMapping("/habits")
    public List<Habit> habits() {
        return service.getHabits();
    }
    @PostMapping("/habit")
    public void addHabit(@RequestBody Habit habit) {
        service.addHabit(habit);
    }
    @DeleteMapping("/habit")
    public void deleteHabit(@RequestBody Habit habit) {
        service.deleteHabit(habit);
    }
    @PostMapping("/habit/{id}/increment")
    public void incrementTimesCompleted(@PathVariable Integer id) {
        service.incrementTimesCompleted(id);
    }


}
