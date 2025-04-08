package studybuddy.commands;

import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.storage.StorageManager;

public class DeletePlanCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            delete_plan
                Shows the menu for the user to choose a plan to delete.""";

    public DeletePlanCommand() {
        super("");
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        storage.deletePlanWithSelection();
        return "";
    }
}
