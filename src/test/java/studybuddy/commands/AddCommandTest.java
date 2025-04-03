package studybuddy.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.storage.StorageManager;

public class AddCommandTest {
    public static final String TEST_CODE = "CS2103";
    public static final String TEST_TITLE = "Software Engineering";
    public static final String TEST_MC = "4";
    public static final String TEST_SEM = "2";
    public static final String TEST_YEAR = "2";

    public static final String[] TEST_INVALID_PARAM = {"four", "1.5", "-2"};
    public static final String[] TEST_OOB_PARAM = {"0", "3", "6"};
    public static final String[] TEST_MISSING_PARAM = {"c/" + TEST_CODE,
                                                       "t/" + TEST_TITLE,
                                                       "mc/" + TEST_MC,
                                                       "y/" + TEST_YEAR,
                                                       "s/" + TEST_SEM,};

    public static final String ADD_COURSE_EXPECTED = "Course added: "
            + TEST_CODE + " - " + TEST_TITLE + " (" + TEST_MC + " MCs)";
    public static final String MISSING_INPUT_EXPECTED = "You missed an input.";
    public static final String INVALID_INPUT_EXPECTED = "You did not enter a valid number.";

    private CourseList courses;
    private StorageManager storage = new StorageManager("./PlanData", courses);

    @BeforeEach
    public void setup() {
        courses = new CourseList("test");
    }

    @Test
    public void testAddCourse() {
        String testInput = getTestInput(TEST_MC, TEST_SEM, TEST_YEAR);
        AddCommand addCourse = new AddCommand(testInput);
        String output = executeTest(addCourse);
        assertEquals(ADD_COURSE_EXPECTED, output);
    }

    @Test
    public void testMissingParam() {
        for (String param : TEST_MISSING_PARAM) {
            AddCommand addCourse = new AddCommand(param);
            String output = executeTest(addCourse);
            assertEquals(MISSING_INPUT_EXPECTED, output);
        }
    }

    @Test
    public void testInvalidParam() {
        for (String param : TEST_INVALID_PARAM) {
            String testInput = getTestInput(param, param, param);
            AddCommand addCourse = new AddCommand(testInput);
            String output = executeTest(addCourse);
            assertEquals(INVALID_INPUT_EXPECTED, output);
        }
    }

    @Test
    public void testOutOfBoundsParam() {
        String testInput = getTestInput(TEST_OOB_PARAM[0], TEST_OOB_PARAM[1], TEST_OOB_PARAM[2]);
        AddCommand addCourse = new AddCommand(testInput);
        String output = executeTest(addCourse);
        assertEquals(INVALID_INPUT_EXPECTED, output);
    }

    String getTestInput(String mc, String sem, String year) {
        return "c/" + AddCommandTest.TEST_CODE +
                " t/" + AddCommandTest.TEST_TITLE +
                " mc/" + mc +
                " y/" + year +
                " s/" + sem;
    }

    String executeTest(AddCommand cmd) {
        try {
            return cmd.execute(courses, storage);
        } catch (CEGStudyBuddyException e) {
            return e.getMessage();
        }
    }
}
