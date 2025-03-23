package studybuddy.commands;
import studybuddy.CEGStudyBuddy;
import studybuddy.course.Course;

public class RequiredWorkLoad extends Command {
    public RequiredWorkLoad(String param) {
        super(param);
    }
    public String execute() throws CEGStudyBuddyException {
        int[] MCs = new int[] {0,0,0,0,0,0,0,0};
        int mc;
        for (Course course : CEGStudyBuddy.courses){
            int index = course.getTakeInYear() * 2 + course.getTakeInSem();
            mc = course.getTakeInSem();
            assert mc >= 0;
            MCs[index] += mc;
        }
        if(param.trim().equals("max")){
            int max = 0;
            for(int i : MCs){
                if(i > max){
                    max = i;
                }
            }
            return max + "";
        }
        if (param.trim().equals("min")){
            int min = 0;
            for(int i : MCs){
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