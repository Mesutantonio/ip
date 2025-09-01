package conversal.task;

import conversal.exception.ConversalException;

import java.util.ArrayList;

// More OOP - conversal.task.TaskList: contains the task list
public class TaskList {

    // Fields
    private final ArrayList<Task> tasks;

    // Constructor
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // Method 1: add task
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    // Method 2: delete task
    public Task deleteTask(int index) throws ConversalException {
        // Error handling
        if (index < 0 || index >= tasks.size()) {
            throw new ConversalException("To delete a task, enter: delete (task number)");
        }

        return this.tasks.remove(index);
    }

    // Method 3: get task
    public Task get(int index) throws ConversalException {
        // Error handling
        if (index < 0 || index >= tasks.size()) {
            throw new ConversalException("Enter a valid task number between 1 and " + tasks.size());
        }

        return this.tasks.get(index);
    }

    // Method 4: mark task as complete
    public void markTaskComplete(int index) throws ConversalException  {
        // Error handling
        if (index < 0 || index >= tasks.size()) {
            throw new ConversalException("Enter a valid task number between 1 and " + tasks.size());
        }

        this.tasks.get(index).markAsComplete();
    }

    // Method 5: mark task as incomplete
    public void markTaskIncomplete(int index) throws ConversalException {
        // Error handling
        if (index < 0 || index >= tasks.size()) {
            throw new ConversalException("Enter a valid task number between 1 and " + tasks.size());
        }

        this.tasks.get(index).markAsIncomplete();
    }

    // Method 6: return task list itself
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    // Method 7: size helper
    public int size() {
        return this.tasks.size();
    }

    // Method 8: find task(s) by searching for keyword in description
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        String k = keyword.toLowerCase();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(k)) {
                result.add(t);
            }
        }
        return result;
    }

}
