package studybuddy.commands;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.io.Parser;
import studybuddy.data.storage.StorageManager;
import studybuddy.data.storage.UndoManager;

public class AddCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            add c/CODE t/TITLE mc/MODULAR_CREDITS y/YEAR s/SEMESTER
                Adds a course to your plan with the given parameters.""";

    public AddCommand(String param) {
        super(param);
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        Course newCourse = Parser.parseCourse(param, false);

        // Check for duplicate course (same code in same year and semester)
        for (Course course : courses.getCourses()) {
            if (course.getCode().equalsIgnoreCase(newCourse.getCode())) {
                throw new CEGStudyBuddyException("This course is already added in year " +
                        course.getTakeInYear() + " semester " + course.getTakeInSem());
            }
        }

        // Add the new course to the course list
        courses.add(newCourse);

        // Record the action for undo feature
        UndoManager.recordAdd(newCourse);

        // Return confirmation message
        return "Course added: " + newCourse;
    }
}


