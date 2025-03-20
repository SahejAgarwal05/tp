package studybuddy;

<<<<<<< HEAD
import studybuddy.course.Course;
import studybuddy.course.CourseList;
import studybuddy.commands.DeleteCourse;

import java.util.Scanner;

public class CEGStudyBuddy {
    public static void main(String[] args) {
        CourseList courseList = new CourseList();

        // Adding sample courses for testing
        courseList.addCourse(new Course("CS", "2040", "Data Structures", 4, true, false));
        courseList.addCourse(new Course("MA", "1101", "Linear Algebra", 4, false, true));

        DeleteCourse deleteCourseCommand = new DeleteCourse(courseList);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to CEGStudyBuddy! Type a command:");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting CEGStudyBuddy...");
                break;
            }

            String[] tokens = input.split("\\s+");
            String commandWord = tokens[0];

            switch (commandWord) {
                case "list":
                    System.out.println(courseList.listCourses());
                    break;

                case "delete":
                    // Example input: delete c/CS2040
                    if (tokens.length < 2) {
                        System.out.println("Please specify course code in format: delete c/CODE");
                        break;
                    }
                    String[] deleteArgs = tokens[1].split("/");
                    System.out.println(deleteCourseCommand.execute(deleteArgs));
                    break;

                default:
                    System.out.println("Unknown command! Available commands: list, delete, exit.");
            }
        }

        scanner.close();
=======
import java.util.ArrayList;
import java.util.Scanner;

import studybuddy.commands.Command;
import studybuddy.commands.AddCommand;
import studybuddy.commands.EditCommand;
import studybuddy.commands.InvalidCommand;
import studybuddy.course.Course;

public class CEGStudyBuddy {
    public static ArrayList<Course> courses = new ArrayList<>();
    public static boolean isRunning = true;
    public static Scanner in = new Scanner(System.in);

    static void exitProgram() {
        System.out.println("Bye");
        isRunning = false;
        System.exit(0);
    }

    static String[] readInput() {
        String userInput = in.nextLine();
        return userInput.split(" ", 2);
    }

    static Command parseCommand(String[] inputParts) throws IndexOutOfBoundsException {
        Command c = null;
        switch (inputParts[0]) {
        case "add" -> c = new AddCommand(inputParts[1]);
        case "edit" -> c = new EditCommand(inputParts[1]);
        case "exit" -> exitProgram();
        default -> c = new InvalidCommand(inputParts[1]);
        }
        return c;
    }

    public static void main(String[] args) {
        while (isRunning) {
            String[] userInput = readInput();
            try {
                Command c = parseCommand(userInput);
                System.out.println(c.execute());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Missing Parameters");
            }
        }
>>>>>>> 6bc66244d659bce3eacb850ed281822281d3a08f
    }
}

