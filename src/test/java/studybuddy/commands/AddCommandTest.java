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
    private final StorageManager storage = new StorageManager("./PlanData");

    @BeforeEach
    public void setup() {
        courses = new CourseList("test");
    }

    @Test
    public void testValidAddCommand() {
        String input = formatInput(TEST_CODE, TEST_TITLE, TEST_MC, TEST_YEAR, TEST_SEM);
        AddCommand command = new AddCommand(input);
        String expected = "Course added: " + TEST_CODE + " - " + TEST_TITLE + " (" + TEST_MC + " MCs)";
        assertEquals(expected, execute(command));
    }

    @Test
    public void testDuplicateCourse() {
        String input = formatInput(TEST_CODE, TEST_TITLE, TEST_MC, TEST_YEAR, TEST_SEM);
        execute(new AddCommand(input));
        String output = execute(new AddCommand(input));
        assertEquals("This course is already added in year " +
                TEST_YEAR + " semester " + TEST_SEM, output);
        input = "add c/CG1111A y/1 s/1";
        AddCommand command = new AddCommand(input);
        String expected = "Course added: " + "CG1111A" + " - " +
                "Engineering Principles and Practice I" + " (" + 4 + " MCs)";
        assertEquals(expected, execute(command));
        input = "add c/CG1111A y/1 s/1";
        command = new AddCommand(input);
        expected = "This course is already added in year " +
                1 + " semester " + 1;
        assertEquals(expected, execute(command));
        input = "add c/CG1111A t/EPP1 mc/4 y/1 s/1";
        command = new AddCommand(input);
        expected = "This course is already added in year " +
                1 + " semester " + 1;
        assertEquals(expected, execute(command));
    }

    @Test
    public void testMissingFields() {
        assertEquals(getMissingFieldsMsg(), execute(new AddCommand("t/Title mc/4 y/2 s/1")));
        assertEquals(getMissingFieldsMsg(), execute(new AddCommand("c/CS2040 mc/4 y/2 s/1")));
        assertEquals(getMissingFieldsMsg(), execute(new AddCommand("c/CS2040 t/Title y/2 s/1")));
        assertEquals(getMissingFieldsMsg(), execute(new AddCommand("c/CS2040 t/Title mc/4 s/1")));
        assertEquals(getMissingFieldsMsg(), execute(new AddCommand("c/CS2040 t/Title mc/4 y/2")));
    }

    @Test
    public void testInvalidCourseCodeFormat() {
        String input = formatInput("1234CS", TEST_TITLE, TEST_MC, TEST_YEAR, TEST_SEM);
        AddCommand cmd = new AddCommand(input);
        assertEquals("Invalid course code format. Expected: CS2040, EE2026, CG2111A etc."
                , execute(cmd));
    }

    @Test
    public void testDecimalInputs() {
        AddCommand cmd = new AddCommand("c/CS2040 t/Title mc/3.5 y/2.5 s/1.5");
        assertEquals("Invalid input: MC, year, and semester must be whole numbers, not decimals."
                , execute(cmd));
    }

    @Test
    public void testOutOfRangeValues() {
        AddCommand cmd = new AddCommand("c/CS2040 t/Title mc/20 y/6 s/3");
        assertEquals("Invalid MC value. MC must be an even number between 0 and 12.", execute(cmd));
    }

    private String formatInput(String code, String title, String mc, String year, String sem) {
        return "c/" + code + " t/" + title + " mc/" + mc + " y/" + year + " s/" + sem;
    }

    private String execute(AddCommand cmd) {
        try {
            return cmd.execute(courses, storage);
        } catch (CEGStudyBuddyException e) {
            return e.getMessage();
        }
    }

    private String getMissingFieldsMsg() {
        return """
            Missing or disordered fields. Please follow the format:
            add c/CODE t/TITLE mc/VALUE y/YEAR s/SEM""";
    }

    @Test
    public void testAddDuplicateCourse() {
        String input = "add c/CG1111A y/1 s/1";
        AddCommand command = new AddCommand(input);
        String expected = "Course added: " + "CG1111A" + " - " +
                "Engineering Principles and Practice I" + " (" + 4 + " MCs)";
        assertEquals(expected, execute(command));
        input = "add c/CG1111A y/1 s/1";
        command = new AddCommand(input);
        expected = "This course is already added in year " +
                1 + " semester " + 1;
        assertEquals(expected, execute(command));
        input = "add c/CG1111A t/EPP1 mc/4 y/1 s/1";
        command = new AddCommand(input);
        expected = "This course is already added in year " +
                1 + " semester " + 1;
        assertEquals(expected, execute(command));
    }

    @Test
    public void testAddDummy() {
        AddCommand cmd = new AddCommand("c/DUM01 t/Title mc/2 y/2 s/1");
        assertEquals("Invalid course code format. Course code cannot contain \"DUM\"."
                , execute(cmd));
        cmd = new AddCommand("c/DUM900 t/Title mc/0 y/2 s/1");
        assertEquals("Invalid course code format. Course code cannot contain \"DUM\"."
                , execute(cmd));
        cmd = new AddCommand("c/DUM10000 t/Title mc/4 y/2 s/1");
        assertEquals("Invalid course code format. Expected: CS2040, EE2026, CG2111A etc."
                , execute(cmd));
    }
}







