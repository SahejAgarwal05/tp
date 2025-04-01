package studybuddy.commands;

import java.util.ArrayList;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.storage.StorageManager;

public class WorkloadSummaryCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            workload_summary
                Displays the total workload for all semesters.""";

    // move to studybuddy.common.Util
    public static final int NUM_OF_SEMESTERS = 8;

    public WorkloadSummaryCommand(String param) {
        super(param);
    }

    // move to studybuddy.common.Util
    public static String checkWorkload(int mc, int sem) {
        // three return strings move to Ui
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

    public String execute(CourseList courses, StorageManager storage) {
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
        for (Course course : courses.getCourses()) {
            year = course.getTakeInYear();
            semester = course.getTakeInSem();
            int sem = ((year - 1) * 2) + semester;
            mcsInEachSemester[sem - 1] += course.getMc();
        }

        // move to Ui
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NUM_OF_SEMESTERS; i++) {
            sb.append(period[i] + ": " + mcsInEachSemester[i] + "MCs ");
            sb.append(checkWorkload(mcsInEachSemester[i], i) + "\n");
        }
        return sb.toString();
    }
}
