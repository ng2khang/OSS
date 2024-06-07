/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Common;

import DAO.Common.OrderDAO;
import DAO.Common.OrderDetailDAO;
import DAO.Common.OrderStatusDAO;
import DAO.Common.ShippingCompanyDAO;
import DAO.Common.UserDAO;
import DAO.ProductDAO.ProductDetailDAO;
import Service.SendMailService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Common.Order;
import model.Common.OrderDetail;
import model.Common.OrderStatus;
import model.Common.ShippingCompany;
import model.Common.User;

/**
 *
 * @author ifyou
 */
public class EditOrderController extends HttpServlet {

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
            out.println("<title>Servlet EditOrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditOrderController at " + request.getContextPath() + "</h1>");
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
        String orderId = request.getParameter("id");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // Check if user have loged in
        if (user == null) {
            request.setAttribute("loginMessage", "Vui lòng đăng nhập để sử dụng dịch vụ!");
            request.getRequestDispatcher("view\\customer\\login.jsp").forward(request, response);
            return;
        } else if (user.getRole() == 4) {
            response.sendRedirect("404.jsp");
        } else {
            OrderDAO orderDAO = new OrderDAO();
            Order order = orderDAO.searchOrderById(orderId);
            request.setAttribute("order", order);
            ShippingCompanyDAO spDAO = new ShippingCompanyDAO();
            ArrayList<ShippingCompany> shippingCompanies = spDAO.getAllShippingCompany();
            OrderStatusDAO orderStatusDAO = new OrderStatusDAO();
            OrderStatus orderStatus = orderStatusDAO.searchById(order.getOrderStatus());
            if (orderStatus.getId() == 1) {
                ArrayList<OrderStatus> orderStatusForSale = orderStatusDAO.getOrderStatus1();
                request.setAttribute("orderStatusForSale", orderStatusForSale);
            } else if (orderStatus.getId() == 2) {
                ArrayList<OrderStatus> orderStatusForSale = orderStatusDAO.getOrderStatus2();
                request.setAttribute("orderStatusForSale", orderStatusForSale);
            } else if (orderStatus.getId() == 3) {
                ArrayList<OrderStatus> orderStatusForSale = orderStatusDAO.getOrderStatus3();
                request.setAttribute("orderStatusForSale", orderStatusForSale);
            } else if (orderStatus.getId() == 4) {
                ArrayList<OrderStatus> orderStatusForSale = orderStatusDAO.getOrderStatus4();
                request.setAttribute("orderStatusForSale", orderStatusForSale);
            } else if (orderStatus.getId() == 5) {
                ArrayList<OrderStatus> orderStatusForSale = orderStatusDAO.getOrderStatus5();
                request.setAttribute("orderStatusForSale", orderStatusForSale);
            } else {
                ArrayList<OrderStatus> orderStatusForSale = orderStatusDAO.getOrderStatus6();
                request.setAttribute("orderStatusForSale", orderStatusForSale);
            }
            ArrayList<OrderStatus> orderStatusForShipper = orderStatusDAO.getOrderStatusForShipper();
            request.setAttribute("shippingCompanies", shippingCompanies);
            request.setAttribute("orderStatusForShipper", orderStatusForShipper);
            request.setAttribute("orderStatus", orderStatus);
            request.getRequestDispatcher("view\\sale\\UpdateOrder.jsp").forward(request, response);
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
        String orderId = request.getParameter("id");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // Check if user have loged in
        if (user == null) {
            request.setAttribute("loginMessage", "Vui lòng đăng nhập để sử dụng dịch vụ!");
            request.getRequestDispatcher("view\\customer\\login.jsp").forward(request, response);
            return;
        } else if (user.getRole() == 4) {
            response.sendRedirect("404.jsp");
        } else {
            String id = request.getParameter("id");
            String shippingCompanyId = request.getParameter("shippingCompany");
            String shipping_code = request.getParameter("shippingCode");
            String status = request.getParameter("status");
            String note = request.getParameter("note");
            String expectedDeliveryDate = request.getParameter("expectedDeliveryDate");
            expectedDeliveryDate += " 00:00:00";

            OrderDAO orderDAO = new OrderDAO();
            ProductDetailDAO pdDAO = new ProductDetailDAO();
            if (status.equals("1")) {
                orderDAO.editOrder(Integer.parseInt(id), Integer.parseInt(shippingCompanyId), Integer.parseInt(status), note, Timestamp.valueOf(expectedDeliveryDate), shipping_code);
                SendMailService sms = new SendMailService();
                Order o = orderDAO.searchOrderById(id);
                UserDAO udao = new UserDAO();
                User customer = udao.getUserByID(String.valueOf(o.getCustomer().getId()));
                sms.sendOrderNoti1(customer.getEmail(), o);
            } else if (status.equals("2")) {
                orderDAO.editOrder(Integer.parseInt(id), Integer.parseInt(shippingCompanyId), Integer.parseInt(status), note, Timestamp.valueOf(expectedDeliveryDate), shipping_code);
                SendMailService sms = new SendMailService();
                Order o = orderDAO.searchOrderById(id);
                UserDAO udao = new UserDAO();
                User customer = udao.getUserByID(String.valueOf(o.getCustomer().getId()));
                sms.sendOrderNoti2(customer.getEmail(), o);
            } else if (status.equals("3")) {
                orderDAO.editOrder(Integer.parseInt(id), Integer.parseInt(shippingCompanyId), Integer.parseInt(status), note, Timestamp.valueOf(expectedDeliveryDate), shipping_code);
                SendMailService sms = new SendMailService();
                Order o = orderDAO.searchOrderById(id);
                UserDAO udao = new UserDAO();
                User customer = udao.getUserByID(String.valueOf(o.getCustomer().getId()));
                sms.sendOrderNoti3(customer.getEmail(), o);
            } else if (status.equals("4")) {
                orderDAO.editOrder(Integer.parseInt(id), Integer.parseInt(shippingCompanyId), Integer.parseInt(status), note, Timestamp.valueOf(expectedDeliveryDate), shipping_code);
                SendMailService sms = new SendMailService();
                Order o = orderDAO.searchOrderById(id);
                UserDAO udao = new UserDAO();
                User customer = udao.getUserByID(String.valueOf(o.getCustomer().getId()));
                sms.sendOrderNoti4(customer.getEmail(), o);
            } else if (status.equals("5")) {
                Order o = orderDAO.searchOrderById(id);
                OrderDetailDAO odDAO = new OrderDetailDAO();
                ArrayList<OrderDetail> orderDetails = odDAO.selectByOrderId(o.getId());
                for (OrderDetail orderDetail : orderDetails) {
                    pdDAO.addQuantity(orderDetail.getProductDetail().getId(), String.valueOf(orderDetail.getQuantity()));
                }
                orderDAO.editOrder(Integer.parseInt(id), Integer.parseInt(shippingCompanyId), Integer.parseInt(status), note, Timestamp.valueOf(expectedDeliveryDate), shipping_code);
                SendMailService sms = new SendMailService();
                UserDAO udao = new UserDAO();
                User customer = udao.getUserByID(String.valueOf(o.getCustomer().getId()));
                sms.sendOrderNoti4(customer.getEmail(), o);
            }else{
                Order o = orderDAO.searchOrderById(id);
                OrderDetailDAO odDAO = new OrderDetailDAO();
                ArrayList<OrderDetail> orderDetails = odDAO.selectByOrderId(o.getId());
                for (OrderDetail orderDetail : orderDetails) {
                    pdDAO.addQuantity(orderDetail.getProductDetail().getId(), String.valueOf(orderDetail.getQuantity()));
                }
                orderDAO.editOrder(Integer.parseInt(id), Integer.parseInt(shippingCompanyId), Integer.parseInt(status), note, Timestamp.valueOf(expectedDeliveryDate), shipping_code);
            }
//            if (status.equals("5") || status.equals("6")) {
//                Order o = orderDAO.searchOrderById(id);
//                OrderDetailDAO odDAO = new OrderDetailDAO();
//                ArrayList<OrderDetail> orderDetails = odDAO.selectByOrderId(o.getId());
//                for (OrderDetail orderDetail : orderDetails) {
//                    pdDAO.addQuantity(orderDetail.getProductDetail().getId(), String.valueOf(orderDetail.getQuantity()));
//                }
//                orderDAO.editOrder(Integer.parseInt(id), Integer.parseInt(shippingCompanyId), Integer.parseInt(status), note, Timestamp.valueOf(expectedDeliveryDate), shipping_code);
//            } else {
//                orderDAO.editOrder(Integer.parseInt(id), Integer.parseInt(shippingCompanyId), Integer.parseInt(status), note, Timestamp.valueOf(expectedDeliveryDate), shipping_code);
//            }
            response.sendRedirect("OrderController");
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
