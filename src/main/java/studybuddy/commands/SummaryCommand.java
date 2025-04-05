package studybuddy.commands;

import studybuddy.data.course.CourseList;
import studybuddy.data.storage.CommandHistoryManager;
import studybuddy.data.storage.StorageManager;

/**
 * Represents the summary command that displays all commands executed
 * during the current session (excluding summary commands themselves).
 */
public class SummaryCommand extends Command {

    // Description used in help menu or command list output
    public static final String COMMAND_DESCRIPTION = """
            summary
                Displays all executed and failed commands for the current session.""";

    /**
     * Constructor for SummaryCommand.
     * No parameters are required.
     */
    public SummaryCommand() {
        super("");
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) {
        return CommandHistoryManager.getSummary();
    }
}


