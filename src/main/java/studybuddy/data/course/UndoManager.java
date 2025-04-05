package studybuddy.data.course;

import java.util.Stack;

/**
 * Manages undo operations for modifying course plans.
 * Supports undoing add, delete, and replace course actions.
 */
public class UndoManager {

    // Stack to store the history of user actions for undo
    private static final Stack<Action> actionHistory = new Stack<>();

    /**
     * Records an ADD action when a course is added.
     *
     * @param course The course that was added.
     */
    public static void recordAdd(Course course) {
        actionHistory.push(new Action(ActionType.ADD, course));
    }

    /**
     * Records a DELETE action when a course is deleted.
     *
     * @param course The course that was deleted.
     */
    public static void recordDelete(Course course) {
        actionHistory.push(new Action(ActionType.DELETE, course));
    }

    /**
     * Records a REPLACE action when a course is replaced.
     *
     * @param oldCourse The original course that was removed.
     * @param newCourse The new course that was added.
     */
    public static void recordReplace(Course oldCourse, Course newCourse) {
        actionHistory.push(new Action(ActionType.REPLACE, oldCourse, newCourse));
    }

    /**
     * Undoes the most recent action from the history.
     *
     * @param courses The current list of courses in the plan.
     * @return A message indicating the result of the undo action.
     */
    public static String undo(CourseList courses) {
        if (actionHistory.isEmpty()) {
            return "No actions to undo.";
        }

        Action lastAction = actionHistory.pop();

        switch (lastAction.getType()) {
        case ADD:
            // Undoing an add means removing the course
            courses.deleteCourseByCode(lastAction.getCourse().getCode());
            return "Undo: Added course \"" + lastAction.getCourse().getCode() + "\" has been removed.";

        case DELETE:
            // Undoing a delete means restoring the course
            courses.addCourse(lastAction.getCourse());
            return "Undo: Deleted course \"" + lastAction.getCourse().getCode() + "\" has been restored.";

        case REPLACE:
            // Undoing a replace means removing the new course and restoring the old one
            courses.deleteCourseByCode(lastAction.getNewCourse().getCode());
            courses.addCourse(lastAction.getCourse());
            return "Undo: Module changes for \"" + lastAction.getCourse().getCode() + "\" have been reverted.";

        default:
            return "Nothing to undo.";
        }
    }

    /**
     * Enumeration of action types that can be undone.
     */
    private enum ActionType {
        ADD, DELETE, REPLACE
    }

    /**
     * Represents a user action that can be undone.
     */
    private static class Action {
        private final ActionType type;
        private final Course course;      // For ADD and DELETE actions
        private final Course newCourse;   // Only used for REPLACE actions

        // Constructor for ADD and DELETE actions
        public Action(ActionType type, Course course) {
            this.type = type;
            this.course = course;
            this.newCourse = null;
        }

        // Constructor for REPLACE actions
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


