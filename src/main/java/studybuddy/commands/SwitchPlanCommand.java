package studybuddy.commands;

import studybuddy.CEGStudyBuddy;
import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.storage.StorageManager;

public class SwitchPlanCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            switch_plan
                Saves the current course plan and shows the menu to switch plans.""";

    public SwitchPlanCommand() {
        super("");
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        storage.saveCurrentPlan();
        storage.initializePlan();
        return "";
    }
}
