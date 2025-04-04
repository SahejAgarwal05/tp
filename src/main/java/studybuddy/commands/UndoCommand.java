package studybuddy.commands;

import studybuddy.data.course.CourseList;
import studybuddy.data.storage.StorageManager;
import studybuddy.data.course.UndoManager;

public class UndoCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            undo
                Undoes the most recent course-related command (add, delete, replace).""";

    public UndoCommand() {
        super("");
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) {
        return UndoManager.undo(courses);
    }
}

