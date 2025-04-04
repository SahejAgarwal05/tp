package studybuddy.commands;

import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.storage.StorageManager;

public class RenamePlanCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            rename_plan
                helps renaming the current plan""";
    public RenamePlanCommand() {
        super("rename");
    }
    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        assert storage != null;
        storage.renamePlan();
        return "";
    }
}
