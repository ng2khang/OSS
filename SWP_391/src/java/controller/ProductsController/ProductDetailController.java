/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.ProductsController;

import DAO.ColorsDAO.ColorsDAO;
import DAO.ProductDAO.ProductDetailDAO;
import DAO.ProductDAO.ProductsDAO;
import DAO.ProductDAO.SizeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import model.Colors.Colors;
import model.Product.ProductDetails;
import model.Product.Products;
import model.Product.Sizes;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;

/**
 *
 * @author lucdu
 */
@WebServlet("/your-servlet-url")
@MultipartConfig
public class ProductDetailController extends HttpServlet {

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
            out.println("<title>Servlet ProductDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductDetailController at " + request.getContextPath() + "</h1>");
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
        ProductDetailDAO pd = new ProductDetailDAO();
        ProductsDAO pdao = new ProductsDAO();
        ColorsDAO cdao = new ColorsDAO();
        SizeDAO sdao = new SizeDAO();
        ArrayList<Products> data1 = pdao.selectAllProducts();
        ArrayList<Colors> data2 = cdao.getAll();
        ArrayList<Sizes> data3 = sdao.selectAllSizes();
        request.setAttribute("data1", data1);
        request.setAttribute("data2", data2);
        request.setAttribute("data3", data3);

        if (request.getParameter("mod") != null && request.getParameter("mod").equals("1")) {
            request.getRequestDispatcher("view\\Products\\CreatePDetails.jsp").forward(request, response);
        }
        if (request.getParameter("mod") != null && request.getParameter("mod").equals("2")) {
            ProductDetails pDetail = pd.selectProductDetailById(request.getParameter("id"));
            request.setAttribute("pDetail", pDetail);
            request.getRequestDispatcher("view\\Products\\UpdatePDetail.jsp").forward(request, response);
        }
        if (request.getParameter("mod") != null && request.getParameter("mod").equals("3")) {
            pd.softDeleteProductDetail(request.getParameter("id"));
        }
        if (request.getParameter("mod") != null && request.getParameter("mod").equals("4")) {
            ProductDetails pDetail = pd.selectProductDetailById(request.getParameter("id"));
            request.setAttribute("pd", pDetail);
            request.getRequestDispatcher("view\\ProductCustomer\\ProductDetail.jsp").forward(request, response);
        }
        ArrayList<ProductDetails> data = pd.selectAllProductDetails();
        request.setAttribute("data", data);
        request.getRequestDispatcher("view\\Products\\VeiwPDetails.jsp").forward(request, response);
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
        String id = request.getParameter("id");
        String product_id = request.getParameter("product_id");
        String color_id = request.getParameter("color_id");
        String size_id = request.getParameter("size_id");
        String inventory_number = request.getParameter("inventory");

        // Lưu trữ tên file ảnh
        String image_url_1 = "";
        String image_url_2 = "";
        String image_url_3 = "";
        String image_url_4 = "";

        // Xử lý tải ảnh
        for (int i = 1; i <= 4; i++) {
            Part filePart = request.getPart("image" + i); // Lấy tệp ảnh từ request
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Lấy tên tệp
            String savePath = "C:\\FPT\\JavaWeb\\SWP\\SWP391\\SWP_391\\web\\images\\" + fileName; // Đường dẫn lưu trữ trong dự án
            // Đường dẫn lưu trữ ảnh mà bạn muốn so sánh
            String imagePath = "C:\\FPT\\JavaWeb\\SWP\\SWP391\\SWP_391\\web\\images\\" + fileName;
            if (savePath == imagePath) {
               switch (i) {
                case 1:
                    image_url_1 = fileName;
                    break;
                case 2:
                    image_url_2 = fileName; 
                    break;
                case 3:
                    image_url_3 = fileName;
                    break;
                case 4:
                    image_url_4 = fileName;
                    break;
            } 
            } else {
                filePart.write(savePath); // Lưu tệp ảnh vào thư mục
            switch (i) {
                case 1:
                    image_url_1 = fileName;
                    break;
                case 2:
                    image_url_2 = fileName; 
                    break;
                case 3:
                    image_url_3 = fileName;
                    break;
                case 4:
                    image_url_4 = fileName;
                    break;
            }
            }
        }

        ProductDetailDAO pd = new ProductDetailDAO();
        if (request.getParameter("add") != null) {
            pd.createNewProductDetails(product_id, color_id, size_id, inventory_number, image_url_1, image_url_2, image_url_3, image_url_4);
            response.sendRedirect("productdetails");
            return;
        }
        if (request.getParameter("update") != null) {
            pd.updateProductDetail(id, product_id, color_id, size_id, inventory_number, image_url_1, image_url_2, image_url_3, image_url_4);
            response.sendRedirect("productdetails");
            return;
        }
        if (request.getParameter("delete") != null) {
            pd.softDeleteProductDetail(id);
            response.sendRedirect("productdetails");
            return;
        }
        if (request.getParameter("restore") != null) {
           pd.restoreProductDetail(id);
            response.sendRedirect("productdetails");
            return;
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
