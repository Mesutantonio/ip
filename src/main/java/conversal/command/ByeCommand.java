package conversal.command;

import conversal.task.TaskList;
import conversal.storage.Storage;
import conversal.ui.Ui;
import conversal.exception.ConversalException;

// Bye Command to exit chatbot
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
