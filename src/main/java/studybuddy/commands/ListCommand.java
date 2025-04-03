package studybuddy.commands;

import studybuddy.data.course.CourseList;
import studybuddy.data.storage.StorageManager;

public class ListCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            list
                Lists all courses in your plan, grouped by Year and Semester.""";

    public ListCommand() {
        super(""); // no parameter needed
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) {
        if (courses.isEmpty()) {
            return "No courses added yet!";
        }
        return ui.printCourseList(courses);
    }
}
