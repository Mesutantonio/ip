// Command interface on which all other commands are built on
public interface Command {
    // Method 1: execute the correct action
    void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException;

    // Method 2: return true if command is to exit chatbot
    boolean isExit();
}
