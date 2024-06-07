/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.ProductsController;

import DAO.ColorsDAO.ColorsDAO;
import DAO.GroupsDAO.BrandsDAO;
import DAO.GroupsDAO.CategoryDAO;
import DAO.GroupsDAO.FormDAO;
import DAO.MaterialsDAO.MaterialsDAO;
import DAO.ProductDAO.ProductDetailDAO;
import DAO.ProductDAO.ProductsDAO;
import DAO.ProductDAO.SizeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Categories.Category;
import model.Colors.Colors;
import model.Groups.Brands;
import model.Materials.materials;
import model.Product.ProductDetails;
import model.Product.Sizes;
import model.Sale.Form;

/**
 *
 * @author lucdu
 */
public class DetailCustomer extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DetailCustomer</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailCustomer at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ProductsDAO p = new ProductsDAO();
        CategoryDAO g = new CategoryDAO();
        BrandsDAO b = new BrandsDAO();
        ColorsDAO cdao = new ColorsDAO();
        SizeDAO sdao = new SizeDAO();
        FormDAO fdao = new FormDAO();
        MaterialsDAO mdao = new MaterialsDAO();
        ProductDetailDAO pd = new ProductDetailDAO();
        List<Category> data1 = g.selectAllCategory();
        List<ProductDetails> data0 = pd.selectAllProductDetails();
        List<Colors> data3 = cdao.getAll();
        List<Sizes> data4 = sdao.selectAllSizes();
        List<Form> data5 = fdao.selectAllForm();
        List<materials> data6 = mdao.getAll();
        List<Brands> data2 = b.selectAllBrands();

        request.setAttribute("data0", data0);
        request.setAttribute("data1", data1);
        request.setAttribute("data2", data2);
        request.setAttribute("data3", data3);
        request.setAttribute("data4", data4);
        request.setAttribute("data5", data5);
        request.setAttribute("data6", data6);
        if (request.getParameter("productId") != null && request.getParameter("colorId") != null && request.getParameter("sizeId") != null) {
            // Lấy các tham số từ request
            String productId = request.getParameter("productId");
            String colorId = request.getParameter("colorId");
            String sizeId = request.getParameter("sizeId");
            request.setAttribute("pd", pd.selectProductDetailByProductIdColorIdAndSizeId(productId, colorId, sizeId));
            ProductDetails productDetails = (ProductDetails) request.getAttribute("pd");
            String prodId = productDetails.getProduct().getId();
            ArrayList<Colors> cls = pd.getAllColorsByProductID(prodId);
            request.setAttribute("cls", cls);
// Lấy ArrayList<Sizes> từ ProductDetail của productId và liên quan đến colorId

            ArrayList<Sizes> si = pd.getAllSizesByColorIDAndProductID(colorId, productId);
            request.setAttribute("si", si);
         
            request.getRequestDispatcher("view\\ProductCustomer\\PDetailCustomer.jsp").forward(request, response);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
