/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Common;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ifyou
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private int is_deleted;
    private int role;
    private int status;
    private Timestamp created_at;
    ArrayList<UserAddress> address;
    ArrayList<UserContract> contract;

    public User(int id, String name, String email, String password, String phone, int is_deleted, int role, int status, Timestamp created_at, ArrayList<UserAddress> address, ArrayList<UserContract> contract) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.is_deleted = is_deleted;
        this.role = role;
        this.status = status;
        this.created_at = created_at;
        this.address = address;
        this.contract = contract;
    }
    private String image;

    public User(int id, String name, String email, String password, String phone, int is_deleted, int role, int status, ArrayList<UserAddress> address, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.is_deleted = is_deleted;
        this.role = role;
        this.status = status;
        this.address = address;
        this.image = image;
    }

    public ArrayList<UserAddress> getAddress() {
        return address;
    }

    public void setAddress(ArrayList<UserAddress> address) {
        this.address = address;
    }
    
    public User(String name, String email, String password, String phone, int is_deleted, int role, int status) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.is_deleted = is_deleted;
        this.role = role;
        this.status = status;
    }
    
    

    public User(int id, String name, String email, String password, String phone, int is_deleted, int role, int status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.is_deleted = is_deleted;
        this.role = role;
        this.status = status;
    }

    public User(int id, String name, String email, String password, String phone, int is_deleted, int role, int status, Timestamp created_at, ArrayList<UserAddress> address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.is_deleted = is_deleted;
        this.role = role;
        this.status = status;
        this.created_at = created_at;
        this.address = address;
    }

    public User(int id, String name, String email, String phone, int is_deleted, int role, int status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.is_deleted = is_deleted;
        this.role = role;
        this.status = status;
    }

  
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone + ", is_deleted=" + is_deleted + ", role=" + role + ", status=" + status + ", address="   + '}';
    }

    

    
    
}