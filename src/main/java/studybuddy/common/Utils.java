package studybuddy.common;

public class Utils {
    public static final int GRADUATION_MCS = 160;
    public static final int NUM_OF_SEMESTERS = 8;

    public static boolean isValidMC(int mc) {
        return mc >= 1 && mc <= 12;
    }

    public static boolean isValidYear(int year) {
        return year >= 1 && year <= 4;
    }

    public static boolean isValidSem(int sem) {
        return sem >= 1 && sem <= 2;
    }

    public static boolean hasIdentifier(String str) {
        return str.startsWith("t/") || str.startsWith("c/") ||
                str.startsWith("mc/") || str.startsWith("y/") ||
                str.startsWith("s/");
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
}
