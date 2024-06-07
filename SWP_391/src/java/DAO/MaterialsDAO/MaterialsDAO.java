/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.MaterialsDAO;

import DAO.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Materials.materials;

/**
 *
 * @author Admin
 */
public class MaterialsDAO extends DBContext {

    // ham hien thi list 
   public ArrayList<materials> getAll() {
        ArrayList<materials> list = new ArrayList<>();
        try {
            String sql = "select * from materials";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                String Name = rs.getString("name");
                String Description = rs.getString("description");
                String Created_at = rs.getString("created_at");
                String Deleted_at = rs.getString("deleted_at");
                String Updated_at = rs.getString("updated_at");
                materials m = new materials(Id, Name, Description, Created_at, Deleted_at, Updated_at);
                list.add(m);
            }
        } catch (Exception e) {
            System.out.println("getMaterialsList: " + e.getMessage());
        }
        return list;
    }
    // ham create
    public void createNewMaterials(String name, String description) {
        try {
            String sql = "INSERT INTO materials (name, description, created_at) VALUES (?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, description);
            st.executeUpdate();
            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newMaterialsId = generatedKeys.getInt(1);
                System.out.println("New materials created with ID: " + newMaterialsId);
            }
        } catch (Exception e) {
            System.out.println("createNewMaterials: " + e.getMessage());
        }
    }

 
    // ham selectByID
    public materials selectMaterialsByID(String id) {
        materials m = null;
        try {
            String sql = "SELECT * FROM materials WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                String Name = rs.getString("name");
                String Description = rs.getString("description");
                String Created_at = rs.getString("created_at");
                String Deleted_at = rs.getString("deleted_at");
                String Updated_at = rs.getString("updated_at");

                m = new materials(Id, Name, Description, Created_at, Deleted_at, Updated_at);
            }
        } catch (Exception e) {
            System.out.println("selectMaterialsByID: " + e.getMessage());
        }
        return m;
    }

    // ham update
    public void updateMaterials(String id, String name, String description) {
        try {
            String sql = "UPDATE materials SET name = ?, description = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, description);
            st.setInt(3, Integer.parseInt(id));

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Materials with ID " + id + " updated successfully.");
            } else {
                System.out.println("No materials found with ID " + id);
            }
        } catch (Exception e) {
            System.out.println("updateMaterials: " + e.getMessage());
        }
    }
    // ham softdeleted

    public void softDeleteMaterials(String id) {
        try {
            String sql = "UPDATE materials SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Materials with ID " + id + " soft-deleted successfully.");
            } else {
                System.out.println("No materials found with ID " + id);
            }
        } catch (Exception e) {
            System.out.println("softDeleteMaterials: " + e.getMessage());
        }
    }
    // ham restore

    public void restoreMaterials(String id) {
        try {
            String sql = "UPDATE materials SET deleted_at = NULL WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("materials with ID " + id + " restored successfully.");
            } else {
                System.out.println("No materials found with ID " + id);
            }
        } catch (Exception e) {
            System.out.println("restoreMaterials: " + e.getMessage());
        }
    }

    // ham main
    public static void main(String[] args) {
        MaterialsDAO mdao = new MaterialsDAO();
        ArrayList<materials> data = mdao.getAll();
        System.out.println(data);

    }

}
