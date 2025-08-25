import java.util.Scanner;

public class Conversal {

    public static void main(String[] args) {

        String name = "Conversal";
        String greeting = "What can I do for you?";
        String exit = "Bye! Hope end see you again!";
        String instruction1 = "To close chatbot, enter: bye.";
        String instruction2 = "To display tasks list, enter: list";
        String instruction3 = "To mark task as Complete: mark (task no.)";
        String instruction4 = "To mark task as Incomplete: unmark (task no.)";

        System.out.println("Hello! I'm " + name + ".");
        System.out.println(greeting + "\n");
        System.out.println("Instructions: ");
        System.out.println("> " + instruction1);
        System.out.println("> " + instruction2);
        System.out.println("> " + instruction3);
        System.out.println("> " + instruction4);
        System.out.println();

        Task[] tasks = new Task[100];
        int tasksCounter = 0;
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print("User: ");
            input = scanner.nextLine();

            if (input.equals("bye")) {
                // Exit when user types: bye
                break;

            } else if (input.equals("list")) {
                // Print list when user types: list
                System.out.println("\nHere is your list of tasks:");
                for (int i = 0; i < tasksCounter; i++) {
                    String taskString = tasks[i].toString();
                    System.out.println((i + 1) + ". " + taskString);
                }
                System.out.println();

            } else if (input.startsWith("mark ")) {
                // Marking tasks as complete
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks[index].markAsComplete();
                String taskString = tasks[index].toString();

                System.out.println("Nice! I've marked this task as complete:");
                System.out.println(taskString + "\n");

            } else if (input.startsWith("unmark ")) {
                // Marking tasks as incomplete
                int index = Integer.parseInt(input.substring(7)) - 1;
                tasks[index].markAsIncomplete();
                String taskString = tasks[index].toString();

                System.out.println("OK! I've marked this task as incomplete:");
                System.out.println(taskString + "\n");

            } else {
                tasks[tasksCounter] = new Task(input);
                tasksCounter++;
                // Echo tasks added
                System.out.println("Task added: " + input + "\n");
            }

            // Repeat user input
            //System.out.println("Conversal: " + input + "\n");
        }

        System.out.println("\n" + exit);
        scanner.close();
    }
}
