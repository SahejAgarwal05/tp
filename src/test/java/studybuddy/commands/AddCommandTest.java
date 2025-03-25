package studybuddy.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import studybuddy.CEGStudyBuddy;
import studybuddy.course.Course;

public class AddCommandTest {
    public static final String TEST_CODE = "CS2113";
    public static final String TEST_TITLE = "Software Engineering";
    public static final String TEST_MC = "4";
    public static final String TEST_SEM = "2";
    public static final String TEST_YEAR = "2";

    public static final String ADD_COURSE_EXPECTED = "Course added: "
            + TEST_CODE + " - " + TEST_TITLE + " (" + TEST_MC + " MCs)";
    public static final String MISSING_INPUT_EXPECTED = "You missed an input.";

    public static final String TEST_INPUT = "c/" + TEST_CODE +
            "t/" + TEST_TITLE +
            "mc/" + TEST_MC +
            "y/" + TEST_YEAR +
            "s/" + TEST_SEM;
    public static final String TEST_MISSING_PARAM = "c/" + TEST_CODE;

    @BeforeEach
    public void setup() {
        CEGStudyBuddy.courses.clear();
    }

    @Test
    public void testAddCourse() {
        AddCommand addCourse = new AddCommand(TEST_INPUT);
        String output = addCourse.execute();
        assertEquals(ADD_COURSE_EXPECTED, output);
    }

    @Test
    public void testMissingParam() {
        AddCommand addCourse = new AddCommand(TEST_MISSING_PARAM);
        String output = addCourse.execute();
        assertEquals(MISSING_INPUT_EXPECTED, output);
    }
}
