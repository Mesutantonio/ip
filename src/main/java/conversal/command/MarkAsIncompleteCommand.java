package conversal.command;

import conversal.storage.Storage;
import conversal.task.TaskList;
import conversal.ui.Ui;
import conversal.exception.ConversalException;

// MarkAsIncomplete Command to mark task as incomplete
public class MarkAsIncompleteCommand implements Command {

    // Fields
    private String input;

    // Constructor
    public MarkAsIncompleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        if (input.length() <= 7) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionUnmark());
        }
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            tasks.markTaskIncomplete(index);
            storage.save(tasks.getList());
            ui.acknowledge(tasks.get(index), false);
        } catch (NumberFormatException e) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionUnmark());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
