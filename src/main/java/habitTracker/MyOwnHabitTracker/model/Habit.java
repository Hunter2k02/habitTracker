package habitTracker.MyOwnHabitTracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Habit {
    @Id
    Integer Id;
    String Name;
    @Column
    String Description;
    Integer timesCompleted;

}
