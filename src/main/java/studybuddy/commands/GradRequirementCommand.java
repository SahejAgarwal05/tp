package studybuddy.commands;

import studybuddy.CEGStudyBuddy;
import studybuddy.course.Course;

public class GradRequirementCommand extends Command {
    public static final String COMMAND_DESCRIPTION = """
    gradreq
        Displays information about your graduation requirements based on your plan.
    """;

    private static final int GRADUATION_MCS = 160;

    public GradRequirementCommand() {
        super(""); // no params needed
    }

    @Override
    public String execute() {
        int completedMCs = 0;

        for (Course course : CEGStudyBuddy.courses.getCourses()) {
            completedMCs += course.getMc();
        }

        int remaining = GRADUATION_MCS - completedMCs;
        StringBuilder sb = new StringBuilder();

        sb.append("Current MCs Completed: ").append(completedMCs).append(" MCs\n");
        sb.append("Graduation Requirement: ").append(GRADUATION_MCS).append(" MCs\n");
        sb.append("Remaining MCs: ").append(remaining).append(" MCs\n\n");

        if (remaining > 0) {
            sb.append("Oh no! You don't meet graduation requirement yet, you need to finish ")
                    .append(remaining).append(" more units of courses in order to graduate\n");
            sb.append("Keep on going Champ! You got this!\n");
            // 👍 Thumbs up made of hashtags
            sb.append("      ####\n");
            sb.append("     ######\n");
            sb.append("     ######\n");
            sb.append("     ######\n");
            sb.append("     ######\n");
            sb.append("     ######\n");
            sb.append("   ################\n");
            sb.append("   ################\n");
            sb.append("   ################\n");
            sb.append("   ################\n");
            sb.append("   ################\n");
            sb.append("   ################\n");
            sb.append("    ##############\n");
        } else {
            sb.append("Congratulations! You have met the graduation requirement!");
            // Your uploaded graduation ASCII art
            sb.append("     ##                             \n");
            sb.append("                           #######                          \n");
            sb.append("                        #############                       \n");
            sb.append("                         ###########                        \n");
            sb.append("                           #######                          \n");
            sb.append("                           ####### #                        \n");
            sb.append("                           ######  #                        \n");
            sb.append("                           ######  #                        \n");
            sb.append("                           ######  #                        \n");
            sb.append("                           ######  #                        \n");
            sb.append("                            ####                             \n");
            sb.append("                            ####                             \n");
            sb.append("                            ####                             \n");
            sb.append("                         ### ##  ###                         \n");
            sb.append("                       ####  # ######                       \n");
            sb.append("                      ####### ########                      \n");
            sb.append("                     ##################                     \n");
            sb.append("                     ###################                    \n");
            sb.append("                     ###################                    \n");
            sb.append("                    ####################                    \n");
            sb.append("                    ####################                    \n");
            sb.append("                    #####################                   \n");
            sb.append("                    #####################                   \n");
            sb.append("                    #####################                   \n");
            sb.append("                    #####################                   \n");
        }
        return sb.toString();
    }
}

