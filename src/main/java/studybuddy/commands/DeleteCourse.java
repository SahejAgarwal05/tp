package studybuddy.commands;

import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.io.Parser;
import studybuddy.data.storage.StorageManager;

public class DeleteCourse extends Command {
    public static final String COMMAND_DESCRIPTION = """
            delete c/CODE
                Deletes a course from your plan.""";

    public DeleteCourse(String param) {
        super(param); // Passes the command parameter
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        try {
            return Parser.parseDelete(courses, param);
        } catch (Exception e) {
            throw new CEGStudyBuddyException("An error occurred while trying to delete the course.");
        }
    }
}
