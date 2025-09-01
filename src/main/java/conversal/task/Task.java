package conversal.task;

public class Task {
    // Fields
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    // Constructor
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    // Method 1: Get status
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    // Method 2: Marking task as complete
    public void markAsComplete() {
        this.isDone = true;
    }

    // Method 3: Marking task as incomplete
    public void markAsIncomplete() {
        this.isDone = false;
    }

    // Method 4: Getter
    public TaskType getTaskType() {
        return taskType;
    }

    // Method 5: Getter
    public String getDescription() {
        return description;
    }

    // Method 6: Getter
    public Boolean isDone() {
        return isDone;
    }

    // Override toString method
    @Override
    public String toString() {
        return taskType.getSymbol() + "[" + getStatusIcon() + "] " + description;
    }
}
