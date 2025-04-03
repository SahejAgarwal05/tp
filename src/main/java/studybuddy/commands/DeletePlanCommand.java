package studybuddy.commands;

import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.storage.StorageManager;

public class DeletePlanCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            save
                Saves the current course plan.""";

    public DeletePlanCommand() {
        super("");
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        storage.deletePlanWithSelection();
        storage.initializePlan();
        return "Deleted"; // move to Ui
    }
}
