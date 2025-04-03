package studybuddy.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.storage.StorageManager;

public class CourseManagerTest {
    private CourseList courses = new CourseList("test");
    private StorageManager storage = new StorageManager("./PlanData", courses);

    @Test
    public void testAddCourse1() {
        AddCommand addCourse = new AddCommand("c/CDE2501");
        String output = "";
        String expected = "Course added: CDE2501 - Liveable Cities (4 MCs)";
        try {
            output = addCourse.execute(courses, storage);
        } catch (CEGStudyBuddyException e) {
            output = e.getMessage();
        }
        assertEquals(expected, output);
    }

    @Test
    public void testAddCourse2() {
        AddCommand addCourse = new AddCommand("c/PF1101A t/Project Management mc/4 y/2 s/2");
        String output = "";
        String expected = "Course added: PF1101A - Project Management and Finance (4 MCs)";
        try {
            output = addCourse.execute(courses, storage);
        } catch (CEGStudyBuddyException e) {
            output = e.getMessage();
        }
        assertEquals(expected, output);
    }

    @Test
    public void testAddCourse3() {
        AddCommand addCourse = new AddCommand("c/CDE2301 t/Value Creation mc/4 y/2 s/1");
        String output = "";
        String expected = "Course added: CDE2301 - Value Creation (4 MCs)";
        try {
            output = addCourse.execute(courses, storage);
        } catch (CEGStudyBuddyException e) {
            output = e.getMessage();
        }
        assertEquals(expected, output);
    }
}
