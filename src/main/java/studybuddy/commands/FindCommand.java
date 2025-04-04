package studybuddy.commands;

import java.util.ArrayList;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.io.Parser;
import studybuddy.data.storage.StorageManager;

public class FindCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            find c/CODE
                Finds the course with the given code in your plan.""";

    public FindCommand(String param) {
        super(param);
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        String[] parts = Parser.parseFind(param);

        String targetCode = parts[1].trim().toUpperCase();
        ArrayList<Course> courseList = courses.getCourses();

        return ui.printFindCourse(courseList, targetCode);
    }
}
