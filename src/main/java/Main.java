import picocli.CommandLine;

import java.util.logging.Logger;


@CommandLine.Command(name = "task",
header = "The Task tracker cli",
description = {""})
class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String... args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }
}
