package studybuddy.commands;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.io.Parser;
import studybuddy.data.storage.StorageManager;

public class AddCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            add c/CODE t/TITLE mc/MODULAR_CREDITS y/YEAR s/SEMESTER
                Adds a course to your plan with the given parameters.""";

    public AddCommand(String param) {
        super(param);
    }

    // 3 methods below move to studybuddy.common.Utils
    public static boolean isValidMC(int mc) {
        return mc >= 1 && mc <= 12;
    }

    public static boolean isValidYear(int year) {
        return year >= 1 && year <= 4;
    }

    public static boolean isValidSem(int sem) {
        return sem >= 1 && sem <= 2;
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        Course newCourse = Parser.parseCourse(param);
        courses.add(newCourse);
        return "Course added: " + newCourse.getCode()
                + " - " + newCourse.getTitle() + " (" + newCourse.getMc() + " MCs)";
    }
}
