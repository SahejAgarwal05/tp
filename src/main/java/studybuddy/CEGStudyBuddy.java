package studybuddy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import studybuddy.course.*;
import studybuddy.commands.*;

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
            case "wokload"-> c = new TotalWorkLoadCommand(inputParts[1]);
            case "requiredworkload"-> c = new RequiredWorkLoadCommand(inputParts[1]);
        }
        return c;
    }

    public static void main(String[] args) {
        while (isRunning) {
            String[] userInput = readInput();
            try {
                Command c = parseCommand(userInput);
                System.out.println(c.execute());
            } catch (Exception e) {
                System.out.println("Missing Parameters");
            }
        }
    }
}