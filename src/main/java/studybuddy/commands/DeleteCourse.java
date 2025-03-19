package studybuddy.commands;

import studybuddy.course.CourseList;

public class DeleteCourse extends Command {
    private CourseList courseList; // Reference to CourseList

    public DeleteCourse(CourseList courseList) {
        super("delete");
        this.courseList = courseList;
    }

    @Override
    public String execute(String[] args) {
        // Check if the format is correct and returns error if not correct
        if (args.length < 2 || !args[0].equalsIgnoreCase("c")) {
            return "Invalid format! Please use: delete c/CODE";
        }

        String code = args[1].trim().toUpperCase(); // Extract and format course code
        boolean deleted = courseList.deleteCourseByCode(code);

        // Return message based on whether deletion was successful
        if (deleted) {
            return "Course with code " + code + " has been deleted.";
        } else {
            return "Course with code " + code + " not found.";
        }
    }
}



