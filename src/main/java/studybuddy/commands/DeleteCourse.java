package studybuddy.commands;

import studybuddy.course.CourseList;

public class DeleteCourse extends Command {
    private CourseList courseList;

    public DeleteCourse(CourseList courseList) {
        super("delete");
        this.courseList = courseList;
    }

    @Override
    public String execute(String[] args) {
        if (args.length < 2 || !args[0].equalsIgnoreCase("c")) {
            return "Invalid format! Please use: delete c/CODE";
        }
        String code = args[1].trim().toUpperCase();
        boolean deleted = courseList.deleteCourseByCode(code);
        if (deleted) {
            return "Course with code " + code + " has been deleted.";
        } else {
            return "Course with code " + code + " not found.";
        }
    }
}


