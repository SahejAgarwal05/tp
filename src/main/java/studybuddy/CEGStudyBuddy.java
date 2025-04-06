package studybuddy;

import studybuddy.data.course.CourseList;
import studybuddy.commands.Command;
import studybuddy.data.io.Parser;
import studybuddy.data.io.Ui;
import studybuddy.data.storage.CommandHistoryManager;
import studybuddy.data.storage.StorageManager;

public class CEGStudyBuddy {
    public static CourseList courses;
    private static boolean isRunning = true;
    private static final Ui ui = new Ui();
    private static final StorageManager storage = new StorageManager("./PlanData");

    public static void main(String[] args) {
        ui.showWelcome();
        storage.initializePlan();

        while (isRunning) {
            String[] userInput = ui.readInput();
            String fullCommand = String.join(" ", userInput).trim(); // full input line

            // Skip logging if it's the summary command
            boolean isSummary = fullCommand.toLowerCase().startsWith("summary");

            try {
                Command c = Parser.parseCommand(userInput);
                String output = c.execute(courses, storage);
                ui.showMessage(output);
                if (!isSummary) {
                    CommandHistoryManager.record(fullCommand);
                }
                isRunning = c.isRunning();
            } catch (Exception e) {
                if (!isSummary) {
                    CommandHistoryManager.record(fullCommand);
                }
                ui.showMessage(e.getMessage());
            }
        }
    }
}
