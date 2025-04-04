package studybuddy.commands;

import studybuddy.data.course.CourseList;
import studybuddy.data.storage.StorageManager;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super(""); // no parameter needed
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) {
        return "Invalid Command";
    }
}
