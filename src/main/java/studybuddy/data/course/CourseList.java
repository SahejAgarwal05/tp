package studybuddy.data.course;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseList implements Serializable {
    private ArrayList<Course> courses; // List to store all courses
    private String planName;
    // Initializes the Course List
    public CourseList(String planName) {
        this.courses = new ArrayList<Course>();
        this.planName = planName;
    }

    public Course setEditedParams(String[] editedParams, Course course) {
        if (editedParams.length != 5) {
            // throw an exception here
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

    // Add a course to the list
    public void addCourse(Course course) {
        courses.add(course);
    }

    // Delete a course by matching the full code (e.g., CS2040)
    public boolean deleteCourseByCode(String code) {
        String formattedCode = code.trim().toUpperCase(); // Uniform formatting
        return courses.removeIf(course ->
                course.getCode().equalsIgnoreCase(formattedCode)
        );
    }

    // Return the list of all courses
    public ArrayList<Course> getCourses() {
        return courses;
    }

    // Return a formatted string listing all courses
    public String listCourses() {
        if (courses.isEmpty()) {
            return "No courses added yet!"; // Display message if empty
        }
        StringBuilder sb = new StringBuilder();
        int count = 1; // Numbering each course
        for (Course course : courses) {
            sb.append(count).append(". ")
                    .append(course.getCode()) // Course code (e.g., CS2040)
                    .append(" - ").append(course.getTitle())
                    .append(" (").append(course.getMc()).append(" MCs)").append("\n");
            count++;
        }
        return sb.toString().trim(); // Return formatted string
    }
    public boolean isEmpty() {
        return courses.isEmpty();
    }
    public void add(Course course) {
        courses.add(course);
    }
    public void clear(){
        courses.clear();
    }
}




