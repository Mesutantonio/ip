package conversal.parser;

import conversal.command.*;
import conversal.exception.ConversalException;

// More OOP - conversal.parser.Parser: deals with making sense of the user command
public class Parser {

    public static Command parse(String input) throws ConversalException {

        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("find ")) {
            return new FindCommand(input);
        } else if (input.startsWith("mark ")) {
            return new MarkAsCompleteCommand(input);
        } else if (input.startsWith("unmark ")) {
            return new MarkAsIncompleteCommand(input);
        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("todo ")) {
            return new TodoCommand(input);
        } else if (input.startsWith("deadline ")) {
            return new DeadlineCommand(input);
        } else if (input.startsWith("event ")) {
            return new EventCommand(input);
        } else {
            // Invalid command, throw exception
            throw new ConversalException("I can't seem to locate the issue, please try again!");
        }
    }
}
