// Command to mark task as complete
public class MarkAsCompleteCommand implements Command {

    // Fields
    private String input;

    // Constructor
    public MarkAsCompleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        if (input.length() <= 5) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionMark());
        }
        try {
            int index = Integer.parseInt(input.substring(5)) - 1;
            tasks.markTaskComplete(index);
            storage.save(tasks.getList());
            ui.acknowledge(tasks.get(index), true);
        } catch (NumberFormatException e) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionMark());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
