package studybuddy.commands;

import java.util.ArrayList;

import studybuddy.course.Course;

public class WorkloadCommand extends Command {
    private static final int NUM_OF_SEMESTERS = 8;
    private final ArrayList<Course> courses;

    public WorkloadCommand(String param, ArrayList<Course> courses) {
        super(param);
        this.courses = courses;
    }

    public String execute() {
        assert (courses != null);
        int[] mcsInEachSemester = new int[NUM_OF_SEMESTERS];
        String[] period = new String[NUM_OF_SEMESTERS];
        for (Course course : courses) {
            int year = course.getTakeInYear();
            int semester = course.getTakeInSem();
            int sem = ((year - 1) * 2) + semester;
            mcsInEachSemester[sem - 1] += course.getMc();
            period[sem - 1] = "Year " + year + " Semester " + semester;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NUM_OF_SEMESTERS; i++) {
            sb.append(period[i] + ": " + mcsInEachSemester[i] + "MCs\n");
        }
        return sb.toString();
    }
}
