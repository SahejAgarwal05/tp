package studybuddy.commands;

import studybuddy.CEGStudyBuddy;
import studybuddy.common.Utils;
import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.io.Parser;
import studybuddy.data.io.Ui;
import studybuddy.data.storage.StorageManager;

/**
 * The ReplaceCommand class replaces an existing course in the planner with a new one.
 * Usage: replace c/OLD_CODE c/NEW_CODE t/TITLE mc/MODULAR_CREDITS y/YEAR s/SEMESTER
 * Example: replace c/CS2040 c/CS2100 t/Computer Organization mc/4 y/2 s/1
 */

public class ReplaceCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
        replace c/OLD_CODE NEW_CODE mc/MODULAR_CREDITS y/YEAR s/SEMESTER t/TITLE
            Replaces an existing course with a new one, preserving year/sem/mc."""; // help reference

    private final Ui ui = new Ui(); // UI for output messages

    public ReplaceCommand(String param) {
        super(param);
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        // Step 1: Parse old and new codes using Parser utility
        String[] codes = Parser.parseReplace(param);
        String oldCode = codes[0];
        String newCode = codes[1];

        // Step 2: Extract title, mc, y, s from param using split logic
        String[] paramParts = param.split("t/|mc/|y/|s/");
        if (paramParts.length < 4) {
            throw new CEGStudyBuddyException("Missing required fields! Please use format: "
                    + "replace c/OLD c/NEW t/TITLE mc/VALUE y/YEAR s/SEM");
        }

        // Extract values and sanitize
        String title = paramParts[1].trim();
        int mc;
        int year;
        int sem;

        try {
            mc = Integer.parseInt(paramParts[2].trim());
            year = Integer.parseInt(paramParts[3].trim());
            sem = Integer.parseInt(paramParts[4].trim());
        } catch (Exception e) {
            throw new CEGStudyBuddyException("Invalid input format! Make sure mc/y/s are numbers. Please use format: replace c/OLD c/NEW t/TITLE mc/VALUE y/YEAR s/SEM");
        }

        // Validate extracted values
        if (!Utils.isValidMC(mc) || !Utils.isValidYear(year) || !Utils.isValidSem(sem)) {
            throw new CEGStudyBuddyException("Invalid MC/Year/Semester values! Format: replace c/OLD c/NEW t/TITLE mc/VALUE y/YEAR s/SEM");
        }

        // Step 3: Search for old course and remove
        boolean found = false;
        for (int i = 0; i < courses.getCourses().size(); i++) {
            Course course = courses.getCourses().get(i);
            if (course.getCode().equalsIgnoreCase(oldCode)) {
                courses.getCourses().remove(i);
                found = true;
                break;
            }
        }

        if (!found) {
            return ui.showCourseNotFoundInReplaceMessage(oldCode);
        }

        // Step 4: Create and add the new course
        Course newCourse = new Course(newCode, title, mc, year, sem);
        courses.addCourse(newCourse);

        // Step 5: Return success message
        return ui.showCourseReplacedMessage(oldCode, newCode);
    }
}



