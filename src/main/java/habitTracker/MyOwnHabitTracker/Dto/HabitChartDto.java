package habitTracker.MyOwnHabitTracker.Dto;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Data
public class HabitChartDto {
    Integer habitId;
    String year;
    String month;
    Integer timesCompleted;
}
