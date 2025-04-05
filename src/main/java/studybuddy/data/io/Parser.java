package studybuddy.data.io;

import studybuddy.commands.AddCommand;
import studybuddy.commands.Command;
import studybuddy.commands.DeleteCourse;
import studybuddy.commands.DeletePlanCommand;
import studybuddy.commands.EditCommand;
import studybuddy.commands.ExitCommand;
import studybuddy.commands.FindCommand;
import studybuddy.commands.GradRequirementCommand;
import studybuddy.commands.HelpCommand;
import studybuddy.commands.InvalidCommand;
import studybuddy.commands.ListCommand;
import studybuddy.commands.RenamePlanCommand;
import studybuddy.commands.ReplaceCommand;
import studybuddy.commands.SavePlanCommand;
import studybuddy.commands.SummaryCommand;
import studybuddy.commands.SwitchPlanCommand;
import studybuddy.commands.UndoCommand;
import studybuddy.commands.WorkloadForCommand;
import studybuddy.commands.WorkloadBalanceCommand;
import studybuddy.commands.WorkloadSummaryCommand;
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
        try {
            //Logic to solve Exception message for command inputs without space and with space (no information)
            String command = inputParts[0].toLowerCase();
            switch (command) {
            case CommandNames.ADD:
                if (inputParts.length < 2){
                    throw new CEGStudyBuddyException("Missing parameters! Format: add c/CODE t/TITLE mc/MC y/YEAR s/SEM");
                }
                return new AddCommand(inputParts[1]);

            case CommandNames.EDIT:
                if (inputParts.length < 2){
                    throw new CEGStudyBuddyException("Missing parameters! Format: edit c/CODE [t/TITLE] [mc/MC] [y/YEAR] [s/SEM]");
                }
                return new EditCommand(inputParts[1]);

            case CommandNames.REPLACE:
                if (inputParts.length < 2){
                    throw new CEGStudyBuddyException("Missing parameters! Format: replace c/OLD_CODE c/NEW_CODE t/TITLE mc/MC y/YEAR s/SEM");
                }
                return new ReplaceCommand(inputParts[1]);

            case CommandNames.DELETE:
                if (inputParts.length < 2){
                    throw new CEGStudyBuddyException("Missing parameters! Format: delete c/CODE");
                }
                return new DeleteCourse(inputParts[1]);

            case CommandNames.FIND:
                if (inputParts.length < 2){
                    throw new CEGStudyBuddyException("Missing parameters! Format: find c/CODE");
                }
                return new FindCommand(inputParts[1]);

            case CommandNames.WORKLOAD_FOR:
                if (inputParts.length < 2) {
                    throw new CEGStudyBuddyException("Missing parameters! Format: workload_for y/YEAR s/SEM");
                }
                return new WorkloadForCommand(inputParts[1]);

            case CommandNames.LIST:
                return new ListCommand();

            case CommandNames.GRADREQ:
                return new GradRequirementCommand();

            case CommandNames.WORKLOAD_SUMMARY:
                return new WorkloadSummaryCommand();

            case CommandNames.WORKLOAD_BALANCE:
                return new WorkloadBalanceCommand();

            case CommandNames.SAVE:
                return new SavePlanCommand();

            case CommandNames.SWITCH_PLAN:
                return new SwitchPlanCommand();

            case CommandNames.DELETE_PLAN:
                return new DeletePlanCommand();

            case CommandNames.RENAME_PLAN:
                return new RenamePlanCommand();

            case CommandNames.HELP:
                return new HelpCommand();

            case CommandNames.EXIT:
                return new ExitCommand();

            case CommandNames.UNDO:
                return new UndoCommand();

            case CommandNames.SUMMARY:
                return new SummaryCommand();

            default:
                return new InvalidCommand();
            }

        } catch (CEGStudyBuddyException e) {
            throw e;
        } catch (Exception e) {
            throw new CEGStudyBuddyException("An unknown error occurred while parsing the command.");
        }
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
            throw new CEGStudyBuddyException("Format: replace c/OLD c/NEW t/TITLE mc/VALUE y/YEAR s/SEM");
        }

        String oldCode = tokens[0].substring(2).toUpperCase();
        String newCode = tokens[1].substring(2).toUpperCase();

        if (oldCode.isEmpty() || newCode.isEmpty()) {
            throw new CEGStudyBuddyException("Course codes cannot be empty.");
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
