import java.time.LocalDate;
import java.util.ArrayList;

public class Conversal {

    public static void main(String[] args) {

        Ui ui = new Ui();
        ui.welcomeMessage();

        // Load storage
        Storage storage = new Storage("./data/tasks.txt");
        ArrayList<Task> tasks = storage.load();

        String input;

        while (true) {
            input = ui.readInput();

            try {
                if (input.equals("bye")) {
                    break;

                } else if (input.equals("list")) {
                    // Print list when user types: list
                    ui.showList(tasks);

                } else if (input.startsWith("mark ")) {
                    // Error handling
                    if (input.length() <= 5) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionMark());
                    }
                    try {
                        // Attempt to mark task as complete
                        int index = Integer.parseInt(input.substring(5)) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new ConversalException("Enter a valid task number between 1 and " + tasks.size());
                        }

                        tasks.get(index).markAsComplete();
                        storage.save(tasks);
                        ui.acknowledge(tasks.get(index), true);

                    } catch (NumberFormatException e) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionMark());
                    }

                } else if (input.startsWith("unmark ")) {
                    // Error handling
                    if (input.length() <= 7) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionUnmark());
                    }
                    try {
                        // Attempt to mark task as incomplete
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new ConversalException("Enter a valid task number between 1 and " + tasks.size());
                        }

                        tasks.get(index).markAsIncomplete();
                        storage.save(tasks);
                        ui.acknowledge(tasks.get(index), false);

                    } catch (NumberFormatException e) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionUnmark());
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
                        ui.deleteMessage(tasks.size(), removedTask.toString());

                    } catch (NumberFormatException e) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionDelete());
                    }

                } else if (input.startsWith("todo ")) {
                    // Error handling
                    if (input.length() <= 5) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionTodo());
                    }

                    // Add a to-do task to list
                    String description = input.substring(5);

                    // Add to array and print message
                    Task t = new Todo(description);
                    tasks.add(t);
                    storage.save(tasks);
                    ui.addMessage(t, tasks.size());

                } else if (input.startsWith("deadline ")) {
                    // Error handling
                    if (input.length() <= 9) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionDeadline());
                    }
                    if (!input.contains(" /by ")) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionDeadline());
                    }

                    // Add a deadline task to list with error handling
                    String[] info = input.substring(9).split(" /by ");
                    if (info.length < 2 || info[0].trim().isEmpty() || info[1].trim().isEmpty()) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionDeadline());
                    }

                    try {
                        // Convert String to LocalDate object
                        LocalDate dueDate = LocalDate.parse(info[1].trim());
                        Task t = new Deadline(info[0], dueDate);
                        tasks.add(t);
                        storage.save(tasks);
                        ui.addMessage(t, tasks.size());

                    } catch (Exception e) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionDeadline());
                    }

                } else if (input.startsWith("event ")) {
                    // Error handling
                    if (input.length() <= 6) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionEvent());
                    }
                    if (!input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionEvent());
                    }

                    // Add an event task to list with error handling
                    String[] info = input.substring(6).split(" /from | /to ");
                    if (info.length < 3 || info[0].trim().isEmpty() || info[1].trim().isEmpty()
                            || info[2].trim().isEmpty()) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionEvent());
                    }

                    // Add to array and print message
                    Task t = new Event(info[0], info[1], info[2]);
                    tasks.add(t);
                    storage.save(tasks);
                    ui.addMessage(t, tasks.size());

                } else {
                    // Error handling: Invalid command
                    throw new ConversalException("I can't seem to locate the issue, please try again!");
                }

            } catch (ConversalException e) {
                // Prints error message
                ui.printError(e.toString());
            }
        }

        ui.close();
        ui.exitMessage();

    }
}