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
import model.Common.UserContract;

/**
 *
 * @author Admin
 */
public class UserContractDAO extends DBContext {

    public ArrayList<UserContract> sellectAllUserContract(int userID) {
        ArrayList<UserContract> userContract = new ArrayList<>();

        try {
            String sql = "SELECT uc.[id] AS [id], uc.[user_id] AS [user_id], uc.[start_date], uc.[slot], uc.[salary] ,uc.[gender] \n"
                    + "FROM [users] AS u JOIN [user_contracts] AS uc ON u.[id] = uc.[user_id]\n"
                    + "WHERE u.[id] = ? AND u.[role] IN (2,3) AND u.[is_deleted] = 0 ";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String start_date = rs.getString("start_date");
                int slot = rs.getInt("slot");
                int salary = rs.getInt("salary");
                int gender = rs.getInt("gender");
                UserContract uc = new UserContract(id, user_id, start_date, slot, salary, gender);
                userContract.add(uc);
            }
        } catch (Exception e) {

        }
        return userContract;
    }

    public static void main(String[] args) {
        UserContractDAO udao = new UserContractDAO();
        ArrayList<UserContract> userContracts = udao.sellectAllUserContract(19);
        for (UserContract userContract : userContracts) {
            System.out.println("UserContract ID: " + userContract.getId());
            System.out.println("User ID: " + userContract.getUser_id());
            System.out.println("Start Date: " + userContract.getStart_date());
            System.out.println("Slot: " + userContract.getSlot());
            System.out.println("Salary: " + userContract.getSalary());
            System.out.println("Gender: " + userContract.getGender());
            System.out.println("------------------------------");
        }

    }
}
