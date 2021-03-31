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

/**
 *
 * @author seanl
 */
public class Navbar extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            
            //Get session object
            HttpSession session = request.getSession();
            out.println("<nav id=\"navbar\">\n"
                    + "           <img src=\"" + request.getContextPath() + "/images/GCU_SkillsLogoWordsSmall.png\" alt=\"GCU Skills\"/><ul>");
            out.println("<li><a href=\"" + request.getContextPath() + "/index.jsp\">Home</a></li> "
                        + "<li><a href=\"" + request.getContextPath() + "/index.jsp#about\">About</a></li>"
                        + "<li><a href=\"" + request.getContextPath() + "/index.jsp#contact\" id=\"contactlink\">Contact</a></li> "
                        + "<li><a href=\"" + request.getContextPath() + "/courses.jsp#start\">Courses</a></li>");
            
            //Check if user logged in and print out different links for logged in and not logged in users
            if (session.getAttribute("loggedIn") != null && session.getAttribute("student") != null) {
                        out.println("<li><a href=\"" + request.getContextPath() + "/student/account.jsp\">Account</a></li>"
                        + "<li><a href=\"" + request.getContextPath() + "/logout\">Logout</a></li>");
            }
            
            else if(session.getAttribute("tutor") != null){
                out.println("<li><a href=\"" + request.getContextPath() + "/tutor/account.jsp\">Account</a></li>"
                        + "<li><a href=\"" + request.getContextPath() + "/logout\">Logout</a></li>");
            }
            
            else if(session.getAttribute("admin") != null){
                out.println("<li><a href=\"" + request.getContextPath() + "/admin/account.jsp\">Account</a></li>"
                        + "<li><a href=\"" + request.getContextPath() + "/logout\">Logout</a></li>");
            }
            
            else {
                out.println("<li><a href=\"" + request.getContextPath() + "/register.jsp\">Register</a></li>"
                        + "<li><a href=\"" + request.getContextPath() + "/login.jsp\">Log in</a></li>");
            }
            out.println("</ul>\n"
                    + "        </nav>");
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
