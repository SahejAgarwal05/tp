package studybuddy.commands;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.storage.StorageManager;

/**
 * The GradRequirementCommand calculates the total Modular Credits (MCs)
 * the user has accumulated and compares it to the graduation requirement.
 *
 * Usage: gradreq
 * Displays current MCs completed, how many are needed to graduate, and motivation message.
 */
public class GradRequirementCommand extends Command {

    // Static description used for help or command summary
    public static final String COMMAND_DESCRIPTION = """
            gradreq
                Displays information about your graduation requirements based on your plan.""";

    public GradRequirementCommand() {
        super("");
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) {
        int completedMCs = 0;

        // Iterate through the user's course list and sum up all modular credits
        for (Course course : courses.getCourses()) {
            completedMCs += course.getMc();
        }

        // Use UI helper to display formatted graduation requirement message
        return ui.printGradReq(completedMCs);
    }
}

