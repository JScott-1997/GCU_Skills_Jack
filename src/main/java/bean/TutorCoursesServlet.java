/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author seanl
 */
public class TutorCoursesServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        //Get session and user objects
        HttpSession session = request.getSession();
        Tutor tutor = (Tutor) session.getAttribute("tutor");
        
        //Load courses that user is registered on
        CourseManager cm = new CourseManager();
        ArrayList<Course> courses = cm.loadTutorCourses(tutor.getTutorId());
        
        try (PrintWriter out = response.getWriter()) {
            if (courses == null) {
                out.println("There are no courses to display.");
            } else {
                out.println("<table class=\"lessonstable\">");
                out.println("<tr>");
                out.println("<th>Course ID</th>");
                out.println("<th>Course Name</th>");
                out.println("<th>Start Date</th>");
                out.println("<th>No. Lessons</th>");
                out.println("<th>Students Enrolled</th>");
                out.println("<th></th>");
                out.println("</tr>");
                
                //For each course, display row with details and link to see enrolled students
                for (Course course : courses) {
                    ArrayList<Student> courseStudents = cm.loadCourseStudents(course.getCourseId());
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String startDate = sdf.format(course.getLessons().get(0).getTimeSlot());
                    out.println("<tr>");
                    out.format("<td>%s</td>", course.getCourseId());
                    out.format("<td>%s</td>", course.getCourseName());
                    out.format("<td>%s</td>", startDate);
                    out.format("<td>%s</td>", course.getLessons().size());
                    out.format("<td>%s</td>", courseStudents.size());
                    out.format("<td><small><a href=\"%1$s/tutor/coursestudents.jsp?courseId=%2$s\">View Students</small></td>", request.getContextPath(), course.getCourseId());
                    out.println("</tr>");
                }
                out.println("</table>");
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
