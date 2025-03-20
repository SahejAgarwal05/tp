package studybuddy.commands;

import studybuddy.CEGStudyBuddy;
import studybuddy.course.Course;

public class ListCommand extends Command {
    public ListCommand() {
        super(""); // no parameter needed
    }

    @Override
    public String execute() {
        if (CEGStudyBuddy.courses.isEmpty()) {
            return "No courses added yet!";
        }
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Course course : CEGStudyBuddy.courses) {
            sb.append(count).append(". ")
                    .append(course.getCode()).append(" - ").append(course.getTitle())
                    .append(" (").append(course.getMc()).append(" MCs)").append("\n");
            count++;
        }
        return sb.toString().trim();
    }
}

