import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// Loads data from hard disc (and stores it) and saves data to hard disc
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Loads data from hard disc and stores it
    public ArrayList<Task> load() {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        return loadedTasks;
    }

    // Saves data to hard disc
    public void save(ArrayList<Task> tasks) {
        try {
            File file = new File(this.filePath);

            // Create the new directory if file does not exist
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            // Write to the file
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {

                String type = "T"; // Default is Task
                String description = task.getDescription();
                String isDone = task.isDone() ? "1" : "0";
                String info = "";

                if (task instanceof Deadline) {
                    type = "D";
                    info = " | " + ((Deadline) task).getDueDate();
                } else if (task instanceof Event) {
                    type = "E";
                    info = " | " + ((Event) task).getSchedule();
                }

                fw.write(type + " | " + isDone + " | " + description + info + System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("Unable to save task: " + e.getMessage());
        }
    }
}