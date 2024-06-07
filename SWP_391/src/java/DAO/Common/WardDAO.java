/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Common;

import DAO.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Common.District;
import model.Common.Ward;

/**
 *
 * @author ifyou
 */
public class WardDAO extends DBContext{
    public ArrayList selectAllWard() {
        ArrayList<Ward> wards = new ArrayList<Ward>();

        try {
            String sql = "  SELECT * FROM [wards]";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String district_code = rs.getString("district_code");
                Ward ward = new Ward(code, name, district_code);
                wards.add(ward);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return wards;
    }
    public ArrayList selectWardByDistrictCode(String districtCode){
        ArrayList<Ward> wards = new ArrayList<>();

        try {
            String sql = "  SELECT * FROM [wards] WHERE district_code = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, districtCode);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String district_code = rs.getString("district_code");
                Ward ward = new Ward(code, name, district_code);
                wards.add(ward);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return wards;
    }
    
    public Ward findWardByCode(String wardCode){
        try {
            String sql = "  SELECT * FROM [wards] WHERE code = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, wardCode);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String district_code = rs.getString("district_code");
                Ward ward = new Ward(code, name, district_code);
                return ward;
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}