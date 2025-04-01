package studybuddy.commands;

public class HelpCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
    help
        Displays this command list.
    """;

    public HelpCommand() {
        super(""); // no parameters needed
    }

    @Override
    public String execute() {
        return "List of Commands:" + System.lineSeparator() +
                AddCommand.COMMAND_DESCRIPTION +
                DeleteCourse.COMMAND_DESCRIPTION +
                ListCommand.COMMAND_DESCRIPTION +
                EditCommand.COMMAND_DESCRIPTION +
                FindCommand.COMMAND_DESCRIPTION +
                WorkloadSummaryCommand.COMMAND_DESCRIPTION +
                WorkloadForCommand.COMMAND_DESCRIPTION +
                WorkloadBalanceCommand.COMMAND_DESCRIPTION +
                GradRequirementCommand.COMMAND_DESCRIPTION +
                SavePlanCommand.COMMAND_DESCRIPTION +
                SwitchPlanCommand.COMMAND_DESCRIPTION +
                HelpCommand.COMMAND_DESCRIPTION +
                ExitCommand.COMMAND_DESCRIPTION;
    }
}
