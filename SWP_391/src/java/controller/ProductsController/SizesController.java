/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.ProductsController;

import DAO.ProductDAO.SizeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Common.User;
import model.Product.Sizes;

/**
 *
 * @author lucdu
 */
public class SizesController extends HttpServlet {

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
            out.println("<title>Servlet SizesController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SizesController at " + request.getContextPath() + "</h1>");
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
            SizeDAO g = new SizeDAO();
            if (request.getParameter("mod") != null && request.getParameter("mod").equals("1")) {
                request.getRequestDispatcher("view\\Products\\CreateSizes.jsp").forward(request, response);
            }
            if (request.getParameter("mod") != null && request.getParameter("mod").equals("2")) {
                Sizes size = g.selectSizeByID(request.getParameter("id"));
                request.setAttribute("size", size);
                request.getRequestDispatcher("view\\Products\\UpdateSizes.jsp").forward(request, response);
            }
            if (request.getParameter("mod") != null && request.getParameter("mod").equals("3")) {
                g.softDeleteSize(request.getParameter("id"));
            }
            //------------------------------------------------------------------------------------------------------------------
            ArrayList<Sizes> data = g.selectAllSizes();
            request.setAttribute("data", data);
            request.getRequestDispatcher("view\\Products\\VeiwSizes.jsp").forward(request, response);
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
                SizeDAO p = new SizeDAO();
                p.createNewSize(name, description);
                response.sendRedirect("sizes");
                return;
            }
            if (request.getParameter("update") != null) {
                SizeDAO p = new SizeDAO();
                p.updateSize(id, name, description);
                response.sendRedirect("sizes");
                return;
            }
            if (request.getParameter("delete") != null) {
                SizeDAO p = new SizeDAO();
                p.softDeleteSize(id);
                response.sendRedirect("sizes");
                return;
            }
            if (request.getParameter("restore") != null) {
                SizeDAO p = new SizeDAO();
                p.restoreSize(id);
                response.sendRedirect("sizes");
                return;
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
