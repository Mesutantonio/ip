// Events: tasks that start at a specific date/time and ends at a specific date/time
public class Event extends Task {
    // Fields
    protected String start;
    protected String end;

    // Constructor
    public Event(String description, String start, String end) {
        super(description, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    // Method 1: Getter
    public String getSchedule() {
        return (start + "-" + end);
    }

    // Override toString method
    @Override
    public String toString() {
        return super.toString() + " (from: " + start + " to: " + end + ")";
    }
}