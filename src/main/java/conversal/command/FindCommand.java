package conversal.command;

import conversal.storage.Storage;
import conversal.task.Task;
import conversal.task.TaskList;
import conversal.ui.Ui;
import conversal.exception.ConversalException;

import java.util.ArrayList;

// Find Command to find all tasks in TaskList with description containing keyword
public class FindCommand implements Command {

    private final String input;

    public FindCommand(String fullInput) {
        this.input = fullInput;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        if (input.length() <= 5) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionFind());
        }

        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionFind());
        }

        ArrayList<Task> matches = tasks.find(keyword);
        ui.showFound(matches);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
