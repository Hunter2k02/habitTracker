package habitTracker.MyOwnHabitTracker.controller;

import habitTracker.MyOwnHabitTracker.model.Habit;
import habitTracker.MyOwnHabitTracker.service.HabitService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class HabitController {

    HabitService service;

    public HabitController(HabitService service) {
        this.service = service;
    }

    @GetMapping("/habits")
    public List<Habit> habits() {
        return service.getHabits();
    }

    @PostMapping("/habit")
    public ResponseEntity<?> addHabit(@RequestBody Habit habit) {
        return service.addHabit(habit);
    }

    @DeleteMapping("/habit")
    public ResponseEntity<?> deleteHabit(@RequestBody Habit habit) {
        return service.deleteHabit(habit);
    }

    @PostMapping("/habit/{habitId}/completion")
    public ResponseEntity<?> updateHabitCompletion(@PathVariable Integer habitId, @RequestBody Map<String, Boolean> payload) {
        return service.isHabitCompleted(habitId, payload);
    }


}
