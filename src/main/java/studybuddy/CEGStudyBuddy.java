package studybuddy;

import studybuddy.data.course.CourseList;
import studybuddy.commands.Command;
import studybuddy.data.io.Parser;
import studybuddy.data.storage.StorageManager;
import studybuddy.data.io.Ui;

public class CEGStudyBuddy {
    protected static CourseList courses; // Global course list
    private static boolean isRunning = true;
    private static Ui ui = new Ui();
    private static StorageManager storage = new StorageManager("./PlanData", courses);


    public static void main(String[] args) {
        ui.showWelcome();
        storage.initializePlan();

        while (isRunning) {
            String[] userInput = ui.readInput();
            try {
                Command c = Parser.parseCommand(userInput);
                ui.showMessage(c.execute(courses, storage));
                isRunning = c.isRunning();
            } catch (Exception e) {
                ui.showMessage(e.getMessage());
            }
        }
    }
}
