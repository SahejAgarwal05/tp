package studybuddy.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.course.CourseManager;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.io.Ui;
import studybuddy.data.storage.StorageManager;

public class ReplaceCommandTest {
    private CourseList courses = new CourseList("test");
    private StorageManager storage = new StorageManager("./PlanData");
    private Ui ui = new Ui();

    @BeforeEach
    public void setup() {
        // c/OLD_CODE c/NEW_CODE mc/MODULAR_CREDITS y/YEAR s/SEMESTER t/TITLE
        // Adds a fresh test data before each test
        courses.clear();
        courses.add(new Course("CS2113", "Software Engineering", 4, 2, 2));
        courses.add(new Course("CS2040C", "Data Structures", 4, 2, 1));
        courses.add(new Course("CS1010", "Programming Methodology", 4, 1, 1));
        courses.add(new Course("DTK1234", "Design Thinking", 4, 1, 1));
        courses.add(new Course("PF1101", "Project Management", 4, 3, 2));
        courses.add(new Course("CDE2300", "DTK II", 4, 1, 2));
        courses.add(new Course("GEC1001", "Singapore Culture", 4, 1, 2));
    }

    private String execute(ReplaceCommand cmd) {
        try {
            return cmd.execute(courses, storage);
        } catch (CEGStudyBuddyException e) {
            return e.getMessage();
        }
    }

    @Test
    public void testReplaceExistingCourse1() throws CEGStudyBuddyException {
        String output = execute(new ReplaceCommand("c/PF1101 c/CS3242 t/Machine Learning mc/4 y/3 s/2"));
        assertEquals("Course \"PF1101\" has been successfully replaced with \"CS3242\".", output);

        FindCommand findCourse = new FindCommand("c/PF1101");
        String actualContent = findCourse.execute(courses, storage);
        String expectedContent = "Course " + "PF1101" + " not found in your course list.";
        assertEquals(expectedContent, actualContent);

        findCourse = new FindCommand("c/CS3242");
        actualContent = findCourse.execute(courses, storage);
        expectedContent = "Course Code: CS3242\n"
                + "Course Title: Machine Learning\n"
                + "Number of MCs: 4\n"
                + "Year and Sem: Y3S2";
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void testReplaceExistingCourse2() throws CEGStudyBuddyException {
        String output = execute(new ReplaceCommand("c/CS2040C c/ABCDEFG t/xxxxx mc/7 y/3 s/2"));
        assertEquals("Invalid course code format. Expected format: CS2040, EE2026, CG2111A", output);

        FindCommand findCourse = new FindCommand("c/CS2040C");
        String actualContent = findCourse.execute(courses, storage);
        String expectedContent = "Course Code: CS2040C\n" +
                "Course Title: Data Structures\n" +
                "Number of MCs: 4\n" +
                "Year and Sem: Y2S1";
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void testReplaceNonExistingCourse1() throws CEGStudyBuddyException {
        String output = execute(
                new ReplaceCommand("c/AB1234 c/CS1231S t/Discrete Structures mc/4 y/2 s/1"));
        assertEquals("Could not find a course with the code \"AB1234\" in your plan.", output);

        FindCommand findCourse = new FindCommand("c/CS1231S");
        String actualContent = findCourse.execute(courses, storage);
        String expectedContent = "Course " + "CS1231S" + " not found in your course list.";
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void testReplaceNonExistingCourse2() throws CEGStudyBuddyException {
        String output = execute(
                new ReplaceCommand("c/AB1234 c/CSCSCS t/CSCSCS mc/4 y/2 s/1"));
        assertEquals("Invalid course code format. Expected format: CS2040, EE2026, CG2111A", output);
    }

    @Test
    public void testReplaceNonExistingCourse3() throws CEGStudyBuddyException {
        String output = execute(
                new ReplaceCommand("c/ABABAB c/CG2023 t/Signal Processing mc/4 y/2 s/2"));
        assertEquals("Invalid course code format. Expected format: CS2040, EE2026, CG2111A", output);
    }

    @Test
    public void testReplaceExistingCourse3() throws CEGStudyBuddyException {
        String output = execute(
                new ReplaceCommand("c/PF1101 c/CG2023 y/2 s/2"));
        assertEquals(
                "Missing required fields! Please use format: replace c/OLD c/NEW t/TITLE mc/VALUE y/YEAR s/SEM",
                output);
    }
}
