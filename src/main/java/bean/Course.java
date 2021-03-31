package bean;

import java.util.ArrayList;

/**
 * @author jackb
 */
public class Course {

    private int courseId;
    private String courseName;
    private String courseStatus;
    private ArrayList<Lesson> lessons;
    private Tutor courseTutor;
    private String description;

    //Zero-Parameter Constructor
    public Course()
    {

        courseId = 0;
        courseName = "";
        courseStatus = "";
        lessons = new ArrayList<>();
        courseTutor = new Tutor();
        description = "";

    }

    public Course(int courseIdIn)
    {

        courseId = courseIdIn;
        courseName = "";
        courseStatus = "";
        lessons = new ArrayList<>();
        courseTutor = new Tutor();
        description = "";

    }
    
    //Overloaded Constructor
    public Course(int courseIdIn, String courseNameIn, String courseStatusIn, Tutor courseTutorIn)
    {

        courseId = courseIdIn;
        courseName = courseNameIn;
        courseStatus = courseStatusIn;
        lessons = new ArrayList<>();
        courseTutor = courseTutorIn;

    }
    
    //Overloaded Constructor
    public Course(int courseIdIn, String courseNameIn, String courseStatusIn, ArrayList<Lesson> lessonsIn, Tutor courseTutorIn, String descriptionIn)
    {

        courseId = courseIdIn;
        courseName = courseNameIn;
        courseStatus = courseStatusIn;
        lessons = lessonsIn;
        courseTutor = courseTutorIn;
        description = descriptionIn;

    }

    //Getters
    public int getCourseId() {return courseId;}

    public String getCourseName() {return courseName;}

    public String getCourseStatus() {return courseStatus;}

    public ArrayList<Lesson> getLessons() {return lessons;}

    public Tutor getCourseTutor() {return courseTutor;}
    
    public String getDescription() {return description;}

    //Setters
    public void setCourseId(int courseIdIn) {courseId = courseIdIn;}

    public void setCourseName(String courseNameIn) {courseName = courseNameIn;}

    public void setCourseStatus(String courseStatusIn) {courseStatus= courseStatusIn;}

    public void setLessons(ArrayList<Lesson> lessonsIn) {lessons = lessonsIn;}

    public void setCourseTutor(Tutor courseTutorIn) {courseTutor = courseTutorIn;}
    
    public void setDescription(String descriptionIn) {description = descriptionIn;}
}