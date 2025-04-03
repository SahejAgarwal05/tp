package studybuddy.commands;

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

        // can move to Ui
        for (Course course : courses.getCourses()) {
            if (course.getCode().equalsIgnoreCase(targetCode)) {
                return "Course Code: " + course.getCode() + "\n"
                        + "Course Title: " + course.getTitle() + "\n"
                        + "Number of MCs: " + course.getMc() + "\n"
                        + "Year and Sem: Y" + course.getTakeInYear() + "S" + course.getTakeInSem();
            }
        }

        return "Course " + targetCode + " not found in your course list.";
    }
}
