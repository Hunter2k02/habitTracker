package habitTracker.MyOwnHabitTracker.exceptionHandler;

public class HabitNotFoundException extends RuntimeException {


    public HabitNotFoundException(String message) {
        super(message);
    }
}