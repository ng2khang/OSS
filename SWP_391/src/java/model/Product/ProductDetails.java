/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Product;

import java.sql.Timestamp;
import model.Colors.Colors;

/**
 *
 * @author lucdu
 */
public class ProductDetails {

    private String id;
    private Products product;
    private Colors color;
    private Sizes size;
    private String inventory_number, image_url_1, image_url_2, image_url_3, image_url_4;
    Timestamp created_at, edited_at, deleted_at;

    public ProductDetails() {
    }

    public ProductDetails(String id, Products product, Colors color, Sizes size, String inventory_number, String image_url_1, String image_url_2, String image_url_3, String image_url_4, Timestamp created_at, Timestamp edited_at, Timestamp deleted_at) {
        this.id = id;
        this.product = product;
        this.color = color;
        this.size = size;
        this.inventory_number = inventory_number;
        this.image_url_1 = image_url_1;
        this.image_url_2 = image_url_2;
        this.image_url_3 = image_url_3;
        this.image_url_4 = image_url_4;
        this.created_at = created_at;
        this.edited_at = edited_at;
        this.deleted_at = deleted_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public Sizes getSize() {
        return size;
    }

    public void setSize(Sizes size) {
        this.size = size;
    }

    public String getInventory_number() {
        return inventory_number;
    }

    public void setInventory_number(String inventory_number) {
        this.inventory_number = inventory_number;
    }

    public String getImage_url_1() {
        return image_url_1;
    }

    public void setImage_url_1(String image_url_1) {
        this.image_url_1 = image_url_1;
    }

    public String getImage_url_2() {
        return image_url_2;
    }

    public void setImage_url_2(String image_url_2) {
        this.image_url_2 = image_url_2;
    }

    public String getImage_url_3() {
        return image_url_3;
    }

    public void setImage_url_3(String image_url_3) {
        this.image_url_3 = image_url_3;
    }

    public String getImage_url_4() {
        return image_url_4;
    }

    public void setImage_url_4(String image_url_4) {
        this.image_url_4 = image_url_4;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getEdited_at() {
        return edited_at;
    }

    public void setEdited_at(Timestamp edited_at) {
        this.edited_at = edited_at;
    }

    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProductDetails{");
        sb.append("id=").append(id);
        sb.append(", product=").append(product);
        sb.append(", color=").append(color);
        sb.append(", size=").append(size);
        sb.append(", inventory_number=").append(inventory_number);
        sb.append(", image_url_1=").append(image_url_1);
        sb.append(", image_url_2=").append(image_url_2);
        sb.append(", image_url_3=").append(image_url_3);
        sb.append(", image_url_4=").append(image_url_4);
        sb.append(", created_at=").append(created_at);
        sb.append(", edited_at=").append(edited_at);
        sb.append(", deleted_at=").append(deleted_at);
        sb.append('}');
        return sb.toString();
    }

}
