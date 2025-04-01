package studybuddy.commands;

import studybuddy.CEGStudyBuddy;

public class SavePlanCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
    save
        Saves the current course plan.""";

    public SavePlanCommand() {
        super("");
    }

    @Override
    public String execute() throws CEGStudyBuddyException {
        CEGStudyBuddy.storage.saveCurrentPlan();
        System.out.println("Plan saved.");
        return "";
    }
}
