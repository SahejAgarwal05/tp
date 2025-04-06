package studybuddy.commands;

import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.storage.StorageManager;
import java.util.HashMap;
import java.util.Map;

/**
 * The PrereqCommand class displays the prerequisites and description of a course,
 * if it is one of the predefined Computer Engineering core modules.
 */
public class PrereqCommand extends Command {

    public static final String COMMAND_DESCRIPTION = """
            prereq c/CODE
                Displays the prerequisites and description for the specified course code if available.""";

    // Map of predefined courses and their details
    private static final Map<String, CourseInfo> prereqMap = new HashMap<>();

    // Initialize the predefined courses
    static {
        prereqMap.put("CS1010", new CourseInfo("Programming Methodology", "None"));
        prereqMap.put("CS1231", new CourseInfo("Discrete Structures", "None"));
        prereqMap.put("CS2030S", new CourseInfo("Programming Methodology II", "CS1010"));
        prereqMap.put("CS2040S", new CourseInfo("Data Structures and Algorithms", "CS1010, CS1231"));
        prereqMap.put("CS2100", new CourseInfo("Computer Organisation", "CS1010"));
        prereqMap.put("CS2101", new CourseInfo("Effective Communication for CPs", "Must be taken with CS2103T"));
        prereqMap.put("CS2103T", new CourseInfo("Software Engineering", "CS2040S"));
        prereqMap.put("CS2105", new CourseInfo("Introduction to Computer Networks", "CS2040S"));
        prereqMap.put("CS2106", new CourseInfo("Introduction to Operating Systems", "CS2040S, CS2100"));
        prereqMap.put("CS3230", new CourseInfo("Design and Analysis of Algorithms", "CS2040S"));
        prereqMap.put("CG1111A", new CourseInfo("Engineering Principles and Practice I", "None"));
        prereqMap.put("CG2111A", new CourseInfo("Engineering Principles and Practice II", "CG1111A"));
        prereqMap.put("EE2026", new CourseInfo("Digital Design", "CS1010"));
        prereqMap.put("CG2023", new CourseInfo("Signals and Systems", "MA1512"));
        prereqMap.put("EG1311", new CourseInfo("Design and Make", "None"));
        prereqMap.put("CG2271", new CourseInfo("Real-time Operating Systems", "CS2040C"));
        prereqMap.put("CS3240", new CourseInfo("Interaction Design", "CS2103T"));
        prereqMap.put("CG3207", new CourseInfo("Computer Architecture", "CG2028, CG2271"));
        prereqMap.put("CG4002", new CourseInfo("Capstone Project", "CS2113"));
        prereqMap.put("IE2141", new CourseInfo("Industrial and Systems Engineering Principles", "None"));
        prereqMap.put("MA1511", new CourseInfo("Engineering Calculus", "None"));
        prereqMap.put("MA1508E", new CourseInfo("Linear Algebra in Engineering", "MA1301"));
        prereqMap.put("CS2040", new CourseInfo("Data Structures and Algorithms", "CS1010"));
        prereqMap.put("CS2113", new CourseInfo("Software Engineering and OOP", "CS2040C"));
        prereqMap.put("EE4204", new CourseInfo("Computer Networks", "one of ST2334, EE2012, ESP2107"));
        prereqMap.put("EE2211", new CourseInfo("Introduction to Machine Learning", "CS1010, MA1511, MA1508E"));
        prereqMap.put("MA1512", new CourseInfo("Differential Equations for Engineering", "None"));
        prereqMap.put("DTK1234", new CourseInfo("Design Thinking", "None"));
        prereqMap.put("PF1101", new CourseInfo("Fundamentals of Project Mgmt", "None"));
        prereqMap.put("CG2027", new CourseInfo("Transistor Level Digital Circuits", "CG1111A"));
        prereqMap.put("CG2028", new CourseInfo("Computer Organization", "CS1010, EE2026"));
    }

    public PrereqCommand(String param) {
        super(param);
    }

    /**
     * Executes the prereq command, validating input and printing prerequisites if available.
     */
    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        String[] parts = param.trim().split("c/", 2);

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new CEGStudyBuddyException("Please provide a valid course code after c/");
        }

        String courseCode = parts[1].trim().toUpperCase();

        // Validate course code format: 2–3 capital letters followed by 4 digits, optionally ending in a capital letter
        if (!courseCode.matches("^[A-Z]{2,3}\\d{4}[A-Z]?$")) {
            throw new CEGStudyBuddyException("Please enter a valid NUS course code like CS2040 or CG2111A.");
        }

        if (!prereqMap.containsKey(courseCode)) {
            return buildUnavailableCourseResponse(); // fallback list
        }

        CourseInfo info = prereqMap.get(courseCode);
        return "Course Code: " + courseCode +
                "\nTitle: " + info.title +
                "\nPrerequisites: " + info.prerequisites;
    }

    /**
     * If course is unknown, show fallback list of predefined course codes and prereqs.
     */
    private String buildUnavailableCourseResponse() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sorry, this function is still in progress.\n");
        sb.append("For now, we have defined the prerequisites for the following courses:\n\n");

        sb.append(String.format("%-10s | %-40s | %-30s\n", "Code", "Title", "Prerequisites"));
        sb.append("-".repeat(90)).append("\n");

        for (Map.Entry<String, CourseInfo> entry : prereqMap.entrySet()) {
            String code = entry.getKey();
            CourseInfo info = entry.getValue();
            sb.append(String.format("%-10s | %-40s | %-30s\n", code, info.title, info.prerequisites));
        }

        return sb.toString().trim();
    }

    /**
     * Helper class to store course information.
     */
    private static class CourseInfo {
        String title;
        String prerequisites;

        CourseInfo(String title, String prerequisites) {
            this.title = title;
            this.prerequisites = prerequisites;
        }
    }
}

