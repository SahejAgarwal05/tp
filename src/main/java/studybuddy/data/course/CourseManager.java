package studybuddy.data.course;

import com.google.gson.Gson;

public class CourseManager {
    public static void main(String[] args) {
        String s = "{\"code\": \"CS1010\", \"title\": \"Programming Met\", \"mc\": 4, " +
                "\"offerInSem1\": true, \"offerInSem2\": true, " +
                "takeInSem\": 0, \"takeInYear\": 0, \"isCleared\": false}";
        Course c = jsonToCourse(s);
    }

    public static Course jsonToCourse (String jsonString) {
        // String processedString = jsonString.replace("\"", "\\\"");
        System.out.println(jsonString);
        Gson gson = new Gson();
        Course course = gson.fromJson(jsonString, Course.class);
        System.out.println(course);
        return course;
    }
}
