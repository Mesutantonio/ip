import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

// More OOP - Storage: Deals with loading tasks from the file and saving tasks in the file
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Method 1: Loads data from hard disc and stores it in an arraylist to be returned
    public ArrayList<Task> load() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        // If file does not exist, return empty list
        if (!file.exists()) {
            return loadedTasks;
        }

        try {
            Scanner scanner = new Scanner(file);

            // For each data entry
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");

                boolean isDone = parts[1].equals("1");
                Task newTask;

                // Create correct task
                switch (parts[0]) {
                    case "D": // Deadline task
                        newTask = new Deadline(parts[2], LocalDate.parse(parts[3]));
                        break;
                    case "E": // Event task
                        String[] subParts = parts[3].split("-", 2);
                        newTask = new Event(parts[2], subParts[0], subParts[1]); // parts[3] is the 'at' string
                        break;
                    default: // Normal task
                        newTask = new Todo(parts[2]);
                        break;
                }

                // Add completion status
                if (isDone) {
                    newTask.markAsComplete();
                }

                // Add task to the list
                loadedTasks.add(newTask);
            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("Unable to load task: " + e.getMessage());
        }

        return loadedTasks;
    }

    // Method 2: Saves data to hard disc
    public void save(ArrayList<Task> tasks) {
        try {
            File file = new File(this.filePath);

            // Create the new directory if directory does not exist
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            // Write to created file
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {

                String type = "T"; // Default is Task
                String description = task.getDescription();
                String isDone = task.isDone() ? "1" : "0";
                String info = "";

                if (task instanceof Deadline) {
                    type = "D";
                    info = " | " + ((Deadline) task).getDueDate().toString();
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