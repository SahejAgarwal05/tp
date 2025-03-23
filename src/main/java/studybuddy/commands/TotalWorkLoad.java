package studybuddy.commands;
import studybuddy.CEGStudyBuddy;
import studybuddy.course.Course;

public class TotalWorkLoad extends Command {
    public TotalWorkLoad(String params) {
        super(params);
    }
    @Override
    public String execute() throws CEGStudyBuddyException {
        String[] splits = param.trim().split("/s");
        int sem = -1;
        int year = -1;
        try{
            sem = Integer.parseInt(splits[1].trim());
            year = Integer.parseInt(splits[0].substring(2).trim());
        } catch (Exception e){
            throwException("Invalid year and/or sem");
        }
        int totalWorkLoad = 0;
        for(Course course : CEGStudyBuddy.courses){
            if( course.getTakeInSem() == sem && course.getTakeInYear() == year){
                totalWorkLoad += course.getMc();;
            }
        }
        return totalWorkLoad + "";
    }

}