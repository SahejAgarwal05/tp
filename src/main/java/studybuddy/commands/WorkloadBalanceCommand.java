package studybuddy.commands;
import studybuddy.CEGStudyBuddy;
import studybuddy.course.Course;

public class WorkloadBalanceCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
    workload_balance
        Displays the minimum and maximum semester workload for the course plan.
    """;

    public WorkloadBalanceCommand() {
        super("No param");
    }

    public String execute() throws CEGStudyBuddyException {
        int[] mCs = new int[] {0,0,0,0,0,0,0,0};
        for (Course course : CEGStudyBuddy.courses.getCourses()){
            int index = course.getTakeInYear() * 2 + course.getTakeInSem();
            mCs[index] += course.getTakeInSem();
        }
        int max = 0;
        int min = 1000000;
        for(int i : mCs){
            if(i > max){
                max = i;
            }
            if(i < min){
                min = i;
            }
        }

        throwException("please enter min or max");
        return "Max : " + max + "\nMin : " + min;
    }
}
