package bean;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author seanl
 */
public class StudentRegisterServlet extends HttpServlet {

    private final UserManager userManager = new UserManager();
    private final PassHash passHash = new PassHash();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //No doGet() method as will not be used in registration (not secure method of transmitting data)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

            //Read variables in from web form request and trim any whitespace
            String email = request.getParameter("email").trim();
            String password = passHash.hashPassword(request.getParameter("password"));
            String firstName = request.getParameter("fname").trim();
            String lastName = request.getParameter("surname").trim();
            String phone = request.getParameter("contactno").trim();
            String sDob = request.getParameter("dob");

            //Convert sDob string from request to Date object
            Date dob = new SimpleDateFormat("dd-MM-yyyy").parse(sDob);

            //Create new student object with details read in
            Student student = new Student(email, password, firstName, lastName, dob, phone);

            //Register student in database, if successful will return studentId which is > 0
            int i = userManager.registerStudent(student);

            //Send error message if registration was unsuccessful
            if (i == 0) {
                RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
                request.setAttribute("error", "Registration error.");
                rd.forward(request, response);
            } //Return studentId in request and redirect to page confirming registration
            else {
                HttpSession session = request.getSession(false);
                request.setAttribute("studentid", i);
                RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
                rd.forward(request, response);
            }

            //Send error message if exception occurs
        } catch (ParseException ex) {
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            request.setAttribute("error", "Registration error.");
            rd.forward(request, response);
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
