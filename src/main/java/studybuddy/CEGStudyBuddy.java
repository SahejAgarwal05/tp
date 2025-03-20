package studybuddy;

import java.util.ArrayList;
import java.util.Scanner;

import studybuddy.commands.Command;
import studybuddy.commands.WorkloadCommand;
import studybuddy.course.Course;

public class CEGStudyBuddy {
    public static ArrayList<Course> courses = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    static String[] readInput() {
        String userInput = sc.nextLine();
        return userInput.split(" ", 2);
    }

    static Command parseCommand(String[] inputParts) throws IndexOutOfBoundsException {
        Command c;
        switch (inputParts[0]) {
        case "workload": c = new WorkloadCommand(courses);
            break;
        default: c = null;
            break;
        }
        return c;
    }

    public static void main(String[] args) {
        boolean isExit = false;
        while (!isExit) {
            String[] input = readInput();
            Command c = parseCommand(input);
            System.out.print(c.execute());
            isExit = c.isExit();
        }
    }
}
