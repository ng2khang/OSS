/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Common;

import DAO.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Common.UserAddress;
import java.util.ArrayList;

/**
 *
 * @author ifyou
 */
public class UserAddressDAO extends DBContext {

    public void addNewUserAddress(UserAddress userAddress) {
        try {
            // SQL INSERT query
            String sql = "INSERT INTO user_addresses (user_id, province_code, district_code, ward_code, address, created_at, is_default) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userAddress.getUser_id());
            st.setString(2, userAddress.getProvince_code());
            st.setString(3, userAddress.getDistrict_code());
            st.setString(4, userAddress.getWard_code());
            st.setString(5, userAddress.getAddress());
            st.setTimestamp(6, new java.sql.Timestamp(new java.util.Date().getTime()));
            st.setInt(7, userAddress.getIs_default());
            // Thực hiện truy vấn INSERT
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteUserAddress(String id){
        try {
            String sql = "UPDATE [user_addresses] SET deleted_at = CURRENT_TIMESTAMP WHERE [id] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setDefaultAddressToZero(String userId){
        try {
            String sql = "UPDATE [user_addresses] SET is_default = 0 WHERE [user_id] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList<UserAddress> sellectallUserAddress(int userID) {
        ArrayList<UserAddress> userAddresses = new ArrayList<>();
        try {
            // Select address from user with user id
            String sql = "SELECT ua.[id] AS [id], ua.[user_id] AS [user_id], ua.[province_code] AS [province_code], p.[name] as [province_name], ua.[district_code] AS [district_code], d.name as [district_name], ua.[ward_code] as [ward_code], w.[name] as [ward_name], ua.[address], ua.[is_default]"
                    + " FROM [users] AS u JOIN [user_addresses] AS ua ON u.[id] = ua.[user_id]"
                    + "JOIN [provinces] AS p ON ua.[province_code] = p.[code] JOIN [districts] AS d ON ua.[district_code] = d.[code] JOIN [wards] AS w ON ua.[ward_code] = w.[code]"
                    + "WHERE u.[id] = ? AND ua.[deleted_at] IS NULL";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String province_code = rs.getString("province_code");
                String province_name = rs.getString("province_name");
                String district_code = rs.getString("district_code");
                String district_name = rs.getString("district_name");
                String ward_code = rs.getString("ward_code");
                String ward_name = rs.getString("ward_name");
                String address = rs.getString("address");
                int is_default = rs.getInt("is_default");
                UserAddress ua = new UserAddress(id, user_id, province_name, province_code, district_name, district_code, ward_name, ward_code, address, is_default);
                userAddresses.add(ua);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAddresses;
    }
    
    public ArrayList<UserAddress> sellectallPersonalAddress(int userID) {
        ArrayList<UserAddress> userAddresses = new ArrayList<>();
        try {
            // Select address from user with user id
            String sql = "SELECT ua.[id] AS [id], ua.[user_id] AS [user_id], ua.[province_code] AS [province_code], p.[name] as [province_name], ua.[district_code] AS [district_code], d.name as [district_name], ua.[ward_code] as [ward_code], w.[name] as [ward_name], ua.[address], ua.[is_default]"
                    + " FROM [users] AS u JOIN [user_addresses] AS ua ON u.[id] = ua.[user_id]"
                    + "JOIN [provinces] AS p ON ua.[province_code] = p.[code] JOIN [districts] AS d ON ua.[district_code] = d.[code] JOIN [wards] AS w ON ua.[ward_code] = w.[code]"
                    + "WHERE u.[id] = ? AND ua.[deleted_at] IS NULL ORDER BY ua.[is_default] DESC";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String province_code = rs.getString("province_code");
                String province_name = rs.getString("province_name");
                String district_code = rs.getString("district_code");
                String district_name = rs.getString("district_name");
                String ward_code = rs.getString("ward_code");
                String ward_name = rs.getString("ward_name");
                String address = rs.getString("address");
                int is_default = rs.getInt("is_default");
                UserAddress ua = new UserAddress(id, user_id, province_name, province_code, district_name, district_code, ward_name, ward_code, address, is_default);
                userAddresses.add(ua);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAddresses;
    }
    
    public UserAddress searchById(String id){
        try {
            // Select address from user with user id
            String sql = "SELECT ua.[id] AS [id], ua.[user_id] AS [user_id], ua.[province_code] AS [province_code], p.[name] as [province_name], ua.[district_code] AS [district_code], d.name as [district_name], ua.[ward_code] as [ward_code], w.[name] as [ward_name], ua.[address], ua.[is_default]"
                    + " FROM [users] AS u JOIN [user_addresses] AS ua ON u.[id] = ua.[user_id]"
                    + "JOIN [provinces] AS p ON ua.[province_code] = p.[code] JOIN [districts] AS d ON ua.[district_code] = d.[code] JOIN [wards] AS w ON ua.[ward_code] = w.[code]"
                    + "WHERE ua.[id] = ? AND ua.[deleted_at] IS NULL";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int addressId = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String province_code = rs.getString("province_code");
                String province_name = rs.getString("province_name");
                String district_code = rs.getString("district_code");
                String district_name = rs.getString("district_name");
                String ward_code = rs.getString("ward_code");
                String ward_name = rs.getString("ward_name");
                String address = rs.getString("address");
                int is_default = rs.getInt("is_default");
                UserAddress ua = new UserAddress(addressId, user_id, province_name, province_code, district_name, district_code, ward_name, ward_code, address, is_default);
                return ua;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public UserAddress searchByUserId(String userId){
        try {
            // Select address from user with user id
            String sql = "SELECT ua.[id] AS [id], ua.[user_id] AS [user_id], ua.[province_code] AS [province_code], p.[name] as [province_name], ua.[district_code] AS [district_code], d.name as [district_name], ua.[ward_code] as [ward_code], w.[name] as [ward_name], ua.[address], ua.[is_default]"
                    + " FROM [users] AS u JOIN [user_addresses] AS ua ON u.[id] = ua.[user_id]"
                    + "JOIN [provinces] AS p ON ua.[province_code] = p.[code] JOIN [districts] AS d ON ua.[district_code] = d.[code] JOIN [wards] AS w ON ua.[ward_code] = w.[code]"
                    + "WHERE ua.[user_id] = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int addressId = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String province_code = rs.getString("province_code");
                String province_name = rs.getString("province_name");
                String district_code = rs.getString("district_code");
                String district_name = rs.getString("district_name");
                String ward_code = rs.getString("ward_code");
                String ward_name = rs.getString("ward_name");
                String address = rs.getString("address");
                int is_default = rs.getInt("is_default");
                UserAddress ua = new UserAddress(addressId, user_id, province_name, province_code, district_name, district_code, ward_name, ward_code, address, is_default);
                return ua;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public void editUserAddress(String id, String provineCode, String districtCode, String wardCode, String address, int is_default){
        try {
            String sql = "UPDATE user_addresses SET [province_code] = ?, [district_code] = ?, [ward_code] = ?, [address] = ? , [is_default] = ? WHERE [id] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, provineCode);
            statement.setString(2, districtCode);
            statement.setString(3, wardCode);
            statement.setString(4, address);
            statement.setInt(5, is_default);
            statement.setString(6, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int isHavingDefaultAddress(int userID){
        int defaultAddress;
        try {
            String sql = "select COUNT(*) as default_address from user_addresses where user_id = ? and deleted_at IS NULL and is_default = 1 ";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                defaultAddress = rs.getInt("default_address");
                return defaultAddress;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public int totalUserAddreesNumber(int userID){
        int totalUserAddreesNumber;
        try {
            String sql = "select COUNT(*) as totalUserAddreesNumber from user_addresses where user_id = ? and deleted_at IS NULL";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                totalUserAddreesNumber = rs.getInt("totalUserAddreesNumber");
                return totalUserAddreesNumber;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    
    public static void main(String[] args) {
        UserAddressDAO uaDAO = new UserAddressDAO();
        UserAddress ua = uaDAO.searchById("10");
        System.out.println(ua.toString());
    }
}
