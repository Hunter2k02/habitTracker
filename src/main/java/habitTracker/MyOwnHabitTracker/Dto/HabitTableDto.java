package habitTracker.MyOwnHabitTracker.Dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HabitTableDto {
    Integer id;
    String name;
    Integer goal;
    Boolean isCompleted;
    Integer timesCompleted;
}
