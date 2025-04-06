package studybuddy.commands;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.io.Parser;
import studybuddy.data.storage.StorageManager;

public class PlaceHolderCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            dummy mc/MODULAR_CREDITS y/YEAR s/SEMESTER
                Adds a dummy course to your plan with the given parameters.""";

    /**
     * @param param A string in the formate of "y/number1 s/number2"
     */
    public PlaceHolderCommand(String param) {
        super(param);
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        Course newCourse = Parser.parseDummy(param, courses);
        courses.add(newCourse);
        return "Course added: " + newCourse.getCode()
                + " - " + newCourse.getTitle() + " (" + newCourse.getMc() + " MCs)";
    }
}
