/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.ProductsController;

import DAO.GroupsDAO.BrandsDAO;
import DAO.GroupsDAO.CategoryDAO;
import DAO.GroupsDAO.FormDAO;
import DAO.GroupsDAO.GroupsDAO;
import DAO.MaterialsDAO.MaterialsDAO;
import DAO.ProductDAO.ProductsDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Categories.Category;
import model.Common.User;
import model.Groups.Brands;
import model.Groups.Groups;
import model.Materials.materials;
import model.Product.Products;
import model.Sale.Form;

/**
 *
 * @author lucdu
 */
public class ProductsController extends HttpServlet {

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
            out.println("<title>Servlet ProductsController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductsController at " + request.getContextPath() + "</h1>");
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
            ProductsDAO p = new ProductsDAO();
            CategoryDAO cdao = new CategoryDAO();
            FormDAO fdao = new FormDAO();
            BrandsDAO bdao = new BrandsDAO();
            MaterialsDAO mdao = new MaterialsDAO();
            GroupsDAO gdao = new GroupsDAO();
            ArrayList<Form> data2 = fdao.selectAllForm();
            ArrayList<Brands> data3 = bdao.selectAllBrands();
            ArrayList<materials> data4 = mdao.getAll();
            ArrayList<Groups> data5 = gdao.selectAllGroups();
            List<Category> data1 = cdao.selectAllCategory();
            request.setAttribute("data1", data1);
            request.setAttribute("data2", data2);
            request.setAttribute("data3", data3);
            request.setAttribute("data4", data4);
            request.setAttribute("data5", data5);
            if (request.getParameter("mod") != null && request.getParameter("mod").equals("1")) {
                request.getRequestDispatcher("view\\Products\\CreateProducts.jsp").forward(request, response);
            }
            if (request.getParameter("mod") != null && request.getParameter("mod").equals("2")) {
                Products product = p.selectProductByID(request.getParameter("id"));
                request.setAttribute("product", product);
                request.getRequestDispatcher("view\\Products\\UpdateProduct.jsp").forward(request, response);
            }
            if (request.getParameter("mod") != null && request.getParameter("mod").equals("3")) {
                p.softDeleteProduct(request.getParameter("id"));
            }
            //------------------------------------------------------------------------------------------------------------------
            ArrayList<Products> data = p.selectAllProducts();
            request.setAttribute("data", data);
            request.getRequestDispatcher("view\\Products\\VeiwProducts.jsp").forward(request, response);
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
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String price = request.getParameter("price");
            String category_id = request.getParameter("category_id");
            String form_id = request.getParameter("form_id");
            String brand_id = request.getParameter("brand_id");
            String material_id = request.getParameter("material_id");
            String group_id = request.getParameter("group_id");
            ProductsDAO p = new ProductsDAO();

            if (request.getParameter("add") != null) {
                p.createNewProduct(code, name, description, price, category_id, form_id, brand_id, material_id, group_id);
                response.sendRedirect("products");
                return;
            }
            if (request.getParameter("delete") != null) {
                p.softDeleteProduct(id);
                response.sendRedirect("products");
                return;
            }
            if (request.getParameter("update") != null) {
                p.updateProduct(id, code, name, description, price, category_id, form_id, brand_id, material_id, group_id);
                response.sendRedirect("products");
                return;
            }
            if (request.getParameter("restore") != null) {
                p.restoreProduct(id);
                response.sendRedirect("products");
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
