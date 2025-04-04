package studybuddy.commands;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.io.Parser;
import studybuddy.data.storage.StorageManager;
import studybuddy.data.course.UndoManager;

public class DeleteCourse extends Command {
    public static final String COMMAND_DESCRIPTION = """
            delete c/CODE
                Deletes a course from your plan.""";

    public DeleteCourse(String param) {
        super(param);
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        try {
            Course deletedCourse = Parser.parseDeleteReturnCourse(courses, param);
            UndoManager.recordDelete(deletedCourse);
            return "Course with code " + deletedCourse.getCode() + " has been deleted.";
        } catch (CEGStudyBuddyException e) {
            throw e;
        } catch (Exception e) {
            throw new CEGStudyBuddyException("An error occurred while trying to delete the course.");
        }
    }
}
