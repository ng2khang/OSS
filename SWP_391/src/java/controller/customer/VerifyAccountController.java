/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import DAO.Common.AccountActiveTokenDAO;
import DAO.Common.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Common.AccountActiveToken;
import model.Common.User;

/**
 *
 * @author ifyou
 */
public class VerifyAccountController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String verifyToken = request.getParameter("token");
            AccountActiveTokenDAO accountActiveTokenDAO = new AccountActiveTokenDAO();
            AccountActiveToken accountActiveToken = accountActiveTokenDAO.findByActiveToken(verifyToken);
            // check if token is valid
            if (accountActiveToken != null) {
                // Get the user with account ative token
                UserDAO userDAO = new UserDAO();
                User user = userDAO.searchUserByEmail(accountActiveToken.getUserEmail());
                // change the status of user
                userDAO.activeUserAccount(user.getEmail());
                // delete the active tokens
                accountActiveTokenDAO.deleteAccountActiveToken(accountActiveToken.getUserEmail());
                // redirect to the login page with success message
                String message = "Tài khoản của bạn đã được kích hoạt thành công. Xin hãy đăng nhập để tiếp tục sử dụng dịch vụ";
                request.setAttribute("message", message);
                request.getRequestDispatcher("view\\customer\\login.jsp").forward(request, response);
            }else{
             // return to 404 not found page
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
