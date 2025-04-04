package studybuddy.commands;

import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.io.Ui;
import studybuddy.data.storage.StorageManager;

public class FillCommand extends Command {
    private Ui ui = new Ui();

    /**
     * @param param A string in the formate of "y/number1 s/number2"
     */
    public FillCommand(String param) {
        super(param);
    }

    @Override
    public String execute(CourseList courses, StorageManager storage) throws CEGStudyBuddyException {
        return "";
    }
}
