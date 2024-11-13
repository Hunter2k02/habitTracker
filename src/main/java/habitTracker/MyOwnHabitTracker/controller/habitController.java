package habitTracker.MyOwnHabitTracker.controller;

import habitTracker.MyOwnHabitTracker.model.Habit;
import habitTracker.MyOwnHabitTracker.service.habitService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> addHabit(@RequestBody Habit habit) {
        service.addHabit(habit);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/habit")
    public ResponseEntity<?> deleteHabit(@RequestBody Habit habit) {
        service.deleteHabit(habit);
        return ResponseEntity.ok().build();
    }
    @PostMapping("habit/{habitId}/completion")
    public ResponseEntity<?> updateHabitCompletion(@PathVariable Integer habitId, @RequestBody Map<String, Boolean> payload) {
        service.isHabitCompleted(habitId, payload);
        return ResponseEntity.ok().build();
    }


}
