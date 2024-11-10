package habitTracker.MyOwnHabitTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pageController {
    @GetMapping("/habit-tracker")
    public String viewHabitTracker() {
        return "habitTrackerTable";
    }
    @GetMapping("/")
    public String home() {

        return "home";
    }
}
