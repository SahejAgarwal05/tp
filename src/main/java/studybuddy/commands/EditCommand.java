package studybuddy.commands;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.io.Parser;
import studybuddy.data.storage.StorageManager;
import studybuddy.common.Utils;

public class EditCommand extends Command {

    public static final String COMMAND_DESCRIPTION = """
            edit c/CODE [t/TITLE] [mc/MODULAR_CREDITS] [y/YEAR] [s/SEMESTER]
                Edits a course with the given parameters.""";

    public EditCommand(String param) {
        super(param);
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) {
        try {
            String[] paramParts = Parser.parseEdit(param);
            boolean found = false;

            if (paramParts[0] == null) {
                return ui.missingCodeErrorMessage();
            }

            // Validate MC if provided
            if (!paramParts[2].isEmpty()) {
                int mc = Integer.parseInt(paramParts[2]);
                if (!Utils.isValidMC(mc)) {
                    return "Error: Invalid MC value. Please provide a value between 1 and 12.";
                }
            }

            // Validate Year if provided
            if (!paramParts[3].isEmpty()) {
                int year = Integer.parseInt(paramParts[3]);
                if (!Utils.isValidYear(year)) {
                    return "Error: Invalid Year. Year must be between 1 and 4.";
                }
            }

            // Validate Semester if provided
            if (!paramParts[4].isEmpty()) {
                int sem = Integer.parseInt(paramParts[4]);
                if (!Utils.isValidSem(sem)) {
                    return "Error: Invalid Semester. Semester must be either 1 or 2.";
                }
            }

            for (Course course : courses.getCourses()) {
                if (course.getCode().equals(paramParts[0])) {
                    // Deep copy before applying changes
                    Course oldVersion = new Course(
                            course.getCode(),
                            course.getTitle(),
                            course.getMc(),
                            course.getTakeInYear(),
                            course.getTakeInSem()
                    );

                    // Check if user provided any actual changes
                    boolean hasChanges = !paramParts[1].isBlank() || !paramParts[2].isBlank()
                            || !paramParts[3].isBlank() || !paramParts[4].isBlank();

                    if (!hasChanges) {
                        return "No changes suggested or made.";
                    }
                    course = courses.setEditedParams(paramParts, course);
                    UndoManager.recordReplace(oldVersion, course);

                    ui.printCourse(course);
                    found = true;
                    break;
                }
            }

            if (found) {
                return ui.editSuccessMessage();
            }

            return ui.courseNotInPlannerMessage();

        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.indexOutOfBoundErrorMessage();
        } catch (NumberFormatException e) {
            return ui.parseIntErrorMessage();
        }
    }
}




