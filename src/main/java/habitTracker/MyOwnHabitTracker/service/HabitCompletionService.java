package habitTracker.MyOwnHabitTracker.service;

import habitTracker.MyOwnHabitTracker.Dto.HabitChartDto;
import habitTracker.MyOwnHabitTracker.model.HabitCompletion;
import habitTracker.MyOwnHabitTracker.repository.HabitCompletionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HabitCompletionService {
    HabitCompletionRepository completionRepository;


    public HabitCompletionService(HabitCompletionRepository completionRepository) {
        this.completionRepository = completionRepository;
    }

    public List<HabitCompletion> getHabitCompletions() {
        return completionRepository.findAll();
    }

    public void saveAHabitCompletion(Integer id) {
        HabitCompletion completion = new HabitCompletion();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        completion.setHabitId(id);
        completion.setDate(currentDate.format(formatter));
        completionRepository.save(completion);
    }

    public void deleteByHabitId(Integer id) {
        completionRepository.deleteByHabitId(id);
    }

    public void deleteAHabitCompletion(Integer id) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        completionRepository.deleteByHabitIdAndDate(id, currentDate.format(formatter));
    }

    public List<HabitChartDto> getChartData(Integer habitCompletionID) {
        List<Object[]> rawResults = completionRepository.findAllByHabitId(habitCompletionID);
        List<HabitChartDto> stats = new ArrayList<>();

        if (rawResults.isEmpty()) {
            stats.addAll(defaultResult(habitCompletionID));
            return stats;
        }

        for (Object[] row : rawResults) {
            stats.add(new HabitChartDto(
                    ((Number) row[0]).intValue(),  // habitId
                    (String) row[1],
                    (String) row[2],
                    ((Number) row[3]).intValue()
            ));
        }
        Set<String> existingMonths = stats.stream()
                .map(HabitChartDto::getMonth)
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
        int currentYear = LocalDate.now().getYear();
        stats = stats.stream()
                .filter(habit -> Integer.parseInt(habit.getYear()) == currentYear)
                .collect(Collectors.toList());
        int min = Integer.parseInt(Collections.min(existingMonths));
        int max = Integer.parseInt(Collections.max(existingMonths));

        for (int i = min; i <= max; i++) {
            String month = (i < 10) ? "0" + i : String.valueOf(i);
            if (!existingMonths.contains(month)) {
                stats.add(new HabitChartDto(
                        stats.get(0).getHabitId(),
                        stats.get(0).getYear(),
                        month,
                        0
                ));
            }
        }

        stats.sort(Comparator.comparing(HabitChartDto::getMonth));


        return stats;
    }

    public List<HabitChartDto> defaultResult(Integer habitCompletionID) {
        List<HabitChartDto> stats = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        for (int i = 1; i <= 12; i++) {
            stats.add(new HabitChartDto(
                    habitCompletionID,
                    String.valueOf(currentYear),
                    (i < 10) ? "0" + i : String.valueOf(i),
                    0

            ));
        }
        return stats;
    }

    public Integer getTimesCompletedByMonth(Integer habitCompletionID) {
        List<Object[]> rawResults = completionRepository.findAllByHabitId(habitCompletionID);
        List<HabitChartDto> stats = new ArrayList<>();

        String currentMonth = String.valueOf(LocalDate.now().getMonthValue());
        String currentYear = String.valueOf(LocalDate.now().getYear());
        if (Integer.parseInt(currentMonth) < 10) {
            currentMonth = "0" + currentMonth;
        }
        for (Object[] row : rawResults) {
            String year = (String) row[1];  // year from the result
            String month = (String) row[2]; // month from the result

            if (currentYear.equals(year) && currentMonth.equals(month)) {
                stats.add(new HabitChartDto(
                        ((Number) row[0]).intValue(),  // habitId
                        year,                          // year
                        month,                         // month
                        ((Number) row[3]).intValue()   // timesCompleted
                ));
            }
        }
        for (HabitChartDto stat : stats) {
            System.out.println(stat);
        }
        if (stats.isEmpty()) {
            return 0;
        }
        return stats.get(0).getTimesCompleted();
    }
}
