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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;
    String Name;
    @Column
    Integer Goal;
    @Column(name="times_completed")
    Integer timesCompleted;
    public Habit(String name, Integer goal, Integer timesCompleted) {
        Name = name;
        Goal = goal;
        this.timesCompleted = timesCompleted;
    }

}
