// More OOP - Parser: deals with making sense of the user command
public class Parser {

    public static Command parse(String input) throws ConversalException {
        String trimmed = input.trim();

        if (trimmed.equals("bye")) {
            return new ByeCommand();
        } else if (trimmed.equals("list")) {
            return new ListCommand();
        } else if (trimmed.startsWith("mark ")) {
            return new MarkAsCompleteCommand(trimmed.substring(5));        // arg after "mark "
        } else if (trimmed.startsWith("unmark ")) {
            return new MarkAsIncompleteCommand(trimmed.substring(7));      // arg after "unmark "
        } else if (trimmed.startsWith("delete ")) {
            return new DeleteCommand(trimmed.substring(7));      // arg after "delete "
        } else if (trimmed.startsWith("todo ")) {
            return new TodoCommand(trimmed.substring(5));        // description
        } else if (trimmed.startsWith("deadline ")) {
            return new DeadlineCommand(trimmed.substring(9));    // "<desc> /by <yyyy-mm-dd>"
        } else if (trimmed.startsWith("event ")) {
            return new EventCommand(trimmed.substring(6));       // "<desc> /from <start> /to <end>"
        } else {
            throw new ConversalException("I can't seem to locate the issue, please try again!");
        }
    }
}
