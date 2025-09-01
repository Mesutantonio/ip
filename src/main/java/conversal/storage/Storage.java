package conversal.storage;

import conversal.task.Event;
import conversal.task.Task;
import conversal.task.Todo;
import conversal.task.Deadline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * Handles the saving and loading of tasks to and from hard disc
 *
 * The Storage class reads and writes task data to a text file.
 *
 */
public class Storage {
    private String filePath;

    /**
     * Creates a new Storage object that will load from and save to
     * the given file path.
     *
     * @param filePath the path to the file used for storage
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file into memory.
     *
     * If the file does not exist, an empty list is returned.
     * Each line in the file is expected to be in the format:
     *
     * T | isDone | description
     * D | isDone | description | yyyy-MM-dd
     * E | isDone | description | start-end
     *
     * @return an ArrayList of tasks reconstructed from the file
     */
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
                        newTask = new Event(parts[2], subParts[0], subParts[1]);
                        break;
                    default: // To-do task
                        newTask = new Todo(parts[2]);
                        break;
                }

                // Add completion status
                if (isDone) {
                    newTask.markAsComplete();
                }

                loadedTasks.add(newTask);
            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("Unable to load task: " + e.getMessage());
        }

        return loadedTasks;
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * The file will be created if it does not exist.
     * Each task will added in the following format:
     *
     * T | isDone | description
     * D | isDone | description | yyyy-MM-dd
     * E | isDone | description | start-end
     *
     * @param tasks the list of tasks to save
     */
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
                String type = "T"; // Default is Todo
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