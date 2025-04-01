package studybuddy.commands;

import studybuddy.CEGStudyBuddy;

public class SwitchPlanCommand extends Command {
    public SwitchPlanCommand() {
        super("");
    }

    @Override
    public String execute() throws CEGStudyBuddyException {
        CEGStudyBuddy.storage.saveCurrentPlan();
        CEGStudyBuddy.storage.initializePlan();
        return "";
    }
}
