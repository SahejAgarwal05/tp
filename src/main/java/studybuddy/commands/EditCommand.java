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
            if (parts[i].startsWith("c/")) {
                code = parts[i].substring(2);
            } else if (parts[i].startsWith("t/")) {
                // title may consist of a few words
                title = parts[i].substring(2);
                i += 1;
                // while i is in bound and parts[i] is not next identifier
                while (i < parts.length && !hasIdentifier(parts[i])) {
                    title += " " + parts[i];
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
        return new String[]{
            code,
            title,
            mc != null ? mc.toString() : "",
            y != null ? y.toString() : "",
            s != null ? s.toString() : ""
        };
    }

    protected boolean hasIdentifier(String str) {
        return str.startsWith("t/") || str.startsWith("c/") ||
                str.startsWith("mc/") || str.startsWith("y/") ||
                str.startsWith("s/");
    }

    @Override
    public String execute() {
        String[] paramParts = parseEdit();
        boolean found = false;
        try {
            if (paramParts[0] == null) {
                return "Course code is missing.";
            }
            for (Course course: CEGStudyBuddy.courses) {
                if (course.getCode().equals(paramParts[0])) {
                    course = setEditedParams(paramParts, course);
                    System.out.println(course);
                    found = true;
                    break;
                }
            }
            if (found) {
                return "Success";
            }
            return "Course not found.";
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
