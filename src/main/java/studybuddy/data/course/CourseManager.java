package studybuddy.data.course;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.google.gson.Gson;

public class CourseManager {
    private static final String FILEPATH = "./src/main/java/studybuddy/course/Defined_Courses";
    private static final File file = new File(FILEPATH);

    public static void main(String[] args) {
        String s = "{\"code\": \"CS1010\", \"title\": \"Programming Met\", \"mc\": 4, " +
                "\"offerInSem1\": true, \"offerInSem2\": true, " +
                "takeInSem\": 0, \"takeInYear\": 0, \"isCleared\": false}";
        Course c1 = jsonToCourse(s);
        File file = new File(FILEPATH);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            s = scanner.nextLine();
            Course c2 = jsonToCourse(s);
            s = scanner.nextLine();
            Course c3 = jsonToCourse(s);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(ifDefined("Hi"));
        System.out.println(ifDefined("CS1231"));
        System.out.println(getCourse("Hi"));
        System.out.println(getCourse("CS1231"));
    }

    public static Course jsonToCourse (String jsonString) {
        System.out.println(jsonString);
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Course.class);
    }

    public static boolean ifDefined(String code) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.contains("\"code\": \"" + code + "\"")) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Warning: Defined course list is missing.");
            throw new RuntimeException(e);
        }
        return false;
    }

    public static Course getCourse(String code) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.contains("\"code\": \"" + code + "\"")) {
                    return jsonToCourse(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Warning: Defined course list is missing.");
            throw new RuntimeException(e);
        }
        System.out.println("This course is not found in defined course list.");
        return null;
    }
}
