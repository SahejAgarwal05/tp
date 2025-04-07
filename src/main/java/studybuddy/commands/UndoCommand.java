package studybuddy.commands;

import studybuddy.data.course.CourseList;
import studybuddy.data.storage.StorageManager;
import studybuddy.data.storage.UndoManager;

/**
 * Represents the undo command which reverts the most recent course-related action
 * such as add, delete, or replace.
 */
public class UndoCommand extends Command {

    // Description used for help message or command listings
    public static final String COMMAND_DESCRIPTION = """
            undo
                Undoes the most recent course-related command (add, delete, replace).
                Note: dummy cannot be undone, please use delete command""";

    /**
     * Constructs an UndoCommand.
     * No parameters are required for this command.
     */
    public UndoCommand() {
        super("");
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) {
        return UndoManager.undo(courses);
    }
}


