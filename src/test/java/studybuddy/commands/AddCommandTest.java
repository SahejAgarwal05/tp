package studybuddy.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import studybuddy.CEGStudyBuddy;

public class AddCommandTest {
    public static final String TEST_CODE = "CS2113";
    public static final String TEST_TITLE = "Software Engineering";
    public static final String TEST_MC = "4";
    public static final String TEST_SEM = "2";
    public static final String TEST_YEAR = "2";

    public static final String[] TEST_INVALID_PARAM = {"four", "1.5", "-2"};
    public static final String[] TEST_OOB_PARAM = {"0", "3", "6"};
    public static final String[] TEST_MISSING_PARAM =
            {"c/" + TEST_CODE, "t/" + TEST_TITLE, "mc/" + TEST_MC, "y/" + TEST_YEAR, "s/" + TEST_SEM,};

    public static final String ADD_COURSE_EXPECTED = "Course added: "
            + TEST_CODE + " - " + TEST_TITLE + " (" + TEST_MC + " MCs)";
    public static final String MISSING_INPUT_EXPECTED = "You missed an input.";
    public static final String INVALID_INPUT_EXPECTED = "You did not enter a valid number.";

    @BeforeEach
    public void setup() {
        CEGStudyBuddy.courses.clear();
    }

    public static String getTestInput(String c, String t, String mc, String sem, String year) {
        return "c/" + c +
                " t/" + t +
                " mc/" + mc +
                " y/" + year +
                " s/" + sem;
    }

    public static String execute(AddCommand cmd) {
        try {
            return cmd.execute();
        } catch (CEGStudyBuddyException e) {
            return e.getMessage();
        }
    }

    @Test
    public void testAddCourse() {
        String testInput = getTestInput(TEST_CODE, TEST_TITLE, TEST_MC, TEST_SEM, TEST_YEAR);
        AddCommand addCourse = new AddCommand(testInput);
        String output = execute(addCourse);
        assertEquals(ADD_COURSE_EXPECTED, output);
    }

    @Test
    public void testMissingParam() {
        for (String param : TEST_MISSING_PARAM) {
            AddCommand addCourse = new AddCommand(param);
            String output = execute(addCourse);
            assertEquals(MISSING_INPUT_EXPECTED, output);
        }
    }

    @Test
    public void testInvalidParam() {
        for (String param : TEST_INVALID_PARAM) {
            String testInput = getTestInput(TEST_CODE, TEST_TITLE, param, param, param);
            AddCommand addCourse = new AddCommand(testInput);
            String output = execute(addCourse);
            assertEquals(INVALID_INPUT_EXPECTED, output);
        }
    }

    @Test
    public void testOutOfBoundsParam() {
        String testInput = getTestInput(TEST_CODE, TEST_TITLE, TEST_OOB_PARAM[0], TEST_OOB_PARAM[1], TEST_OOB_PARAM[2]);
        AddCommand addCourse = new AddCommand(testInput);
        String output = execute(addCourse);
        assertEquals(INVALID_INPUT_EXPECTED, output);
    }
}
