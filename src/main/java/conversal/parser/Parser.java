package conversal.parser;

import conversal.command.ByeCommand;
import conversal.command.Command;
import conversal.command.DeadlineCommand;
import conversal.command.DeleteCommand;
import conversal.command.EventCommand;
import conversal.command.FindCommand;
import conversal.command.ListCommand;
import conversal.command.MarkAsCompleteCommand;
import conversal.command.MarkAsIncompleteCommand;
import conversal.command.TodoCommand;
import conversal.exception.ConversalException;

/**
 * Deals with interpreting user input strings
 * and converting them into Command objects.
 *
 * <p>The parser takes the given input and decides which command to instantiate.
 * If the input does not match any known command, a {@code ConversalException} is thrown.</p>
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input the raw input string entered by the user
     * @return the appropriate {@link Command} to execute
     * @throws ConversalException if the input does not match any known command
     */
    public static Command parse(String input) throws ConversalException {
        assert input != null : "input must not be null";
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
        } else if (input.startsWith("within ")) {
            return new DoWithinCommand(input);
        } else {
            throw new ConversalException("I can't seem to locate the issue, please try again!");
        }
    }
}
