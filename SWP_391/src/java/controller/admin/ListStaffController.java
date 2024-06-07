/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import DAO.Common.UserContractDAO;
import DAO.Common.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Common.User;
import model.Common.UserContract;

/**
 *
 * @author Admin
 */
public class ListStaffController extends HttpServlet {

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
            out.println("<title>Servlet ListStaffController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListStaffController at " + request.getContextPath() + "</h1>");
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
        if (user == null) {
            request.setAttribute("loginMessage", "Vui lòng đăng nhập để sử dụng dịch vụ!");
            request.getRequestDispatcher("view\\customer\\login.jsp").forward(request, response);
            return;
        } else if (user.getRole() != 1) {
            response.sendRedirect("404.jsp");
        } else {
            UserDAO userDAO = new UserDAO();
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);

            String txtSearch = request.getParameter("txtSearch");
            if (txtSearch != null && !txtSearch.isEmpty()) {
                int countName = userDAO.getTotalUsersByName(txtSearch);
                int endPage = countName / 10;
                if (countName % 10 != 0) {
                    endPage++;
                }
                UserContractDAO ucdao = new UserContractDAO();
                ArrayList<User> selectStaff = userDAO.SearchUserByName(txtSearch, index);
                request.setAttribute("endPage", endPage);
                request.setAttribute("selectStaff", selectStaff);
                request.setAttribute("txtSearch", txtSearch);
                request.getRequestDispatcher("view\\admin\\StaffList.jsp").forward(request, response);
            } else {
                int count = userDAO.getTotalUsers();
                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage++;
                }
                ArrayList<User> selectStaff = userDAO.sellectallStaffByPaging(index);
                UserContractDAO ucdao = new UserContractDAO();
                request.setAttribute("txtSearch", txtSearch);
                request.setAttribute("endPage", endPage);
                request.setAttribute("selectStaff", selectStaff);
                request.getRequestDispatcher("view\\admin\\StaffList.jsp").forward(request, response);
            }

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
