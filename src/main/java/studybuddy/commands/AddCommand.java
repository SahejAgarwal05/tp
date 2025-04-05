package studybuddy.commands;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.io.Parser;
import studybuddy.data.storage.StorageManager;
import studybuddy.data.course.UndoManager;

public class AddCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            add c/CODE t/TITLE mc/MODULAR_CREDITS y/YEAR s/SEMESTER
                Adds a course to your plan with the given parameters.""";

    public AddCommand(String param) {
        super(param);
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        Course newCourse = Parser.parseCourse(param);
        courses.add(newCourse);
        UndoManager.recordAdd(newCourse);
        return "Course added: " + newCourse.getCode()
                + " - " + newCourse.getTitle() + " (" + newCourse.getMc() + " MCs)";
    }
}
