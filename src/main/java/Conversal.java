import java.util.Scanner;

public class Conversal {

    public static void main(String[] args) {

        String name = "Conversal";
        String greeting = "What can I do for you?";
        String exit = "Bye! Hope to see you again!";
        String instruction = "Enter bye to close chatbot.";


        System.out.println("Hello! I'm " + name + ".");
        System.out.println(greeting + "\n");
        System.out.println("Instructions: " + instruction + "\n");

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print("User: ");
            input = scanner.nextLine();

            if (input.equals("bye")) {
                break;  // Exit when user types Bye
            }

            System.out.println("Conversal: " + input + "\n");  // Repeat user input
        }

        System.out.println("\n" + exit);
        scanner.close();
    }
}
