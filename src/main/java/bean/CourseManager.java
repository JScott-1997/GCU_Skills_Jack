package bean;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author jackb
 */
public class CourseManager {

    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    private final String connectionString = "jdbc:ucanaccess://C:\\Users\\seanl\\Documents\\NetBeansProjects\\GCU_1\\data\\GCU_SkillsDB.accdb";

    public int addCourse(Course newCourse) throws SQLException, ClassNotFoundException {

        int courseId = 0;

        try {

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("INSERT INTO Courses(CourseName, CourseStatus, TutorId, Description) " + "VALUES('" + newCourse.getCourseName() + "', '" + newCourse.getCourseStatus() + "', '" + newCourse.getCourseTutor().getTutorId() + "', '" + newCourse.getDescription() + "')");

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {

                courseId = rs.getInt(1);

            }

            conn.close();
            return courseId;

        } catch (ClassNotFoundException | SQLException ex) {

            String message = ex.getMessage();
            return 0;

        }

    }

    public Course updateCourseAttribute(String parameterName, Object parameter, Course course) throws SQLException, ClassNotFoundException {

        //Check name of parameter submitted from web form (through servlet) and update course object
        switch (parameterName)
        {

            case "courseName":
                course.setCourseName((String) parameter);
                break;

            case "courseStatus":
                course.setCourseStatus((String) parameter);
                break;

            case "tutor":
                course.setCourseTutor((Tutor) parameter);

                break;

            case "description":
                course.setDescription((String) parameter);
                break;

        }

        //Connect to database and update parameter
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectionString);
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("UPDATE Course SET " + parameterName + " = " + "\"" + parameter + "\" " + "WHERE CourseId= " + course.getCourseId());
        conn.close();

