package bean;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author seanl
 */
public class LessonsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Get session object so session attributes such as user object can be read
        HttpSession session = request.getSession();
        ArrayList<Course> courses = null;
        Student student = null;

        //Get user object from session and load courses
        if (session.getAttribute("student") != null) {
            student = (Student) session.getAttribute("student");
            courses = student.getCourse();
        } else if (session.getAttribute("tutor") != null) {
            Tutor tutor = (Tutor) session.getAttribute("tutor");
            CourseManager cm = new CourseManager();
            courses = cm.loadTutorCourses(tutor.getTutorId());
        }

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            //Display message if user isn't registered on a course
            if (courses.isEmpty()) {
                out.println("<p>There are no lessons to display.</p><p>Visit <a href=\"../courses.jsp\">Courses</a> to view and register for available courses.</p>");
            }

            //Generate table for each course in the array
            for (Course course : courses) {
                ArrayList<Lesson> lessons = course.getLessons();
                out.println("<table class=\"lessonstable\">");
                out.println("<tr>");
                out.println("<th colspan=\"4\">" + course.getCourseName() + "</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th>Date</th>");
                out.println("<th>Time</th>");
                out.println("<th>Lesson no.</th>");
                out.println("<th>Tutor</th>");
                out.println("</tr>");

                //Generate rows and populate with each lessons info from courses Lesson ArrayList
                for (Lesson lesson : lessons) {
                    SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
                    String time = sdf1.format(lesson.getTimeSlot().getTime());
                    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
                    String lessonDate = sdf2.format(lesson.getTimeSlot());
                    out.println("<tr>");
                    out.println("<td>" + lessonDate + "</td>");
                    out.println("<td>" + time + "</td>");
                    out.println("<td>" + (lessons.indexOf(lesson) + 1) + "</td>");
                    out.println("<td>" + course.getCourseTutor().getFirstName() + " " + course.getCourseTutor().getLastName() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");

                if (session.getAttribute("student") != null) {
                    out.println("Course status: ");

                    //Display course status with different font colours depending on status
                    switch (course.getCourseStatus()) {
                        case "Beginner":
                            out.println("<span style=\"color: #0065BF\">" + course.getCourseStatus() + "</span>");
                            break;

                        case "On-Going":
                            out.println("<span style=\"color: orange\">" + course.getCourseStatus() + "</span>");
                            break;

                        case "Not-complete":
                            out.println("<span style=\"color: red\">" + course.getCourseStatus() + "</span>");
                            break;

                        case "Completed":
                            out.println("<span style=\"color: green\">" + course.getCourseStatus() + "</span>");
                            break;
                    }

                    //Display option to end lessons if course has yet to begin or is currently underway. Link calls the EndLessonsServlet and sends the servlet the course ID.
                    if (course.getCourseStatus().equals("On-Going") || course.getCourseStatus().equals("Beginner")) {
                        out.println("<a href=\"" + request.getContextPath() + "/EndLessonsServlet?courseId=" + course.getCourseId() +"&studentId=" + student.getStudentId() + "\" class=\"bottomlink\" style=\"color: red\">End Lessons</a>");
                    }
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
