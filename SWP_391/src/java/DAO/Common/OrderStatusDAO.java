/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Common;

import DAO.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Common.OrderStatus;

/**
 *
 * @author ifyou
 */
public class OrderStatusDAO extends DBContext{
    public ArrayList<OrderStatus> getOrderStatusForSale(){
        ArrayList<OrderStatus> orderStatuses = new ArrayList<>();
        try {
            // Select address from user with user id
            String sql = "SELECT * from order_status";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String status = rs.getString("status");
                OrderStatus orderStatus = new OrderStatus(id, status);
                orderStatuses.add(orderStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderStatuses;
    }
    
    public ArrayList<OrderStatus> getOrderStatus1(){
        ArrayList<OrderStatus> orderStatuses = new ArrayList<>();
        try {
            // Select address from user with user id
            String sql = "SELECT * from order_status WHERE id IN (1,2,6)";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String status = rs.getString("status");
                OrderStatus orderStatus = new OrderStatus(id, status);
                orderStatuses.add(orderStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderStatuses;
    }

    public ArrayList<OrderStatus> getOrderStatus2(){
        ArrayList<OrderStatus> orderStatuses = new ArrayList<>();
        try {
            // Select address from user with user id
            String sql = "SELECT * from order_status WHERE id IN (2,3,6)";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String status = rs.getString("status");
                OrderStatus orderStatus = new OrderStatus(id, status);
                orderStatuses.add(orderStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderStatuses;
    }
    
    public ArrayList<OrderStatus> getOrderStatus3(){
        ArrayList<OrderStatus> orderStatuses = new ArrayList<>();
        try {
            // Select address from user with user id
            String sql = "SELECT * from order_status WHERE id IN (3,4,5,6)";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String status = rs.getString("status");
                OrderStatus orderStatus = new OrderStatus(id, status);
                orderStatuses.add(orderStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderStatuses;
    }
    
        public ArrayList<OrderStatus> getOrderStatus4(){
        ArrayList<OrderStatus> orderStatuses = new ArrayList<>();
        try {
            // Select address from user with user id
            String sql = "SELECT * from order_status WHERE id IN (4)";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String status = rs.getString("status");
                OrderStatus orderStatus = new OrderStatus(id, status);
                orderStatuses.add(orderStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderStatuses;
    }
    
    public ArrayList<OrderStatus> getOrderStatus5(){
        ArrayList<OrderStatus> orderStatuses = new ArrayList<>();
        try {
            // Select address from user with user id
            String sql = "SELECT * from order_status WHERE id IN (5)";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String status = rs.getString("status");
                OrderStatus orderStatus = new OrderStatus(id, status);
                orderStatuses.add(orderStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderStatuses;
    }
    
    public ArrayList<OrderStatus> getOrderStatus6(){
        ArrayList<OrderStatus> orderStatuses = new ArrayList<>();
        try {
            // Select address from user with user id
            String sql = "SELECT * from order_status WHERE id IN (6)";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String status = rs.getString("status");
                OrderStatus orderStatus = new OrderStatus(id, status);
                orderStatuses.add(orderStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderStatuses;
    }
    
    public ArrayList<OrderStatus> getOrderStatusForShipper(){
        ArrayList<OrderStatus> orderStatuses = new ArrayList<>();
        try {
            // Select address from user with user id
            String sql = "SELECT * from order_status WHERE [id] IN (3,4,5)";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String status = rs.getString("status");
                OrderStatus orderStatus = new OrderStatus(id, status);
                orderStatuses.add(orderStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderStatuses;
    }
    
    public OrderStatus searchById(int statusId){
        try {
            // Select address from user with user id
            String sql = "SELECT * from order_status WHERE [id] = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, statusId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String status = rs.getString("status");
                OrderStatus orderStatus = new OrderStatus(id, status);
                return orderStatus;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        OrderStatusDAO orderStatusDAO = new OrderStatusDAO();
//        ArrayList<OrderStatus> orderStatus = orderStatusDAO.getOrderStatusForShipper();
//        for (OrderStatus orderStatu : orderStatus) {
//            System.out.println(orderStatu.toString());
//        }
        OrderStatus orderStatus = orderStatusDAO.searchById(1);
        System.out.println(orderStatus.toString());
    }
    
}
