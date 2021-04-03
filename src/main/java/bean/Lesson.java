package bean;

import java.util.Date;

/**
 * @author jackb
 */
public class Lesson {

    private int lessonId;
    private Date timeSlot;
    private int courseId;

    //Zero-Parameter Constructor
    public Lesson()
    {

        lessonId = 0;
        timeSlot = new Date();
        courseId = 0;

    }

    //Overloaded Constructor
    public Lesson(int lessonIdIn, Date timeSlotIn, int courseIdIn)
    {

        lessonId = lessonIdIn;
        timeSlot = timeSlotIn;
        courseId = courseIdIn;

    }

    //Getters
    public int getLessonId() {return lessonId;}

    public Date getTimeSlot() {return timeSlot;}

    public int getCourseId() {return courseId;}

    //Setters
    public void setLessonId(int lessonIdIn) {lessonId = lessonIdIn;}

    public void setTimeSlot(Date timeSlotIn) {timeSlot = timeSlotIn;}

    public void setCourseId(int courseIdIn) {courseId = courseIdIn;}

}