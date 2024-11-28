package habitTracker.MyOwnHabitTracker.configuration;

import habitTracker.MyOwnHabitTracker.service.HabitService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    private final HabitService habitService;
    public AppConfig(HabitService habitService) {
        this.habitService = habitService;
    }
    @PostConstruct
    public void resetCheckmarks(){
        habitService.resetForNewDay();
    }
}
