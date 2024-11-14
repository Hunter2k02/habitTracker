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
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer goal;

    @Column(name = "times_completed")
    private Integer timesCompleted = 0;

    @Column(name = "is_completed")
    private Boolean isCompleted = false;

    public Habit(String name, Integer goal, Integer timesCompleted, Boolean isCompleted) {
        this.name = name;
        this.goal = goal;
        this.timesCompleted = timesCompleted;
        this.isCompleted = isCompleted;
    }
}
