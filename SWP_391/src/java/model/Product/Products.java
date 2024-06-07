/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Product;

import java.sql.Timestamp;
import model.Categories.Category;
import model.Groups.Brands;
import model.Groups.Groups;
import model.Materials.materials;
import model.Sale.Form;

/**
 *
 * @author lucdu
 */
public class Products {

    private String id, code, name, description, price;
    private Category category;
    private Form form;
    private Brands brand;
    private materials material;
    private Groups group;
    private Timestamp created_at;
    private Timestamp edited_at;
    private Timestamp deleted_at;

    public Products(String id, String code, String name, String description, String price, Category category, Form form, Brands brand, materials material, Groups group, Timestamp created_at, Timestamp edited_at, Timestamp deleted_at) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.form = form;
        this.brand = brand;
        this.material = material;
        this.group = group;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Brands getBrand() {
        return brand;
    }

    public void setBrand(Brands brand) {
        this.brand = brand;
    }

    public materials getMaterial() {
        return material;
    }

    public void setMaterial(materials material) {
        this.material = material;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
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
        sb.append("Products{");
        sb.append("id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", price=").append(price);
        sb.append(", category=").append(category);
        sb.append(", form=").append(form);
        sb.append(", brand=").append(brand);
        sb.append(", material=").append(material);
        sb.append(", group=").append(group);
        sb.append(", created_at=").append(created_at);
        sb.append(", edited_at=").append(edited_at);
        sb.append(", deleted_at=").append(deleted_at);
        sb.append('}');
        return sb.toString();
    }


  
    
    
    

    
}
