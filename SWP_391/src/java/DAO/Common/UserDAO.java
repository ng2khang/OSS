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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Common.User;
import model.Common.UserAddress;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ifyou
 */
public class UserDAO extends DBContext {

    public void addNewUser(User user) {
        try {
            // SQL INSERT query
            String sql = "INSERT INTO [users] (name, email, password, phone, is_deleted, role, status, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());
            st.setString(4, user.getPhone());
            st.setInt(5, user.getIs_deleted());
            st.setInt(6, user.getRole());
            st.setInt(7, user.getStatus());
            st.setTimestamp(8, new java.sql.Timestamp(new java.util.Date().getTime()));

            // Execute INSERT
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userID) {
        try {
            String sql = "UPDATE users SET [is_deleted] = 1 WHERE [id] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editUser(int userID, String name, String phone) {
        try {
            String sql = "UPDATE users SET [name] = ?, [phone] = ? WHERE [id] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, phone);
            statement.setInt(3, userID);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(User user) {
        String sql = "update users SET [name]=?,"
                + " [email] =?,"
                + "[phone] = ?,"
                + "[role]= ?,"
                + "[status]=? where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhone());
            statement.setInt(4, user.getRole());
            statement.setInt(5, user.getStatus());
            statement.setInt(6, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
        }

    }

    public User getUserByID(String userId) {

        try {
            // Select user with email
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String user_email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                int is_deleted = rs.getInt("is_deleted");
                int role = rs.getInt("role");
                int status = rs.getInt("status");
                User u = new User(id, name, user_email, password, phone, is_deleted, role, status);
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User searchUserByEmailAndPassword(String email) {

        try {
            // Select user with email
            String sql = "SELECT * FROM [users] WHERE [email] = ? AND is_deleted != 1";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String user_email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                int is_deleted = rs.getInt("is_deleted");
                int role = rs.getInt("role");
                int status = rs.getInt("status");
                UserAddressDAO uadao = new UserAddressDAO();
                ArrayList userAddresses = uadao.sellectallUserAddress(id);
                String image = rs.getString("image_url");
                User u = new User(id, name, email, password, phone, is_deleted, role, status, userAddresses, image);
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User searchUserByEmail(String email) {

        try {
            // Select user with email
            String sql = "SELECT * FROM [users] WHERE [email] = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String user_email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                int is_deleted = rs.getInt("is_deleted");
                int role = rs.getInt("role");
                int status = rs.getInt("status");
                UserAddressDAO uadao = new UserAddressDAO();
                ArrayList userAddresses = uadao.sellectallUserAddress(id);
                String image = rs.getString("image_url");
                User u = new User(id, name, email, password, phone, is_deleted, role, status, userAddresses, image);
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User searchUserByEmailAndPassword(String userEmail, String userPassword) {

        try {
            // Select user with email
            String sql = "SELECT * FROM users WHERE email = ? AND password = ? AND is_deleted = 0";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userEmail);
            st.setString(2, userPassword);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String user_email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                int is_deleted = rs.getInt("is_deleted");
                int role = rs.getInt("role");
                int status = rs.getInt("status");
                User u = new User(id, name, user_email, password, phone, is_deleted, role, status);
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void activeUserAccount(String email) {
        try {
            String sql = "UPDATE users SET [status] = 1 WHERE [email] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void changePassword(String email, String newPassword) {
        try {
            String sql = "UPDATE users SET [password] = ? WHERE [email] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<User> sellectallUser() {
        ArrayList<User> users = new ArrayList<>();
        try {
            // Select address from user with user id
            String sql = "SELECT * from [users] WHERE is_deleted = 0";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("id");
                String user_name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                int is_deleted = rs.getInt("is_deleted");
                int role = rs.getInt("role");
                int status = rs.getInt("status");
                Timestamp created_at = rs.getTimestamp("created_at");
                UserAddressDAO uadao = new UserAddressDAO();
                ArrayList user_addresses = uadao.sellectallUserAddress(user_id);
                String image = rs.getString("image_url");
                User u = new User(user_id, user_name, email, password, phone, is_deleted, role, status, user_addresses, image);
                users.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public ArrayList<User> sellectallShipper() {
        ArrayList<User> users = new ArrayList<>();
        try {
            // Select address from user with user id
            String sql = "SELECT * from [users] WHERE is_deleted = 0 AND role = 3";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("id");
                String user_name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                int is_deleted = rs.getInt("is_deleted");
                int role = rs.getInt("role");
                int status = rs.getInt("status");
                Timestamp created_at = rs.getTimestamp("created_at");
                UserAddressDAO uadao = new UserAddressDAO();
                ArrayList user_addresses = uadao.sellectallUserAddress(user_id);
                String image = rs.getString("image_url");
                User u = new User(user_id, user_name, email, password, phone, is_deleted, role, status, user_addresses, image);
                users.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public ArrayList<User> sellectallStaffByPaging(int index) {
        ArrayList<User> users = new ArrayList<>();
        try {
            // Select address from user with user id
            String sql = "select * from users  WHERE is_deleted = 0 AND [role] = 2\n"
                    + "order by id  desc\n"
                    + "offset ? rows fetch  next 10 rows only";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * 10);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("id");
                String user_name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                int is_deleted = rs.getInt("is_deleted");
                int role = rs.getInt("role");
                int status = rs.getInt("status");
                Timestamp created_at = rs.getTimestamp("created_at");
                UserAddressDAO uadao = new UserAddressDAO();
                UserContractDAO ucdao = new UserContractDAO();
                ArrayList user_contract = ucdao.sellectAllUserContract(user_id);
                ArrayList user_addresses = uadao.sellectallUserAddress(user_id);
                User u = new User(user_id, user_name, email, password, phone, is_deleted, role, status, created_at, user_addresses, user_contract);
                users.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // ham search user by name
    public ArrayList<User> SearchUserByName(String txtSearch, int index) {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE is_deleted = 0 AND [role] = 2 AND[name] LIKE ? order by id  desc\n"
                + "offset ? rows fetch  next 10 rows only";
        try {
            // Select address from user with user id

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            st.setInt(2, (index - 1) * 10);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("id");
                String user_name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                int is_deleted = rs.getInt("is_deleted");
                int role = rs.getInt("role");
                int status = rs.getInt("status");
                Timestamp created_at = rs.getTimestamp("created_at");
                UserAddressDAO uadao = new UserAddressDAO();
                UserContractDAO ucdao = new UserContractDAO();
                ArrayList user_contract = ucdao.sellectAllUserContract(user_id);
                ArrayList user_addresses = uadao.sellectallUserAddress(user_id);
                User u = new User(user_id, user_name, email, password, phone, is_deleted, role, status, created_at, user_addresses, user_contract);
                users.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // ham dem so luong user trong database
    public int getTotalUsers() {
        String sql = "select count(*) from users WHERE is_deleted = 0 AND [role] = 2";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    //
    public int getTotalUsersByName(String txtSearch) {
        String sql = "select count(*) from users WHERE is_deleted = 0 AND [role] = 2 AND [Name] like ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public static void main(String[] args) {
        UserDAO udao = new UserDAO();
//        ArrayList<User> shippers = udao.sellectallShipper();
//        for (User shipper : shippers) {
//            System.out.println(shipper.toString());
//        }
        String encodedPassword = BCrypt.hashpw("LB@123456", BCrypt.gensalt(10));
        udao.changePassword("khangnhhe160625@fpt.edu.vn", encodedPassword);
//        ArrayList<User> u = udao.sellectallStaffByPaging(1);
//        for (User o : u) {
//            System.out.println(o);
//        }
//        int count = udao.getTotalUsers();
//        System.out.println(count);
//        System.out.println(udao.getUserByID("83"));
//
//        ArrayList<User> user = udao.SearchUserByName("h", 1);
//        for (User o : user) {
//            System.out.println(o);
//        }
//        
//        int count2 =udao.getTotalUsersByName("j");
//        System.out.println(count2);
    }
}
