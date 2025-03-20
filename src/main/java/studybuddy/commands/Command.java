package studybuddy.commands;

public abstract class Command {

    /**
     * Abstract method to execute the command
     * @param args
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

    /**
     * Default isExit command for most commands to not exit program\
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
