import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "create")

public class Create {

    @Parameters(index = "0", description = "The name of the task")
    String name;

    @Option(names = "-status")
    String status;

    private Task task;

    public Integer call() throws Exception {
        System.out.println("Creating task " + name);
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new Create()).execute(args);
        System.exit(exitCode);
    }
}
