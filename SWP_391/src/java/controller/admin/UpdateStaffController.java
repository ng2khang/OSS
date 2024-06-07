/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

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
 * @author Admin
 */
public class UpdateStaffController extends HttpServlet {

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
            out.println("<title>Servlet UpdateStaffController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateStaffController at " + request.getContextPath() + "</h1>");
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
        // processRequest(request, response);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.setAttribute("loginMessage", "Vui lòng đăng nhập để sử dụng dịch vụ!");
            request.getRequestDispatcher("view\\customer\\login.jsp").forward(request, response);
            return;
        } else if (user.getRole() != 1) {
            response.sendRedirect("404.jsp");
        } else {
            String id_raw = request.getParameter("id");
            int id;
            UserDAO udao = new UserDAO();
            try {
                id = Integer.parseInt(id_raw);
                User getStaffId = udao.getUserByID(id_raw);
                request.setAttribute("getStaffId", getStaffId);
                request.getRequestDispatcher("view\\admin\\UpdateStaff.jsp").forward(request, response);
            } catch (NumberFormatException e) {

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.setAttribute("loginMessage", "Vui lòng đăng nhập để sử dụng dịch vụ!");
            request.getRequestDispatcher("view\\customer\\login.jsp").forward(request, response);
            return;
        } else if (user.getRole() != 1) {
            response.sendRedirect("404.jsp");
        } else {
            int sid = Integer.parseInt(request.getParameter("id"));
            String sName = request.getParameter("name");
            String sEmail = request.getParameter("email");
            String sPhone = request.getParameter("phone");
            String sRole = request.getParameter("role");
            String sStatus = request.getParameter("status");
            UserDAO udao = new UserDAO();

            if (sStatus.equals("1")) {
                if (sRole.equals("2")) {
                    User newUser = new User(sid, sName, sEmail, sPhone, 0, 2, 1);
                    udao.update(newUser);
                    response.sendRedirect("staff");
                } else if (sRole.equals("3")) {
                    User newUser = new User(sid, sName, sEmail, sPhone, 0, 3, 1);
                    udao.update(newUser);
                    response.sendRedirect("staff");
                }
            } else if (sStatus.equals("0")) {
                if (sRole.equals("2")) {
                    User newUser = new User(sid, sName, sEmail, sPhone, 0, 2, 0);
                    udao.update(newUser);
                    response.sendRedirect("staff");
                } else if (sRole.equals("3")) {
                    User newUser = new User(sid, sName, sEmail, sPhone, 0, 3, 0);
                    udao.update(newUser);
                    response.sendRedirect("staff");
                }
            }
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
