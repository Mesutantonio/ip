package conversal.parser;

import conversal.command.*;
import conversal.exception.ConversalException;

/**
 * Deals with interpreting user input strings
 * and converting them into Command objects.
 *
 * The Parser takes the given input and decides
 * which is the appropriate command subclass to instantiate
 * If the input does not match any known command,
 * a Conversal Exception is thrown.
 *
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command.
     *
     * @param input the raw input string entered by the user
     * @return the appropriate Command (subclass) object
     * @throws ConversalException if the input does not match any known command
     */
    public static Command parse(String input) throws ConversalException {
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
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
            throw new ConversalException("I can't seem to locate the issue, please try again!");
        }
    }
}