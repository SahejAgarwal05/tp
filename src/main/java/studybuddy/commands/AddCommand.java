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
        try {
            String code = paramParts[1];
            String title = paramParts[2];
            int mc = Integer.parseInt(paramParts[3]);
            int takeInYear = Integer.parseInt(paramParts[4]);
            int takeInSem = Integer.parseInt(paramParts[5]);
            CEGStudyBuddy.courses.add(new Course(code, title, mc, takeInSem, takeInYear));
        } catch (ArrayIndexOutOfBoundsException e) {
            // print proper error message
            return "Error";
        } catch (NumberFormatException e) {
            // print proper error message
            return "Int Error";
        }
        // print proper success message
        return "Success";
    }
}