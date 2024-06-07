/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Common;

import DAO.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Common.Order;
import model.Common.OrderDetail;
import model.Common.ShippingCompany;
import model.Common.User;
import model.Common.UserAddress;

/**
 *
 * @author ifyou
 */
public class OrderDAO extends DBContext {

    public ArrayList<Order> selectAllOrder() {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            // Select address from user with user id
            String sql = "SELECT * from orders ORDER BY oder_date DESC";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String orderCode = rs.getString("order_code");
                int customerId = rs.getInt("customer_id");
                Timestamp orderDate = rs.getTimestamp("oder_date");
                int orderStatus = rs.getInt("order_status");
                int deliveryAddressId = rs.getInt("delivery_address_id");
                int shippingCompanyId = rs.getInt("shipping_company_id");
                ShippingCompanyDAO spDAO = new ShippingCompanyDAO();
                ShippingCompany shippingCompany = spDAO.getById(shippingCompanyId);
                String shippingCode = rs.getString("shipping_code");
                Timestamp deliveryDate = rs.getTimestamp("delivery_date");
                int paymentMethod = rs.getInt("payment_method");
                String note = rs.getString("note");
                UserDAO udao = new UserDAO();
                User customer = udao.getUserByID(String.valueOf(customerId));
                UserAddressDAO usDAO = new UserAddressDAO();
                UserAddress shippingAddress = usDAO.searchById(String.valueOf(deliveryAddressId));
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                ArrayList<OrderDetail> orderDetails = orderDetailDAO.selectByOrderId(id);
                int total = 0;
                for (OrderDetail orderDetail : orderDetails) {
                    total += orderDetail.getQuantity() * orderDetail.getUnitPrice();
                }
                Order order = new Order(id, orderCode, customer, orderDate, orderStatus, shippingAddress, deliveryDate, paymentMethod, orderDetails, note, shippingCompany, shippingCode, total);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;

    }

    public void editOrder(int orderId, int shippingCompanyId, int status, String note, Timestamp deliveryDate, String shippingCode) {
        try {
            String sql = "UPDATE [orders] SET [shipping_company_id] = ?, [order_status] = ?, [delivery_date] = ?, [note] = ?, [shipping_code] = ? WHERE [id] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, shippingCompanyId);
            statement.setInt(2, status);
            statement.setTimestamp(3, deliveryDate);
            statement.setString(4, note);
            statement.setString(5, shippingCode);
            statement.setInt(6, orderId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public Order searchOrderByCode(String code) {
        try {
            // Select address from user with user id
            String sql = "SELECT * from orders WHERE order_code = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String orderCode = rs.getString("order_code");
                int customerId = rs.getInt("customer_id");
                Timestamp orderDate = rs.getTimestamp("oder_date");
                int orderStatus = rs.getInt("order_status");
                int deliveryAddressId = rs.getInt("delivery_address_id");
                int shippingCompanyId = rs.getInt("shipping_company_id");
                ShippingCompanyDAO spDAO = new ShippingCompanyDAO();
                ShippingCompany shippingCompany = spDAO.getById(shippingCompanyId);
                String shippingCode = rs.getString("shipping_code");
                Timestamp deliveryDate = rs.getTimestamp("delivery_date");
                int paymentMethod = rs.getInt("payment_method");
                String note = rs.getString("note");
                UserDAO udao = new UserDAO();
                User customer = udao.getUserByID(String.valueOf(customerId));
                UserAddressDAO usDAO = new UserAddressDAO();
                UserAddress shippingAddress = usDAO.searchById(String.valueOf(deliveryAddressId));
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                ArrayList<OrderDetail> orderDetails = orderDetailDAO.selectByOrderId(id);
                int total = 0;
                for (OrderDetail orderDetail : orderDetails) {
                    total += orderDetail.getQuantity() * orderDetail.getUnitPrice();
                }
                Order order = new Order(id, orderCode, customer, orderDate, orderStatus, shippingAddress, deliveryDate, paymentMethod, orderDetails, note, shippingCompany, shippingCode, total);
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Order searchOrderById(String id) {
        try {
            // Select address from user with user id
            String sql = "SELECT * from orders WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int oId = rs.getInt("id");
                String orderCode = rs.getString("order_code");
                int customerId = rs.getInt("customer_id");
                Timestamp orderDate = rs.getTimestamp("oder_date");
                int orderStatus = rs.getInt("order_status");
                int deliveryAddressId = rs.getInt("delivery_address_id");
                int shippingCompanyId = rs.getInt("shipping_company_id");
                ShippingCompanyDAO spDAO = new ShippingCompanyDAO();
                ShippingCompany shippingCompany = spDAO.getById(shippingCompanyId);
                String shippingCode = rs.getString("shipping_code");
                Timestamp deliveryDate = rs.getTimestamp("delivery_date");
                int paymentMethod = rs.getInt("payment_method");
                String note = rs.getString("note");
                UserDAO udao = new UserDAO();
                User customer = udao.getUserByID(String.valueOf(customerId));
                UserAddressDAO usDAO = new UserAddressDAO();
                UserAddress shippingAddress = usDAO.searchById(String.valueOf(deliveryAddressId));
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                ArrayList<OrderDetail> orderDetails = orderDetailDAO.selectByOrderId(oId);
                int total = 0;
                for (OrderDetail orderDetail : orderDetails) {
                    total += orderDetail.getQuantity() * orderDetail.getUnitPrice();
                }
                Order order = new Order(oId, orderCode, customer, orderDate, orderStatus, shippingAddress, deliveryDate, paymentMethod, orderDetails, note, shippingCompany, shippingCode, total);
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public int test(String id) {
        try {
            // Select address from user with user id
            String sql = "SELECT * from orders WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int oId = rs.getInt("id");
                String orderCode = rs.getString("order_code");
                int customerId = rs.getInt("customer_id");
                Timestamp orderDate = rs.getTimestamp("oder_date");
                int orderStatus = rs.getInt("order_status");
                int deliveryAddressId = rs.getInt("delivery_address_id");
                int shippingCompanyId = rs.getInt("shipping_company_id");
                String shippingCode = rs.getString("shipping_code");
                Timestamp deliveryDate = rs.getTimestamp("delivery_date");
                int paymentMethod = rs.getInt("payment_method");
                String note = rs.getString("note");
                return customerId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addNewOrder(String orderCode, int customerId, int deliveryAddressId, int paymentMethod) {
        try {
            // SQL INSERT query
            String sql = "INSERT INTO [orders] (order_code, customer_id, oder_date, order_status, delivery_address_id, payment_method) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderCode);
            st.setInt(2, customerId);
            st.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            st.setInt(4, 1);
            st.setInt(5, deliveryAddressId);
            st.setInt(6, paymentMethod);
            // Execute INSERT
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
//        System.out.println(orderDAO.test("10"));
//        String date = "2023-03-15";
//        date += " 00:00:00";
//
//        orderDAO.editOrder(20, 1, 2, "test", Timestamp.valueOf(date), "abcdef");
////        System.out.println("fuc 1");
        ArrayList<Order> orders = orderDAO.selectAllOrder();
        for (Order order : orders) {
            System.out.println(order.toString());
        }
//        Order o = orderDAO.searchOrderById("1");
//        System.out.println(o);
////         System.out.println("fuc 2");
//        Order order = orderDAO.searchOrderByCode("1234");
//        System.out.println(order.toString());
    }
}
