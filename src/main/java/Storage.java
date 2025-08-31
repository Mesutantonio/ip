import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Loads data from hard disc (and stores it) and saves data to hard disc
public class Database {
    private String filePath;

    public Database(String filePath) {
        this.filePath = filePath;
    }

    // Loads data from hard disc and stores it
    public ArrayList<Task> load() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        // Implementation will go here in the next commit
        return loadedTasks;
    }

    // Saves data to hard disc
    public void save(ArrayList<Task> tasks) {
        // Implementation will go here in the next commit
    }
}