package studybuddy.commands;

import studybuddy.CEGStudyBuddy;
import studybuddy.course.Course;

public class AddCommand extends Command {

    public static boolean isValidMC(int mc) {
        return mc >= 1 && mc <= 12;
    }

    public static boolean isValidYear(int year) {
        return year >= 1 && year <= 4;
    }

    public static boolean isValidSem(int sem) {
        return sem >= 1 && sem <= 2;
    }

    public AddCommand(String param) {
        super(param);
    }

    public String[] parseAdd() {
        assert (!param.isEmpty());
        return param.split("c/| t/| mc/| y/| s/", 6);
    }

    @Override
    public String execute() throws CEGStudyBuddyException {
        String[] paramParts = parseAdd();

        String code;
        String title;
        int mc;
        int takeInYear;
        int takeInSem;

        try {
            code = paramParts[1];
            title = paramParts[2];
            mc = Integer.parseInt(paramParts[3]);
            takeInYear = Integer.parseInt(paramParts[4]);
            takeInSem = Integer.parseInt(paramParts[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CEGStudyBuddyException("You missed an input.");
        } catch (NumberFormatException e) {
            throw new CEGStudyBuddyException("You did not enter a valid number.");
        }

        if (!isValidMC(mc) || !isValidYear(takeInYear) || !isValidSem(takeInSem)) {
            throw new CEGStudyBuddyException("You did not enter a valid number.");
        }
        
        CEGStudyBuddy.courses.add(new Course(code, title, mc, takeInSem, takeInYear));
        return "Course added: " + code + " - " + title + " (" + mc + " MCs)";
    }
}
