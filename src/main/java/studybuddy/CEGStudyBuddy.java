package studybuddy;

import java.util.ArrayList;
import java.util.Scanner;

import studybuddy.commands.Command;
import studybuddy.commands.AddCommand;
import studybuddy.commands.EditCommand;
import studybuddy.commands.DeleteCourse;
import studybuddy.commands.InvalidCommand;
import studybuddy.commands.ListCommand;
import studybuddy.course.Course;

public class CEGStudyBuddy {
    public static ArrayList<Course> courses = new ArrayList<>(); // Global course list
    public static boolean isRunning = true;
    public static Scanner in = new Scanner(System.in);

    // Exit the program
    static void exitProgram() {
        System.out.println("Bye");
        isRunning = false;
        System.exit(0);
    }

    // Read user input and split into command and param
    static String[] readInput() {
        String userInput = in.nextLine();
        return userInput.split(" ", 2);
    }

    // Parse the command and create the correct Command object
    static Command parseCommand(String[] inputParts) throws IndexOutOfBoundsException {
        Command c = null;
        switch (inputParts[0]) {
            case "add" -> c = new AddCommand(inputParts[1]);
            case "edit" -> c = new EditCommand(inputParts[1]);
            case "delete" -> c = new DeleteCourse(inputParts[1]);
            case "list" -> c = new ListCommand();
            case "exit" -> exitProgram();
            default -> c = new InvalidCommand(inputParts.length > 1 ? inputParts[1] : "");
        }
        return c;
    }

    // Main loop
    public static void main(String[] args) {
        System.out.println("Welcome to CEGStudyBuddy! Type a command:");
        while (isRunning) {
            String[] userInput = readInput();
            try {
                Command c = parseCommand(userInput);
                System.out.println(c.execute());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Missing Parameters");
            }
        }
    }
}
