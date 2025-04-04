package studybuddy.data.io;

import studybuddy.commands.*;
import studybuddy.common.Utils;
import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.course.CourseManager;
import studybuddy.data.exception.CEGStudyBuddyException;

public class Parser {
    private static Ui ui = new Ui();
    /**
     * Parses the input into a command and returns the Command object for the command.
     *
     * @param inputParts Input String array containing the command and parameters.
     * @return The Command object corresponding to the input.
     * @throws IndexOutOfBoundsException If parameters are not included (inputParts.len() = 1).
     */
    public static Command parseCommand(String[] inputParts) throws CEGStudyBuddyException {
        Command c;
        try {
            switch (inputParts[0]) {
            case CommandNames.ADD -> c = new AddCommand(inputParts[1]);
            case CommandNames.EDIT -> c = new EditCommand(inputParts[1]);
            case CommandNames.LIST -> c = new ListCommand();
            case CommandNames.FIND -> c = new FindCommand(inputParts[1]);
            case CommandNames.DELETE -> c = new DeleteCourse(inputParts[1]);
            case CommandNames.GRADREQ -> c = new GradRequirementCommand();
            case CommandNames.WORKLOAD_SUMMARY -> c = new WorkloadSummaryCommand();
            case CommandNames.WORKLOAD_FOR -> c = new WorkloadForCommand(inputParts[1]);
            case CommandNames.WORKLOAD_BALANCE -> c = new WorkloadBalanceCommand();
            case CommandNames.SAVE -> c = new SavePlanCommand();
            case CommandNames.SWITCH_PLAN -> c = new SwitchPlanCommand();
            case CommandNames.HELP -> c = new HelpCommand();
            case CommandNames.EXIT -> c = new ExitCommand();
            case CommandNames.DELETE_PLAN -> c = new DeletePlanCommand();
            case CommandNames.REPLACE -> c = new ReplaceCommand(inputParts[1]);
            case CommandNames.UNDO -> c = new UndoCommand();


                default -> c = new InvalidCommand();
            }
        } catch (Exception e) {
            throw new CEGStudyBuddyException(e.getMessage());
        }
        return c;
    }

    public static Course parseDeleteReturnCourse(CourseList courses, String param) throws CEGStudyBuddyException {
        String[] parts = param.trim().split("c/", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new CEGStudyBuddyException("Invalid format! Please use: delete c/CODE");
        }

        String code = parts[1].trim().toUpperCase();

        for (Course course : courses.getCourses()) {
            if (course.getCode().equalsIgnoreCase(code)) {
                courses.getCourses().remove(course);
                return course; // Return the deleted course for undo
            }
        }

        throw new CEGStudyBuddyException("Course with code " + code + " not found.");
    }

    public static String[] parseReplace(String param) throws CEGStudyBuddyException {
        String[] tokens = param.trim().split("\\s+");

        if (tokens.length < 2 || !tokens[0].startsWith("c/") || !tokens[1].startsWith("c/")) {
            throw new CEGStudyBuddyException("Please provide exactly two course codes: OLD_CODE NEW_CODE. Format: replace c/OLD c/NEW t/TITLE mc/VALUE y/YEAR s/SEM");
        }

        String oldCode = tokens[0].substring(2).toUpperCase();
        String newCode = tokens[1].substring(2).toUpperCase();

        if (oldCode.isEmpty() || newCode.isEmpty()) {
            throw new CEGStudyBuddyException("Course codes cannot be empty. Format: replace c/OLD c/NEW t/TITLE mc/VALUE y/YEAR s/SEM");
        }

        return new String[]{oldCode, newCode};
    }

