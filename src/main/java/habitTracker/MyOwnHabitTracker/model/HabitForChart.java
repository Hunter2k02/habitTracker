package habitTracker.MyOwnHabitTracker.model;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.context.annotation.Bean;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Data
public class HabitForChart {
    Integer habitId;
    String year;
    String month;
    Integer timesCompleted;
}
