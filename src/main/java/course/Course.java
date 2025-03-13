package course;

/**
 * Represents a course in NUS
 * Pre-set info: department, code, title, module credit (mc), offered in semester, ...
 * Editable info:
 */
public class Course {
    protected String department;
    protected int code;
    protected String title;
    protected int mc;
    protected int offerInSem;
    // Editable to user
    protected int takeInSem;
    protected boolean isCleared;

    public Course(String department, int code, String title, int mc, int offerInSem) {
        this.department = department;
        this.code = code;
        this.title = title;
        this.mc = mc;
        this.offerInSem = offerInSem;

        this.takeInSem = 0;
        this.isCleared = false;
    }

    public String getDepartment() {
        return department;
    }

    public int getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getMc() {
        return mc;
    }

    public int getOfferInSem() {
        return offerInSem;
    }

    public int getTakeInSem() {
        return takeInSem;
    }

    public boolean isCleared() {
        return isCleared;
    }

    public void setTakeInSem(int takeInSem) {
        this.takeInSem = takeInSem;
    }

    public void setCleared(boolean cleared) {
        isCleared = cleared;
    }
}
