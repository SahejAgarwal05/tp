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
public class DeleteCourseCommand extends Command {

    // Description shown to the user when help or command summary is requested
    public static final String COMMAND_DESCRIPTION = """
            delete c/CODE
                Deletes a course from your plan.""";

    // Constructor takes the user input parameter
    public DeleteCourseCommand(String param) {
        super(param);
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        try {
            // Parse input parameters to a valid course code
            String code = Parser.parseDelete(param);
            Course deletedCourse;

            for (Course course : courses.getCourses()) {
                if (course.getCode().equalsIgnoreCase(code)) {
                    courses.getCourses().remove(course);
                    deletedCourse = course;

                    // Record the action for undo functionality
                    UndoManager.recordDelete(deletedCourse);

                    // Return confirmation message
                    return "Course with code " + deletedCourse.getCode() + " has been deleted.";
                }
            }
            throw new CEGStudyBuddyException("Course with code " + code + " not found.");
        } catch (CEGStudyBuddyException e) {
            // Throw CEGStudyBuddyException again to maintain error message
            throw e;
        } catch (Exception e) {
            // Catch-all for unexpected runtime issues
            throw new CEGStudyBuddyException("An error occurred while trying to delete the course.");
        }
    }
}


