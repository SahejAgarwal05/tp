package studybuddy.commands;

import studybuddy.course.*;
import studybuddy.CEGStudyBuddy;
import studybuddy.commands.*;

public class TotalWorkLoadCommand extends Command {
    private final String SEMERRORMESSAGE = "Please input a valid semester";

    public TotalWorkLoadCommand(String param) {
        super(param);
    }

    @Override
    public String execute(String[] args) throws CEGStudyBuddyException{
        int sem = 0;
        try {
            sem = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throwException(SEMERRORMESSAGE);
        }

        int totalWorkLoad = 0;
        for(Course course : CEGStudyBuddy.courses){
            if(course.getTakeInSem() == sem){
                totalWorkLoad += course.getMc();;
            }
        }
        return totalWorkLoad + "";
    }
}