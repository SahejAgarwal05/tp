package studybuddy.commands;

public abstract class Command {
    protected String param;

    public Command(String param) {
        this.param = param;
    }

    /**
     * Abstract method to execute the command
     * @return
     */
    public abstract String execute();

    /**
     * Throws CEGStudyBuddyException of the message
     * @param message
     * @throws CEGStudyBuddyException
     */
    private void throwException (String message) throws CEGStudyBuddyException {
        throw new CEGStudyBuddyException(message);
    }
}
