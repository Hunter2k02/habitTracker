package habitTracker.MyOwnHabitTracker.service;

import habitTracker.MyOwnHabitTracker.model.HabitCompletion;
import habitTracker.MyOwnHabitTracker.repository.HabitCompletionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
}
