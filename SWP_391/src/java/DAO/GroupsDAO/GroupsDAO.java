/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.GroupsDAO;

import DAO.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Groups.Groups;

/**
 *
 * @author lucdu
 */
public class GroupsDAO extends DBContext {

    public ArrayList<Groups> selectAllGroups() {
        ArrayList<Groups> groupsList = new ArrayList<>();
        try {
            String sql = "select * from groups";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                String Name = rs.getString("name");
                String Description = rs.getString("description");
                String Created_at = rs.getString("created_at");
                String Deleted_at = rs.getString("deleted_at");
                String Updated_at = rs.getString("updated_at");
                Groups groups = new Groups(Id, Name, Description, Created_at, Deleted_at, Updated_at);
                groupsList.add(groups);
            }
        } catch (Exception e) {
            System.out.println("getGroupsList: " + e.getMessage());
        }
        return groupsList;
    }

    public void createNewGroups(String name, String description) {
        try {
            String sql = "INSERT INTO groups (name, description, created_at) VALUES (?, ?, CURRENT_TIMESTAMP)";
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
    public Groups selectGroupsByID(String id) {
        Groups group = null;
        try {
            String sql = "SELECT * FROM groups WHERE id = ?";
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

                group = new Groups(Id, Name, Description, Created_at, Deleted_at, Updated_at);
            }
        } catch (Exception e) {
            System.out.println("selectGroupsByID: " + e.getMessage());
        }
        return group;
    }

    public void updateGroups(String id, String name, String description) {
        try {
            String sql = "UPDATE groups SET name = ?, description = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
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

    public void softDeleteGroups(String id) {
        try {
            String sql = "UPDATE groups SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?";
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
            String sql = "UPDATE groups SET deleted_at = NULL WHERE id = ?";
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
    GroupsDAO g = new GroupsDAO();
//        ArrayList<Groups> data = g.selectAllGroups();
//        System.out.println(data);
//        g.createNewGroups("dsds", "dsdsd");
        Groups selectedGroup = g.selectGroupsByID("2");
        if (selectedGroup != null) {
            System.out.println("Selected Group: " + selectedGroup);
            g.updateGroups(selectedGroup.getId(), "New Name", "New Description");
        } else {
            System.out.println("Group not found.");
        }
    }
}
