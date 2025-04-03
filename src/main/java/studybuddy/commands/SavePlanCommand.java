package studybuddy.commands;

import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.storage.StorageManager;

public class SavePlanCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            save
                Saves the current course plan.""";

    public SavePlanCommand() {
        super("");
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        assert storage != null;
        return storage.saveCurrentPlan();
    }
}
