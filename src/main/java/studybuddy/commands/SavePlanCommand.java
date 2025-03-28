package studybuddy.commands;

import studybuddy.CEGStudyBuddy;

public class SavePlanCommand extends Command {
    public SavePlanCommand(){
        super("saveplan");
    }
    @Override
    public String execute() throws CEGStudyBuddyException {
        CEGStudyBuddy.storage.saveCurrentPlan();
        System.out.println("Plan saved.");
        return "";
    }
}
