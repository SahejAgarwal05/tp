package studybuddy.commands;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.io.Parser;
import studybuddy.data.storage.StorageManager;
import studybuddy.data.storage.UndoManager;

/**
 * DeleteCourse command is responsible for deleting a course from the course plan.
 * It validates the input format, ensures proper course code formatting,
 * and utilizes the Parser and UndoManager to perform deletion and support undo functionality.
 */
public class DeleteCourse extends Command {

    // Description shown to the user when help or command summary is requested
    public static final String COMMAND_DESCRIPTION = """
            delete c/CODE
                Deletes a course from your plan.""";

    // Constructor takes the user input parameter
    public DeleteCourse(String param) {
        super(param);
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        try {
            // Ensure the parameter starts with the required prefix
            String trimmedParam = param.trim();
            if (!trimmedParam.startsWith("c/")) {
                throw new CEGStudyBuddyException("Invalid format. Use: delete c/CODE");
            }

            // Extract and normalize course code (convert to uppercase)
            String code = trimmedParam.substring(2).trim().toUpperCase();

            // Validate course code format using regex
            if (!code.matches("^[A-Z]{2,3}\\d{4}[A-Z]?$")) {
                throw new CEGStudyBuddyException("Invalid course code format. Expected: CS2040, EE2026, CG2111A, etc.");
            }

            // Use parser to locate and remove course from course list
            Course deletedCourse = Parser.parseDeleteReturnCourse(courses, param);

            // Record the action for undo functionality
            UndoManager.recordDelete(deletedCourse);

            // Return confirmation message
            return "Course with code " + deletedCourse.getCode() + " has been deleted.";

        } catch (CEGStudyBuddyException e) {
            // Re-throw expected exceptions to maintain message
            throw e;
        } catch (Exception e) {
            // Catch-all for unexpected runtime issues
            throw new CEGStudyBuddyException("An error occurred while trying to delete the course.");
        }
    }
}


