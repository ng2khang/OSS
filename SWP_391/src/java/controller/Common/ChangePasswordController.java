/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Common;

import DAO.Common.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Common.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ifyou
 */
public class ChangePasswordController extends HttpServlet {

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
            out.println("<title>Servlet ChangePasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // Check if user have loged in
        if (user == null) {
            request.setAttribute("loginMessage", "Vui lòng đăng nhập để sử dụng dịch vụ!");
            request.getRequestDispatcher("view\\customer\\login.jsp").forward(request, response);
            return;
        }
        response.sendRedirect("changepassword.jsp");
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
        String oldPassword = request.getParameter("oldPassword");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserDAO userDAO = new UserDAO();
        // Check if old password ís true
        boolean is_true_password = BCrypt.checkpw(oldPassword, user.getPassword());
        if (is_true_password) {
            // check if password and password confirm is match
            if (password.equals(confirmPassword)) {
                // Change the password of user
                String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
                userDAO.changePassword(user.getEmail(), encodedPassword);
                session.removeAttribute("user");
                request.setAttribute("passwordChange", "Bạn đã thay đổi mật khẩu thành công. Vui lòng đăng nhập lại!");
                request.getRequestDispatcher("view\\customer\\login.jsp").forward(request, response);
            } else {
                // return info to user
                request.setAttribute("passwordNotMatch", "Mật khẩu và xác nhận mật khẩu không khớp!");
                request.setAttribute("oldPassword", oldPassword);
                request.setAttribute("password", password);
                request.setAttribute("confirmPassword", confirmPassword);
                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("passwordIncorrect", "Mật khẩu cũ không chính xác!");
            request.setAttribute("oldPassword", oldPassword);
            request.setAttribute("password", password);
            request.setAttribute("confirmPassword", confirmPassword);
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
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
