package studybuddy.data.course;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseList implements Serializable {
    private final ArrayList<Course> courses; // List to store all courses
    private String planName;

    public CourseList(String planName) {
        this.courses = new ArrayList<Course>();
        this.planName = planName;
    }

    /**
     * Edits the parameters of a course.
     *
     * @param editedParams The String array containing the new parameters.
     * @param course The Course object to be edited.
     * @return The newly edited Course object.
     */
    public Course setEditedParams(String[] editedParams, Course course) {
        if (editedParams.length != 5) {
            return course;
        }
        if (!editedParams[1].isEmpty()) {
            course.setTitle(editedParams[1]);
        }
        if (!editedParams[2].isEmpty()) {
            course.setMc(Integer.parseInt(editedParams[2]));
        }
        if (!editedParams[3].isEmpty()) {
            course.setTakeInYear(Integer.parseInt(editedParams[3]));
        }
        if (!editedParams[4].isEmpty()) {
            course.setTakeInSem(Integer.parseInt(editedParams[4]));
        }
        return course;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    /**
     * Add a course to the list
     *
     * @param course The Course object to be added
     */
    public void add(Course course) {
        courses.add(course);
    }

    /**
     * Deletes a course matching the full code (e.g., CS2040)
     *
     * @param code The course code to be deleted
     * @return True if the deletion is successful
     */
    public boolean deleteCourseByCode(String code) {
        String formattedCode = code.trim().toUpperCase(); // Uniform formatting
        return courses.removeIf(course ->
                course.getCode().equalsIgnoreCase(formattedCode)
        );
    }

    /**
     * Return the list of all courses
     *
     * @return ArrayList of courses
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    public boolean isEmpty() {
        return courses.isEmpty();
    }

    public void clear() {
        courses.clear();
    }

    /**
     * Function to get the storage format string for the course list
     *
     * @return Storage format String
     */
    public String toStoreFormat() {
        StringBuilder sb = new StringBuilder();
        for (Course course : courses) {
            sb.append(course.toStoreFormat()).append("\n");
        }
        return sb.toString();
    }
}
