package habitTracker.MyOwnHabitTracker.service;

import habitTracker.MyOwnHabitTracker.model.HabitCompletion;
import habitTracker.MyOwnHabitTracker.model.HabitForChart;
import habitTracker.MyOwnHabitTracker.repository.HabitCompletionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class HabitCompletionService {
    HabitCompletionRepository completionRepository;


    public HabitCompletionService(HabitCompletionRepository completionRepository) {
        this.completionRepository = completionRepository;
    }

    public void saveAHabitCompletion(Integer id) {
        HabitCompletion completion = new HabitCompletion();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        completion.setHabitId(id);
        completion.setDate(currentDate.format(formatter));
        completionRepository.save(completion);
    }

    public void deleteAHabitCompletion(Integer id) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        completionRepository.deleteByHabitIdAndDate(id, currentDate.format(formatter));
    }
    public List<HabitForChart> getChartData(Integer habitCompletionID) {
        List<Object[]> rawResults = completionRepository.findAllByHabitId(habitCompletionID);
        List<HabitForChart> stats = new ArrayList<>();

        for (Object[] row : rawResults) {
            stats.add(new HabitForChart(
                    ((Number)row[0]).intValue(),  // habitId
                    (String) row[1],               // year
                    (String) row[2],               // month
                    ((Number) row[3]).intValue() // timesCompleted
            ));
        }
        return stats;
    }
}
