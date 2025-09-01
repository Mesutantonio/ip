package conversal.command;

import conversal.exception.ConversalException;
import conversal.storage.Storage;
import conversal.task.Event;
import conversal.task.Task;
import conversal.task.TaskList;
import conversal.ui.Ui;

// Event Command to create event task
public class EventCommand implements Command {

    // Fields
    private String input;

    // Constructor
    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        if (input.length() <= 6) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionEvent());
        }
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionEvent());
        }
        String[] info = input.substring(6).split(" /from | /to ");
        if (info.length < 3 || info[0].trim().isEmpty() || info[1].trim().isEmpty()
                || info[2].trim().isEmpty()) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionEvent());
        }
        Task t = new Event(info[0], info[1], info[2]);
        tasks.addTask(t);
        storage.save(tasks.getList());
        ui.addMessage(t, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}