package studybuddy.commands;

import studybuddy.CEGStudyBuddy;
import studybuddy.course.Course;

public class AddCommand extends Command {

    public AddCommand(String param) {
        super(param);
    }

    public String[] parseAdd() {
        assert (!param.isEmpty());
        return param.split("c/| t/| mc/| y/| s/", 6);
    }

    @Override
    public String execute() {
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
            return "You missed an input.";
        } catch (NumberFormatException e) {
            return "You did not enter a proper number.";
        }
        
        CEGStudyBuddy.courses.add(new Course(code, title, mc, takeInSem, takeInYear));
        return "Course added: " + code + " - " + title + " (" + mc + " MCs)";
    }
}
