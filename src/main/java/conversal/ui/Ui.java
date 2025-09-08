package conversal.ui;

import java.util.ArrayList;
import java.util.Scanner;

import conversal.task.Task;

/**
 * Handles user I/O (welcome, bye, instructions, messages, results, and errors).
 */
public class Ui {

    /* ======================= Constants (in ALL_CAPS, before fields) ======================= */

    private static final String NAME = "conversal.Conversal";
    private static final String GREETING = "What can I do for you?";
    private static final String EXIT = "Bye! Hope to see you again!";

    private static final String INSTRUCTION_BYE = "To close chatbot, enter: bye.";
    private static final String INSTRUCTION_LIST = "To display tasks list, enter: list";
    private static final String INSTRUCTION_FIND = "To find task(s) with keyword, enter: find (keyword)";
    private static final String INSTRUCTION_MARK = "To mark task as Complete, enter: mark (task no.)";
    private static final String INSTRUCTION_UNMARK = "To mark task as Incomplete, enter: unmark (task no.)";
    private static final String INSTRUCTION_DELETE = "To delete a task: delete (task no.)";
    private static final String INSTRUCTION_TODO = "To add conversal.task.Todo task, enter: todo (task)";
    private static final String INSTRUCTION_DEADLINE =
            "To add conversal.task.Deadline task, enter: deadline (task) /by (date in YYYY-MM-DD format)";
    private static final String INSTRUCTION_EVENT =
            "To add conversal.task.Event task, enter: event (task) /from (start) /to (end)";

    /* ======================= Fields ======================= */

    private final Scanner scanner;

    /* ======================= Constructors ======================= */

    /** Creates a UI that reads from standard input. */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /* ======================= Input / Output ======================= */

    /**
     * Prompts user for their input and captures it.
     *
     * @return user input as a string
     */
    public String readInput() {
        System.out.print("User: ");
        return scanner.nextLine();
    }

    /** Prints welcome message and instructions. */
    public void welcomeMessage() {
        System.out.println("Hello! I'm " + NAME + ".");
        System.out.println(GREETING + "\n");
        System.out.println("Instructions: ");
        System.out.println("> " + INSTRUCTION_BYE);
        System.out.println("> " + INSTRUCTION_LIST);
        System.out.println("> " + INSTRUCTION_MARK);
        System.out.println("> " + INSTRUCTION_UNMARK);
        System.out.println("> " + INSTRUCTION_DELETE);
        System.out.println("> " + INSTRUCTION_TODO);
        System.out.println("> " + INSTRUCTION_DEADLINE);
        System.out.println("> " + INSTRUCTION_EVENT);
        System.out.println();
    }

    /** Prints exit message. */
    public void exitMessage() {
        System.out.println("\n" + EXIT);
    }

    /**
     * Prints acknowledgement after adding a task.
     *
     * @param task        the task added
     * @param totalTasks  current number of tasks
     */
    public void addMessage(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.\n");
    }

    /**
     * Shows list of tasks.
     *
     * @param tasks ArrayList of tasks
     */
    public void showList(ArrayList<Task> tasks) {
        System.out.println("\nHere is your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            String taskString = tasks.get(i).toString();
            System.out.println((i + 1) + ". " + taskString);
        }
        System.out.println();
    }

    /**
     * Prints acknowledgement of marking or unmarking a task.
     *
     * @param task       the task that was marked or unmarked
     * @param isComplete true if the task was marked as complete, false if unmarked
     */
    public void acknowledge(Task task, boolean isComplete) {
        if (isComplete) {
            System.out.println("Nice! I've marked this task as complete:");
        } else {
            System.out.println("OK! I've marked this task as incomplete:");
        }
        System.out.println(task + "\n");
    }

    /**
     * Prints acknowledgement of a task being deleted, and the updated task count.
     *
     * @param size the number of tasks remaining in the list
     * @param name the string representation of the deleted task
     */
    public void deleteMessage(int size, String name) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + name);
        System.out.println("Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Displays tasks whose descriptions contain the keyword.
     *
     * @param matches the matching tasks
     */
    public void showFound(ArrayList<Task> matches) {
        System.out.println("\nHere are the matching tasks in your list:");
        for (int i = 0; i < matches.size(); i++) {
            System.out.println((i + 1) + ". " + matches.get(i));
        }
        System.out.println();
    }

    /**
     * Prints an error message.
     *
     * @param message the message to be printed out
     */
    public void printError(String message) {
        System.out.println(message + "\n");
    }

    /** Closes the scanner resource. */
    public void close() {
        scanner.close();
    }

    /* ======================= Getters for instruction strings ======================= */

    public String getInstructionFind() {
        return INSTRUCTION_FIND;
    }

    public String getInstructionMark() {
        return INSTRUCTION_MARK;
    }

    public String getInstructionUnmark() {
        return INSTRUCTION_UNMARK;
    }

    public String getInstructionDelete() {
        return INSTRUCTION_DELETE;
    }

    public String getInstructionTodo() {
        return INSTRUCTION_TODO;
    }

    public String getInstructionDeadline() {
        return INSTRUCTION_DEADLINE;
    }

    public String getInstructionEvent() {
        return INSTRUCTION_EVENT;
    }
}
