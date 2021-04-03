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
    private final String connectionString = "jdbc:ucanaccess://C:\\Users\\seanl\\Documents\\NetBeansProjects\\GCU_1\\data\\GCU_SkillsDB.accdb";
    PassHash passHash = new PassHash();

    public int registerStudent(Student student) {

        int studentId = 0;

        try
        {

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("INSERT INTO Students(EmailAddress, Password, FirstName, LastName, DateOfBirth, PhoneNumber) " + "VALUES('" + student.getEmail() + "', '" + student.getPassword() + "', '" + student.getFirstName() + "', '" + student.getLastName() + "', '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(student.getDob()) + "', '" + student.getPhoneNumber() + "')");

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next())
            {

                studentId = rs.getInt(1);

            }

            conn.close();
            return studentId;

        }

        catch (ClassNotFoundException | SQLException ex)
        {

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
        while (rs.next())
        {

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

        if (isPassValid)
        {

            CourseManager cm = new CourseManager();
            ArrayList<Course> studentCourses = cm.loadStudentCourses(studentId);
            Student student = new Student(emailAddress, password, firstName, lastName, dob, studentId, phoneNumber, studentCourses);

            //Loads the students course and the course's lessons
            //Returns the completed student object
            student.setStudentId(studentId);
            return student;

        }

        else
        {
            return null;
        }
    }

    public Student updateStudentAttribute(String parameterName, String parameter, Student student) throws SQLException, ClassNotFoundException {

        //Check name of parameter submitted from web form (through servlet) and update student object
        switch (parameterName)
        {

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

    public ArrayList<Student> loadAllStudents() throws SQLException, ClassNotFoundException {

        ArrayList<Student> allStudents = new ArrayList<>();

        try
        {

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();

            //Selects all entries in the Courses table of the database
            ResultSet rs = stmt.executeQuery("SELECT StudentId, EmailAddress, FirstName, LastName, DateOfBirth, PhoneNumber, CourseId FROM Students");

            while (rs.next())
            {

                int studentId = rs.getInt("StudentId");
                String emailAddress = rs.getString("EmailAddress");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                Date dob = rs.getDate("DateOfBirth");
                String phoneNumber = rs.getString("PhoneNumber");

                CourseManager cm = new CourseManager();

                ArrayList<Course> studentCourse = cm.loadStudentCourses(studentId);

                Student loadedStudent = new Student(emailAddress, firstName, lastName, dob, studentId, phoneNumber, studentCourse);

                allStudents.add(loadedStudent);

            }

        }

        catch (Exception ex)
        {

            String message = ex.getMessage();

        }

        finally
        {

            return allStudents;

        }

    }

    public ArrayList<Tutor> loadAllTutors() throws SQLException, ClassNotFoundException {

        ArrayList<Tutor> allTutors = new ArrayList<>();

        try
        {

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();

            //Selects all entries in the Courses table of the database
            ResultSet rs = stmt.executeQuery("SELECT StudentId, EmailAddress, FirstName, LastName, DateOfBirth, PhoneNumber, CourseId FROM Students");

            while (rs.next())
            {

                int tutorId = rs.getInt("TutorId");
                String emailAddress = rs.getString("EmailAddress");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                Date dob = rs.getDate("DateOfBirth");
                String role = rs.getString("Role");
                String department = rs.getString("Department");
                int payGrade = rs.getInt("PayGrade");
                CourseManager cm = new CourseManager();

                ArrayList<Course> tutorCourses = cm.loadTutorCourses(tutorId);

                Tutor loadedTutor = new Tutor(emailAddress, firstName, lastName, dob, tutorId, role, department, payGrade, tutorCourses);

                allTutors.add(loadedTutor);

            }

        }

        catch (Exception ex)
        {

            String message = ex.getMessage();

        }

        finally
        {

            return allTutors;

        }

    }

    public int registerTutor(Tutor tutor) {

        int tutorId = 0;

        try
        {

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("INSERT INTO Tutors(EmailAddress, Password, FirstName, LastName, DateOfBirth, Role, Department, PayGrade) " + "VALUES('" + tutor.getEmail() + "', '" + tutor.getPassword() + "', '" + tutor.getFirstName() + "', '" + tutor.getLastName() + "', '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tutor.getDob()) + "', '" + tutor.getRole() + "', '" + tutor.getDepartment() + "', '" + tutor.getPayGrade() + "')");

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next())
            {

                tutorId = rs.getInt(1);

            }

            conn.close();
            return tutorId;

        }

        catch (ClassNotFoundException | SQLException ex)
        {

            String message = ex.getMessage();
            return 0;

        }

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
        while (rs.next())
        {

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
    
    public Tutor updateStudentAttribute(String parameterName, String parameter, Tutor tutor) throws SQLException, ClassNotFoundException {

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