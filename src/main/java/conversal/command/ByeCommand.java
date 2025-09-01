package conversal.command;

import conversal.exception.ConversalException;
import conversal.storage.Storage;
import conversal.task.TaskList;
import conversal.ui.Ui;

// conversal.command.Command to exit chatbot
public class ByeCommand implements Command {

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        // Do nothing
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
