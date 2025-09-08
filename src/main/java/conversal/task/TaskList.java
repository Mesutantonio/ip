package conversal.task;

import java.util.ArrayList;

import conversal.exception.ConversalException;

/**
 * Represents a list of tasks in the Conversal chatbot.
 *
 * <p>Provides operations to add, delete, find, mark, and unmark tasks.
 * Also includes operations to check the current size of the list and return the list itself.</p>
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Creates a TaskList object backed by the given ArrayList of tasks.
     *
     * @param tasks the list of tasks to initialise with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes and returns the task at the given index.
     *
     * @param index the index of the task to delete
     * @return the removed task
     * @throws ConversalException if the index is invalid
     */
    public Task deleteTask(int index) throws ConversalException {
        if (index < 0 || index >= tasks.size()) {
            throw new ConversalException("To delete a task, enter: delete (task number)");
        }
        return this.tasks.remove(index);
    }

    /**
     * Retrieves the task at the given index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the given index
     * @throws ConversalException if the index is invalid
     */
    public Task get(int index) throws ConversalException {
        if (index < 0 || index >= tasks.size()) {
            throw new ConversalException("Enter a valid task number between 1 and " + tasks.size());
        }
        return this.tasks.get(index);
    }

    /**
     * Marks the task at the given index as complete.
     *
     * @param index the index of the task to mark as complete
     * @throws ConversalException if the index is invalid
     */
    public void markTaskComplete(int index) throws ConversalException {
        if (index < 0 || index >= tasks.size()) {
            throw new ConversalException("Enter a valid task number between 1 and " + tasks.size());
        }
        this.tasks.get(index).markAsComplete();
    }

    /**
     * Marks the task at the given index as incomplete.
     *
     * @param index the index of the task to mark as incomplete
     * @throws ConversalException if the index is invalid
     */
    public void markTaskIncomplete(int index) throws ConversalException {
        if (index < 0 || index >= tasks.size()) {
            throw new ConversalException("Enter a valid task number between 1 and " + tasks.size());
        }
        this.tasks.get(index).markAsIncomplete();
    }

    /**
     * Returns the list of tasks.
     *
     * @return the ArrayList of tasks
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the list
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Finds all tasks whose descriptions contain the given keyword.
     *
     * @param keyword the keyword to search for (case-insensitive)
     * @return a list of matching tasks
     */
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
