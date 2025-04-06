package studybuddy.commands;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.storage.StorageManager;

import java.util.ArrayList;
import java.util.HashSet;

public class ListCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            list
                Lists all courses in your plan, grouped by Year and Semester only where courses are taken.""";

    public ListCommand() {
        super(""); // no parameter needed
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) {
        if (courses.isEmpty()) {
            return "No courses added yet!";
        }

        StringBuilder sb = new StringBuilder();
        HashSet<String> activeSemesters = new HashSet<>();

        // First pass: display courses taken per active semester
        for (int year = 1; year <= 4; year++) {
            for (int sem = 1; sem <= 2; sem++) {
                ArrayList<Course> filtered = new ArrayList<>();
                for (Course course : courses.getCourses()) {
                    if (course.getTakeInYear() == year && course.getTakeInSem() == sem) {
                        filtered.add(course);
                    }
                }

                if (!filtered.isEmpty()) {
                    String label = "Y" + year + "S" + sem;
                    sb.append(label).append(" Courses\n");
                    int count = 1;
                    for (Course course : filtered) {
                        sb.append(count).append(". ")
                                .append(course.getCode()).append(" - ").append(course.getTitle())
                                .append(" (").append(course.getMc()).append(" MCs)\n");
                        count++;
                    }
                    sb.append("\n");
                    activeSemesters.add(label);
                }
            }
        }

        // Second pass: summarize empty semesters
        ArrayList<String> emptySemesters = new ArrayList<>();
        for (int year = 1; year <= 4; year++) {
            for (int sem = 1; sem <= 2; sem++) {
                String label = "Y" + year + "S" + sem;
                if (!activeSemesters.contains(label)) {
                    emptySemesters.add(label);
                }
            }
        }

        if (!emptySemesters.isEmpty()) {
            sb.append("No courses taken in the remaining years: ");
            sb.append(String.join(", ", emptySemesters));
        }

        return sb.toString().trim();
    }
}

