package conversal.task;

import conversal.exception.ConversalException;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    // Helper test: check if task status is visually updated
    private static boolean looksComplete(Task t) {
        String s = t.toString();
        return s.contains("[X]");
    }
    private static boolean looksIncomplete(Task t) {
        String s = t.toString();
        return s.contains("[ ]");
    }

    // Test 1 - 2 : adding task(s)
    @Test
    void addTask_addOne_increasesSizeByOne() {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new Todo("buy milk"));
        assertEquals(1, list.size());
    }

    @Test
    void addTask_keepsOrder() throws ConversalException {
        TaskList list = new TaskList(new ArrayList<>());
        Task t1 = new Todo("first");
        Task t2 = new Todo("second");
        list.addTask(t1);
        list.addTask(t2);
        assertSame(t1, list.get(0));
        assertSame(t2, list.get(1));
    }

    // Test 3 - 4: getting task
    @Test
    void get_validIndex_returnsThatTask() throws ConversalException {
        TaskList list = new TaskList(new ArrayList<>());
        Task t = new Todo("hello");
        list.addTask(t);
        assertSame(t, list.get(0));
    }

    @Test
    void get_invalidIndex_throws() {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new Todo("only one"));
        assertThrows(ConversalException.class, () -> list.get(1)); // out of bounds
    }

    // Test 5 - 7: deleting task(s)
    @Test
    void deleteTask_validIndex_returnsRemovedTask() throws ConversalException {
        TaskList list = new TaskList(new ArrayList<>());
        Task t1 = new Todo("a");
        Task t2 = new Todo("b");
        list.addTask(t1);
        list.addTask(t2);

        Task removed = list.deleteTask(0);

        assertSame(t1, removed);
    }

    @Test
    void deleteTask_validIndex_decreasesSize() throws ConversalException {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new Todo("a"));
        list.addTask(new Todo("b"));
        list.deleteTask(0);
        assertEquals(1, list.size());
    }

    @Test
    void deleteTask_invalidIndex_throws() {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new Todo("a"));
        assertThrows(ConversalException.class, () -> list.deleteTask(5));
    }

    // Task 8 - 11: markTaskComplete and markTaskIncomplete
    @Test
    void markTaskComplete_setsTaskToComplete() throws ConversalException {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new Todo("do it"));
        list.markTaskComplete(0);
        assertTrue(looksComplete(list.get(0)));
    }

    @Test
    void markTaskIncomplete_setsTaskBackToIncomplete() throws ConversalException {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new Todo("do it"));
        list.markTaskComplete(0);
        list.markTaskIncomplete(0);
        assertTrue(looksIncomplete(list.get(0)));
    }

    @Test
    void markTaskComplete_invalidIndex_throws() {
        TaskList list = new TaskList(new ArrayList<>());
        assertThrows(ConversalException.class, () -> list.markTaskComplete(0));
    }

    @Test
    void markTaskIncomplete_invalidIndex_throws() {
        TaskList list = new TaskList(new ArrayList<>());
        assertThrows(ConversalException.class, () -> list.markTaskIncomplete(0));
    }

    // Test 12 - 13: size of Tasklist
    @Test
    void size_returnsZeroForNewList() {
        TaskList list = new TaskList(new ArrayList<>());
        assertEquals(0, list.size());
    }

    @Test
    void getList_reflectsUnderlyingChanges() {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new Todo("x"));
        assertEquals(1, list.getList().size());
    }
}