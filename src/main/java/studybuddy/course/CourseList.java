package studybuddy.course;

import java.util.ArrayList;

public class CourseList {
    private ArrayList<Course> courses;

    public CourseList() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean deleteCourseByCode(String code) {
        String formattedCode = code.trim().toUpperCase();
        return courses.removeIf(course ->
                (course.getDepartment() + course.getCode()).equalsIgnoreCase(formattedCode)
        );
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String listCourses() {
        if (courses.isEmpty()) {
            return "No courses added yet!";
        }
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Course course : courses) {
            sb.append(count).append(". ")
                    .append(course.getDepartment()).append(course.getCode())
                    .append(" - ").append(course.getTitle())
                    .append(" (").append(course.getMc()).append(" MCs)").append("\n");
            count++;
        }
        return sb.toString().trim();
    }
}


