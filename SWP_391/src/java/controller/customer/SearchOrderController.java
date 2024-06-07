/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import DAO.Common.OrderDAO;
import DAO.Common.OrderDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Common.Order;
import model.Common.OrderDetail;

/**
 *
 * @author Admin
 */
public class SearchOrderController extends HttpServlet {

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
        //    processRequest(request, response);
        String orderCode = request.getParameter("orderCode");
        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.searchOrderByCode(orderCode);

        if (order != null && order.getOrderCode().equals(orderCode)) {
            ArrayList<OrderDetail> NewOrderDetails = order.getOrderDetail();
            int total = 0;
            for (OrderDetail orderDetail : NewOrderDetails) {
                total += orderDetail.getUnitPrice() * orderDetail.getQuantity();
            }
            request.setAttribute("total", total);
            request.setAttribute("order", order);
            request.setAttribute("NewOrderDetails", NewOrderDetails);
            request.getRequestDispatcher("/displayOrder.jsp").forward(request, response);
        } else {
            String errorMessage = "Mã đặt hàng không hợp lệ, vui lòng nhập lại mã";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/SearchOrder.jsp").forward(request, response);
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
