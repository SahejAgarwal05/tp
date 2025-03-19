package studybuddy.course;

/**
 * Represents a course in NUS.
 * Pre-set info: department, code, title, module credit (mc), offered in semester 1/2, ...
 * Editable info: take(n) in semester, is cleared/taken
 */
public class Course {
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
     * Constructor.
     * @param code Code number of the course, a number between 1000 and 5999 with an optional
     *             upper case letter, e.g. 1231, 2040C.
     * @param title Text title of the course, e.g. Programming Methodology.
     * @param mc Module credit of the course, e.g. 2, 4, 8.
     * @param takeInSem The semester the course is being taken.
     * @param takeInYear The year the course is being taken.
     */
    public Course(String code, String title, int mc,
                  int takeInSem, int takeInYear) {
        this.code = code;
        this.title = title;
        this.mc = mc;
        this.takeInSem = takeInSem;
        this.takeInYear = takeInYear;

        this.offerInSem1 = takeInSem == 1;
        this.offerInSem2 = takeInSem == 2;
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
