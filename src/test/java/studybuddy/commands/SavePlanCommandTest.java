package studybuddy.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import studybuddy.data.storage.StorageManager;
import studybuddy.data.course.CourseList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SavePlanCommandTest {
    private static final String TEST_DIR = "./testdata/";

    private CourseList courses;
    private StorageManager storage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() throws Exception {
        System.setOut(new PrintStream(outContent));
        storage = new StorageManager(TEST_DIR, courses);;
        File dir = new File(TEST_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        for (File file : dir.listFiles()) {
            file.delete();
        }
        courses = new CourseList("MyPlan");
    }

    @Test
    public void testSavePlanCommandPrintsSuccess() throws Exception {
        Command command = new SavePlanCommand();
        command.execute(courses, storage);
        String output = outContent.toString();
        assertTrue(output.contains("Plan saved successfully"));
        File file = new File(TEST_DIR + "MyPlan.bin");
        assertTrue(file.exists());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}
