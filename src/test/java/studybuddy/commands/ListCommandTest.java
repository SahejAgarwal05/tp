package studybuddy.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.storage.StorageManager;

public class ListCommandTest {
    private CourseList courses = new CourseList("test");
    private StorageManager storage = new StorageManager("./PlanData", courses);

    @BeforeEach
    public void setup() {
        courses.clear();
        courses.add(new Course("CS2113", "Software Engineering", 4, 2, 2));
        courses.add(new Course("CS2040", "Data Structures", 4, 2, 1));
    }

    @Test
    public void testListCoursesOutput() {
        ListCommand listCommand = new ListCommand();
        String output = listCommand.execute(courses, storage);
        assertTrue(output.contains("CS2113"));
        assertTrue(output.contains("CS2040"));
        assertTrue(output.contains("Software Engineering"));
        assertTrue(output.contains("Data Structures"));
    }

    @Test
    public void testEmptyCourseList() {
        courses.clear();
        ListCommand listCommand = new ListCommand();
        String output = listCommand.execute(courses, storage);
        assertEquals("No courses added yet!", output);
    }
}

