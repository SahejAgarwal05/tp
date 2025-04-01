package studybuddy.data.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import studybuddy.CEGStudyBuddy;
import studybuddy.data.exception.CEGStudyBuddyException;
import studybuddy.data.course.CourseList;

public class StorageManager {
    private String directory;
    private CourseList courses;

    /**
     * Constructs a StorageManager with a specified directory for storing plans.
     *
     * @param directory The directory path where plans will be stored.
     */
    public StorageManager(String directory, CourseList courses) {
        this.directory = directory;
        this.courses = courses;
    }

    /**
     * Gets the current directory used for storing plans.
     *
     * @return The directory path.
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * Sets the directory path for storing plans.
     *
     * @param directory The new directory path.
     */
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    /**
     * Saves a new plan to storage. If a plan with the same name already exists, an exception is thrown.
     *
     * @param plan The name of the new plan.
     * @throws CEGStudyBuddyException If a plan with the same name exists or if an error occurs during saving.
     */
    public void saveNewPlan(String plan) throws CEGStudyBuddyException {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs(); // Create directory if it doesn't exist
        }

        if (courses == null) {
            courses = new CourseList(plan);
        }

        String planFileName = plan + ".bin";
        File planFile = new File(dir, planFileName);
        if (planFile.exists()) {
            throw new CEGStudyBuddyException("A plan with this name already exists.");
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(planFile))) {
            courses.setPlanName(plan);
            oos.writeObject(courses);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CEGStudyBuddyException("Error in making new plan");
        }
    }

    /**
     * Saves the currently loaded plan to storage.
     *
     * @throws CEGStudyBuddyException If an error occurs during saving.
     */
    public void saveCurrentPlan() throws CEGStudyBuddyException {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String planFileName = courses.getPlanName() + ".bin";
        File planFile = new File(dir, planFileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(planFile))) {
            oos.writeObject(courses);
        } catch (Exception e) {
            throw new CEGStudyBuddyException("Error in saving");
        }
        System.out.println("Plan saved successfully.");
    }

    /**
     * Loads a saved plan by name and sets it as the current plan.
     *
     * @param planName The name of the plan to load.
     * @throws CEGStudyBuddyException If the plan does not exist or the data is corrupted.
     */
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
            courses = (CourseList) ois.readObject();
        } catch (Exception e) {
            throw new CEGStudyBuddyException("Data Source Corrupted");
        }
    }

    /**
     * Lists all saved plan names in the storage directory.
     *
     * @return An array of plan names without file extensions.
     * @throws CEGStudyBuddyException If no plans are found.
     */
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

        // Remove ".bin" extension
        for (int i = 0; i < plans.length; i++) {
            plans[i] = plans[i].substring(0, plans[i].length() - 4);
        }

        return plans;
    }

    /**
     * Prompts the user to create a new plan by entering a valid alphanumeric plan name.
     *
     * @throws CEGStudyBuddyException If saving the new plan fails.
     */
    public void newPlan() throws CEGStudyBuddyException {
        String planName = "";
        while (planName.isEmpty()) {
            System.out.print("Please enter a plan name \nNo special characters are allowed, only alphanumeric input: ");
            planName = CEGStudyBuddy.in.nextLine().trim();
            if (!planName.matches("[a-zA-Z0-9]*")) {
                planName = "";
            }
        }
        try {
            this.saveNewPlan(planName);
            System.out.println("New plan has been created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Initializes the plan selection process.
     * If an error occurs, it prompts the user again.
     */
    public void initializePlan() {
        boolean initRun = true;
        while (initRun) {
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
     * Allows the user to select an existing plan or create a new one.
     *
     * @throws CEGStudyBuddyException If the selected plan number is invalid.
     */
    public void selectPlan() throws CEGStudyBuddyException {
        String[] plans;
        try {
            plans = this.listPlans();
        } catch (Exception e) {
            System.out.println("You have no previous plans");
            this.newPlan();
            return;
        }

        for (int i = 0; i < plans.length; i++) {
            System.out.println((i + 1) + ". " + plans[i]);
        }

        System.out.print("Please enter a plan number between 1 and " + plans.length + " or 0 to create a new plan: ");
        String planNumber = CEGStudyBuddy.in.nextLine().trim();

        if (planNumber.equals("0")) {
            this.newPlan();
            return;
        }

        try {
            int planNo = Integer.parseInt(planNumber);
            this.loadPlan(plans[planNo - 1]);
        } catch (Exception e) {
            throw new CEGStudyBuddyException("Invalid plan number");
        }

        System.out.println("Plan loaded successfully");
    }
}
