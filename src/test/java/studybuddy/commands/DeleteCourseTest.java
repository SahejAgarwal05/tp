package studybuddy.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.storage.StorageManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteCourseTest {
    private CourseList courses;
    private StorageManager storage = new StorageManager("./PlanData");

    @BeforeEach
    public void setup() {
        courses = new CourseList("test");
        courses.clear();
        courses.add(new Course("CS2113", "Software Engineering", 4, 2, 2));
        courses.add(new Course("CS2040", "Data Structures", 4, 2, 1));
    }

    @Test
    public void testDeleteExistingCourse() {
        try {
            DeleteCourse deleteCommand = new DeleteCourse("c/CS2113");
            String output = deleteCommand.execute(courses, storage);
            assertEquals("Course with code CS2113 has been deleted.", output);

            // Ensure the course has actually been removed
            ListCommand listCommand = new ListCommand();
            String listOutput = listCommand.execute(courses, storage);
            assertFalse(listOutput.contains("CS2113"));
        } catch (CEGStudyBuddyException e) {
            assert false : e.getMessage();
        }
    }

    @Test
    public void testDeleteNonExistingCourse() {
        String output;
        try {
            DeleteCourse deleteCommand = new DeleteCourse("c/CS9999");
            output = deleteCommand.execute(courses, storage);
        } catch (CEGStudyBuddyException e) {
            output = e.getMessage();
        }

        assertEquals("Course with code CS9999 not found.", output);
    }

    @Test
    public void testInvalidFormatMissingPrefix() {
        String output;
        try {
            DeleteCourse deleteCommand = new DeleteCourse("CS2040");
            output = deleteCommand.execute(courses, storage);
        } catch (CEGStudyBuddyException e) {
            output = e.getMessage();
        }
        assertEquals("Invalid format. Use: delete c/CODE", output);
    }

    @Test
    public void testInvalidCourseCodeFormat() {
        String output;
        try {
            DeleteCourse deleteCommand = new DeleteCourse("c/1234CS");
            output = deleteCommand.execute(courses, storage);
        } catch (CEGStudyBuddyException e) {
            output = e.getMessage();
        }
        assertEquals("Invalid course code format. Expected: CS2040, EE2026, CG2111A, etc.", output);
    }
}



