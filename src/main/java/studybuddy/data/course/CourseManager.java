package studybuddy.data.course;

import java.io.InputStream;
import java.util.Scanner;

import com.google.gson.Gson;

import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.io.Ui;

public class CourseManager {
    private static final String FILEPATH = "data/Defined_Courses";
    private static final Ui ui = new Ui();

    public static void main(String[] args) {
        String s = "{\"code\": \"CS1010\", \"title\": \"Programming Met\", \"mc\": 4, " +
                "\"offerInSem1\": true, \"offerInSem2\": true, " +
                "takeInSem\": 0, \"takeInYear\": 0}";
        Course c1 = jsonToCourse(s);
        System.out.println(c1);
        try {
            InputStream file = loadCourseFile();
            Scanner scanner = new Scanner(file);
            s = scanner.nextLine();
            Course c2 = jsonToCourse(s);
            System.out.println(c2);
            s = scanner.nextLine();
            Course c3 = jsonToCourse(s);
            System.out.println(c3);
        } catch (CEGStudyBuddyException e) {
            ui.showMissingDefinedListMessage();
        }
        System.out.println(ifDefined("Hi"));
        System.out.println(ifDefined("CS1231"));
        System.out.println(getCourse("Hi"));
        System.out.println(getCourse("CS1231"));
        System.out.println(c1);
    }

    /**
     * Loads the defined courses file.
     *
     * @return The InputStream file representing the defined courses list.
     * @throws CEGStudyBuddyException If the defined courses list is missing.
     */
    private static InputStream loadCourseFile() throws CEGStudyBuddyException {
        InputStream file = CourseManager.class.getClassLoader().getResourceAsStream(FILEPATH);
        if (file == null) {
            throw new CEGStudyBuddyException(ui.missingDefinedListMessage());
        }
        return file;
    }

    /**
     * Convert a string in json format to a Course object.
     *
     * @param jsonString Input String array containing parameters of a Course object.
     * @return The Course object corresponding to the input.
     */
    public static Course jsonToCourse(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Course.class);
    }

    /**
     * Convert a Course object to a string in json format.
     *
     * @param course Input Course object to be converted.
     * @return Json format string.
     */
    public static String courseToJson(Course course) {
        Gson gson = new Gson();
        return gson.toJson(Course.class);
    }

    /**
     * Check if a course code is already defined in the file Defined_Courses.
     *
     * @param code Course code.
     * @return if the course code is already defined.
     */
    public static boolean ifDefined(String code) {
        InputStream file;
        try {
            file = loadCourseFile();
        } catch (CEGStudyBuddyException e) {
            ui.showMissingDefinedListMessage();
            return false;
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.contains("\"code\": \"" + code + "\"")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get a course with parameters defined as in the file Defined_Courses.
     *
     * @param code Course code.
     * @return A course object using predefined information in database Defined_Courses.
     */
    public static Course getCourse(String code) {
        InputStream file;
        try {
            file = loadCourseFile();
        } catch (CEGStudyBuddyException e) {
            ui.showMissingDefinedListMessage();
            return null;
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.contains("\"code\": \"" + code + "\"")) {
                return jsonToCourse(line);
            }
        }
        ui.showUndefinedCourseMessage();
        return null;
    }
}
