/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import DAO.Common.CartDAO;
import DAO.Common.OrderDAO;
import DAO.Common.OrderDetailDAO;
import DAO.ProductDAO.ProductDetailDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.UUID;
import model.Common.Cart;
import model.Common.Order;
import model.Common.OrderDetail;
import model.Common.User;
import model.Product.ProductDetails;

/**
 *
 * @author ifyou
 */
public class AddOrderServlet extends HttpServlet {

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
        String addressId = request.getParameter("address");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // Check if user have loged in
        if (user == null) {
            request.setAttribute("loginMessage", "Vui lòng đăng nhập để sử dụng dịch vụ!");
            request.getRequestDispatcher("view\\customer\\login.jsp").forward(request, response);
            return;
        } else {
            
            OrderDAO orderDAO = new OrderDAO();
            String orderCode = UUID.randomUUID().toString();
            orderDAO.addNewOrder(orderCode, user.getId(), Integer.parseInt(addressId), 1);
            Order order = orderDAO.searchOrderByCode(orderCode);
            CartDAO cartDAO = new CartDAO();
            ArrayList<Cart> orderItems = cartDAO.selectCheckoutItem(user.getId());
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            ProductDetailDAO pdDAO = new ProductDetailDAO();
            for (Cart orderItem : orderItems) {
                ProductDetails p = pdDAO.selectProductDetailById(orderItem.getProductDetail().getId());
                if (orderItem.getQuantity() > Integer.parseInt(p.getInventory_number())) {
                    request.getSession().setAttribute("invalidInventory", "Sản phẩm bạn muốn đặt hàng tạm thời hết hàng!");
                    response.sendRedirect("CheckoutController");
                    return;
                }
            }
            for (Cart orderItem : orderItems) {
                orderDetailDAO.addNewOrderDetail(order.getId(), Integer.parseInt(orderItem.getProductDetail().getId()), orderItem.getQuantity(), Integer.parseInt(orderItem.getProductDetail().getProduct().getPrice()));
                cartDAO.removeItem(String.valueOf(orderItem.getId()));
                pdDAO.reduceQuantity(orderItem.getProductDetail().getId(), String.valueOf(orderItem.getQuantity()));
            }
            Order newOrder = orderDAO.searchOrderByCode(orderCode);
            ArrayList<OrderDetail> orderDetails = newOrder.getOrderDetail();
            int totalAmount = 0;
            for (OrderDetail orderDetail : orderDetails) {
                totalAmount += orderDetail.getUnitPrice() * orderDetail.getQuantity();
            }          
            session.removeAttribute("total");
            int total = cartDAO.getCartQuantity(user.getId());
            session.setAttribute("total", total);
            request.setAttribute("totalAmount", totalAmount);
            request.setAttribute("orderDetails", orderDetails);
            request.setAttribute("newOrder", newOrder);
            request.getRequestDispatcher("ThankPage.jsp").forward(request, response);
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
