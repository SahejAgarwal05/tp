package studybuddy.course;

/**
 * Represents a course in NUS.
 * Pre-set info: code, title, module credit (mc), offered in semester 1/2, ...
 * Editable info: take(n) in semester, is cleared/taken
 */
public class aCourse {
    protected String code;
    protected String title;
    protected int mc;
    protected boolean offerInSem1;
    protected boolean offerInSem2;
    // Editable to user
    protected int takeInSem;
    protected int takeInYear;
    protected boolean isCleared;

    /**
     * Constructor for v1.0
     * @param code Code of the course, e.g. CS1231, CG1111As.
     * @param title Text title of the course, e.g. Programming Methodology.
     * @param mc Module credit of the course, e.g. 2, 4, 8.
     * @param takeInYear Take/Taken this course in which year, e.g. 1, 2, 3, 4.
     * @param takeInSem Take/Taken this course in which semester, e.g. 1, 2.
     */
    public Course(String code, String title, int mc, int takeInYear, int takeInSem) {
        this.code = code;
        this.title = title;
        this.mc = mc;
        this.takeInYear = takeInYear;
        this.takeInSem = takeInSem;

        this.offerInSem1 = true;
        this.offerInSem2 = true;
        this.isCleared = false;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getMc() {
        return mc;
    }

    public boolean isOfferInSem1() {
        return offerInSem1;
    }

    public boolean isOfferInSem2() {
        return offerInSem2;
    }

    public int getTakeInSem() {
        return takeInSem;
    }

    public int getTakeInYear() {
        return takeInYear;
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

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMc(int mc) {
        this.mc = mc;
    }

    public void setOfferInSem1(boolean offerInSem1) {
        this.offerInSem1 = offerInSem1;
    }

    public void setOfferInSem2(boolean offerInSem2) {
        this.offerInSem2 = offerInSem2;
    }

    public void setTakeInYear(int takeInYear) {
        this.takeInYear = takeInYear;
    }

    @Override
    public String toString() {
        return "(c/" + getCode() + " " +
                "t/" + getTitle() + " " +
                "mc/" + getMc() + " " +
                "y/" + getTakeInYear() + " " +
                "s/" + getTakeInSem() + ")";
    }
}
