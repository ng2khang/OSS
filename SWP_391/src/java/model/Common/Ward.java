/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Common;

/**
 *
 * @author ifyou
 */
public class Ward {
    private String code;
    private String name;
    private String district_code;

    public Ward(String code, String name, String district_code) {
        this.code = code;
        this.name = name;
        this.district_code = district_code;
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

    public String getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }

    @Override
    public String toString() {
        return "Ward{" + "code=" + code + ", name=" + name + ", district_code=" + district_code + '}';
    }
}
