package studybuddy.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import studybuddy.course.*;

public class AddCommandTest {
    public static final String TEST_INPUT =
            "/cCS2113 /dSoftware Engineering & Object-Oriented Programming /mc4 /y2 /s2";
    public static final String TEST_CODE = "CS2113";
    public static final String TEST_DESC = "Software Engineering & Object-Oriented Programming";
    public static final int TEST_MC = 4;
    public static final int TEST_YEAR = 2;
    public static final int TEST_SEM = 2;

    public static void testInput() {
        AddCommand testAdd = new AddCommand(TEST_INPUT);
        assertEquals(6, testAdd.parseAdd().length);
    }

    public static Course generateTestCourse() {
        try {
            return new Course(TEST_CODE, TEST_DESC, TEST_MC, TEST_YEAR, TEST_SEM);
        } catch (Exception e) {
            fail("Test course should be valid by definition");
            return null;
        }
    }
}
