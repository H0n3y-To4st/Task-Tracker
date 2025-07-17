import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class TaskTracker {
    private static final Logger LOGGER = Logger.getLogger(FileHandler.class.getName());

    public static void main(String[] args) {
        DateHandler dateHandler = new DateHandler();

        FileHandler fileHandler = new FileHandler();
        fileHandler.createTaskFile();

        if (args.length == 0) {
            System.out.println("Please provide a command. Try 'help' to see the command list.");
            return;
        }

        String command = args[0];

        switch (command) {
            case "add":
                if (args.length < 2) {
                    System.out.println("Usage: add <title>");
                    return;
                }
                String title = args[1];
                createTask(title);
                break;

            case "list":
                fileHandler.read();
                break;

            case "delete":
                if (args.length < 2) {
                    System.out.println("Usage: delete <taskID>");
                    return;
                }
                try {
                    int idToDelete = Integer.parseInt(args[1]);
                    fileHandler.delete(idToDelete);
                } catch (NumberFormatException e) {
                    System.out.println("Task ID must be a number.");
                }
                break;

            case "update":
                if (args.length < 4) {
                    System.out.println("Usage: update <taskID> <newTitle> <state>");
                    return;
                }
                try {
                    int idToUpdate = Integer.parseInt(args[1]);
                    String newTitle = args[2];
                    String newState = args[3];
                    Task updatedTask = new Task(idToUpdate, dateHandler.getLocalDate(), newTitle, newState);
                    fileHandler.update(updatedTask); // better to use the record like this
                } catch (NumberFormatException e) {
                    System.out.println("Task ID must be a number.");
                }
                break;

            case "help":
                System.out.println("Available commands:");
                System.out.println("  add <title>                  - Add a new task");
                System.out.println("  list                         - List all tasks");
                System.out.println("  delete <taskID>              - Delete a task by ID");
                System.out.println("  update <taskID> <title> <state> - Update a task's title and state");
                break;

            default:
                System.out.println("Unknown command: " + command);
                System.out.println("Try 'help' to see the command list.");
        }
    }

    private static void createTask(String title) {
        DateHandler dateHandler = new DateHandler();
        FileHandler fileHandler = new FileHandler();
        int id = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"))) {
            while (reader.readLine() != null) id++;
        } catch (IOException e) {
            LOGGER.severe("Error reading file for task ID assignment: " + e.getMessage());
        }

        Task task = new Task(id, dateHandler.getLocalDate(), title, "todo");
        fileHandler.write(task);
        LOGGER.info("Task added: " + title);

    }
}