        //Return updated course object
        return course;

    }

    //Returns all courses starting in the future, for display to prospective students
    public ArrayList<Course> loadFutureCourses() throws SQLException, ClassNotFoundException {

        ArrayList<Course> allCourses = new ArrayList();

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectionString);
        Statement stmt = conn.createStatement();

        //Selects all entries in the Courses table of the database
        ResultSet rs = stmt.executeQuery("SELECT * FROM Courses");

        while (rs.next()) {

            //Loads all values for an entry into an course object
            int courseId = rs.getInt("CourseId");
            String courseName = rs.getString("CourseName");
            String courseStatus = rs.getString("CourseStatus");
            String description = rs.getString("Description");

            Tutor courseTutor;
            courseTutor = loadCourseTutor(courseId);

            //Creates a course object with the data from the database and calls the loadCourseLessons method to populate that course with its associated lessons
            Course loadedCourse = new Course(courseId, courseName, courseStatus, loadCourseLessons(courseId), courseTutor, description);
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);

            //Check if course date is later than today and add course to array
            if (loadedCourse.getLessons().get(0).getTimeSlot().after(today.getTime())) {
                allCourses.add(loadedCourse);
            }
        }

        conn.close();

        return allCourses;
    }

    public boolean enrollStudent(int studentId, int courseId) throws SQLException, ClassNotFoundException {

        //Connect to database and insert studentid and courseid to table to record enrollment
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectionString);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO Enrollments(StudentId, CourseId, CourseStatus) " + "VALUES('" + studentId + "', '" + courseId + "', 'Beginner')");

        //If key was generated from statement, return true to confirm enrollment success, otherwise return false
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Course> loadStudentCourses(int studentIdIn) {

        ArrayList<Course> studentCourses = new ArrayList();

        try {

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();

            //Selects all entries in the Courses table of the database
            ResultSet rs = stmt.executeQuery("SELECT Enrollments.StudentId, Enrollments.CourseId, Enrollments.CourseStatus, Courses.CourseId, Courses.CourseName, Courses.TutorId, Courses.Description FROM Courses INNER JOIN Enrollments ON Courses.CourseId = Enrollments.CourseId WHERE Enrollments.CourseId = Courses.CourseId AND Enrollments.StudentId = '" + studentIdIn + "'");

            //Executes while the results set has a next value
            while (rs.next()) {

                //Loads all values for an entry into an course object
                int courseId = rs.getInt("CourseId");
                String courseName = rs.getString("CourseName");
                String courseStatus = rs.getString("CourseStatus");
                int tutorId = rs.getInt("TutorId");
                String description = rs.getString("Description");

                new Tutor();
                Tutor courseTutor;
                courseTutor = loadCourseTutor(courseId);

                //Creates a course object with the data from the database and calls the loadCourseLessons method to populate that course with its associated lessons
                Course loadedCourse = new Course(courseId, courseName, courseStatus, loadCourseLessons(courseId), courseTutor, description);

                studentCourses.add(loadedCourse);

            }

            conn.close();

        } catch (Exception ex) {

            String message = ex.getMessage();

        } finally {

            //returns the student object populated with a course
            return studentCourses;

        }

    }

    public Tutor loadCourseTutor(int courseId) {

        Tutor courseTutor = new Tutor();

        try {

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();

            //Selects all entries in the Courses table of the database
            ResultSet rs = stmt.executeQuery("SELECT * FROM Tutors INNER JOIN Courses ON Tutors.TutorId = Courses.TutorId WHERE CourseId = '" + courseId + "'");

            while (rs.next()) {

                int tutorId = rs.getInt("TutorId");
                String emailAddress = rs.getString("EmailAddress");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                Date dob = rs.getDate("DateOfBirth");
                String role = rs.getString("Role");
                String department = rs.getString("Department");
                int payGrade = rs.getInt("PayGrade");

                courseTutor = new Tutor(emailAddress, firstName, lastName, dob, tutorId, role, department, payGrade);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            String message = ex.getMessage();
        } finally {
            return courseTutor;
        }

    }

    public ArrayList<Lesson> loadAllLessons() {

        //Initialises an ArrayList object
        ArrayList<Lesson> lessonsArrayList = new ArrayList<>();

        try {

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();

            //Selects all entries in the Lessons table in the database
            ResultSet rs = stmt.executeQuery("SELECT * FROM Lessons");

            //Populates the lessonsArrayList with all lessons in the database
            while (rs.next()) {

                //Loads all values for an entry into an lesson object
                int lessonId = rs.getInt("LessonId");
                Date timeSlot = rs.getDate("TimeSlot");
                int courseId = rs.getInt("CourseId");

                Lesson lesson = new Lesson(lessonId, timeSlot, courseId);

                //Adds the lesson object to the lessonsArrayList
                lessonsArrayList.add(lesson);

            }

        } catch (ClassNotFoundException | SQLException ex) {

            String message = ex.getMessage();

        } finally {

            //Returns an ArrayList of all objects in the database
            return lessonsArrayList;

        }

    }

    public ArrayList<Lesson> loadCourseLessons(int courseId) {

        //Calls the loadAllLessons object to get an ArrayList of all lessons in the database
        ArrayList<Lesson> allLessons = loadAllLessons();

        //Creates a condition which will be used select only the lessons for an associated courseId
        Predicate<Lesson> byCourseId = lesson -> lesson.getCourseId() == courseId;

        //Filters all lessons by courseId and selects those that match the students course
        ArrayList<Lesson> result = (ArrayList<Lesson>) allLessons.stream().filter(byCourseId).collect(Collectors.toList());

        //returns the lessons for the students course in the form of an ArrayList
        return result;
    }

    public Course updateAttribute(String parameterName, String parameter, Course course, int studentId) throws SQLException, ClassNotFoundException {

        //Check name of parameter submitted from web form (through servlet) and update course object
        String dbTable = "";
        switch (parameterName) {
            case "coursestatus":
                course.setCourseStatus(parameter);
                dbTable = "Enrollments";
                break;

            case "coursename":
                course.setCourseName(parameter);
                dbTable = "Courses";
                break;

        }

        //Connect to database and update parameter
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(connectionString);
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("UPDATE " + dbTable + " SET " + parameterName + " = " + "\"" + parameter + "\" " + "WHERE CourseId= " + course.getCourseId() + " AND StudentId= " + studentId);
        conn.close();

        //Return updated student object
        return course;
    }

    public ArrayList<Course> loadTutorCourses(int tutorId) {

        ArrayList<Course> tutorCourses = new ArrayList();

        try {

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();

            //Selects all entries in the Courses table of the database
            ResultSet rs = stmt.executeQuery("SELECT * FROM Courses WHERE TutorId = '" + tutorId + "'");

            //Executes while the results set has a next value
            while (rs.next()) {

                //Loads all values for an entry into an course object
                int courseId = rs.getInt("CourseId");
                String courseName = rs.getString("CourseName");
                String courseStatus = rs.getString("CourseStatus");
                String description = rs.getString("Description");

                new Tutor();
                Tutor courseTutor;
                courseTutor = loadCourseTutor(courseId);

                //Creates a course object with the data from the database and calls the loadCourseLessons method to populate that course with its associated lessons
                Course loadedCourse = new Course(courseId, courseName, courseStatus, loadCourseLessons(courseId), courseTutor, description);
                
                //Get Calendar object of todays date
                Calendar today = Calendar.getInstance();
                today.set(Calendar.HOUR_OF_DAY, 0);

                //Check if last lesson date is later than today and add course to array (prevents old courses from loading)
                if (loadedCourse.getLessons().get(loadedCourse.getLessons().size() - 1).getTimeSlot().after(today.getTime())) {
                    tutorCourses.add(loadedCourse);
                }
            }

            conn.close();

        } catch (Exception ex) {

            String message = ex.getMessage();

        } finally {

            //returns the student object populated with a course
            return tutorCourses;

        }

    }

    public ArrayList<Student> loadCourseStudents(int courseId) {

        ArrayList<Student> studentsOnCourse = new ArrayList<>();

        try
        {

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();

            //Selects all entries in the Courses table of the database
            ResultSet rs = stmt.executeQuery("SELECT Students.StudentId, Students.EmailAddress, Students.FirstName, Students.LastName, Students.DateOfBirth, Students.PhoneNumber, Enrollments.CourseId FROM Enrollments INNER JOIN Students ON Enrollments.StudentId = Students.StudentId WHERE Enrollments.CourseId =  '" + courseId + "'");

            while (rs.next())
            {

                int studentId = rs.getInt("StudentId");
                String emailAddress = rs.getString("EmailAddress");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                Date dob = rs.getDate("DateOfBirth");
                String phoneNumber = rs.getString("PhoneNumber");
                
                ArrayList<Course> studentCourse = loadStudentCourses(studentId);

                Student loadedStudent = new Student(emailAddress, firstName, lastName, dob, studentId, phoneNumber, studentCourse);

                studentsOnCourse.add(loadedStudent);

            }

        }

        catch (Exception ex)
        {

            String message = ex.getMessage();

        }

        finally
        {

            return studentsOnCourse;

        }

    }

}
