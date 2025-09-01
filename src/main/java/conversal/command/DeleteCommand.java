package conversal.command;

import conversal.storage.Storage;
import conversal.task.Task;
import conversal.task.TaskList;
import conversal.ui.Ui;
import conversal.exception.ConversalException;

// Delete Command to delete task
public class DeleteCommand implements Command {

    // Fields
    private String input;

    // Constructor
    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        if (input.length() <= 7) {
            throw new ConversalException("To delete a task, enter: delete (task number)");
        }
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task removedTask = tasks.deleteTask(index);
            storage.save(tasks.getList());
            ui.deleteMessage(tasks.size(), removedTask.toString());
        } catch (NumberFormatException e) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionDelete());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}