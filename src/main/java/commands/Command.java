package commands;

public abstract class Command {
    private String commandName;
    public Command(String commandName) {
        this.commandName = commandName;
    }
    public String getCommandName() {
        return commandName;
    }
    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }
    public abstract String execute(String[] args);
}
