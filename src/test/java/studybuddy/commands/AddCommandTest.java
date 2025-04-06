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
        assertEquals(expected, execute(command));
    }

    @Test
    public void testDuplicateCourse() {
        String input = formatInput(TEST_CODE, TEST_TITLE, TEST_MC, TEST_YEAR, TEST_SEM);
        execute(new AddCommand(input));
        String output = execute(new AddCommand(input));
        assertEquals("This course is already added for the same year and semester.", output);
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
        assertEquals("Invalid MC value. MC must be between 1 and 12.", execute(cmd));
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
                Missing fields. Please follow the format:
                + add c/CODE t/TITLE mc/VALUE y/YEAR s/SEM
                + or the input is decimal :(""";
    }
}







