package conversal.command;

import conversal.storage.Storage;
import conversal.task.Task;
import conversal.task.TaskList;
import conversal.ui.Ui;
import conversal.exception.ConversalException;
import conversal.task.Deadline;

import java.time.LocalDate;

// conversal.command.Command to create deadline task
public class DeadlineCommand implements Command {

    // Fields
    private String input;

    // Constructor
    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        if (input.length() <= 9) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionDeadline());
        }
        if (!input.contains(" /by ")) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionDeadline());
        }
        String[] info = input.substring(9).split(" /by ");
        if (info.length < 2 || info[0].trim().isEmpty() || info[1].trim().isEmpty()) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionDeadline());
        }
        try {
            LocalDate dueDate = LocalDate.parse(info[1].trim());
            Task t = new Deadline(info[0], dueDate);
            tasks.addTask(t);
            storage.save(tasks.getList());
            ui.addMessage(t, tasks.size());
        } catch (Exception e) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionDeadline());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}