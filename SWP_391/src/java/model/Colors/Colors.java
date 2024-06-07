/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Colors;

/**
 *
 * @author Admin
 */
public class Colors {

    String id;
    String name, color_code, created_at, deleted_at, updated_at;

    public Colors() {
    }

    public Colors(String id, String name, String color_code, String created_at, String deleted_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.color_code = color_code;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Colors{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", color_code=").append(color_code);
        sb.append(", created_at=").append(created_at);
        sb.append(", deleted_at=").append(deleted_at);
        sb.append(", updated_at=").append(updated_at);
        sb.append('}');
        return sb.toString();
    }

}
