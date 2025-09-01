package conversal;

import conversal.ui.Ui;
import conversal.storage.Storage;
import conversal.task.TaskList;
import conversal.command.Command;
import conversal.parser.Parser;
import conversal.exception.ConversalException;

/**
 * Entry point of the Conversal chatbot application.
 *
 * Initialises core components (ui, storage, task list) and runs
 * the main input-processing loop until the user exits.
 *
 */
public class Conversal {

    // Fields
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    // Constructor
    public Conversal(String filePath) {
        ui = new Ui();    // Create new user interface object
        storage = new Storage(filePath);   // Create conversal.storage.Storage object
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the chatbot
     */
    public void run() {
        ui.welcomeMessage();    // Print Welcome message

        while (true) {
            String input = ui.readInput();  // Read user input

            try {
                Command cmd = Parser.parse(input);   // Parse input
                cmd.execute(tasks, storage, ui);     // Execute command

                if (cmd.isExit()) {
                    break;    // Exit from while loop
                }

            } catch (ConversalException e) {
                ui.printError(e.toString());   // Print error message
            }
        }

        ui.exitMessage();   // Print exit message
        ui.close();         // Close scanner
    }

    // Main entry point
    public static void main(String[] args) {
        new Conversal("./data/tasks.txt").run();
    }
}