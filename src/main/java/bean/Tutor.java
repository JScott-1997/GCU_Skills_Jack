package bean;

import java.security.cert.CertificateParsingException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author jackb
 */
public class Tutor extends User
{

    private int tutorId;
    private String role;
    private String department;
    private int payGrade;
    private ArrayList<Course> tutorCourses;

    //Zero-Parameter Constructor
    public Tutor()
    {
        super();
        tutorId = 0;
        role = "";
        department = "";
        payGrade = 0;
        tutorCourses = new ArrayList<>();

    }

    //Overloaded Constructor
    public Tutor(String usernameIn, String firstNameIn, String lastNameIn, Date dobIn, int tutorIdIn, String roleIn, String departmentIn, int payGradeIn) {

        super(usernameIn, firstNameIn, lastNameIn, dobIn);
        tutorId = tutorIdIn;
        role = roleIn;
        department = departmentIn;
        payGrade = payGradeIn;
        tutorCourses = new ArrayList<>();

    }

    //Overloaded Constructor
    public Tutor(String usernameIn, String firstNameIn, String lastNameIn, Date dobIn, int tutorIdIn, String roleIn, String departmentIn, int payGradeIn, ArrayList<Course> tutorCoursesIn) {

        super(usernameIn, firstNameIn, lastNameIn, dobIn);
        tutorId = tutorIdIn;
        role = roleIn;
        department = departmentIn;
        payGrade = payGradeIn;
        tutorCourses = tutorCoursesIn;

    }

    public Tutor(String usernameIn, String passwordIn, String firstNameIn, String lastNameIn, Date dobIn, int tutorIdIn, String roleIn, String departmentIn, int payGradeIn) {

        super(usernameIn, passwordIn, firstNameIn, lastNameIn, dobIn);
        tutorId = tutorIdIn;
        role = roleIn;
        department = departmentIn;
        payGrade = payGradeIn;
        tutorCourses = new ArrayList<>();

    }

    //Overloaded Constructor
    public Tutor(String usernameIn, String passwordIn, String firstNameIn, String lastNameIn, Date dobIn, int tutorIdIn, String roleIn, String departmentIn, int payGradeIn, ArrayList<Course> tutorCourseIn) {

        super(usernameIn, passwordIn, firstNameIn, lastNameIn, dobIn);
        tutorId = tutorIdIn;
        role = roleIn;
        department = departmentIn;
        payGrade = payGradeIn;
        tutorCourses = tutorCourseIn;

    }

    //Getters
    public int getTutorId() {return tutorId;}

    public String getRole() {return role;}

    public String getDepartment() {return department;}

    public int getPayGrade() {return payGrade;}

    public ArrayList<Course> getTutorCourse() {return tutorCourses;}

    //Setters
    public void setTutorId(int tutorIdIn) {tutorId = tutorIdIn;}

    public void setRole(String roleIn) {role = roleIn;}

    public void setDepartment(String departmentIn) {department = departmentIn;}

    public void setPayGrade(int payGradeIn) {payGrade = payGradeIn;}

    public void setTutorCourse(ArrayList<Course> tutorCourseIn) {tutorCourses = tutorCourseIn;}

}