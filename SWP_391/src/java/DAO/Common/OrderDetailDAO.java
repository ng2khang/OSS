/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Common;

import DAO.DBContext;
import DAO.ProductDAO.ProductDetailDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Common.Order;
import model.Common.OrderDetail;
import model.Common.User;
import model.Common.UserAddress;
import model.Product.ProductDetails;

/**
 *
 * @author ifyou
 */
public class OrderDetailDAO extends DBContext{
    public ArrayList<OrderDetail> selectByOrderId(int orderId){
        ArrayList<OrderDetail> orderDetails = new ArrayList();
        try {
            // Select address from user with user id
            String sql = "SELECT * from order_details WHERE order_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int orderID = rs.getInt("order_id");
                int productDetailId = rs.getInt("product_detail_id");
                int quantity = rs.getInt("quantity");
                int unitPrice = rs.getInt("unit_price");
                ProductDetailDAO productDetailDAO = new ProductDetailDAO();
                ProductDetails productDetail = productDetailDAO.selectProductDetailById(String.valueOf(productDetailId));
                OrderDetail orderDetail = new OrderDetail(id, orderID, productDetail, quantity, unitPrice);
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }
    
    public void addNewOrderDetail(int orderid, int productDetailId, int quantity, int unitPrice){
        try {
            // SQL INSERT query
            String sql = "INSERT INTO [order_details] (order_id, product_detail_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderid);
            st.setInt(2, productDetailId);
            st.setInt(3, quantity);
            st.setInt(4, unitPrice);
            // Execute INSERT
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        ArrayList<OrderDetail> orderDetails = orderDetailDAO.selectByOrderId(3);
        for (OrderDetail orderDetail : orderDetails) {
            System.out.println(orderDetail.getQuantity());
        }
    }
}
