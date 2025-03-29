package studybuddy;

import java.util.Scanner;
import studybuddy.course.CourseList;
import studybuddy.commands.CEGStudyBuddyException;
import studybuddy.commands.Command;
import studybuddy.commands.AddCommand;
import studybuddy.commands.EditCommand;
import studybuddy.commands.HelpCommand;
import studybuddy.commands.DeleteCourse;
import studybuddy.commands.InvalidCommand;
import studybuddy.commands.TotalWorkLoad;
import studybuddy.commands.ListCommand;
import studybuddy.commands.RequiredWorkLoad;
import studybuddy.commands.WorkloadCommand;
import studybuddy.commands.FindCommand;
import studybuddy.commands.GradRequirementCommand;
import studybuddy.commands.SwiitchPlanCommand;
import studybuddy.commands.SavePlanCommand;

public class CEGStudyBuddy {
    public static CourseList courses;// Global course list
    public static boolean isRunning = true;
    public static Scanner in = new Scanner(System.in);
    public static StorageManager storage = new StorageManager("./PlanData");

    public static void main(String[] args) {
        System.out.println("Welcome to CEGStudyBuddy!");
        storage.initializePlan();
        while (isRunning) {
            System.out.print("Enter command: ");
            String[] userInput = readInput();
            try {
                Command c = parseCommand(userInput);
                System.out.println(c.execute());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Exits the program.
     */
    static void exitProgram() {
        System.out.println("Bye");
        isRunning = false;
        System.exit(0);
    }

    /**
     * Reads the input and parses it into a String array.
     *
     * @return The String array containing the command and parameters.
     */
    static String[] readInput() {
        String userInput = in.nextLine();
        return userInput.split(" ", 2);
    }

    /**
     * Parses the input into a command and returns the Command object for the command.
     *
     * @param inputParts Input String array containing the command and parameters.
     * @return The Command object corresponding to the input.
     * @throws IndexOutOfBoundsException If parameters are not included (inputParts.len() = 1).
     */
    static Command parseCommand(String[] inputParts) throws CEGStudyBuddyException {
        Command c = null;
        try {
            switch (inputParts[0]) {
            case "save" -> c = new SavePlanCommand();
            case "switch_plan" -> c = new SwiitchPlanCommand();
            case "add" -> c = new AddCommand(inputParts[1]);
            case "edit" -> c = new EditCommand(inputParts[1]);
            case "workload" -> c = new WorkloadCommand("", courses.getCourses());
            case "help" -> c = new HelpCommand();
            case "exit" -> exitProgram();
            case "total_workload" -> c = new TotalWorkLoad(inputParts[1]);
            case "required_workload" -> c = new RequiredWorkLoad(inputParts[1]);
            case "delete" -> c = new DeleteCourse(inputParts[1]);
            case "list" -> c = new ListCommand();
            case "find" -> c = new FindCommand(inputParts[1]);
            case "gradreq" -> c = new GradRequirementCommand();
            default -> c = new InvalidCommand();
            }
        } catch (Exception e) {
            throw new CEGStudyBuddyException(e.getMessage());
        }
        return c;
    }
}
