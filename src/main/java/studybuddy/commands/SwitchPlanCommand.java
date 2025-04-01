package studybuddy.commands;

import studybuddy.CEGStudyBuddy;

public class SwitchPlanCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
    switch_plan
        Saves the current course plan and shows the menu to switch plans.
    """;

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
