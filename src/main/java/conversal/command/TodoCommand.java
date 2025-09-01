package conversal.command;

import conversal.exception.ConversalException;
import conversal.storage.Storage;
import conversal.task.Task;
import conversal.task.TaskList;
import conversal.task.Todo;
import conversal.ui.Ui;

// conversal.command.Command to create to-do task
public class TodoCommand implements Command {

    // Fields
    private String input;

    // Constructor
    public TodoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        if (input.length() <= 5) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionTodo());
        }
        String description = input.substring(5);
        Task t = new Todo(description);
        tasks.addTask(t);
        storage.save(tasks.getList());
        ui.addMessage(t, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}