package studybuddy.commands;

import java.util.ArrayList;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.io.Parser;
import studybuddy.data.storage.StorageManager;

/**
 * The FindCommand class allows the user to search for a course by its code
 * from the existing course plan.
 * Format: find c/CODE
 */
public class FindCommand extends Command {

    // Description of the command for help or usage messages
    public static final String COMMAND_DESCRIPTION = """
            find c/CODE
                Finds the course with the given code in your plan.""";

    public FindCommand(String param) {
        super(param);
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        // Parse the input to extract parts (e.g., ["find", "CS2040"])
        String[] parts = Parser.parseFind(param);

        // Ensure a course code was provided after "c/"
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new CEGStudyBuddyException("Missing course code. Use: find c/CODE");
        }

        // Normalize input to uppercase for case-insensitive comparison
        String targetCode = parts[1].trim().toUpperCase();

        // Validate the course code format using regex (e.g., CS2040, EE2026, CG2111A)
        if (!targetCode.matches("^[A-Z]{2,3}\\d{4}[A-Z]?$")) {
            throw new CEGStudyBuddyException("Invalid course code format. Expected: CS2040, EE2026, CG2111A, etc.");
        }

        // Retrieve all courses in the current planner
        ArrayList<Course> courseList = courses.getCourses();

        // Use UI helper to print matching course or a message if not found
        return ui.printFindCourse(courseList, targetCode);
    }
}


