package conversal.ui;

import conversal.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

// More OOP - conversal.ui.Ui: deals with interactions with the user
public class Ui {

    // Fields
    protected Scanner scanner;
    protected final static String name = "conversal.Conversal";
    protected final static String greeting = "What can I do for you?";
    protected final static String exit = "Bye! Hope to see you again!";
    protected final static String instructionBye = "To close chatbot, enter: bye.";
    protected final static String instructionList = "To display tasks list, enter: list";
    protected final static String instructionMark =
            "To mark task as Complete, enter: mark (task no.)";
    protected final static String instructionUnmark =
            "To mark task as Incomplete, enter: unmark (task no.)";
    protected final static String instructionDelete =
            "To delete a task: delete (task no.)";
    protected final static String instructionTodo =
            "To add conversal.task.Todo task, enter: todo (task)";
    protected final static String instructionDeadline =
            "To add conversal.task.Deadline task, enter: deadline (task) /by (date in YYYY-MM-DD format)";
    protected final static String instructionEvent =
            "To add conversal.task.Event task, enter: event (task) /from (start) /to (end)";

    // Constructor
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    // Method 1: read user's input
    public String readInput() {
        System.out.print("User: ");
        return scanner.nextLine();
    }

    // Method 2: print welcome message and instructions
    public void welcomeMessage() {
        System.out.println("Hello! I'm " + name + ".");
        System.out.println(greeting + "\n");
        System.out.println("Instructions: ");
        System.out.println("> " + instructionBye);
        System.out.println("> " + instructionList);
        System.out.println("> " + instructionMark);
        System.out.println("> " + instructionUnmark);
        System.out.println("> " + instructionDelete);
        System.out.println("> " + instructionTodo);
        System.out.println("> " + instructionDeadline);
        System.out.println("> " + instructionEvent);
        System.out.println();
    }

    // Method 3: print exit message
    public void exitMessage() {
        System.out.println("\n" + exit);
    }

    // Method 4: print message when task is added
    public void addMessage(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.\n");
    }

    // Method 5: show list
    public void showList(ArrayList<Task> tasks) {
        System.out.println("\nHere is your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            String taskString = tasks.get(i).toString();
            System.out.println((i + 1) + ". " + taskString);
        }
        System.out.println();
    }

    // Method 6: acknowledge action (marking and unmarking)
    public void acknowledge(Task task, boolean isComplete) {
        if (isComplete) {
            System.out.println("Nice! I've marked this task as complete:");
        } else {
            System.out.println("OK! I've marked this task as incomplete:");
        }
        System.out.println(task + "\n");
    }

    // Method 7: acknowledge deletion
    public void deleteMessage(int size, String name) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + name);
        System.out.println("Now you have " + size + " tasks in the list.\n");
    }

    // Method 8: print error
    public void printError(String message) {
        System.out.println(message + "\n");
    }

    // Method 9: close scanner (new)
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
