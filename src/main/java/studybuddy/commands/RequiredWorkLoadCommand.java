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
    public String execute() throws CEGStudyBuddyException{
        Float[] workloads = new Float[]{0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f};
        for (int i = 1; i < 9; i++) {
            TotalWorkLoadCommand workLoadCommand = new TotalWorkLoadCommand(i + " ");
            try {
                workloads[i - 1] = Float.valueOf(workLoadCommand.execute());
            } catch(Exception e){
                throwException(e.getMessage());
            }
        }
        if(this.param.trim().equals("max")){
            return max(workloads) + "";
        }
        if (param.trim().equals("min")){
            return min(workloads) + "";
        }
        throwException("Please use min or max");
        return "";
    }
}