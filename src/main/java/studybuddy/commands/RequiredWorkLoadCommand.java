package studybuddy.commands;
import studybuddy.CEGStudyBuddy;

import static java.lang.Float.max;
import static java.lang.Float.min;

public class RequiredWorkLoadCommand extends Command{
    public RequiredWorkLoadCommand(String param) {
        super(param);
    }
    private static float min(Float[] nums){
        float minimum = 100;
        for(Float num : nums){
            if(num < minimum){
                minimum = num;
            }
        }
        return minimum;
    }
    private static float max(Float[] nums){
        float maximum = -100;
        for(Float num : nums){
            if(num > maximum){
                maximum = num;
            }
        }
        return maximum;
    }
    @Override
    public String execute(String[] args) throws CEGStudyBuddyException{
        TotalWorkLoadCommand workLoadCalc = new TotalWorkLoadCommand("Workload calculator for required workloads");
        Float[] workloads = new Float[]{0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f};
        for (int i = 1; i < 9; i++) {
            String[] arguments = new String[]{i + "",""};
            try {
                workloads[i - 1] = Float.valueOf(workLoadCalc.execute(arguments));
            } catch(Exception e){
                throwException(e.getMessage());
            }
        }
        if(args[1].trim().equals("max")){
            return max(workloads) + "";
        }
        if (args[1].trim().equals("min")){
            return min(workloads) + "";
        }
        throwException("Please use min or max");
        return "";
    }
}