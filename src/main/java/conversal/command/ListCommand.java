package conversal.command;

import conversal.storage.Storage;
import conversal.task.TaskList;
import conversal.ui.Ui;
import conversal.exception.ConversalException;

// conversal.command.Command to list all tasks in task list
public class ListCommand implements Command {

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        ui.showList(tasks.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
