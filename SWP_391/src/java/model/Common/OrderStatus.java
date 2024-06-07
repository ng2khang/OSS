/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Common;

/**
 *
 * @author ifyou
 */
public class OrderStatus {
    private int id;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderStatus{" + "id=" + id + ", status=" + status + '}';
    }
    
}
