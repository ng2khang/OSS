/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Common;

import DAO.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Common.Province;

/**
 *
 * @author ifyou
 */
public class ProvinceDAO extends DBContext{
    public ArrayList selectAllProvince() {
        ArrayList<Province> provinces = new ArrayList<Province>();

        try {
            String sql = "  SELECT * FROM [provinces]";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                Province province = new Province(code, name);
                provinces.add(province);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return provinces;
    }
    public Province findProvinceByCode(String povinceCode){
        try {
            String sql = "  SELECT * FROM [provinces] WHERE code = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, povinceCode);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                Province province = new Province(code, name);
                return province;
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}