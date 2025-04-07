package studybuddy.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studybuddy.data.course.Course;
import studybuddy.data.course.CourseList;
import studybuddy.data.storage.StorageManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GradRequirementCommandTest {
    private CourseList courses;
    private StorageManager storage;

    @BeforeEach
    public void setup() {
        courses = new CourseList("test");
        storage = new StorageManager("./PlanData");
    }

    @Test
    public void testMeetsGraduationRequirement() {
        // Add courses totaling exactly 160 MCs
        for (int i = 0; i < 40; i++) {
            courses.add(new Course("CS" + i, "Sample Course " + i, 4, 1, 1));
        }

        GradRequirementCommand gradCommand = new GradRequirementCommand();
        String output = gradCommand.execute(courses, storage);

        assertTrue(output.contains("Congratulations! You have met the graduation requirement!"));
        assertTrue(output.contains("Graduation Requirement: 160 MCs"));
        assertTrue(output.contains("Remaining MCs: 0 MCs"));
    }

    @Test
    public void testDoesNotMeetGraduationRequirement() {
        // Add courses totaling 100 MCs
        for (int i = 0; i < 25; i++) {
            courses.add(new Course("CS" + i, "Sample Course " + i, 4, 2, 1));
        }

        GradRequirementCommand gradCommand = new GradRequirementCommand();
        String output = gradCommand.execute(courses, storage);

        assertTrue(output.contains("Oh no! You don't meet graduation requirement yet"));
        assertTrue(output.contains("Remaining MCs: 60 MCs"));
        assertTrue(output.contains("Keep on going Champ! You got this!"));
    }
}

