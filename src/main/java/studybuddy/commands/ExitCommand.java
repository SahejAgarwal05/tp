package studybuddy.commands;

import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.storage.StorageManager;

public class ExitCommand extends Command {
    private boolean isRunning = true;
    public static final String COMMAND_DESCRIPTION = """
            exit
                Exits the program.;""";

    public ExitCommand() {
        super("");
        this.isRunning = true;
    }

    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        if(!ui.isUserConfirm("Are you sure you want to exit?")) {
            isRunning = true;
            return "Exit Cancelled";
        }
        storage.saveCurrentPlan();
        isRunning = false;
        return "Bye"; // print proper message, put in Ui
    }

    public boolean isRunning() {
        return isRunning;
    }
}
