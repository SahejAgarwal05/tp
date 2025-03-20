package studybuddy.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import studybuddy.CEGStudyBuddy;
import studybuddy.course.Course;

public class ListCommandTest {

    @BeforeEach
    public void setup() {
        CEGStudyBuddy.courses.clear();
        CEGStudyBuddy.courses.add(new Course("CS2113", "Software Engineering", 4, 2, 2));
        CEGStudyBuddy.courses.add(new Course("CS2040", "Data Structures", 4, 2, 1));
    }

    @Test
    public void testListCoursesOutput() {
        ListCommand listCommand = new ListCommand();
        String output = listCommand.execute();
        assertTrue(output.contains("CS2113"));
        assertTrue(output.contains("CS2040"));
        assertTrue(output.contains("Software Engineering"));
        assertTrue(output.contains("Data Structures"));
    }

    @Test
    public void testEmptyCourseList() {
        CEGStudyBuddy.courses.clear();
        ListCommand listCommand = new ListCommand();
        String output = listCommand.execute();
        assertEquals("No courses added yet!", output);
    }
}

