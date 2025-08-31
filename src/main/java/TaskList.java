import java.util.ArrayList;

// More OOP - TaskList: contains the task list
public class TaskList {

    // Fields
    private final ArrayList<Task> tasks;

    // Constructor
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<Task>();
    }

    // Method 1: add task
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    // Method 2: delete task
    public void deleteTask(Task task) {
        this.tasks.remove(task);
    }

    // Method 3: get task
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    // Method 4: mark task as complete
    public void markTaskComplete(int index) {
        this.tasks.get(index).markAsComplete();
    }

    // Method 5: mark task as incomplete
    public void markTaskIncomplete(int index) {
        this.tasks.get(index).markAsIncomplete();
    }

    // Method 6: return task list itself
    public ArrayList<Task> getList() {
        return this.tasks;
    }

}
