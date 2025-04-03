package studybuddy.commands;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.storage.StorageManager;

public class ListCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            list
                Lists all courses in your plan.""";

    public ListCommand() {
        super(""); // no parameter needed
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) {
        if (courses.isEmpty()) {
            return "No courses added yet!"; // move to Ui
        }

        // can move to Ui
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Course course : courses.getCourses()) {
            sb.append(count).append(". ")
                    .append(course.getCode()).append(" - ").append(course.getTitle())
                    .append(" (").append(course.getMc()).append(" MCs)").append("\n");
            count++;
        }
        return sb.toString().trim();
    }
}

