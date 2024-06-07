/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Common;

/**
 *
 * @author ifyou
 */
public class District {
    private String code;
    private String name;
    private String province_code;

    public District(String code, String name, String province_code) {
        this.code = code;
        this.name = name;
        this.province_code = province_code;
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

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    @Override
    public String toString() {
        return "District{" + "code=" + code + ", name=" + name + ", province_code=" + province_code + '}';
    }
    
}
