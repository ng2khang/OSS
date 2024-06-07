/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Common;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author ifyou
 */
public class Order {
    private int id;
    private String orderCode;
    private User customer;
    private Timestamp orderDate;
    private int orderStatus;
    private UserAddress deliveryAddress;
    private Timestamp deliveryDate;
    private int paymentMethod;
    private ArrayList<OrderDetail> orderDetail;
    private String note;
    private int total;
    private ShippingCompany shippingCompany;
    private String shippingCode;

    public Order(int id, String orderCode, User customer, Timestamp orderDate, int orderStatus, UserAddress deliveryAddress, Timestamp deliveryDate, int paymentMethod, ArrayList<OrderDetail> orderDetail, String note, ShippingCompany shippingCompany, String shippingCode, int total) {
        this.id = id;
        this.orderCode = orderCode;
        this.customer = customer;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.deliveryDate = deliveryDate;
        this.paymentMethod = paymentMethod;
        this.orderDetail = orderDetail;
        this.note = note;
        this.shippingCompany = shippingCompany;
        this.shippingCode = shippingCode;
        this.total = total;
    }

    public Order(int id, String orderCode, User customer, Timestamp orderDate, int orderStatus, UserAddress deliveryAddress, Timestamp deliveryDate, int paymentMethod, ArrayList<OrderDetail> orderDetail, String note, int total) {
        this.id = id;
        this.orderCode = orderCode;
        this.customer = customer;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.deliveryDate = deliveryDate;
        this.paymentMethod = paymentMethod;
        this.orderDetail = orderDetail;
        this.note = note;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    public ArrayList<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(ArrayList<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public UserAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(UserAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }


    public Timestamp getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ShippingCompany getShippingCompany() {
        return shippingCompany;
    }

    public void setShippingCompany(ShippingCompany shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", orderCode=" + orderCode + ", customer=" + customer + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus + ", deliveryAddress=" + deliveryAddress + ", deliveryDate=" + deliveryDate + ", paymentMethod=" + paymentMethod + ", orderDetail=" + orderDetail + ", note=" + note + ", total=" + total + ", shippingCompany=" + shippingCompany + ", shippingCode=" + shippingCode + '}';
    }


    
    
    
    
}