    public static Course parseCourse(String param) throws CEGStudyBuddyException {
        assert (!param.isEmpty());
        String[] paramParts = param.split("c/| t/| mc/| y/| s/", 6);

        String code;
        String title;
        int mc;
        int takeInYear;
        int takeInSem;

        try {
            code = paramParts[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CEGStudyBuddyException(ui.missingInputErrorMessage());
        }
        if (CourseManager.ifDefined(code)) {
            Course course = getDefinedCourse(code, param);
            if (course != null) {
                return course;
            }
        }

        try {
            title = paramParts[2];
            mc = Integer.parseInt(paramParts[3]);
            takeInYear = Integer.parseInt(paramParts[4]);
            takeInSem = Integer.parseInt(paramParts[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CEGStudyBuddyException(ui.missingInputErrorMessage());
        } catch (NumberFormatException e) {
            throw new CEGStudyBuddyException(ui.parseIntErrorMessage());
        }

        if (!Utils.isValidMC(mc) || !Utils.isValidYear(takeInYear) || !Utils.isValidSem(takeInSem)) {
            throw new CEGStudyBuddyException(ui.parseIntErrorMessage());
        }

        return new Course(code, title, mc, takeInYear, takeInSem);
    }

    private static Course getDefinedCourse(String code, String param)
            throws ArrayIndexOutOfBoundsException, NumberFormatException {
        String[] parts = param.split(" ");
        Integer y = null;
        Integer s = null;

        for (String part : parts) {
            if (part.startsWith("y/")) {
                y = Integer.parseInt(part.substring(2));
            } else if (part.startsWith("s/")) {
                s = Integer.parseInt(part.substring(2));
            }
        }
        Course course = CourseManager.getCourse(code);
        if (y != null && s != null) {
            assert course != null;
            course.setTakeInYear(y);
            course.setTakeInSem(s);
            return course;
        }
        return null;
    }

    public static String parseDelete(CourseList courses, String param) {
        // Example input: c/CS2040
        String[] parts = param.trim().split("c/", 2);
        if (parts.length < 2) {
            return "Invalid format! Please use: delete c/CODE";
        }
        String code = parts[1].trim().toUpperCase();

        boolean deleted = courses.getCourses().removeIf(course ->
                course.getCode().equalsIgnoreCase(code)
        );

        if (deleted) {
            return "Course with code " + code + " has been deleted.";
        } else {
            return "Course with code " + code + " not found.";
        }
    }

    public static String[] parseEdit(String param) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        assert (!param.isEmpty());

        String[] parts = param.split(" ");
        String code = null;
        StringBuilder title = new StringBuilder();
        Integer mc = null;
        Integer y = null;
        Integer s = null;

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].startsWith("c/")) {
                code = parts[i].substring(2);
            } else if (parts[i].startsWith("t/")) {
                // title may consist of a few words
                title = new StringBuilder(parts[i].substring(2));
                i += 1;
                // while i is in bound and parts[i] is not next identifier
                while (i < parts.length && !Utils.hasIdentifier(parts[i])) {
                    title.append(" ").append(parts[i]);
                    i += 1;
                }
                // reset i back to the ending element of title
                i -= 1;
            } else if (parts[i].startsWith("mc/")) {
                mc = Integer.parseInt(parts[i].substring(3));
            } else if (parts[i].startsWith("y/")) {
                y = Integer.parseInt(parts[i].substring(2));
            } else if (parts[i].startsWith("s/")) {
                s = Integer.parseInt(parts[i].substring(2));
            }
        }
        // if no edit value, hold place with empty string
        return new String[]{code,
                            title.toString(),
                            mc != null ? mc.toString() : "",
                            y != null ? y.toString() : "",
                            s != null ? s.toString() : ""};
    }

    public static String[] parseFind(String param) throws CEGStudyBuddyException {
        // Ensure the command has proper format
        if (!param.trim().toLowerCase().startsWith("c/")) {
            throw new CEGStudyBuddyException("Invalid find format! Use: find c/CODE");
        }

        // Extract course code
        String[] parts = param.trim().split("c/", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new CEGStudyBuddyException("Please provide a course code after c/");
        }
        return parts;
    }

    public static int[] parseWorkloadFor(String param) throws CEGStudyBuddyException {
        String[] splits = param.trim().split("s/");
        int[] output = new int[2];
        try {
            output[0] = Integer.parseInt(splits[1].trim());
            output[1] = Integer.parseInt(splits[0].substring(2).trim());
            assert (output[0] == 1 || output[0] == 2) && (output[1] > 0);
        } catch (Exception e) {
            throw new CEGStudyBuddyException("Invalid year and/or sem");
        }

        return output;
    }
}
