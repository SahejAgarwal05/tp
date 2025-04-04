package studybuddy.commands;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.io.Parser;
import studybuddy.data.storage.StorageManager;

public class WorkloadForCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            workload_for y/YEAR s/SEMESTER
                Displays the courses and the total workload for the given semester.""";

    public WorkloadForCommand(String params) {
        super(params);
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        int[] paramParts = Parser.parseWorkloadFor(param);
        int sem = paramParts[0];
        int year = paramParts[1];
        String output = "These are the courses you will be taking:";
        int totalWorkLoad = 0;
        int index = 1;
        for (Course course : courses.getCourses()) {
            if (course.getTakeInSem() == sem && course.getTakeInYear() == year) {
                totalWorkLoad += course.getMc();
                output = output + "\n" + index + "." + course.toString();
                index++;
            }
        }
        output = output + "\nTotal workload: " + totalWorkLoad;
        return output;
    }
}
