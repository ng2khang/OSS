/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Common;

import java.sql.Timestamp;


/**
 *
 * @author ifyou
 */
public class UserAddress {

    private int id;
    private int user_id;
    private String province_name;
    private String province_code;
    private String district_name;
    private String district_code;
    private String ward_name;
    private String ward_code;
    private String address;
    private Timestamp created_at;
    private Timestamp deleted_at;
    private int is_default;

    public UserAddress(int id, int user_id, String province_name, String province_code, String district_name, String district_code, String ward_name, String ward_code, String address, Timestamp created_at, Timestamp deleted_at, int is_default) {
        this.id = id;
        this.user_id = user_id;
        this.province_name = province_name;
        this.province_code = province_code;
        this.district_name = district_name;
        this.district_code = district_code;
        this.ward_name = ward_name;
        this.ward_code = ward_code;
        this.address = address;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
        this.is_default = is_default;
    }

    public UserAddress() {
    }

    public UserAddress(int id, int user_id, String province_name, String province_code, String district_name, String district_code, String ward_name, String ward_code, String address, int is_default) {
        this.id = id;
        this.user_id = user_id;
        this.province_name = province_name;
        this.province_code = province_code;
        this.district_name = district_name;
        this.district_code = district_code;
        this.ward_name = ward_name;
        this.ward_code = ward_code;
        this.address = address;
        this.is_default = is_default;
    }

    public UserAddress(int user_id, String province_code, String district_code, String ward_code, String address, int is_default) {
        this.user_id = user_id;
        this.province_code = province_code;
        this.district_code = district_code;
        this.ward_code = ward_code;
        this.address = address;
        this.is_default = is_default;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }

    public String getWard_name() {
        return ward_name;
    }

    public void setWard_name(String ward_name) {
        this.ward_name = ward_name;
    }

    public String getWard_code() {
        return ward_code;
    }

    public void setWard_code(String ward_code) {
        this.ward_code = ward_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }
    
    public String showAddress(){
        return address + " " + ward_name + " " + district_name + " " + province_name ; 
    }

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }
    
    @Override
    public String toString() {
        return "UserAddress{" + "id=" + id + ", user_id=" + user_id + ", province_name=" + province_name + ", province_code=" + province_code + ", district_name=" + district_name + ", district_code=" + district_code + ", ward_name=" + ward_name + ", ward_code=" + ward_code + ", address=" + address + ", created_at=" + created_at + ", is_default=" + is_default +'}';
    }
    
}
