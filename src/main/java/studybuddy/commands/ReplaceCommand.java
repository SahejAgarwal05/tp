package studybuddy.commands;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.io.Parser;
import studybuddy.data.io.Ui;
import studybuddy.data.storage.StorageManager;

import java.util.ArrayList;

/**
 * The ReplaceCommand class replaces an existing course in the planner with a new one.
 * Usage: replace c/OLD_CODE c/NEW_CODE t/TITLE mc/MODULAR_CREDITS y/YEAR s/SEMESTER
 * Example: replace c/CS2040 c/CS2100 t/Computer Organization mc/4 y/2 s/1
 */

public class ReplaceCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            replace c/OLD_CODE c/NEW_CODE t/TITLE mc/MODULAR_CREDITS y/YEAR s/SEMESTER
                Replaces a course in your plan with a new course.""";

    private final Ui ui = new Ui();

    public ReplaceCommand(String param) {
        super(param);
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) {
        // Step 1: Check if the course list is empty
        if (courses == null || courses.isEmpty()) {
            return "No courses available to replace. Your plan is currently empty.";
        }

        // Step 2: Try splitting the param string into old code and new course info
        String[] parts = param.split(" ", 3); // replace c/OLD_CODE c/NEW_CODE ...

        if (parts.length < 3) {
            return "Invalid format. Please use:\n"
                    + "replace c/OLD_CODE c/NEW_CODE t/TITLE mc/MODULAR_CREDITS y/YEAR s/SEMESTER";
        }

        // Step 3: Extract course codes
        String oldCode = "";
        String newCode = "";

        try {
            oldCode = parts[0].substring(2).trim(); // remove "c/"
            newCode = parts[1].substring(2).trim(); // remove "c/"
        } catch (Exception e) {
            return "Please provide exactly two course codes: c/OLD_CODE c/NEW_CODE";
        }

        // Step 4: Extract new course details and build course
        Course newCourse;
        try {
            newCourse = Parser.parseCourse("c/" + newCode + " " + parts[2]);
        } catch (CEGStudyBuddyException e) {
            return "Missing or invalid input while adding new course details.\n"
                    + "Ensure correct format: t/TITLE mc/MODULAR_CREDITS y/YEAR s/SEMESTER";
        } catch (Exception e) {
            return "An unexpected error occurred while parsing the new course.";
        }

        // Step 5: Search and remove old course
        ArrayList<Course> courseList = courses.getCourses();
        boolean found = false;

        for (int i = 0; i < courseList.size(); i++) {
            Course c = courseList.get(i);
            if (c.getCode().equalsIgnoreCase(oldCode)) {
                courseList.remove(i);
                found = true;
                break;
            }
        }

        if (!found) {
            return ui.courseNotFoundToReplaceMessage(oldCode);
        }

        courses.add(newCourse);
        return ui.successfullyReplacedCourseMessage(oldCode, newCode);

    }
}


