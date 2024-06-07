/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.GroupsDAO;

import DAO.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Categories.Category;

/**
 *
 * @author MTD
 */
public class CategoryDAO extends DBContext{
   PreparedStatement st = null;
    ResultSet rs = null;

    public List<Category> selectAllCategory() {
        List<Category> list = new ArrayList<>();
        try {
            String sql = "select * from categories";
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getTimestamp(4),
                        rs.getTimestamp(5)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void addCategory(String name, String des, String cre
    ) {

        try {
            String sql = "INSERT  dbo.categories ( [name], [description], created_at ) values (?, ?, ?);";
            st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, des);
            st.setString(3, cre);
            st.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void deleteCategory(String pid) {

        try {
            String sql = "delete from categories where id = ?";
            st = connection.prepareStatement(sql);
            st.setString(1, pid);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Category getCaByID(String id) {

        try {
            String sql = "select * from categories where id = ?";
            st = connection.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                return new Category(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getTimestamp(4),
                        rs.getTimestamp(5));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void editCategory(String id, String name, String des, String cre
    ) {

        try {
            String sql = "UPDATE categories SET [name] = ?, [description] = ?, created_at = ? WHERE ID = ?;";
            st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, des);
            st.setString(3, cre);
            st.setString(4, id);
            st.executeUpdate();

        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        CategoryDAO g = new CategoryDAO();
        List<Category> data = g.selectAllCategory();
        System.out.println(data);
    }
}
