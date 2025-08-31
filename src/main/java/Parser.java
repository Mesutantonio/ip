// More OOP - Parser: deals with making sense of the user command
public class Parser {

    public static Command parse(String input) throws ConversalException {

        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ")) {
            return new MarkAsCompleteCommand(input);
        } else if (input.startsWith("unmark ")) {
            return new MarkAsIncompleteCommand(input.substring(7));
        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(input.substring(7));
        } else if (input.startsWith("todo ")) {
            return new TodoCommand(input.substring(5));
        } else if (input.startsWith("deadline ")) {
            return new DeadlineCommand(input.substring(9));
        } else if (input.startsWith("event ")) {
            return new EventCommand(input.substring(6));
        } else {
            // Invalid command, throw exception
            throw new ConversalException("I can't seem to locate the issue, please try again!");
        }
    }
}
