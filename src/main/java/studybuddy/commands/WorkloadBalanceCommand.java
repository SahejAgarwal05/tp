package studybuddy.commands;
import studybuddy.CEGStudyBuddy;
import studybuddy.course.Course;

public class WorkloadBalanceCommand extends Command {
    public WorkloadBalanceCommand(String param) {
        super(param);
    }

    public String execute() throws CEGStudyBuddyException {
        int[] mCs = new int[] {0,0,0,0,0,0,0,0};
        for (Course course : CEGStudyBuddy.courses.getCourses()){
            int index = course.getTakeInYear() * 2 + course.getTakeInSem();
            mCs[index] += course.getTakeInSem();
        }
        if(param.trim().equals("max")){
            int max = 0;
            for(int i : mCs){
                if(i > max){
                    max = i;
                }
            }
            return max + "";
        }
        if (param.trim().equals("min")){
            int min = 0;
            for(int i : mCs){
                if(i < min){
                    min = i;
                }
            }
            return min + "";
        }
        throwException("please enter min or max");
        return "";
    }
}
