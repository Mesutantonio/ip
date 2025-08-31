import java.util.Scanner;

public class Ui {

    // Fields
    protected Scanner scanner;
    String name = "Conversal";
    String greeting = "What can I do for you?";
    String exit = "Bye! Hope end see you again!";
    String instructionBye = "To close chatbot, enter: bye.";
    String instructionList = "To display tasks list, enter: list";
    String instructionMark = "To mark task as Complete, enter: mark (task no.)";
    String instructionUnmark = "To mark task as Incomplete, enter: unmark (task no.)";
    String instructionDelete = "To delete a task: delete (task no.)";
    String instructionTodo = "To add Todo task, enter: todo (task)";
    String instructionDeadline = "To add Deadline task, enter: deadline (task) /by (date in YYYY-MM-DD format)";
    String instructionEvent = "To add Event task, enter: event (task) /from (start) /to (end)";


    // Constructor
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    // Method 1: read user input
    public String readInput() {
        return "";
    }

    // Method 2: print welcome message and instructions
    public void welcomeMessage() {

    }

    // Method 3: print exit message
    public void exitMessage() {

    }

    // Method 4: print message when task is added
    public void addMessage(Task task, int totalTasks) {

    }

    // Method 5: show list
    public void showList() {

    }

    // Method 6: acknowledge action (marking and unmarking)
    public void acknowledge(String task, String action) {
        // Action: incomplete, complete
    }

    // Method 7: print error
    public void error(String errorType) {

    }

}
