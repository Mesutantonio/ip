// More OOP - Parser: deals with making sense of the user command
public class Parser {

    public static Command parse(String input) throws ConversalException {
        String trimmed = input.trim();

        if (trimmed.equals("bye")) {
            return new ByeCommand();
        } else if (trimmed.equals("list")) {
            return new ListCommand();
        } else if (trimmed.startsWith("mark ")) {
            return new MarkAsCompleteCommand(trimmed.substring(5));
        } else if (trimmed.startsWith("unmark ")) {
            return new MarkAsIncompleteCommand(trimmed.substring(7));
        } else if (trimmed.startsWith("delete ")) {
            return new DeleteCommand(trimmed.substring(7));
        } else if (trimmed.startsWith("todo ")) {
            return new TodoCommand(trimmed.substring(5));
        } else if (trimmed.startsWith("deadline ")) {
            return new DeadlineCommand(trimmed.substring(9));
        } else if (trimmed.startsWith("event ")) {
            return new EventCommand(trimmed.substring(6));
        } else {
            // Invalid command, throw exception
            throw new ConversalException("I can't seem to locate the issue, please try again!");
        }
    }
}
