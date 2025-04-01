package studybuddy.commands;

public class HelpCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
    help
        Displays this command list.""";

    public HelpCommand() {
        super(""); // no parameters needed
    }

    @Override
    public String execute() {
        return "List of Commands:" + System.lineSeparator() +
                AddCommand.COMMAND_DESCRIPTION + System.lineSeparator() +
                DeleteCourse.COMMAND_DESCRIPTION + System.lineSeparator() +
                ListCommand.COMMAND_DESCRIPTION + System.lineSeparator() +
                EditCommand.COMMAND_DESCRIPTION + System.lineSeparator() +
                FindCommand.COMMAND_DESCRIPTION + System.lineSeparator() +
                WorkloadSummaryCommand.COMMAND_DESCRIPTION + System.lineSeparator() +
                WorkloadForCommand.COMMAND_DESCRIPTION + System.lineSeparator() +
                WorkloadBalanceCommand.COMMAND_DESCRIPTION + System.lineSeparator() +
                GradRequirementCommand.COMMAND_DESCRIPTION + System.lineSeparator() +
                SavePlanCommand.COMMAND_DESCRIPTION + System.lineSeparator() +
                SwitchPlanCommand.COMMAND_DESCRIPTION + System.lineSeparator() +
                HelpCommand.COMMAND_DESCRIPTION + System.lineSeparator() +
                ExitCommand.COMMAND_DESCRIPTION;
    }
}
