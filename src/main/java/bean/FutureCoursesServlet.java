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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seanl
 */
public class FutureCoursesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        CourseManager cm = new CourseManager();
        
        //Load all courses with a date later than todays
        ArrayList<Course> futureCourses = cm.loadFutureCourses();
        
        //Initialise date and time formatters for displaying course date/time
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //Generate HTML to display each course that's currently available for registration, looping through futureCourses arraylist.
            for(Course course : futureCourses){
                String startDate = sdf2.format(course.getLessons().get(0).getTimeSlot());
                String startTime = sdf1.format(course.getLessons().get(0).getTimeSlot().getTime());
                out.println("<div class=\"course-box\"><div style=\"width: 20%\"><h3>" + course.getCourseName() + "</h3>");
                out.println("<p><b>Start Date: </b>" + startDate + " " + startTime + "</p>");
                out.println("<p> <b>Tutor: </b>" + course.getCourseTutor().getFirstName() + " " + course.getCourseTutor().getLastName() + "</p>");
                out.println("<p><b>No. of lessons: </b>" + course.getLessons().size() +"</p></div>");
                out.println("<div style='max-width: 80%'><p><b>Description: </b><br><p>"+course.getDescription()+"</p>");
                out.println("<small><a href=\"CourseEnrollmentServlet?courseid=" + course.getCourseId() +"\">Register for course</a></small></div></div>");
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
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FutureCoursesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FutureCoursesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
