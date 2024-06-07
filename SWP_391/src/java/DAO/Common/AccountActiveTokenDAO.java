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
import model.Common.AccountActiveToken;

/**
 *
 * @author ifyou
 */
public class AccountActiveTokenDAO extends DBContext{
    public void createAccountActiveToken(AccountActiveToken accountActiveToken){
        try {
            // SQL INSERT query
            String sql = "INSERT INTO [account_active_tokens] (user_email, token, expiration_time) VALUES (?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, accountActiveToken.getUserEmail());
            st.setString(2, accountActiveToken.getToken());
            st.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            // Execute INSERT
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public AccountActiveToken findByUserEmail(String email){
        try {
            String sql = "  SELECT * FROM [account_active_tokens] WHERE user_email = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String userEmail = rs.getString("user_email");
                String token = rs.getString("token");
                Timestamp expirationTime = rs.getTimestamp("expiration_time");
                AccountActiveToken accountActiveToken = new AccountActiveToken(userEmail, token, expirationTime);
                return accountActiveToken;
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public AccountActiveToken findByActiveToken(String activeToken){
        try {
            String sql = "  SELECT * FROM [account_active_tokens] WHERE token = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, activeToken);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String userEmail = rs.getString("user_email");
                String token = rs.getString("token");
                Timestamp expirationTime = rs.getTimestamp("expiration_time");
                AccountActiveToken accountActiveToken = new AccountActiveToken(userEmail, token, expirationTime);
                return accountActiveToken;
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void deleteAccountActiveToken(String email){
        try {
            String sql = "DELETE FROM [account_active_tokens] WHERE [user_email] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
