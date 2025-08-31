import java.time.LocalDate;

public class Conversal {

    public static void main(String[] args) {
        Ui ui = new Ui();   // Create new user interface object
        Storage storage = new Storage("./data/tasks.txt");  // Create Storage object
        TaskList tasks = new TaskList(storage.load());  // Create new TaskList object with arraylist in storage
        ui.welcomeMessage();    // Print welcome message

        while (true) {

            String input = ui.readInput();  // Read user input

            try {
                Command cmd = Parser.parse(input);      // Parse input
                cmd.execute(tasks, storage, ui);    // Execute command

                if (cmd.isExit()) {
                    break;      // Exit from while loop
                }

            } catch (ConversalException e) {
                ui.printError(e.toString());    // Print error message
            }
        }

        ui.exitMessage();   // print exit message
        ui.close();     // Close scanner
    }
}