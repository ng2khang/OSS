/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import DAO.Common.CartDAO;
import DAO.ProductDAO.ProductDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Common.Cart;
import model.Common.User;
import model.Product.ProductDetails;

/**
 *
 * @author ifyou
 */
public class AddToCartServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // Check if user have loged in
        if (user == null) {
            request.setAttribute("loginMessage", "Vui lòng đăng nhập để sử dụng dịch vụ!");
            request.getRequestDispatcher("view\\customer\\login.jsp").forward(request, response);
            return;
        } else {
            String id = request.getParameter("id");
            ProductDetailDAO prdDAO = new ProductDetailDAO();
            ProductDetails pd = prdDAO.selectProductDetailById(id);
            String quantity = "1";
            CartDAO cartDAO = new CartDAO();
            int isExist = cartDAO.isExistItem(user.getId(), Integer.parseInt(id));
            if (isExist != 0) {
                Cart cartItem = cartDAO.getCartItem(user.getId(), Integer.parseInt(id));
                cartDAO.addQuantity(String.valueOf(cartItem.getId()));
                //CustomerProducts?productId=${productdetail.getProduct().getId()}&colorId=${productdetail.getColor().getId()}&sizeId=${productdetail.getSize().getId()}
                request.getSession().setAttribute("addToCartSuccess", "Sản phẩm đã được thêm vào giỏ hàng");
                response.sendRedirect("CustomerProducts?productId=" + pd.getProduct().getId() + "&colorId=" + pd.getColor().getId() + "&sizeId=" + pd.getSize().getId());
            } else {
                cartDAO.addTocart(user.getId(), Integer.parseInt(id), Integer.parseInt(quantity));
                request.getSession().setAttribute("addToCartSuccess", "Sản phẩm đã được thêm vào giỏ hàng");
                session.removeAttribute("total");
                int total = cartDAO.getCartQuantity(user.getId());
                session.setAttribute("total", total);
                response.sendRedirect("CustomerProducts?productId=" + pd.getProduct().getId() + "&colorId=" + pd.getColor().getId() + "&sizeId=" + pd.getSize().getId());
            }

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
