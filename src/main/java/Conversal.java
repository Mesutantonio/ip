// Main file
public class Conversal {

    // Fields
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    // Constructor
    public Conversal(String filePath) {
        ui = new Ui();    // Create new user interface object
        storage = new Storage(filePath);   // Create Storage object
        tasks = new TaskList(storage.load());
    }

    // Run the chatbot
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