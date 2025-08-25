import java.util.Scanner;

public class Conversal {

    public static void main(String[] args) {

        String name = "Conversal";
        String greeting = "What can I do for you?";
        String exit = "Bye! Hope to see you again!";
        String instruction = "To close chatbot, enter: bye. To display tasks list, enter: list.";

        System.out.println("Hello! I'm " + name + ".");
        System.out.println(greeting + "\n");
        System.out.println("Instructions: " + instruction + "\n");

        String[] tasks = new String[100];
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
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println();

            } else {
                tasks[tasksCounter] = input;
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
