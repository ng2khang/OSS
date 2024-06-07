/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Common;

import model.Product.ProductDetails;

/**
 *
 * @author ifyou
 */
public class OrderDetail {
    private int id;
    private int order_id;
    private ProductDetails productDetail;
    private int quantity;
    private int unitPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public ProductDetails getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetails productDetail) {
        this.productDetail = productDetail;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public OrderDetail(int id, int order_id, ProductDetails productDetail, int quantity, int unitPrice) {
        this.id = id;
        this.order_id = order_id;
        this.productDetail = productDetail;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "id=" + id + ", order_id=" + order_id + ", productDetail=" + productDetail + ", quantity=" + quantity + ", unitPrice=" + unitPrice + '}';
    }
    
}
