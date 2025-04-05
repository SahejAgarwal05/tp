package studybuddy.commands;

import studybuddy.data.course.CourseList;
import studybuddy.data.storage.CommandHistoryManager;
import studybuddy.data.storage.StorageManager;

public class SummaryCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            summary
                Displays all executed and failed commands for the current session.""";

    public SummaryCommand() {
        super("");
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) {
        return CommandHistoryManager.getSummary();
    }
}

