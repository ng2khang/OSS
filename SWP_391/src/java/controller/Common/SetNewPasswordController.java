/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Common;

import DAO.Common.PasswordResetTokenDAO;
import DAO.Common.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import model.Common.PasswordResetToken;
import model.Common.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ifyou
 */
public class SetNewPasswordController extends HttpServlet {

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
        String token = request.getParameter("token");
        // check if the token is exist or not
        PasswordResetTokenDAO passwordResetTokenDAO = new PasswordResetTokenDAO();
        PasswordResetToken passwordResetToken = passwordResetTokenDAO.findByToken(token);
        // check if token is exist
        if (passwordResetToken != null) {
            LocalDateTime now = LocalDateTime.now();
            Timestamp nowTimeStamp = Timestamp.valueOf(now);
            // check if token is expired
            if (passwordResetToken.getExpiration_time().before(nowTimeStamp)) {
                // redirect to token expired page
                response.sendRedirect("TokenExpired.jsp");
            } else {
                // token is not expired
                request.setAttribute("token", token);
                request.getRequestDispatcher("SetNewPassword.jsp").forward(request, response);
            }
        } else {
            // token is failed, send to 404 not found
            response.sendRedirect("404.jsp");
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
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String token = request.getParameter("token");
        // check if password and confirm password is match
        if (password.equals(confirmPassword)) {
            // change the password
            PasswordResetTokenDAO passwordResetTokenDAO = new PasswordResetTokenDAO();
            PasswordResetToken passwordResetToken = passwordResetTokenDAO.findByToken(token);
            UserDAO userDAO = new UserDAO();
            User user = userDAO.searchUserByEmail(passwordResetToken.getUserEmail());
            // encode the password
            String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
            userDAO.changePassword(user.getEmail(), encodedPassword);
            // delete the token
            passwordResetTokenDAO.deletePasswordResetToken(user.getEmail());          
            request.setAttribute("passwordChange", "Mật khẩu của bạn đã được thay đổi thành công. Vui lòng đăng nhập để tiếp tục sử dụng dịch vụ!");
            request.getRequestDispatcher("view\\customer\\login.jsp").forward(request, response);
        }else{
            // password and confirm password is not match
            request.setAttribute("message", "Password and Confirm Password is not match!");
            request.setAttribute("password", password);
            request.setAttribute("confirmPassword", confirmPassword);
            request.setAttribute("token", token);
            request.getRequestDispatcher("SetNewPassword.jsp").forward(request, response);
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
