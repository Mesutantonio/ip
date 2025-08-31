import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

// Deadlines: tasks that need to be done before a specific date/tim
public class Deadline extends Task {
    // Fields
    protected LocalDate dueDate;

    // Constructor
    public Deadline(String description, LocalDate dueDate) {
        super(description, TaskType.DEADLINE);
        this.dueDate = dueDate;
    }

    // Method 1: Getter
    public String getDueDate() {
        return dueDate.toString();
    }

    // Override toString method
    @Override
    public String toString() {

        // Format date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String formattedDate = dueDate.format(formatter);
        return super.toString() + " (by: " + formattedDate + ")";
    }
}