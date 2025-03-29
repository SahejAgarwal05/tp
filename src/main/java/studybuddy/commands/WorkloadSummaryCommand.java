package studybuddy.commands;

import java.util.ArrayList;

import studybuddy.course.Course;

public class WorkloadSummaryCommand extends Command {
    private static final int NUM_OF_SEMESTERS = 8;
    private final ArrayList<Course> courses;
    public WorkloadSummaryCommand(String param, ArrayList<Course> courses) {
        super(param);
        this.courses = courses;
    }

    public static String checkWorkload(int mc, int sem) {
        if (mc < 18) {
            return "(Too low! Minimum workload: 18 MCs)";
        }
        if (sem == 1 && mc > 23) {
            return "(Too high, please appeal for waiver! Maximum workload: 23 MCs)";
        }
        if (mc > 27) {
            return "(Too high, please appeal for waiver! Maximum workload: 27 MCs)";
        }
        return "";
    }

    public String execute() {
        assert (courses != null);
        int[] mcsInEachSemester = new int[NUM_OF_SEMESTERS];
        String[] period = new String[NUM_OF_SEMESTERS];
        int year = 1;
        int semester = 1;
        while (year <= 4) {
            int sem = ((year - 1) * 2) + semester;
            period[sem - 1] = "Year " + year + " Semester " + semester++;
            if (semester == 3) {
                semester = 1;
                year++;
            }
        }
        for (Course course : courses) {
            year = course.getTakeInYear();
            semester = course.getTakeInSem();
            int sem = ((year - 1) * 2) + semester;
            mcsInEachSemester[sem - 1] += course.getMc();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NUM_OF_SEMESTERS; i++) {
            sb.append(period[i] + ": " + mcsInEachSemester[i] + "MCs ");
            sb.append(checkWorkload(mcsInEachSemester[i], i) + "\n");
        }
        return sb.toString();
    }
}
