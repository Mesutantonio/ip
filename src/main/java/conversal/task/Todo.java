package conversal.task;

// ToDos: tasks without any date/time attached end it
public class Todo extends Task {
    // Fields
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    // Override toString method
    @Override
    public String toString() {
        return super.toString();
    }
}