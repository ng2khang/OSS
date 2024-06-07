/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.GroupsDAO;

import DAO.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Sale.Form;
/**
 *
 * @author lucdu
 */
public class FormDAO extends DBContext{
    public ArrayList<Form> selectAllForm() {
        ArrayList<Form> groupsList = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n" +
                        "      ,[name]\n" +
                        "      ,[description]\n" +
                        "      ,[created_at]\n" +
                        "      ,[deleted_at]\n" +
                        "      ,[updated_at]\n" +
                        "  FROM [dbo].[forms]";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                String Name = rs.getString("name");
                String Description = rs.getString("description");
                String Created_at = rs.getString("created_at");
                String Deleted_at = rs.getString("deleted_at");
                String Updated_at = rs.getString("updated_at");
                Form groups = new Form(Id, Name, Description, Created_at, Deleted_at, Updated_at);
                groupsList.add(groups);
            }
        } catch (Exception e) {
            System.out.println("getGroupsList: " + e.getMessage());
        }
        return groupsList;
    }

    public void createNewForm(String name, String description) {
        try {
            String sql = "INSERT INTO forms (name, description, created_at) VALUES (?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, description);

            // Execute the update
            st.executeUpdate();

//             Optionally, you can retrieve the generated ID if needed
            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newGroupId = generatedKeys.getInt(1);
                System.out.println("New group created with ID: " + newGroupId);
            }
        } catch (Exception e) {
            System.out.println("createNewGroups: " + e.getMessage());
        }
    }
//    public void createNewGroups(String name, String description) {
//        try {
//            String sql = "INSERT INTO groups (id, name, description, created_at) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
//            PreparedStatement st = connection.prepareStatement(sql);
//            // Find the maximum existing ID
//            int maxId = getMaxGroupId();
//
//            // Set the new ID
//            st.setInt(1, maxId + 1);
//            st.setString(2, name);
//            st.setString(3, description);
//
//            // Execute the update
//            st.executeUpdate();
//        } catch (Exception e) {
//            System.out.println("createNewGroups: " + e.getMessage());
//        }
//    }

    private int getMaxGroupId() {
        int maxId = 0;
        try {
            String sql = "SELECT MAX(id) AS max_id FROM forms";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                maxId = rs.getInt("max_id");
            }
        } catch (Exception e) {
            System.out.println("getMaxGroupId: " + e.getMessage());
        }
        return maxId;
    }

    public Form selectFormByID(String id) {
        Form group = null;
        try {
            String sql = "SELECT * FROM forms WHERE id = ?";
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

                group = new Form(Id, Name, Description, Created_at, Deleted_at, Updated_at);
            }
        } catch (Exception e) {
            System.out.println("selectGroupsByID: " + e.getMessage());
        }
        return group;
    }

    public void updateForm(String id, String name, String description) {
        try {
            String sql = "UPDATE forms SET name = ?, description = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, description);
            st.setInt(3, Integer.parseInt(id));

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Group with ID " + id + " updated successfully.");
            } else {
                System.out.println("No group found with ID " + id);
            }
        } catch (Exception e) {
            System.out.println("updateGroups: " + e.getMessage());
        }
    }

    public void softDeleteForm(String id) {
        try {
            String sql = "UPDATE forms SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Group with ID " + id + " soft-deleted successfully.");
            } else {
                System.out.println("No group found with ID " + id);
            }
        } catch (Exception e) {
            System.out.println("softDeleteGroups: " + e.getMessage());
        }
    }

    public void restoreGroups(String id) {
        try {
            String sql = "UPDATE forms SET deleted_at = NULL WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Group with ID " + id + " restored successfully.");
            } else {
                System.out.println("No group found with ID " + id);
            }
        } catch (Exception e) {
            System.out.println("restoreGroups: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        FormDAO g = new FormDAO();
        ArrayList<Form> data = g.selectAllForm();
//        System.out.println(data);
//        Form selectedGroup = g.selectFormByID("2");
//        if (selectedGroup != null) {
//            System.out.println("Selected Group: " + selectedGroup);
//            g.updateForm(selectedGroup.getId(), "New Name", "New Description");
//        } else {
//            System.out.println("Group not found.");
//        }
    }
}
