package studybuddy.data.storage;

import java.util.ArrayList;

/**
 * Manages the history of commands executed during a session.
 * Stores each command as a full string and provides access
 * to the list for display or reset.
 */
public class CommandHistoryManager {
    // List to store the full text of executed commands
    private static final ArrayList<String> commandHistory = new ArrayList<>();

    /**
     * Records a command entered by the user, unless it's the 'summary' command.
     *
     * @param fullCommand The full text of the command entered by the user.
     */
    public static void record(String fullCommand) {
        // Do not record the summary command itself to avoid cluttering the output
        if (!fullCommand.toLowerCase().startsWith("summary")) {
            commandHistory.add(fullCommand);
        }
    }

    /**
     * Returns a formatted string containing all recorded commands.
     *
     * @return A numbered summary of commands executed during the session.
     */
    public static String getSummary() {
        if (commandHistory.isEmpty()) {
            return "No commands were executed in this session.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("---- Command Summary ----\n");

        int count = 1;
        for (String cmd : commandHistory) {
            sb.append(count++).append(". ").append(cmd).append("\n");
        }

        return sb.toString().trim(); // Remove the trailing newline
    }

    /**
     * Clears all stored command history. Useful for testing or session resets.
     */
    public static void clear() {
        commandHistory.clear();
    }
}



