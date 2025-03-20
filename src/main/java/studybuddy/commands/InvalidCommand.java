package studybuddy.commands;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super(""); // no parameter needed
    }

    @Override
    public String execute() {
        return "Invalid Command";
    }
}
