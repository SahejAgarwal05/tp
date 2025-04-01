package studybuddy.commands;

import studybuddy.CEGStudyBuddy;

public class DeleteCourse extends Command {
    public static final String COMMAND_DESCRIPTION = """
            delete c/CODE
                Deletes a course from your plan.""";

    public DeleteCourse(String param) {
        super(param); // Passes the command parameter
    }

    @Override
    public String execute() {
        try {
            // Example input: c/CS2040
            String[] parts = param.trim().split("c/", 2);
            if (parts.length < 2) {
                return "Invalid format! Please use: delete c/CODE";
            }
            String code = parts[1].trim().toUpperCase();

            boolean deleted = CEGStudyBuddy.courses.getCourses().removeIf(course ->
                    course.getCode().equalsIgnoreCase(code)
            );

            if (deleted) {
                return "Course with code " + code + " has been deleted.";
            } else {
                return "Course with code " + code + " not found.";
            }

        } catch (Exception e) {
            return "An error occurred while trying to delete the course.";
        }
    }
}





