package studybuddy.commands;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.storage.StorageManager;

import java.util.ArrayList;

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

        StringBuilder sb = new StringBuilder();

        // Loop through all semesters: Y1S1 to Y4S2
        for (int year = 1; year <= 4; year++) {
            for (int sem = 1; sem <= 2; sem++) {
                sb.append("Y").append(year).append("S").append(sem).append(" Courses\n");

                // Filter courses taken in this year/sem
                ArrayList<Course> filtered = new ArrayList<>();
                for (Course course : courses.getCourses()) {
                    if (course.getTakeInYear() == year && course.getTakeInSem() == sem) {
                        filtered.add(course);
                    }
                }

                if (filtered.isEmpty()) {
                    sb.append("No courses taken!\n\n");
                } else {
                    int count = 1;
                    for (Course course : filtered) {
                        sb.append(count).append(". ")
                                .append(course.getCode()).append(" - ").append(course.getTitle())
                                .append(" (").append(course.getMc()).append(" MCs)").append("\n");
                        count++;
                    }
                    sb.append("\n");
                }
            }
        }

        return sb.toString().trim();
    }
}


