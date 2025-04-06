package studybuddy.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.io.Ui;
import studybuddy.data.storage.StorageManager;

public class AddCommandTest {
    private static final Ui ui = new Ui();
    private static final String TEST_CODE = "CS2103";
    private static final String TEST_TITLE = "Software Engineering";
    private static final String TEST_MC = "4";
    private static final String TEST_YEAR = "2";
    private static final String TEST_SEM = "1";

    private CourseList courses;
    private StorageManager storage = new StorageManager("./PlanData");

    @BeforeEach
    public void setup() {
        courses = new CourseList("test");
    }

    @Test
    public void testValidAddCommand() {
        String input = formatInput(TEST_CODE, TEST_TITLE, TEST_MC, TEST_YEAR, TEST_SEM);
        AddCommand command = new AddCommand(input);
        String expected = "Course added: " + TEST_CODE + " - " + TEST_TITLE + " (" + TEST_MC + " MCs)";
        String output = executeCommand(command);
        assertEquals(expected, output);
    }

    @Test
    public void testDuplicateAddCommand() {
        String input = formatInput(TEST_CODE, TEST_TITLE, TEST_MC, TEST_YEAR, TEST_SEM);
        AddCommand command1 = new AddCommand(input);
        AddCommand command2 = new AddCommand(input);
        executeCommand(command1); // First one should succeed
        String output = executeCommand(command2); // Second should throw duplicate error
        assertEquals("This course is already added for the same year and semester.", output);
    }

    @Test
    public void testMissingFields() {
        String[] invalidInputs = {"c/CS2040 t/Data Structures mc/4 y/2", "t/Data Structures mc/4 y/2 s/1", "c/CS2040 mc/4 y/2 s/1", "c/CS2040 t/Data Structures y/2 s/1", "c/CS2040 t/Data Structures mc/4 s/1",};

        for (String input : invalidInputs) {
            AddCommand cmd = new AddCommand(input);
            String output = executeCommand(cmd);
            assertEquals("Missing fields. Please follow the format:\n" +
                    "add c/CODE t/TITLE mc/VALUE y/YEAR s/SEM", output);
        }
    }

    @Test
    public void testInvalidCourseCodeFormat() {
        String input = formatInput("1234CS", TEST_TITLE, TEST_MC, TEST_YEAR, TEST_SEM);
        AddCommand cmd = new AddCommand(input);
        String output = executeCommand(cmd);
        assertEquals("Invalid course code format.Expected: CS2040, EE2026, CG2111A etc.", output);
    }

    @Test
    public void testInvalidNumbers() {
        String input = formatInput(TEST_CODE, TEST_TITLE, "abc", TEST_YEAR, TEST_SEM);
        AddCommand cmd = new AddCommand(input);
        String output = executeCommand(cmd);
        assertEquals("Missing fields. Please follow the format:\nadd c/CODE t/TITLE mc/VALUE y/YEAR s/SEM", output);
    }

    @Test
    public void testOutOfRangeValues() {
        String input = formatInput(TEST_CODE, TEST_TITLE, "20", "5", "3");
        AddCommand cmd = new AddCommand(input);
        String output = executeCommand(cmd);
        assertEquals("Invalid MC value. MC must be between 1 and 12.", output);
    }

    // ------------------ Utility Methods ------------------

    private String formatInput(String code, String title, String mc, String year, String sem) {
        return "c/" + code + " t/" + title + " mc/" + mc + " y/" + year + " s/" + sem;
    }

    private String executeCommand(AddCommand command) {
        try {
            return command.execute(courses, storage);
        } catch (CEGStudyBuddyException e) {
            return e.getMessage();
        }
    }
}

