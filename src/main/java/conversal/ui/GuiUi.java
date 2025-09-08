package conversal.ui;

import conversal.task.Task;
import java.util.ArrayList;

public class GuiUi extends Ui {
    private final StringBuilder out = new StringBuilder();
    private void line(String s) { out.append(s).append(System.lineSeparator()); }
    public String flush() { String s = out.toString(); out.setLength(0); return s; }

    @Override public void welcomeMessage() { line("Hello! I'm Conversal."); }
    @Override public void exitMessage() { line("Bye! Hope to see you again!"); }

    @Override public void addMessage(Task task, int totalTasks) {
        line("Got it. I've added this task:");
        line(task.toString());
        line("Now you have " + totalTasks + " tasks in the list.");
    }

    @Override public void showList(ArrayList<Task> tasks) {
        line("Here is your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            line((i + 1) + ". " + tasks.get(i));
        }
    }

    @Override public void acknowledge(Task task, boolean isComplete) {
        line(isComplete ? "Nice! I've marked this task as complete:" :
                "OK! I've marked this task as incomplete:");
        line(task.toString());
    }

    @Override public void deleteMessage(int size, String name) {
        line("Noted. I've removed this task:");
        line("    " + name);
        line("Now you have " + size + " tasks in the list.");
    }

    @Override public void showFound(ArrayList<Task> matches) {
        line("Here are the matching tasks in your list:");
        for (int i = 0; i < matches.size(); i++) {
            line((i + 1) + ". " + matches.get(i));
        }
    }

    @Override public void printError(String message) { line(message); }
}
