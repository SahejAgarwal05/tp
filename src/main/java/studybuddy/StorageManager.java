package studybuddy;

import studybuddy.CEGStudyBuddy;
import studybuddy.commands.CEGStudyBuddyException;
import studybuddy.course.CourseList;

import java.io.*;

public class StorageManager {
    private String directory;

    public StorageManager(String directory) {
        this.directory = directory;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void saveNewPlan(String plan) throws CEGStudyBuddyException {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs(); // Create directory if it doesn't exist
        }

        if (CEGStudyBuddy.courses == null) {
            CEGStudyBuddy.courses = new CourseList(plan);
        }

        String planFileName = plan + ".bin";
        File planFile = new File(dir, planFileName);
        if (planFile.exists()) {
            throw new CEGStudyBuddyException("A plan with this name already exists.");
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(planFile))) {
            CEGStudyBuddy.courses.setPlanName(plan);
            oos.writeObject(CEGStudyBuddy.courses);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CEGStudyBuddyException("Error in making new plan");
        }
    }

    public void saveCurrentPlan() throws CEGStudyBuddyException {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs(); // Create directory if it doesn't exist
        }

        String planFileName = CEGStudyBuddy.courses.getPlanName() + ".bin";
        File planFile = new File(dir, planFileName);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(planFile))) {
            oos.writeObject(CEGStudyBuddy.courses);
        } catch (Exception e) {
            throw new CEGStudyBuddyException("Error in saving");
        }
    }

    public void loadPlan(String planName) throws CEGStudyBuddyException {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
            throw new CEGStudyBuddyException("You have no plans saved");
        }

        File planFile = new File(dir, planName + ".bin");
        if (!planFile.exists()) {
            throw new CEGStudyBuddyException("Invalid Plan Name");
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(planFile))) {
            CEGStudyBuddy.courses = (CourseList) ois.readObject();
        } catch (Exception e) {
            throw new CEGStudyBuddyException("Data Source Corrupted");
        }
    }

    public String[] listPlans() throws CEGStudyBuddyException {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
            throw new CEGStudyBuddyException("You have no plans saved");
        }

        String[] plans = dir.list((d, name) -> name.endsWith(".bin"));
        if (plans == null || plans.length == 0) {
            throw new CEGStudyBuddyException("You have no plans saved");
        }

        // Remove the ".bin" extension from each plan name
        for (int i = 0; i < plans.length; i++) {
            plans[i] = plans[i].substring(0, plans[i].length() - 4);
        }

        return plans;
    }
    /**
     * This function helps to create a new plan
     *
     * @throws CEGStudyBuddyException
     */
     public void newPlan() throws CEGStudyBuddyException {
        String planName = "";
        while (planName.equals("")) {
            System.out.print("Please enter a plan name \nNo special charecters are allowed only alphanumeric input: ");
            planName = CEGStudyBuddy.in.nextLine().trim();
            if(!planName.matches("[a-zA-Z0-9]*")){
                planName = "";
            }
        }
        try{
            this.saveNewPlan(planName);
            System.out.println("New plan has been created");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function loads or creates a new plans as per user input accounting for error
     */
    public  void initializePlan() {
        boolean initRun = true;
        while (initRun){
            initRun = false;
            try {
                selectPlan();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                initRun = true;
            }
        }
    }

    /**
     * This functions helps user select plan
     *
     * @throws CEGStudyBuddyException
     */
     public void selectPlan() throws CEGStudyBuddyException {
        String[] plans;
        try {
            plans = this.listPlans();
        } catch (Exception e){
            System.out.println("You have no previous plans");
            this.newPlan();
            return;
        }
        for(int i = 0; i < plans.length; i ++){
            System.out.println(i + 1 + ". " +plans[i]);
        }
        System.out.print("Please enter a plan number between 1 and " + plans.length + "or 0 to create a new plan: ");
        String planNumber = CEGStudyBuddy.in.nextLine().trim();
        if(planNumber.equals("0")){
            this.newPlan();
            return;
        }
        int planNo;
        try {
            planNo = Integer.parseInt(planNumber);
            this.loadPlan(plans[planNo - 1]);
        } catch (Exception e){
            throw new CEGStudyBuddyException("Invalid plan number");
        }
        System.out.println("Plan loaded successfully");
    }
}

