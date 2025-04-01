package studybuddy.commands;

import studybuddy.CEGStudyBuddy;
import studybuddy.course.Course;

public class FindCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            find c/CODE
                Finds the course with the given code in your plan.
            """;

    public FindCommand(String param) {
        super(param);
    }

    @Override
    public String execute() throws CEGStudyBuddyException {
        // Ensure the command has proper format
        if (!param.trim().toLowerCase().startsWith("c/")) {
            throwException("Invalid find format! Use: find c/CODE");
        }

        // Extract course code
        String[] parts = param.trim().split("c/", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throwException("Please provide a course code after c/");
        }

        String targetCode = parts[1].trim().toUpperCase();

        for (Course course : CEGStudyBuddy.courses.getCourses()) {
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

