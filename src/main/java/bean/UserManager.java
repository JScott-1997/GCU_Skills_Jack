package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jackb
 */
public class UserManager {

    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    private final String connectionString = "jdbc:ucanaccess://C:\\Users\\jackb\\IdeaProjects\\GCU_Skills_1\\data\\GCU_SkillsDB.accdb";
    PassHash passHash = new PassHash();

    public int registerStudent(Student student) {

        int studentId = 0;

        try {

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("INSERT INTO Students(EmailAddress, Password, FirstName, LastName, DateOfBirth, PhoneNumber) " + "VALUES('" + student.getEmail() + "', '" + student.getPassword() + "', '" + student.getFirstName() + "', '" + student.getLastName() + "', '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(student.getDob()) + "', '" + student.getPhoneNumber() + "')");

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {

                studentId = rs.getInt(1);

            }

            conn.close();
            return studentId;

        } catch (ClassNotFoundException | SQLException ex) {

            String message = ex.getMessage();
            return 0;

        }

    }

    public Student logInStudent(String emailAddress, String password) throws SQLException, ClassNotFoundException {

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectionString);
        Statement stmt = conn.createStatement();

        int studentId = 0;
        String realPassword = "";
        String firstName = "";
        String lastName = "";
        Date dob = new Date();
        String phoneNumber = "";

        //Selects every matching entry in the customers table in the database
        ResultSet rs = stmt.executeQuery("SELECT * FROM Students WHERE EmailAddress= '" + emailAddress + "'");

        //Executes for every entry in the results set
        while (rs.next()) {

            //Gets the values from the result set entry 
            studentId = rs.getInt("studentId");
            realPassword = rs.getString("Password");
            firstName = rs.getString("FirstName");
            lastName = rs.getString("LastName");
            dob = rs.getDate("DateOfBirth");
            phoneNumber = rs.getString("PhoneNumber");

        }

        conn.close();

        //Compares encrypted password in database to plaintext password for equality using bCrypt and returns student if valid
        boolean isPassValid = passHash.checkPassword(emailAddress, password, "Students");

        if (isPassValid) {
            CourseManager cm = new CourseManager();
            ArrayList<Course> studentCourses = cm.loadStudentCourses(studentId);
            Student student = new Student(emailAddress, password, firstName, lastName, dob, studentId, phoneNumber, studentCourses);

            //Loads the students course and the course's lessons
            //Returns the completed student object
            student.setStudentId(studentId);
            return student;

        } else {
            return null;
        }
    }

    public Student updateAttribute(String parameterName, String parameter, Student student) throws SQLException, ClassNotFoundException {

        //Check name of parameter submitted from web form (through servlet) and update student object
        switch (parameterName) {
            case "emailaddress":
                student.setEmail(parameter);
                break;

            case "phonenumber":
                student.setPhoneNumber(parameter);
                break;

            case "password":
                student.setPassword(parameter);
                parameter = passHash.hashPassword(parameter);
                break;
        }

        //Connect to database and update parameter
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectionString);
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("UPDATE Students SET " + parameterName + " = " + "\"" + parameter + "\" " + "WHERE StudentId= " + student.getStudentId());
        conn.close();

        //Return updated student object
        return student;
    }

    public Tutor logInTutor(String emailAddress, String password) throws SQLException, ClassNotFoundException {

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectionString);
        Statement stmt = conn.createStatement();

        int tutorId = 0;
        String realPassword = "";
        String firstName = "";
        String lastName = "";
        Date dob = new Date();
        String role = "";
        String department = "";
        int payGrade = 0;

        //Selects every matching entry in the customers table in the database
        ResultSet rs = stmt.executeQuery("SELECT * FROM Tutors WHERE EmailAddress= '" + emailAddress + "'");

        //Executes for every entry in the results set
        while (rs.next()) {

            //Gets the values from the result set entry 
            tutorId = rs.getInt("TutorId");
            realPassword = rs.getString("Password");
            firstName = rs.getString("FirstName");
            lastName = rs.getString("LastName");
            dob = rs.getDate("DateOfBirth");
            role = rs.getString("Role");
            department = rs.getString("Department");
            payGrade = rs.getInt("PayGrade");

        }

        conn.close();

        //Compares encrypted password in database to plaintext password for equality using bCrypt and returns tutor if valid
        boolean isPassValid = passHash.checkPassword(emailAddress, password, "Tutors");

        if (isPassValid)
        {
            CourseManager cm = new CourseManager();

            Tutor tutor = new Tutor(emailAddress, password, firstName, lastName, dob, tutorId, role, department, payGrade, cm.loadTutorCourses(tutorId));

            return tutor;

        }

        else
        {
            return null;
        }
    }
    
    public Tutor updateAttribute(String parameterName, String parameter, Tutor tutor) throws SQLException, ClassNotFoundException {

        //Check name of parameter submitted from web form (through servlet) and update tutor object
        switch (parameterName) {
            case "emailaddress":
                tutor.setEmail(parameter);
                break;

            case "password":
                tutor.setPassword(parameter);
                parameter = passHash.hashPassword(parameter);
                break;
        }

        //Connect to database and update parameter
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectionString);
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("UPDATE Tutors SET " + parameterName + " = " + "\"" + parameter + "\" " + "WHERE TutorId= " + tutor.getTutorId());
        conn.close();

        //Return updated tutor object
        return tutor;
    }
}
