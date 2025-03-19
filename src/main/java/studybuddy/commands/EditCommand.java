package studybuddy.commands;

import studybuddy.CEGStudyBuddy;
import studybuddy.course.Course;

public class EditCommand extends Command {

    public EditCommand(String param) {
        super(param);
    }

    public String[] parseEdit() {
        assert (!param.isEmpty());

        String[] parts = param.split(" ");
        String code = null;
        String title = "";
        Integer mc = null;
        Integer y = null;
        Integer s = null;

        for (int i = 0; i < parts.length; i++) {
            switch (parts[i]) {
            case "c/":
                i++;
                code = parts[i];
                break;
            case "t/":
                i++;
                while (i < parts.length && !parts[i].matches("[a-z]+/.*")) {
                    title += parts[i] + " ";
                    i++;
                }
                // remove " " at tail
                title = title.trim();
                // reverse i by 1 to go back to the index before where the while loop breaks
                i--;
                break;
            case "mc/":
                i++;
                mc = Integer.parseInt(parts[i]);
                break;
            case "y/":
                i++;
                y = Integer.parseInt(parts[i]);
                break;
            case "s/":
                i++;
                s = Integer.parseInt(parts[i]);
                break;
            default:
                break;
            }
        }
        // if no edit value, hold place with empty string
        return new String[]{
                code,
                title,
                mc != null ? mc.toString() : "",
                y != null ? y.toString() : "",
                s != null ? s.toString() : ""
        };
    }

    @Override
    public String execute() {
        String[] paramParts = parseEdit();
        boolean found = false;
        try {
            if (paramParts[0] == null) {
                return "Error: Course code is missing.";
            }
            for (Course course: CEGStudyBuddy.courses) {
                if (course.getTitle().equals(paramParts[0])) {
                    course = setEditedParams(paramParts, course);
                    found = true;
                    break;
                }
            }
            if (found) {
                return "Success";
            }
            return "Error: Course not found.";
        } catch (ArrayIndexOutOfBoundsException e) {
            // print proper error message
            return "Error";
        } catch (NumberFormatException e) {
            // print proper error message
            return "Int Error";
        }
    }

    protected Course setEditedParams(String[] editedParams, Course course)
            throws ArrayIndexOutOfBoundsException, NumberFormatException {
        if (editedParams.length != 5) {
            // throw an exception here
            return course;
        }
        if (!editedParams[1].isEmpty()) {
            course.setTitle(editedParams[1]);
        }
        if (!editedParams[2].isEmpty()) {
            course.setMc(Integer.parseInt(editedParams[2]));
        }
        if (!editedParams[3].isEmpty()) {
            course.setTakeInYear(Integer.parseInt(editedParams[3]));
        }
        if (!editedParams[4].isEmpty()) {
            course.setTakeInSem(Integer.parseInt(editedParams[4]));
        }
        return course;
    }
}
