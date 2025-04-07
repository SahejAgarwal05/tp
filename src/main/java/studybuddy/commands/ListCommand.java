package studybuddy.commands;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.storage.StorageManager;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * The ListCommand class lists all courses in the user's plan grouped by Year and Semester.
 * Only semesters where courses have been added are shown, and the rest are summarized at the end.
 *
 * Usage: list
 * Example Output:
 * Y1S1 Courses
 * 1. CS1010 - Programming Methodology (4 MCs)
 *
 * No courses taken in the remaining years: Y1S2, Y2S1...
 */
public class ListCommand extends Command {

    // Static description used in help summary or command menu
    public static final String COMMAND_DESCRIPTION = """
            list
                Lists all courses in your plan, grouped by Year and Semester only where courses are taken.""";

    public ListCommand() {
        super(""); // no parameters needed
    }

    /**
     * Executes the command by displaying all added courses grouped by year and semester.
     * Also summarizes semesters where no courses were added.
     *
     * @param courses the user's course list
     * @param storage the storage manager (not used here)
     * @return a formatted string listing courses and a summary of empty semesters
     */
    @Override
    public String execute(CourseList courses, StorageManager storage) {
        if (courses.isEmpty()) {
            return "No courses added yet!";
        }

        StringBuilder sb = new StringBuilder();
        HashSet<String> activeSemesters = new HashSet<>();

        // First pass: identify and display semesters with courses
        for (int year = 1; year <= 4; year++) {
            for (int sem = 1; sem <= 2; sem++) {
                ArrayList<Course> filtered = new ArrayList<>();

                // Filter courses for this specific year and semester
                for (Course course : courses.getCourses()) {
                    if (course.getTakeInYear() == year && course.getTakeInSem() == sem) {
                        filtered.add(course);
                    }
                }

                // If there are courses, display them
                if (!filtered.isEmpty()) {
                    String label = "Y" + year + "S" + sem;
                    sb.append(label).append(" Courses\n");
                    int count = 1;
                    for (Course course : filtered) {
                        sb.append(count).append(". ").append(course.toString()).append("\n");
                        count++;
                    }
                    sb.append("\n");
                    activeSemesters.add(label); // Track semesters that had courses
                }
            }
        }

        // Second pass: list remaining semesters with no courses
        ArrayList<String> emptySemesters = new ArrayList<>();
        for (int year = 1; year <= 4; year++) {
            for (int sem = 1; sem <= 2; sem++) {
                String label = "Y" + year + "S" + sem;
                if (!activeSemesters.contains(label)) {
                    emptySemesters.add(label);
                }
            }
        }

        // Append summary of empty semesters if any
        if (!emptySemesters.isEmpty()) {
            sb.append("No courses taken in the remaining years: ");
            sb.append(String.join(", ", emptySemesters));
        }

        // Return the full formatted message
        return sb.toString().trim();
    }
}


