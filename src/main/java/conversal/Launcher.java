package conversal;

/**
 * Launcher class to avoid the classpath issue when launching
 * a JavaFX application in certain environments.
 *
 * Delegates to {@link MainApp}, which contains the actual JavaFX application.
 *
 */
public class Launcher {

    /**
     * Entry point of the program.
     *
     * @param args command line arguments passed to the program
     */
    public static void main(String[] args) {
        MainApp.launch(MainApp.class, args);
    }
}
