package studybuddy.commands;

import studybuddy.CEGStudyBuddy;

public class SwiitchPlanCommand  extends Command {
    public SwiitchPlanCommand() {
        super("swiitchplan");
    }
    @Override
    public String execute() throws CEGStudyBuddyException{
        CEGStudyBuddy.storage.saveCurrentPlan();
        CEGStudyBuddy.storage.initializePlan();
        return "";
    }
}
