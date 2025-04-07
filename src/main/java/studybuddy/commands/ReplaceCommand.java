package studybuddy.commands;

import studybuddy.common.Utils;
import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.course.UndoManager;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.io.Parser;
import studybuddy.data.io.Ui;
import studybuddy.data.storage.StorageManager;

/**
 * Replaces an existing course with a new one, preserving its time and MC attributes.
 * Format: replace c/OLD_CODE c/NEW_CODE t/TITLE mc/VALUE y/YEAR s/SEM
 */
public class ReplaceCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
        replace c/OLD_CODE c/NEW_CODE t/TITLE mc/MODULAR_CREDITS y/YEAR s/SEMESTER
            Replaces an existing course with a new one.""";

    private final Ui ui = new Ui();

    public ReplaceCommand(String param) {
        super(param);
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        String[] codes = Parser.parseReplace(param);
        String oldCode = codes[0].trim();
        String newCode = codes[1].trim();

        // Validate course codes
        if (oldCode.isEmpty() || newCode.isEmpty()) {
            throw new CEGStudyBuddyException("Course code(s) cannot be empty."
                    + "Please use format: c/OLD_CODE c/NEW_CODE");
        }
        if (!oldCode.matches("^[A-Z]{2,3}\\d{4}[A-Z]?$") || !newCode.matches("^[A-Z]{2,3}\\d{4}[A-Z]?$")) {
            throw new CEGStudyBuddyException("Invalid course code format. Expected format: CS2040, EE2026, CG2111A");
        }

        // Split and validate remaining parameters
        String[] paramParts = param.split("t/|mc/|y/|s/");
        if (paramParts.length < 5) {
            throw new CEGStudyBuddyException("Missing required fields! Please use format: "
                    + "replace c/OLD c/NEW t/TITLE mc/VALUE y/YEAR s/SEM");
        }

        String title = paramParts[1].trim();
        String mcStr = paramParts[2].trim();
        String yearStr = paramParts[3].trim();
        String semStr = paramParts[4].trim();

        if (title.isEmpty() || mcStr.isEmpty() || yearStr.isEmpty() || semStr.isEmpty()) {
            throw new CEGStudyBuddyException("One or more fields are empty."
                    + "Complete all: t/TITLE mc/VALUE y/YEAR s/SEM");
        }

        int mc; 
        int year;
        int sem;

        try {
            mc = Integer.parseInt(mcStr);
            year = Integer.parseInt(yearStr);
            sem = Integer.parseInt(semStr);
        } catch (NumberFormatException e) {
            throw new CEGStudyBuddyException("Invalid number format."
                    + "Please enter numeric values for MC, year, and semester.");
        }

        if (!Utils.isValidMC(mc)) {
            throw new CEGStudyBuddyException("Invalid MC value! It should be between 1 and 12.");
        }
        if (!Utils.isValidYear(year)) {
            throw new CEGStudyBuddyException("Invalid Year value! It should be between 1 and 4.");
        }
        if (!Utils.isValidSem(sem)) {
            throw new CEGStudyBuddyException("Invalid Semester value! It should be either 1 or 2.");
        }

        // Find and remove the old course
        Course oldCourse = null;
        for (Course course : courses.getCourses()) {
            if (course.getCode().equalsIgnoreCase(oldCode)) {
                oldCourse = course;
                break;
            }
        }

        if (oldCourse == null) {
            return ui.showCourseNotFoundInReplaceMessage(oldCode);
        }

        Course newCourse = new Course(newCode, title, mc, year, sem);

        // Disallow replace the same course with the same title
        if (oldCourse.getCode().equalsIgnoreCase(newCourse.getCode())) {
            return ui.replaceDuplicateMessage();
        }

        courses.deleteCourseByCode(oldCode);
        courses.addCourse(newCourse);
        UndoManager.recordReplace(oldCourse, newCourse);

        return ui.showCourseReplacedMessage(oldCode, newCode);
    }
}



