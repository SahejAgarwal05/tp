package studybuddy.commands;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.course.UndoManager;
import studybuddy.data.io.Parser;
import studybuddy.data.storage.StorageManager;

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

            for (Course course : courses.getCourses()) {
                if (course.getCode().equals(paramParts[0])) {
                    // Make a deep copy BEFORE editing
                    Course oldVersion = new Course(
                            course.getCode(),
                            course.getTitle(),
                            course.getMc(),
                            course.getTakeInYear(),
                            course.getTakeInSem()
                    );

                    // Apply the edits
                    course = courses.setEditedParams(paramParts, course);

                    // Register undo (edit is same as replacing new version with old)
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

