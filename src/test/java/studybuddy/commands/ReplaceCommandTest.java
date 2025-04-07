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

    @Test
    public void testReplaceExistingCourse1() throws CEGStudyBuddyException {
        ReplaceCommand replaceCourse = new ReplaceCommand("c/PF1101 c/CS3242 t/Machine Learning mc/4 y/3 s/2");
        String output = replaceCourse.execute(courses, storage);
        assertEquals("Course \"PF1101\" has been successfully replaced with \"CS3242\".", output);

        // Ensure the course has actually been edited
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
}
