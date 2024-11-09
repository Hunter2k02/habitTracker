package habitTracker.MyOwnHabitTracker.controller;

import habitTracker.MyOwnHabitTracker.model.Habit;
import habitTracker.MyOwnHabitTracker.service.habitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class habitController {

    habitService service;
    public habitController(habitService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home() {
        return "Home Pageeee";
    }
    @GetMapping("/habits")
    public List<Habit> habits() {
        return service.getHabits();
    }
    @PostMapping("/habit")
    public void addHabit(@RequestBody Habit habit) {
        service.addHabit(habit);
    }
}
