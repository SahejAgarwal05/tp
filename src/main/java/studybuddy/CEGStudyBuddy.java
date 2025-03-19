package studybuddy;

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
    }
}

