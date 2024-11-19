package habitTracker.MyOwnHabitTracker.controller;

import habitTracker.MyOwnHabitTracker.model.Habit;
import habitTracker.MyOwnHabitTracker.model.HabitForChart;
import habitTracker.MyOwnHabitTracker.service.HabitService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class HabitController {

    HabitService service;

    public HabitController(HabitService service) {
        this.service = service;
    }

    @GetMapping("/habits")
    public List<Habit> habits() {
        return service.getHabits();
    }
    @GetMapping("/habit/{name}")
    public Integer getHabitByName(@PathVariable String name) {
        System.out.println(name);
        System.out.println(service.getHabitIdByName(name));
        return service.getHabitIdByName(name);

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
    @GetMapping("/chart-data/{habitCompletionID}")
    public List<HabitForChart> getChartData(@PathVariable Integer habitCompletionID) {
        return service.getChartData(habitCompletionID);
    }


}
