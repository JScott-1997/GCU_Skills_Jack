package bean;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seanl
 */
public class StudentLoginServlet extends HttpServlet {

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
        UserManager userManager = new UserManager();
        RequestDispatcher dispatcher;
        
        //Read in variables from web form request
        String email = request.getParameter("emailaddress");
        String password = request.getParameter("password");
        
        //Create student object and attempt to retrieve details 
        Student student = userManager.logInStudent(email, password);
        
        //Get other user objects from session to make sure no other user is logged in
        HttpSession session = request.getSession();
        Tutor tutor = (Tutor) session.getAttribute("tutor");
        Admin admin = (Admin) session.getAttribute("admin");
            
        //Create student session variable and send to account.jsp if login successful, otherwise send error message
        if (student != null && (tutor == null && admin == null)) {
            session.setAttribute("student", student);
            session.setAttribute("loggedIn", "true");
            response.sendRedirect("student/account.jsp");
        } 
        else if(tutor != null || admin != null) {
            dispatcher = request.getRequestDispatcher("login.jsp");
            request.setAttribute("error", "You are already logged in.");
            dispatcher.forward(request, response);
        }
        else {
            dispatcher = request.getRequestDispatcher("login.jsp");
            request.setAttribute("error", "Invalid login details.");
            dispatcher.forward(request, response);
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
        } catch (SQLException ex) {
            response.sendRedirect("login.jsp?error=true");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            response.sendRedirect("login.jsp?error=true");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
