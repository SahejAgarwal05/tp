package studybuddy.commands;

import studybuddy.data.course.CourseList;
import studybuddy.data.storage.StorageManager;

public class HelpCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
            help
                Displays this command list.""";

    public HelpCommand() {
        super(""); // no parameters needed
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) {
        return ui.printCommandList();
    }
}
