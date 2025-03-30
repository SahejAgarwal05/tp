package studybuddy.commands;
import studybuddy.CEGStudyBuddy;
import studybuddy.course.Course;

public class WorkloadForCommand extends Command {
    public WorkloadForCommand(String params) {
        super(params);
    }

    @Override
    public String execute() throws CEGStudyBuddyException {
        String[] splits = param.trim().split("s/");
        int sem = -1;
        int year = -1;
        try{
            sem = Integer.parseInt(splits[1].trim());
            year = Integer.parseInt(splits[0].substring(2).trim());
            assert (sem == 1 || sem == 2) && (year > 0);
        } catch (Exception e){
            throwException("Invalid year and/or sem");
        }
        String output = "These are the courses you will be taking";
        int totalWorkLoad = 0;
        int index = 1;
        for(Course course : CEGStudyBuddy.courses.getCourses()){
            if( course.getTakeInSem() == sem && course.getTakeInYear() == year){
                totalWorkLoad += course.getMc();
                output = output + "\n" + index + "." + course.toString();
                index++;
            }
        }
        output = output + "\nTotal work load: " + totalWorkLoad;
        return outputs;
    }
}
