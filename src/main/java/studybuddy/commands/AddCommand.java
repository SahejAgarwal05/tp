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

        // Check for duplicates based on code + year + semester
        for (Course existing : courses.getCourses()) {
            if (existing.getCode().equalsIgnoreCase(newCourse.getCode())
                    && existing.getTakeInYear() == newCourse.getTakeInYear()
                    && existing.getTakeInSem() == newCourse.getTakeInSem()) {
                throw new CEGStudyBuddyException("This course already exists for Y" +
                        newCourse.getTakeInYear() + "S" + newCourse.getTakeInSem() + ".");
            }
        }

        // Add course and record for undo
        courses.add(newCourse);
        UndoManager.recordAdd(newCourse);

        return "Course added: " + newCourse.getCode()
                + " - " + newCourse.getTitle() + " (" + newCourse.getMc() + " MCs)";
    }
}

