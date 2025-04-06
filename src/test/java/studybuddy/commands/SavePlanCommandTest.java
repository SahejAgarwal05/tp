package studybuddy.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import studybuddy.CEGStudyBuddy;
import studybuddy.data.course.Course;
import studybuddy.data.storage.StorageManager;
import studybuddy.data.course.CourseList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SavePlanCommandTest {
    private static final String TEST_DIR = "./testdata/";

    private StorageManager storage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() throws Exception {
        System.setOut(new PrintStream(outContent));
        storage = new StorageManager(TEST_DIR);;
        File dir = new File(TEST_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        for (File file : dir.listFiles()) {
            file.delete();
        }

    }

    @Test
    public void testSavePlanCommandPrintsSuccess() throws Exception {
        Command command = new SavePlanCommand();
        CEGStudyBuddy.courses = new CourseList("MyPlan");
        String output = command.execute(CEGStudyBuddy.courses, storage);
        assertTrue(output.contains("Plan saved successfully"));
        File file = new File(TEST_DIR + "MyPlan.txt");
        assertTrue(file.exists());
    }

    @Test
    public void testSavePlanCommandWithCourses() throws Exception {
        Command command = new SavePlanCommand();
        CEGStudyBuddy.courses = new CourseList("MyPlan2");
        CEGStudyBuddy.courses.addCourse(new Course("CS2113", "Software Engineering", 4,2,2));
        CEGStudyBuddy.courses.addCourse(new Course("EE2026", "Digital Design", 4,2,1));
        String output = command.execute(CEGStudyBuddy.courses, storage);
        assertTrue(output.contains("Plan saved successfully"));
        File file = new File(TEST_DIR + "MyPlan2.txt");
        assertTrue(file.exists());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        File dir = new File(TEST_DIR);
        for (File file : dir.listFiles()) {
            file.delete();
        }
        dir.delete();
    }
}
