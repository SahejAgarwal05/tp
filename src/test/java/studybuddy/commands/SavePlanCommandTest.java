package studybuddy.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import studybuddy.CEGStudyBuddy;
import studybuddy.StorageManager;
import studybuddy.course.CourseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class SavePlanCommandTest {
    private static final String TEST_DIR = "./testdata/";
    private StorageManager storage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() throws Exception {
        System.setOut(new PrintStream(outContent)); // Redirect System.out to capture output
        storage = new StorageManager(TEST_DIR);
        CEGStudyBuddy.storage = storage;
        File dir = new File(TEST_DIR);
        if (!dir.exists()) dir.mkdirs();
        for (File file : dir.listFiles()) file.delete();
        CEGStudyBuddy.courses = new CourseList("MyPlan");
    }

    @Test
    public void testSavePlanCommandPrintsSuccess() throws Exception {
        Command command = new SavePlanCommand();
        command.execute();
        String output = outContent.toString();
        assertTrue(output.contains("Plan saved successfully"));
        File file = new File(TEST_DIR + "MyPlan.bin");
        assertTrue(file.exists());
    }

    @AfterEach
    public void tearDown() {
        // Reset System.out after test
        System.setOut(originalOut);
    }
}
