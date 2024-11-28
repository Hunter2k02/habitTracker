package habitTracker.MyOwnHabitTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/habit-tracker")
    public String viewHabitTracker() {
        return "habitTracker";
    }

    @GetMapping("/chart")
    public String chart() {
        return "chart";
    }

    @GetMapping("/tomatillo-timer")
    public String tomatilloTimer() {
        return "tomatillo-timer";
    }
}
