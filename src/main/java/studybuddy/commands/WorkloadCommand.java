package studybuddy.commands;

import java.util.ArrayList;

import studybuddy.course.Course;

public class WorkloadCommand extends Command {
    private final ArrayList<Course> courses;
    private final int NUM_OF_SEMESTERS = 8;

    public WorkloadCommand(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public String execute() {
        int[] mcs_in_each_semester = new int[NUM_OF_SEMESTERS];
        String[] period = new String[NUM_OF_SEMESTERS];
        for (Course course : courses) {
            int year = course.getTakeInYear();
            int semester = course.getTakeInSem();
            int sem = ((year - 1) * 2) + semester;
            mcs_in_each_semester[sem - 1] += course.getMc();
            period[sem - 1] = "Year " + year + " Semester " + semester;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NUM_OF_SEMESTERS; i++) {
            sb.append(period[i] + ": " + mcs_in_each_semester[i] + "MCs\n");
        }
        return sb.toString();
    }
}
