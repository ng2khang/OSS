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
import java.util.ArrayList;
import model.Common.Cart;
import model.Common.User;
import model.Product.ProductDetails;

/**
 *
 * @author ifyou
 */
public class CartDAO extends DBContext {

    public ArrayList<Cart> selectAllCartItem(int userId) {
        ArrayList<Cart> cartItems = new ArrayList<>();
        try {
            // Select address from user with user id
            String sql = "SELECT * from cart_items WHERE customer_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("customer_id");
                int productDetailId = rs.getInt("product_detail_id");
                int quantity = rs.getInt("quantity");
                int isSelected = rs.getInt("is_selected");
                UserDAO udao = new UserDAO();
                User customer = udao.getUserByID(String.valueOf(user_id));
                ProductDetailDAO productDetailDAO = new ProductDetailDAO();
                ProductDetails productDetail = productDetailDAO.selectProductDetailById(String.valueOf(productDetailId));
                Cart cartItem = new Cart(id, customer, productDetail, quantity, isSelected);
                cartItems.add(cartItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }
    
    public int getCartQuantity(int userId){
        try {
            // Select address from user with user id
            String sql = "SELECT COUNT(*) AS quantity from cart_items WHERE customer_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int total = rs.getInt("quantity");
                return total;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int isExistItem(int userId, int productDetailId){
        try {
            // Select address from user with user id
            String sql = "SELECT COUNT(*) AS quantity from cart_items WHERE customer_id = ? AND product_detail_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, productDetailId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int total = rs.getInt("quantity");
                return total;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<Cart> selectCheckoutItem(int userId) {
        ArrayList<Cart> cartItems = new ArrayList<>();
        try {
            // Select address from user with user id
            String sql = "SELECT * from cart_items WHERE customer_id = ? AND is_selected = 1";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("customer_id");
                int productDetailId = rs.getInt("product_detail_id");
                int quantity = rs.getInt("quantity");
                int isSelected = rs.getInt("is_selected");
                UserDAO udao = new UserDAO();
                User customer = udao.getUserByID(String.valueOf(user_id));
                ProductDetailDAO productDetailDAO = new ProductDetailDAO();
                ProductDetails productDetail = productDetailDAO.selectProductDetailById(String.valueOf(productDetailId));
                Cart cartItem = new Cart(id, customer, productDetail, quantity, isSelected);
                cartItems.add(cartItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    public void addQuantity(String id) {
        try {
            String sql = "UPDATE [cart_items] SET quantity = quantity + 1 WHERE [id] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void reduceQuantity(String id) {
        try {
            String sql = "UPDATE [cart_items] SET quantity = quantity - 1 WHERE [id] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeItem(String id) {
        try {
            String sql = "DELETE FROM [cart_items] WHERE [id] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void selectedItem(String id) {
        try {
            String sql = "UPDATE [cart_items] SET is_selected = 1 WHERE [id] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeSelected(String id) {
        try {
            String sql = "UPDATE [cart_items] SET is_selected = 0 WHERE [id] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int getTotalNumber(int userId) {
        int totalNumber;
        try {
            String sql = "select COUNT(*) as total_number from cart_items where customer_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                totalNumber = rs.getInt("total_number");
                return totalNumber;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    public Cart getCartItem(int userId, int productDetailId){
        try {
            // Select address from user with user id
            String sql = "SELECT * from cart_items WHERE customer_id = ? AND product_detail_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, productDetailId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("customer_id");
                int pdId = rs.getInt("product_detail_id");
                int quantity = rs.getInt("quantity");
                int isSelected = rs.getInt("is_selected");
                UserDAO udao = new UserDAO();
                User customer = udao.getUserByID(String.valueOf(user_id));
                ProductDetailDAO productDetailDAO = new ProductDetailDAO();
                ProductDetails productDetail = productDetailDAO.selectProductDetailById(String.valueOf(pdId));
                Cart cartItem = new Cart(id, customer, productDetail, quantity, isSelected);
                return cartItem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void addTocart(int userId, int productDetailId, int quantity) {
        try {
            // SQL INSERT query
            String sql = "INSERT INTO [cart_items] (customer_id, product_detail_id, quantity, is_selected) VALUES (?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, productDetailId);
            st.setInt(3, quantity);
            st.setInt(4, 1);
            // Execute INSERT
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCheckoutNumber(int userId) {
        int totalNumber;
        try {
            String sql = "select COUNT(*) as total_number from cart_items where customer_id = ? and is_selected = 1";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                totalNumber = rs.getInt("total_number");
                return totalNumber;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        ArrayList<Cart> cartItems = new ArrayList<>();
        CartDAO cDAO = new CartDAO();
        System.out.println(cDAO.isExistItem(16, 6));
//        cartItems = cDAO.selectAllCartItem(16);
//        for (Cart cartItem : cartItems) {
//            System.out.println(cartItem.getIsSelected());
//        }
//        int num = cDAO.getTotalNumber(16);
//        System.out.println(num);
    }
}
