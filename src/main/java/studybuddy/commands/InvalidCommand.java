package studybuddy.commands;

public class InvalidCommand extends Command {
    public InvalidCommand(String param) {
        super(param);
    }

    @Override
    public String execute() {
        return "Invalid Command";
    }
}
