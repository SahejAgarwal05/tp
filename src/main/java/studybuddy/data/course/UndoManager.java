package studybuddy.data.course;

import java.util.Stack;

public class UndoManager {
    private static final Stack<Action> actionHistory = new Stack<>();

    public static void recordAdd(Course course) {
        actionHistory.push(new Action(ActionType.ADD, course));
    }

    public static void recordDelete(Course course) {
        actionHistory.push(new Action(ActionType.DELETE, course));
    }

    public static void recordReplace(Course oldCourse, Course newCourse) {
        actionHistory.push(new Action(ActionType.REPLACE, oldCourse, newCourse));
    }

    public static String undo(CourseList courses) {
        if (actionHistory.isEmpty()) {
            return "No actions to undo.";
        }

        Action lastAction = actionHistory.pop();

        switch (lastAction.getType()) {
        case ADD:
            courses.deleteCourseByCode(lastAction.getCourse().getCode());
            return "Undo: Added course \"" + lastAction.getCourse().getCode() + "\" has been removed.";
        case DELETE:
            courses.addCourse(lastAction.getCourse());
            return "Undo: Deleted course \"" + lastAction.getCourse().getCode() + "\" has been restored.";
        case REPLACE:
            courses.deleteCourseByCode(lastAction.getNewCourse().getCode());
            courses.addCourse(lastAction.getCourse());
            return "Undo: Module changes for \"" + lastAction.getCourse().getCode() + "\" have been reverted.";
        default:
            return "Nothing to undo.";
        }

    }


    private enum ActionType {
        ADD, DELETE, REPLACE
    }

    private static class Action {
        private final ActionType type;
        private final Course course;
        private final Course newCourse; // Only used for REPLACE

        public Action(ActionType type, Course course) {
            this.type = type;
            this.course = course;
            this.newCourse = null;
        }

        public Action(ActionType type, Course oldCourse, Course newCourse) {
            this.type = type;
            this.course = oldCourse;
            this.newCourse = newCourse;
        }

        public ActionType getType() {
            return type;
        }

        public Course getCourse() {
            return course;
        }

        public Course getNewCourse() {
            return newCourse;
        }
    }
}

