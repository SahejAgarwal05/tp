package studybuddy;

import java.util.ArrayList;
import java.util.Scanner;

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
import studybuddy.course.Course;

public class CEGStudyBuddy {
    public static ArrayList<Course> courses = new ArrayList<>(); // Global course list
    public static boolean isRunning = true;
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to CEGStudyBuddy! Type a command:");
        while (isRunning) {
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
            case "add" -> c = new AddCommand(inputParts[1]);
            case "edit" -> c = new EditCommand(inputParts[1]);
            case "workload" -> c = new WorkloadCommand("", courses);
            case "help" -> c = new HelpCommand();
            case "exit" -> exitProgram();
            case "total_workload" -> c = new TotalWorkLoad(inputParts[1]);
            case "required_workload" -> c = new RequiredWorkLoad(inputParts[1]);
            case "delete" -> c = new DeleteCourse(inputParts[1]);
            case "list" -> c = new ListCommand();
            default -> c = new InvalidCommand();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CEGStudyBuddyException("You did not input any parameters.");
        }

        return c;
    }
}
