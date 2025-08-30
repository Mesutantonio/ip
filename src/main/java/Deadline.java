// Deadlines: tasks that need to be done before a specific date/time
public class Deadline extends Task {
    // Fields
    protected String dueDate;

    // Constructor
    public Deadline(String description, String dueDate) {
        super(description, TaskType.DEADLINE);
        this.dueDate = dueDate;
    }

    // Override toString method
    @Override
    public String toString() {
        return super.toString() + " (by: " + dueDate + ")";
    }
}