/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.ColorsDAO;

import DAO.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Colors.Colors;

/**
 *
 * @author Admin
 */

public class ColorsDAO extends DBContext {

   public ArrayList<Colors> getAll() {
        ArrayList<Colors> colorsList = new ArrayList<>();
        try {
            String sql = "select * from colors";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                String Name = rs.getString("name");
                String Color_code = rs.getString("color_code");
                String Created_at = rs.getString("created_at");
                String Deleted_at = rs.getString("deleted_at");
                String Updated_at = rs.getString("updated_at");
                Colors c = new Colors(Id, Name, Color_code, Created_at, Deleted_at, Updated_at);
                colorsList.add(c);
            }
        } catch (Exception e) {
            System.out.println("getColorsList: " + e.getMessage());
        }
        return colorsList;
    }
   
   
    public void createNewColors(String name, String color_code) {
        try {
            String sql = "INSERT INTO colors (name, color_code, created_at) VALUES (?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, color_code);
            st.executeUpdate();
            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newColorId = generatedKeys.getInt(1);
                System.out.println("New color created with ID: " + newColorId);
            }
        } catch (Exception e) {
            System.out.println("createNewColors: " + e.getMessage());
        }
    }
    
     public Colors selectColorByID(String id) {
        Colors c = null;
        try {
            String sql = "SELECT * FROM colors WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                String Name = rs.getString("name");
                String Color_code = rs.getString("color_code");
                String Created_at = rs.getString("created_at");
                String Deleted_at = rs.getString("deleted_at");
                String Updated_at = rs.getString("updated_at");

                c = new Colors(Id, Name, Color_code, Created_at, Deleted_at, Updated_at);
            }
        } catch (Exception e) {
            System.out.println("selectColorsByID: " + e.getMessage());
        }
        return c;
    }
     
      public void updateColors(String id, String name, String color_code) {
        try {
            String sql = "UPDATE colors SET name = ?, color_code = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, color_code);
            st.setInt(3, Integer.parseInt(id));

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Colors with ID " + id + " updated successfully.");
            } else {
                System.out.println("No color found with ID " + id);
            }
        } catch (Exception e) {
            System.out.println("updateColors: " + e.getMessage());
        }
    }
      
          public void softDeleteColors(String id) {
        try {
            String sql = "UPDATE colors SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Colors with ID " + id + " soft-deleted successfully.");
            } else {
                System.out.println("No colors found with ID " + id);
            }
        } catch (Exception e) {
            System.out.println("softDeleteColors: " + e.getMessage());
        }
    }

    public void restoreColors(String id) {
        try {
            String sql = "UPDATE colors SET deleted_at = NULL WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Colors with ID " + id + " restored successfully.");
            } else {
                System.out.println("No colors found with ID " + id);
            }
        } catch (Exception e) {
            System.out.println("restoreColors: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        ColorsDAO c = new ColorsDAO();
        ArrayList<Colors> data = c.getAll();
        System.out.println(data);
    }
}
