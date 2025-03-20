package studybuddy;
import studybuddy.course.*;
import java.util.ArrayList;
import studybuddy.commands.*;

public class CEGStudyBuddy {
    public static ArrayList<Course> courses = new ArrayList<>();
    public static int currentYear = 2;
    public static int currentSemester = 1;
    public static void main(String[] args) throws Exception {
        RequiredWorkLoadCommand requiredWorkLoadCommandEg = new RequiredWorkLoadCommand("I love you");
        String[] requiredWorkLoadCommandEgArgs = new String[]{"2", "max"};
        try {
            System.out.println(requiredWorkLoadCommandEg.execute(requiredWorkLoadCommandEgArgs));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
