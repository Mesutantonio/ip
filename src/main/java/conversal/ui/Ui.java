package conversal.ui;

import conversal.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user I/O (welcome, bye, instructions, messages, results and errors).
 */
public class Ui {

    // Fields
    protected Scanner scanner;
    protected String name = "conversal.Conversal";
    protected String greeting = "What can I do for you?";
    protected String exit = "Bye! Hope to see you again!";
    protected String instructionBye = "To close chatbot, enter: bye.";
    protected String instructionList = "To display tasks list, enter: list";
    protected String instructionMark = "To mark task as Complete, enter: mark (task no.)";
    protected String instructionUnmark = "To mark task as Incomplete, enter: unmark (task no.)";
    protected String instructionDelete = "To delete a task: delete (task no.)";
    protected String instructionTodo = "To add conversal.task.Todo task, enter: todo (task)";
    protected String instructionDeadline = "To add conversal.task.Deadline task, enter: deadline (task) /by (date in YYYY-MM-DD format)";
    protected String instructionEvent = "To add conversal.task.Event task, enter: event (task) /from (start) /to (end)";

    // Constructor
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prompts user for their input and captures it
     *
     * @return user input as a string
     */
    public String readInput() {
        System.out.print("User: ");
        return scanner.nextLine();
    }

    /**
     * Prints welcome message and instructions
     */
    public void welcomeMessage() {
        System.out.println("Hello! I'm " + this.name + ".");
        System.out.println(this.greeting + "\n");
        System.out.println("Instructions: ");
        System.out.println("> " + this.instructionBye);
        System.out.println("> " + this.instructionList);
        System.out.println("> " + this.instructionMark);
        System.out.println("> " + this.instructionUnmark);
        System.out.println("> " + this.instructionDelete);
        System.out.println("> " + this.instructionTodo);
        System.out.println("> " + this.instructionDeadline);
        System.out.println("> " + this.instructionEvent);
        System.out.println();
    }

    /**
     * Prints exit message
     */
    public void exitMessage() {
        System.out.println("\n" + this.exit);
    }

    /**
     * Prints welcome message and instructions
     */
    public void addMessage(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.\n");
    }

    /**
     * Shows list of tasks 
     *
     * @param tasks ArrayList of tasks.
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
     * Prints error message due to thrown Conversal Exception
     *
     * @param message the message to be printed out
     */
    public void printError(String message) {
        System.out.println(message + "\n");
    }

    // Method 9: close scanner
    public void close() {
        scanner.close();
    }

    // Method 10 - 15: Getters
    public String getInstructionMark()      { return instructionMark; }
    public String getInstructionUnmark()    { return instructionUnmark; }
    public String getInstructionDelete()    { return instructionDelete; }
    public String getInstructionTodo()      { return instructionTodo; }
    public String getInstructionDeadline()  { return instructionDeadline; }
    public String getInstructionEvent()     { return instructionEvent; }

}
