package bean;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author jackb
 */
public class Student extends User {

    int studentId;
    private String phoneNumber;
    private ArrayList<Course> course;

    //Zero-Parameter Constructor
    public Student() {

        super();
        studentId = 0;
        phoneNumber = "";
        course = new ArrayList();

    }

    //Overloaded Constructor
    public Student(String emailIn, String firstNameIn, String lastNameIn, Date dobIn, int studentIdIn, String phoneNumberIn) {

        super(emailIn, firstNameIn, lastNameIn, dobIn);
        studentId = studentIdIn;
        phoneNumber = phoneNumberIn;
        course = new ArrayList();

    }

    //Overloaded Constructor
    public Student(String emailIn, String passwordIn, String firstNameIn, String lastNameIn, Date dobIn, String phoneNumberIn) {

        super(emailIn, passwordIn, firstNameIn, lastNameIn, dobIn);
        studentId = 0;
        phoneNumber = phoneNumberIn;
        course = new ArrayList();

    }

    //Overloaded Constructor
    public Student(String emailIn, String passwordIn, String firstNameIn, String lastNameIn, Date dobIn, int studentIdIn, String phoneNumberIn, ArrayList<Course> courseIn) {

        super(emailIn, passwordIn, firstNameIn, lastNameIn, dobIn);
        studentId = studentIdIn;
        phoneNumber = phoneNumberIn;
        course = courseIn;

    }

    //Getters
    public int getStudentId() {
        return studentId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Course> getCourse() {
        return course;
    }

    //Setters
    public void setStudentId(int studentIdIn) {
        studentId = studentIdIn;
    }

    public void setPhoneNumber(String phoneNumberIn) {
        phoneNumber = phoneNumberIn;
    }

    public void setCourse(ArrayList<Course> courseIn) {
        course = courseIn;
    }

}