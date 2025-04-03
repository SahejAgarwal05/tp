package studybuddy;

import java.util.Scanner;

import studybuddy.data.course.CourseList;
import studybuddy.commands.Command;
import studybuddy.data.io.Parser;
import studybuddy.data.storage.StorageManager;
import studybuddy.io.Ui;

public class CEGStudyBuddy {
    protected static CourseList courses; // Global course list
    private static boolean isRunning = true;
    public static Scanner in = new Scanner(System.in); // put this in Ui
    private static StorageManager storage = new StorageManager("./PlanData", courses);
    public static Ui ui = new Ui();

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

    // put this in Ui
    /**
     * Reads the input and parses it into a String array.
     *
     * @return The String array containing the command and parameters.
     */
    static String[] readInput() {
        String userInput = in.nextLine();
        return userInput.split(" ", 2);
    }
}
