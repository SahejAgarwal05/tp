package studybuddy.commands;

public class HelpCommand extends Command {
    public HelpCommand(String param) {
        super(param);
    }

    @Override
    public String execute() {
        return """
                List of Commands:
                add c/CODE t/TITLE mc/MODULAR_CREDITS y/YEAR s/SEMESTER
                    Adds a course with the given parameters.
                delete c/CODE
                    Deletes a course.
                list
                    Lists all courses.
                edit c/CODE [t/TITLE] [mc/MODULAR_CREDITS] [y/YEAR] [s/SEMESTER]
                    Edits a course with the given parameters.
                workload
                    Displays the total workload for each semester.
                help
                    Displays this command list.
                exit
                    Exits the program.""";
    }
}
