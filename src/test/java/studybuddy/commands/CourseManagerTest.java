package studybuddy.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import studybuddy.data.course.CourseList;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.storage.StorageManager;

public class CourseManagerTest {

    private final CourseList courses = new CourseList("test");
    private final StorageManager storage = new StorageManager("./PlanData");

    @Test
    public void testAddDefinedCourseCDE2501() {
        AddCommand addCourse = new AddCommand("c/CDE2501 t/Liveable Cities mc/4 y/2 s/1");
        String expected = "Course added: CDE2501 - Liveable Cities (4 MCs)";
        assertEquals(expected, execute(addCourse));
    }

    @Test
    public void testAddDefinedCoursePF1101A() {
        AddCommand addCourse = new AddCommand(
                "c/PF1101A t/Project Management and Finance mc/4 y/2 s/2"
        );
        String expected = "Course added: PF1101A - Project Management and Finance (4 MCs)";
        assertEquals(expected, execute(addCourse));
    }

    @Test
    public void testAddDefinedCourseCDE2301() {
        AddCommand addCourse = new AddCommand("c/CDE2301 t/Value Creation mc/4 y/2 s/1");
        String expected = "Course added: CDE2301 - Value Creation (4 MCs)";
        assertEquals(expected, execute(addCourse));
    }

    private String execute(AddCommand cmd) {
        try {
            return cmd.execute(courses, storage);
        } catch (CEGStudyBuddyException e) {
            return e.getMessage();
        }
    }
}

