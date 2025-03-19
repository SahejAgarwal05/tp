package studybuddy.course;

import java.util.ArrayList;

public class CourseList {
    private ArrayList<Course> courses;

    //Initializes the Course List
    public CourseList() {
        this.courses = new ArrayList<>();
    }

    // Add a course to the list
    public void addCourse(Course course) {
        courses.add(course);
    }

    // Delete a course
    public boolean deleteCourseByCode(String code) {
        String formattedCode = code.trim().toUpperCase();
        return courses.removeIf(course ->
                (course.getDepartment() + course.getCode()).equalsIgnoreCase(formattedCode)
        );
    }

    // Return the list of all courses
    public ArrayList<Course> getCourses() {
        return courses;
    }

    // Return a formatted string listing all the courses
    public String listCourses() {
        if (courses.isEmpty()) {
            return "No courses added yet!"; // If there are no courses
        }
        StringBuilder sb = new StringBuilder();
        int count = 1; // Numbering for each course
        for (Course course : courses) {
            sb.append(count).append(". ")
                    .append(course.getDepartment()).append(course.getCode()) // Combines the department and code
                    .append(" - ").append(course.getTitle())
                    .append(" (").append(course.getMc()).append(" MCs)").append("\n");
            count++;
        }
        return sb.toString().trim(); // Returns the formatted list
    }
}



