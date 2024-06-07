/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.GroupsDAO;

import DAO.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Groups.Brands;

/**
 *
 * @author lucdu
 */
public class BrandsDAO extends DBContext {
    public ArrayList<Brands> selectAllBrands() {
        ArrayList<Brands> brandList = new ArrayList<>();
        try {
            String sql = "select * from brands";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                String Name = rs.getString("name");
                String Description = rs.getString("description");
                String Created_at = rs.getString("created_at");
                String Deleted_at = rs.getString("deleted_at");
                String Updated_at = rs.getString("updated_at");
                Brands brands = new Brands(Id, Name, Description, Created_at, Updated_at, Deleted_at);
                brandList.add(brands);
            }
        } catch (Exception e) {
            System.out.println("getBrandsList: " + e.getMessage());
        }
        return brandList;
    }

    public void createNewBrand(String name, String description) {
        try {
            String sql = "INSERT INTO brands (name, description, created_at) VALUES (?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, description);

            // Execute the update
            st.executeUpdate();

//             Optionally, you can retrieve the generated ID if needed
            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newGroupId = generatedKeys.getInt(1);
                System.out.println("New Brands created with ID: " + newGroupId);
            }
        } catch (Exception e) {
            System.out.println("createNewBrands: " + e.getMessage());
        }
    }
    public Brands selectbrandByID(String id) {
        Brands brand = null;
        try {
            String sql = "SELECT * FROM brands WHERE id = ?";
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

                brand = new Brands(Id, Name, Description, Created_at, Updated_at, Deleted_at);
            }
        } catch (Exception e) {
            System.out.println("selectBrandByID: " + e.getMessage());
        }
        return brand;
    }

    public void updateBrands(String id, String name, String description) {
        try {
            String sql = "UPDATE brands SET name = ?, description = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, description);
            st.setInt(3, Integer.parseInt(id));

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Brands with ID " + id + " updated successfully.");
            } else {
                System.out.println("No Brands found with ID " + id);
            }
        } catch (Exception e) {
            System.out.println("updateBrands: " + e.getMessage());
        }
    }

    public void softDeleteBrands(String id) {
        try {
            String sql = "UPDATE brands SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Brands with ID " + id + " soft-deleted successfully.");
            } else {
                System.out.println("No brands found with ID " + id);
            }
        } catch (Exception e) {
            System.out.println("softDeleteBrands: " + e.getMessage());
        }
    }

    public void restoreBrands(String id) {
        try {
            String sql = "UPDATE brands SET deleted_at = NULL WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Brand with ID " + id + " restored successfully.");
            } else {
                System.out.println("No brand found with ID " + id);
            }
        } catch (Exception e) {
            System.out.println("restoreBrand: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        BrandsDAO b = new BrandsDAO();
        ArrayList<Brands> data = b.selectAllBrands();
        System.out.println(data);
//        b.createNewBrand("dsds", "dsdsd");
//        Groups selectedGroup = g.selectGroupsByID("2");
//        if (selectedGroup != null) {
//            System.out.println("Selected Group: " + selectedGroup);
//            g.updateGroups(selectedGroup.getId(), "New Name", "New Description");
//        } else {
//            System.out.println("Group not found.");
//        }
    }
}
