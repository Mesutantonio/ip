import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Conversal {

    public static void main(String[] args) {

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

        // Load storage
        Storage storage = new Storage("./data/tasks.txt");
        ArrayList<Task> tasks = storage.load();

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print("User: ");
            input = scanner.nextLine();

            try {
                if (input.equals("bye")) {
                    break;

                } else if (input.equals("list")) {
                    // Print list when user types: list
                    System.out.println("\nHere is your list of tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        String taskString = tasks.get(i).toString();
                        System.out.println((i + 1) + ". " + taskString);
                    }
                    System.out.println();

                } else if (input.startsWith("mark ")) {
                    // Error handling
                    if (input.length() <= 5) {
                        throw new ConversalException("Ah, I got it! " + instructionMark);
                    }
                    try {
                        // Attempt to mark task as complete
                        int index = Integer.parseInt(input.substring(5)) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new ConversalException("Enter a valid task number between 1 and " + tasks.size());
                        }

                        tasks.get(index).markAsComplete();
                        storage.save(tasks);
                        System.out.println("Nice! I've marked this task as complete:");
                        System.out.println(tasks.get(index) + "\n");

                    } catch (NumberFormatException e) {
                        throw new ConversalException("Ah, I got it! " + instructionMark);
                    }

                } else if (input.startsWith("unmark ")) {
                    // Error handling
                    if (input.length() <= 7) {
                        throw new ConversalException("Ah, I got it! " + instructionUnmark);
                    }
                    try {
                        // Attempt to mark task as incomplete
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new ConversalException("Enter a valid task number between 1 and " + tasks.size());
                        }

                        tasks.get(index).markAsIncomplete();
                        storage.save(tasks);
                        System.out.println("OK! I've marked this task as incomplete:");
                        System.out.println(tasks.get(index)+ "\n");

                    } catch (NumberFormatException e) {
                        throw new ConversalException("Ah, I got it! " + instructionUnmark);
                    }

                } else if (input.startsWith("delete ")) {
                    // Error handling
                    if (input.length() <= 7) {
                        throw new ConversalException("To delete a task, enter: delete (task number)");
                    }
                    try {
                        // Attempt to delete task
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new ConversalException("Enter a valid task number between 1 and " + tasks.size());
                        }

                        Task removedTask = tasks.remove(index);
                        storage.save(tasks);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("    " + removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");

                    } catch (NumberFormatException e) {
                        throw new ConversalException("Ah, I got it! " + instructionDelete);
                    }

                } else if (input.startsWith("todo ")) {
                    // Error handling
                    if (input.length() <= 5) {
                        throw new ConversalException("Ah, I got it! " + instructionTodo);
                    }

                    // Add a to-do task to list
                    String description = input.substring(5);

                    // Add to array and print message
                    tasks.add(new Todo(description));
                    storage.save(tasks);
                    addMessage(tasks.get(tasks.size() - 1), tasks.size());

                } else if (input.startsWith("deadline ")) {
                    // Error handling
                    if (input.length() <= 9) {
                        throw new ConversalException("Ah, I got it! " + instructionDeadline);
                    }
                    if (!input.contains(" /by ")) {
                        throw new ConversalException("Ah, I got it! " + instructionDeadline);
                    }

                    // Add a deadline task to list with error handling
                    String[] info = input.substring(9).split(" /by ");
                    if (info.length < 2 || info[0].trim().isEmpty() || info[1].trim().isEmpty()) {
                        throw new ConversalException("Ah, I got it! " + instructionDeadline);
                    }

                    // Add to array and print message
                    try {

                        // Convert String to LocalDate object
                        LocalDate dueDate = LocalDate.parse(info[1].trim());
                        tasks.add(new Deadline(info[0], dueDate));
                        storage.save(tasks);
                        addMessage(tasks.get(tasks.size() - 1), tasks.size());

                    } catch (Exception e) {
                        throw new ConversalException("Ah, I got it! " + instructionDeadline);
                    }

                } else if (input.startsWith("event ")) {
                    // Error handling
                    if (input.length() <= 6) {
                        throw new ConversalException("Ah, I got it! " + instructionEvent);
                    }
                    if (!input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new ConversalException("Ah, I got it! " + instructionEvent);
                    }

                    // Add an event task to list with error handling
                    String[] info = input.substring(6).split(" /from | /to ");
                    if (info.length < 3 || info[0].trim().isEmpty() || info[1].trim().isEmpty()
                            || info[2].trim().isEmpty()) {
                        throw new ConversalException("Ah, I got it! " + instructionEvent);
                    }

                    // Add to array and print message
                    tasks.add(new Event(info[0], info[1], info[2]));
                    storage.save(tasks);
                    addMessage(tasks.get(tasks.size() - 1), tasks.size());

                } else {
                    // Error handling: Invalid command
                    throw new ConversalException("I can't seem to locate the issue, please try again!");
                }

            } catch (ConversalException e) {
                System.out.println(e + "\n");
            }
        }

        System.out.println("\n" + exit);
        scanner.close();
    }

    // Helper function #1: Print message when task is added
    private static void addMessage(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.\n");
    }
}
