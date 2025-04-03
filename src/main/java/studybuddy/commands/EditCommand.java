package studybuddy.commands;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.io.Parser;
import studybuddy.data.storage.StorageManager;

public class EditCommand extends Command {

    public static final String COMMAND_DESCRIPTION = """
            edit c/CODE [t/TITLE] [mc/MODULAR_CREDITS] [y/YEAR] [s/SEMESTER]
                Edits a course with the given parameters.""";

    public EditCommand(String param) {
        super(param);
    }

    // move to studybuddy.common.Utils class
    public static boolean hasIdentifier(String str) {
        return str.startsWith("t/") || str.startsWith("c/") ||
                str.startsWith("mc/") || str.startsWith("y/") ||
                str.startsWith("s/");
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) {
        try {
            String[] paramParts = Parser.parseEdit(param);
            boolean found = false;
            if (paramParts[0] == null) {
                return ui.missingCodeErrorMessage();
            }
            for (Course course : courses.getCourses()) {
                if (course.getCode().equals(paramParts[0])) {
                    course = courses.setEditedParams(paramParts, course);
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
