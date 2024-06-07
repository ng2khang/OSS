/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.sale;

import DAO.GroupsDAO.FormDAO;
import DAO.GroupsDAO.GroupsDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Common.User;
import model.Sale.Form;

/**
 *
 * @author lucdu
 */
public class FormsController extends HttpServlet {

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
            out.println("<title>Servlet FormsController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FormsController at " + request.getContextPath() + "</h1>");
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
        } else if (user.getRole() != 2) {
            response.sendRedirect("404.jsp");
        } else {
            FormDAO g = new FormDAO();
            if (request.getParameter("mod") != null && request.getParameter("mod").equals("1")) {
                request.getRequestDispatcher("view\\sale\\CreateForm.jsp").forward(request, response);
            }
            if (request.getParameter("mod") != null && request.getParameter("mod").equals("2")) {
                Form group = g.selectFormByID(request.getParameter("id"));
                request.setAttribute("group", group);
                request.getRequestDispatcher("view\\sale\\UpdateForm.jsp").forward(request, response);
            }
            if (request.getParameter("mod") != null && request.getParameter("mod").equals("3")) {
                g.softDeleteForm(request.getParameter("id"));
            }
            //------------------------------------------------------------------------------------------------------------------
            ArrayList<Form> data = g.selectAllForm();
            request.setAttribute("data", data);
            request.getRequestDispatcher("view\\sale\\ViewForm.jsp").forward(request, response);
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
        } else if (user.getRole() != 2) {
            response.sendRedirect("404.jsp");
        } else {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String created_at = request.getParameter("created_at");
            String deleted_at = request.getParameter("deleted_at");
            if (request.getParameter("add") != null) {
                FormDAO p = new FormDAO();
                p.createNewForm(name, description);
                response.sendRedirect("Forms");
                return;
            }
            if (request.getParameter("update") != null) {
                FormDAO p = new FormDAO();
                p.updateForm(id, name, description);
                response.sendRedirect("Forms");
                return;
            }
            if (request.getParameter("delete") != null) {
                FormDAO p = new FormDAO();
                p.softDeleteForm(id);
                response.sendRedirect("Forms");
                return;
            }
            if (request.getParameter("restore") != null) {
                FormDAO p = new FormDAO();
                p.restoreGroups(id);
                response.sendRedirect("Forms");
                return;
            }
        }

    }
}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
