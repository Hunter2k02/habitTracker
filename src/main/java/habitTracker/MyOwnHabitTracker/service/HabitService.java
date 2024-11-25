package habitTracker.MyOwnHabitTracker.service;

import habitTracker.MyOwnHabitTracker.Dto.HabitChartDto;
import habitTracker.MyOwnHabitTracker.Dto.HabitTableDto;
import habitTracker.MyOwnHabitTracker.exceptionHandler.HabitNotFoundException;
import habitTracker.MyOwnHabitTracker.model.Habit;
import habitTracker.MyOwnHabitTracker.model.HabitCompletion;
import habitTracker.MyOwnHabitTracker.repository.HabitRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HabitService {

    HabitRepository repository;
    HabitCompletionService habitCompletionService;

    public HabitService(HabitRepository repository, HabitCompletionService habitCompletionService) {
        this.repository = repository;
        this.habitCompletionService = habitCompletionService;
    }

    public List<Habit> getHabits() {
        return repository.findAll();
    }

    public ResponseEntity<?> addHabit(Habit habit) {
        try {
            repository.save(habit);
            return ResponseEntity.ok("Habit added successfully!");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: This habit already exists or violates data constraints.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    public ResponseEntity<?> deleteHabit(Habit habit) {
        repository.delete(habit);
        habitCompletionService.deleteByHabitId(habit.getId());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> isHabitCompleted(Integer id, Map<String, Boolean> payload) {

        Boolean isCompletedToday = payload.get("isCompletedToday");

        Habit habit = repository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found with ID: " + id));

        if (isCompletedToday) {
            habitCompletionService.saveAHabitCompletion(id);
        } else {
            habitCompletionService.deleteAHabitCompletion(id);
        }


        habit.setIsCompleted(isCompletedToday);
        repository.save(habit);

        return ResponseEntity.ok().build();
    }

    public List<HabitChartDto> getChartData(Integer habitCompletionID) {
        List<HabitChartDto> chartData = habitCompletionService.getChartData(habitCompletionID);
        return chartData;
    }

    public Integer getHabitIdByName(String name) {
        return repository.findIdByName(name);
    }

    public List<HabitTableDto> getTableData() {

        List<Habit> habits = repository.findAll();
        List<HabitTableDto> habitTableDtos = new ArrayList<>();
        for (Habit habit : habits) {
            habitTableDtos.add(
                    new HabitTableDto(
                            habit.getId(),
                            habit.getName(),
                            habit.getGoal(),
                            habit.getIsCompleted(),
                            habitCompletionService.getTimesCompletedByMonth(habit.getId())
                    )
            );
            for (HabitTableDto habitTableDto : habitTableDtos) {
                System.out.println(habitTableDto);
            }
        }
        return habitTableDtos;

    }

    public void resetForNewDay() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<HabitCompletion> habitCompletions = habitCompletionService.getHabitCompletions();
        for (HabitCompletion habitCompletion : habitCompletions) {
            if (habitCompletion.getDate().equals(currentDate.format(formatter))) {
                return;
            }

        }
        repository.resetAllCheckmarks();
    }


}
