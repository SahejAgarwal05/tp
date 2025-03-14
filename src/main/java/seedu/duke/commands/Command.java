public abstract` class Command {
    private String commandName;
    private String description;
    public Command(String commandName, String description) {
        this.commandName = commandName;
        this.description = description;
    }
    public String getCommandName() {
        return commandName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String setCommandName(String commandName) {
        this.commandName = commandName;
    }
    public abstract String execute();
}