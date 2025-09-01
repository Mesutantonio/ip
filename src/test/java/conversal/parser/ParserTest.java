package conversal.parser;

import conversal.command.*;
import conversal.exception.ConversalException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Junit test for parser to return correct command based on user input
class ParserTest {

    // Test 1: bye input --> Bye command
    @Test
    void parse_bye_returnsByeCommand() throws ConversalException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ByeCommand);
    }

    // Test 2: list input --> List command
    @Test
    void parse_list_returnsListCommand() throws ConversalException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    // Test 3: mark (index) input --> MarkAsComplete command
    @Test
    void parse_mark_returnsMarkAsCompleteCommand() throws ConversalException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkAsCompleteCommand);
    }

    // Test 4: unmark (index) input --> MarkAsIncomplete command
    @Test
    void parse_unmark_returnsMarkAsIncompleteCommand() throws ConversalException {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof MarkAsIncompleteCommand);
    }

    // Test 5: delete (index) input --> Delete command
    @Test
    void parse_delete_returnsDeleteCommand() throws ConversalException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    // Test 6: todo (name) input --> Todo command
    @Test
    void parse_todo_returnsTodoCommand() throws ConversalException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof TodoCommand);
    }

    // Test 7: deadline (name) /by (date YYYY-MM-DD) input --> Deadline command
    @Test
    void parse_deadline_returnsDeadlineCommand() throws ConversalException {
        Command command = Parser.parse("deadline return book /by 2025-09-01");
        assertTrue(command instanceof DeadlineCommand);
    }

    // Test 8: event (name) /from (start) /to (end) input --> Event command
    @Test
    void parse_event_returnsEventCommand() throws ConversalException {
        Command command = Parser.parse("event project meeting /from Mon 2pm /to 4pm");
        assertTrue(command instanceof EventCommand);
    }

    // Test 9: invalid input --> throw ConversalException
    @Test
    void parse_unknown_throwsConversalException() {
        assertThrows(ConversalException.class, () -> Parser.parse("Blah"));
    }
}