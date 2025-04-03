package studybuddy.commands;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.io.Ui;
import studybuddy.data.storage.StorageManager;

public class GradRequirementCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            gradreq
                Displays information about your graduation requirements based on your plan.""";

    public GradRequirementCommand() {
        super(""); // no params needed
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) {
        int completedMCs = 0;

        for (Course course : courses.getCourses()) {
            completedMCs += course.getMc();
        }

        return ui.printGradReq(completedMCs);
    }
}
