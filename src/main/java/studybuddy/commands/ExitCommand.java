package studybuddy.commands;

public class ExitCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
    exit
        Exits the program.;""";

    public ExitCommand() {
        super("");
    }

    public String execute() {
        return "Bye";
    }

    public boolean isRunning() {
        return false;
    }
}
