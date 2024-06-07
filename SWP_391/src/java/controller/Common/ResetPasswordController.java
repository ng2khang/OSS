/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Common;

import DAO.Common.PasswordResetTokenDAO;
import DAO.Common.UserDAO;
import Service.SendMailService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;
import model.Common.PasswordResetToken;
import model.Common.User;

/**
 *
 * @author ifyou
 */
public class ResetPasswordController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResetPasswordController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPasswordController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String email = request.getParameter("email");
        // search the user with email
        UserDAO userDAO = new UserDAO();
        User user = userDAO.searchUserByEmail(email);
        // check if email is exist 
        if(user != null){
            // add reset password token
            PasswordResetTokenDAO passwordResetTokenDAO = new PasswordResetTokenDAO();
            LocalDateTime expirationTime = LocalDateTime.now().plusDays(1);
            Timestamp timeStamp = Timestamp.valueOf(expirationTime);
            String resetToken = UUID.randomUUID().toString();
            PasswordResetToken passwordResetToken = new PasswordResetToken(email, resetToken, timeStamp);
            passwordResetTokenDAO.createPasswordResetToken(passwordResetToken);
            // send the reset password email to the user
            SendMailService mailService = new SendMailService();
            mailService.sendResetPassword(user.getEmail(), resetToken);
            response.sendRedirect("ResetPasswordSuccess.jsp");
        }else{
         // the email is not exist
         request.setAttribute("email", email);
         request.setAttribute("message", "Địa chỉ email này không tồn tại");
         request.getRequestDispatcher("view\\customer\\fogotpassword.jsp").forward(request, response);
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
