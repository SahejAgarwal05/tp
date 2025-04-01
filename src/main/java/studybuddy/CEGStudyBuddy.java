package studybuddy;

import java.util.Scanner;

import studybuddy.data.course.CourseList;
import studybuddy.commands.Command;
import studybuddy.data.io.Parser;
import studybuddy.data.storage.StorageManager;

public class CEGStudyBuddy {
    protected static CourseList courses; // Global course list
    private static boolean isRunning = true;
    public static Scanner in = new Scanner(System.in); // put this in Ui
    private static StorageManager storage = new StorageManager("./PlanData", courses);

    public static void main(String[] args) {
        System.out.println("Welcome to CEGStudyBuddy!");
        storage.initializePlan();

        while (isRunning) {
            System.out.print("Enter command: ");
            String[] userInput = readInput();
            try {
                Command c = Parser.parseCommand(userInput);
                System.out.println(c.execute(courses, storage));
                isRunning = c.isRunning();
            } catch (Exception e) {
                System.out.println(e.getMessage());
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
