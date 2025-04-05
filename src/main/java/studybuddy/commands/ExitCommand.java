package studybuddy.commands;

import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.storage.StorageManager;

public class ExitCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            exit
                Exits the program.;""";

    public ExitCommand() {
        super("");
    }

    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        if(!ui.isUserConfirm("Are you sure you want to exit?")) {
            return "Exit Cancelled";
        }
        storage.saveCurrentPlan();
        return "Bye"; // print proper message, put in Ui
    }

    public boolean isRunning() {
        return false;
    }
}
