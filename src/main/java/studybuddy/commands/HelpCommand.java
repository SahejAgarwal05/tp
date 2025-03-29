package studybuddy.commands;

public class HelpCommand extends Command {
    public HelpCommand() {
        super(""); // no parameters needed
    }

    @Override
    public String execute() {
        return """
                List of Commands:
                add c/CODE t/TITLE mc/MODULAR_CREDITS y/YEAR s/SEMESTER
                    Adds a course to your plan with the given parameters.
                delete c/CODE
                    Deletes a course from your plan.
                list
                    Lists all courses in your plan.
                edit c/CODE [t/TITLE] [mc/MODULAR_CREDITS] [y/YEAR] [s/SEMESTER]
                    Edits a course with the given parameters.
                find c/CODE
                    Finds the course with the given code in your plan.
                workload_summary
                    Displays the total workload for all semesters.
                workload_for y/YEAR s/SEMESTER
                    Displays the courses and the total workload for the given semester.
                required_workload
                    Displays the minimum and maximum semester workload for the course plan.
                gradreq
                    Displays information about your graduation requirements based on your plan.
                save
                    Saves the current course plan.
                switch_plan
                    Saves the current course plan and shows the menu to switch plans.
                help
                    Displays this command list.
                exit
                    Exits the program.""";
    }
}
