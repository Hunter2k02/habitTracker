package habitTracker.MyOwnHabitTracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HabitCompletion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer habitId;
    @Column(nullable = false)
    private String date;

}
