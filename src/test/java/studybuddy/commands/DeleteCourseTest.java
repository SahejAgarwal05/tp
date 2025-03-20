package studybuddy.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import studybuddy.CEGStudyBuddy;
import studybuddy.course.Course;

public class DeleteCourseTest {

    @BeforeEach
    public void setup() {
        // Adds a fresh test data before each test
        CEGStudyBuddy.courses.clear();
        CEGStudyBuddy.courses.add(new Course("CS2113", "Software Engineering", 4, 2, 2));
        CEGStudyBuddy.courses.add(new Course("CS2040", "Data Structures", 4, 2, 1));
    }

    @Test
    public void testDeleteExistingCourse() {
        DeleteCourse deleteCommand = new DeleteCourse("c/CS2113");
        String output = deleteCommand.execute();
        assertEquals("Course with code CS2113 has been deleted.", output);

        // Ensure the course has actually been removed
        ListCommand listCommand = new ListCommand();
        String listOutput = listCommand.execute();
        assertTrue(!listOutput.contains("CS2113"));
    }

    @Test
    public void testDeleteNonExistingCourse() {
        DeleteCourse deleteCommand = new DeleteCourse("c/CS9999");
        String output = deleteCommand.execute();
        assertEquals("Course with code CS9999 not found.", output);
    }

    @Test
    public void testInvalidDeleteFormat() {
        DeleteCourse deleteCommand = new DeleteCourse("invalidFormat");
        String output = deleteCommand.execute();
        assertEquals("Invalid format! Please use: delete c/CODE", output);
    }
}


