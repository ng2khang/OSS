/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Common;

import DAO.Common.CartDAO;
import DAO.Common.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Common.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ifyou
 */
public class LoginController extends HttpServlet {

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
        response.sendRedirect("view\\customer\\login.jsp");
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
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.searchUserByEmailAndPassword(email);
        // check password
        if (user != null) {
            boolean is_true_password = BCrypt.checkpw(password, user.getPassword());
            // if password is true
            if (is_true_password) {
                request.getSession().setAttribute("user", user);
                // check if account is not active
                if (user.getStatus() == 0 && user.getRole() == 4) {
                    request.setAttribute("inactiveMessage", "Tài khoản chưa được kích hoạt. Vui lòng kiểm tra email để kích hoạt tài khoản");
                    // send the message active account to customer
                    request.getRequestDispatcher("view\\customer\\login.jsp").forward(request, response);
                } else if (user.getStatus() == 0 && user.getRole() == 2 || user.getStatus() == 0 && user.getRole() == 3) {
                    request.setAttribute("wrongLoginInfo", "Tài khoản của bạn đã bị khóa!");
                    request.getRequestDispatcher("view\\customer\\login.jsp").forward(request, response);
                } else {
                    switch (user.getRole()) {
                        case 1:
                            response.sendRedirect("staff");
                            break;
                        case 2:
                            response.sendRedirect("OrderController");
                            break;
                        case 3:
                            response.sendRedirect("OrderController");
                            break;
                        default:
                            CartDAO cDAO = new CartDAO();
                            int total = cDAO.getCartQuantity(user.getId());
                            request.getSession().setAttribute("total", total);
                            response.sendRedirect("CustomerProducts");
                            break;
                    }
                }
            } else {
                // password is false
                request.getSession().setAttribute("user", null);
                request.setAttribute("wrongLoginInfo", "Email hoặc mật khẩu không chính xác!");
                request.setAttribute("email", email);
                request.setAttribute("password", password);
                request.getRequestDispatcher("view\\customer\\login.jsp").forward(request, response);
            }
        } else {
            // email is not exist
            request.getSession().setAttribute("user", null);
            request.setAttribute("wrongLoginInfo", "Email hoặc mật khẩu không chính xác!");
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.getRequestDispatcher("view\\customer\\login.jsp").forward(request, response);
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
