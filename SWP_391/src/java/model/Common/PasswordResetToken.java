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
public class PasswordResetToken {
    private int id;
    private String userEmail;
    private String token;
    private Timestamp expiration_time;

    public PasswordResetToken(String userEmail, String token, Timestamp expiration_time) {
        this.userEmail = userEmail;
        this.token = token;
        this.expiration_time = expiration_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getExpiration_time() {
        return expiration_time;
    }

    public void setExpiration_time(Timestamp expiration_time) {
        this.expiration_time = expiration_time;
    }

    @Override
    public String toString() {
        return "PasswordResetToken{" + "id=" + id + ", userEmail=" + userEmail + ", token=" + token + ", expiration_time=" + expiration_time + '}';
    }
    
}
