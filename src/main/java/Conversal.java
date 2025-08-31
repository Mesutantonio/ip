import java.time.LocalDate;

public class Conversal {

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.welcomeMessage();

        Storage storage = new Storage("./data/tasks.txt");
        TaskList tasks = new TaskList(storage.load());

        while (true) {
            String input = ui.readInput();

            try {
                if (input.equals("bye")) {
                    break;

                } else if (input.equals("list")) {
                    ui.showList(tasks.getList());

                } else if (input.startsWith("mark ")) {
                    if (input.length() <= 5) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionMark());
                    }
                    try {
                        int index = Integer.parseInt(input.substring(5)) - 1;
                        tasks.markTaskComplete(index);
                        storage.save(tasks.getList());
                        ui.acknowledge(tasks.get(index), true);
                    } catch (NumberFormatException e) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionMark());
                    }

                } else if (input.startsWith("unmark ")) {
                    if (input.length() <= 7) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionUnmark());
                    }
                    try {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        tasks.markTaskIncomplete(index);
                        storage.save(tasks.getList());
                        ui.acknowledge(tasks.get(index), false);
                    } catch (NumberFormatException e) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionUnmark());
                    }

                } else if (input.startsWith("delete ")) {
                    if (input.length() <= 7) {
                        throw new ConversalException("To delete a task, enter: delete (task number)");
                    }
                    try {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        Task removedTask = tasks.deleteTask(index);
                        storage.save(tasks.getList());
                        ui.deleteMessage(tasks.size(), removedTask.toString());
                    } catch (NumberFormatException e) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionDelete());
                    }

                } else if (input.startsWith("todo ")) {
                    if (input.length() <= 5) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionTodo());
                    }
                    String description = input.substring(5);
                    Task t = new Todo(description);
                    tasks.addTask(t);
                    storage.save(tasks.getList());
                    ui.addMessage(t, tasks.size());

                } else if (input.startsWith("deadline ")) {
                    if (input.length() <= 9) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionDeadline());
                    }
                    if (!input.contains(" /by ")) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionDeadline());
                    }
                    String[] info = input.substring(9).split(" /by ");
                    if (info.length < 2 || info[0].trim().isEmpty() || info[1].trim().isEmpty()) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionDeadline());
                    }
                    try {
                        LocalDate dueDate = LocalDate.parse(info[1].trim());
                        Task t = new Deadline(info[0], dueDate);
                        tasks.addTask(t);
                        storage.save(tasks.getList());
                        ui.addMessage(t, tasks.size());
                    } catch (Exception e) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionDeadline());
                    }

                } else if (input.startsWith("event ")) {
                    if (input.length() <= 6) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionEvent());
                    }
                    if (!input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionEvent());
                    }
                    String[] info = input.substring(6).split(" /from | /to ");
                    if (info.length < 3 || info[0].trim().isEmpty() || info[1].trim().isEmpty()
                            || info[2].trim().isEmpty()) {
                        throw new ConversalException("Ah, I got it! " + ui.getInstructionEvent());
                    }
                    Task t = new Event(info[0], info[1], info[2]);
                    tasks.addTask(t);
                    storage.save(tasks.getList());
                    ui.addMessage(t, tasks.size());

                } else {
                    throw new ConversalException("I can't seem to locate the issue, please try again!");
                }

            } catch (ConversalException e) {
                ui.printError(e.toString());
            }
        }

        ui.exitMessage();
        ui.close();
    }
}