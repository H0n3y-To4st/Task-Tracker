package misc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileHandler {
    private static final String FILE_PATH = "tasks.txt";
    private static final Logger LOGGER = Logger.getLogger(FileHandler.class.getName());


    DateHandler dateHandler = new DateHandler();

    public void createTaskFile() {
        File file = new File(FILE_PATH); //reference to file, not its creation
        if (!file.exists()) {
            try {
                file.createNewFile();
                LOGGER.info("Task file created: " + FILE_PATH);
            } catch (IOException e) {
                LOGGER.severe("Error creating task file: " + e.getMessage());
            }
        }
    }

    // lets use buffered for now
    public void write() {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
//            String taskLine = String.format("%s,%s,%s,%s", task.ID(), task.dateCreated(), task.title(), task.state()); //must be cleaner way to do this?
//            writer.write(taskLine);
//            writer.newLine();
//        } catch (IOException e) {
//            LOGGER.severe("Error creating task file: " + e.getMessage());
//        }
    }

    public void read() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LOGGER.info("Read line: " + line);
                System.out.println(line);
            }
        } catch (IOException e) {
            LOGGER.severe("Error reading tasks: " + e.getMessage());
        }
    }

    //TODO: need to deal with empty lines first
    public List<String> delete(int idToDelete) {
        List<String> updatedList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                // Skip the line with the matching ID (delete it)
                if (line.contains("\"ID\":" + idToDelete + ",")) {
                    LOGGER.info("Deleted line with ID: " + idToDelete);
                    continue;
                }

                updatedList.add(line);
            }
        } catch (IOException e) {
            LOGGER.severe("Error reading tasks: " + e.getMessage());
        }

        return updatedList;
    }
}
