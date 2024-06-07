/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.ProductDAO;

import DAO.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Product.Sizes;

/**
 *
 * @author lucdu
 */
public class SizeDAO extends DBContext {

    public ArrayList<Sizes> selectAllSizes() {
        ArrayList<Sizes> sizesList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM sizes";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                String Name = rs.getString("name");
                String Description = rs.getString("description");
                String Created_at = rs.getString("created_at");
                String Deleted_at = rs.getString("deleted_at");
                String Updated_at = rs.getString("updated_at");
                Sizes sizes = new Sizes(Id, Name, Description, Created_at, Deleted_at, Updated_at);
                sizesList.add(sizes);
            }
        } catch (Exception e) {
            System.out.println("getSizesList: " + e.getMessage());
        }
        return sizesList;
    }

    public Sizes selectSizeByID(String id) {
        Sizes size = null;
        try {
            String sql = "SELECT * FROM sizes WHERE id = ?";
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

                size = new Sizes(Id, Name, Description, Created_at, Deleted_at, Updated_at);
            }
        } catch (Exception e) {
            System.out.println("selectSizeByID: " + e.getMessage());
        }
        return size;
    }

    public void createNewSize(String name, String description) {
        try {
            String sql = "INSERT INTO sizes (name, description, created_at) VALUES (?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, description);

            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("createNewSize: " + e.getMessage());
        }
    }

    public void updateSize(String id, String name, String description) {
        try {
            String sql = "UPDATE sizes SET name = ?, description = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, description);
            st.setInt(3, Integer.parseInt(id));

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Size with ID " + id + " updated successfully.");
            } else {
                System.out.println("No size found with ID " + id);
            }
        } catch (Exception e) {
            System.out.println("updateSize: " + e.getMessage());
        }
    }

    public void softDeleteSize(String id) {
        try {
            String sql = "UPDATE sizes SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Size with ID " + id + " soft-deleted successfully.");
            } else {
                System.out.println("No size found with ID " + id);
            }
        } catch (Exception e) {
            System.out.println("softDeleteSize: " + e.getMessage());
        }
    }

    public void restoreSize(String id) {
        try {
            String sql = "UPDATE sizes SET deleted_at = NULL WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Size with ID " + id + " restored successfully.");
            } else {
                System.out.println("No size found with ID " + id);
            }
        } catch (Exception e) {
            System.out.println("restoreSize: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SizeDAO sizeDAO = new SizeDAO();
        Sizes selectedSize = sizeDAO.selectSizeByID("1");
        if (selectedSize != null) {
            System.out.println("Selected Size: " + selectedSize);
            sizeDAO.updateSize(selectedSize.getId(), "New Name", "New Description");
        } else {
            System.out.println("Size not found.");
        }
    }
}
