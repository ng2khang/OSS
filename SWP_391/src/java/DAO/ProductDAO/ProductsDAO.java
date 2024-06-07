/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.ProductDAO;

import DAO.DBContext;
import DAO.GroupsDAO.BrandsDAO;
import DAO.GroupsDAO.CategoryDAO;
import DAO.GroupsDAO.FormDAO;
import DAO.GroupsDAO.GroupsDAO;
import DAO.MaterialsDAO.MaterialsDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Categories.Category;
import model.Groups.Brands;
import model.Groups.Groups;
import model.Materials.materials;
import model.Product.Products;
import model.Sale.Form;

/**
 *
 * @author lucdu
 */
public class ProductsDAO extends DBContext {

    public ArrayList<Products> selectAllProducts() {
        ArrayList<Products> ProductsList = new ArrayList<>();
        try {
            String sql = "select * from products";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                String Code = rs.getString("code");
                String Name = rs.getString("name");
                String Description = rs.getString("description");
                String Price = String.valueOf(rs.getInt("price"));
                String Category_id = String.valueOf(rs.getInt("category_id"));
                CategoryDAO cDAO = new CategoryDAO();
                Category category = cDAO.getCaByID(Category_id);
                String Form_id = String.valueOf(rs.getInt("form_id"));
                FormDAO fDAO = new FormDAO();
                Form form = fDAO.selectFormByID(Form_id);
                String Brand_id = String.valueOf(rs.getInt("brand_id"));
                BrandsDAO bDAO = new BrandsDAO();
                Brands brand = bDAO.selectbrandByID(Brand_id);
                String Material_id = String.valueOf(rs.getInt("material_id"));
                MaterialsDAO mDAO = new MaterialsDAO();
                materials material = mDAO.selectMaterialsByID(Material_id);
                String Group_id = String.valueOf(rs.getInt("group_id"));
                GroupsDAO gDAO = new GroupsDAO();
                Groups group = gDAO.selectGroupsByID(Group_id);
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp Edited_at = rs.getTimestamp("edited_at");
                Timestamp deletedAt = rs.getTimestamp("deleted_at");
                Products product = new Products(Id, Code, Name, Description, Price, category, form, brand, material, group, createdAt, Edited_at, deletedAt);
                ProductsList.add(product);
            }
        } catch (Exception e) {
            System.out.println("getProductsList: " + e.getMessage());
        }
        return ProductsList;
    }

    public ArrayList<Products> selectProductbyCategory(String cat_id) {
        ArrayList<Products> ProductsList = new ArrayList<>();
        try {
            String sql = "select * from products where category_id = " + cat_id;
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                String Code = rs.getString("code");
                String Name = rs.getString("name");
                String Description = rs.getString("description");
                String Price = String.valueOf(rs.getInt("price"));
                String Category_id = String.valueOf(rs.getInt("category_id"));
                CategoryDAO cDAO = new CategoryDAO();
                Category category = cDAO.getCaByID(Category_id);
                String Form_id = String.valueOf(rs.getInt("form_id"));
                FormDAO fDAO = new FormDAO();
                Form form = fDAO.selectFormByID(Form_id);
                String Brand_id = String.valueOf(rs.getInt("brand_id"));
                BrandsDAO bDAO = new BrandsDAO();
                Brands brand = bDAO.selectbrandByID(Brand_id);
                String Material_id = String.valueOf(rs.getInt("material_id"));
                MaterialsDAO mDAO = new MaterialsDAO();
                materials material = mDAO.selectMaterialsByID(Material_id);
                String Group_id = String.valueOf(rs.getInt("group_id"));
                GroupsDAO gDAO = new GroupsDAO();
                Groups group = gDAO.selectGroupsByID(Group_id);
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp Edited_at = rs.getTimestamp("edited_at");
                Timestamp deletedAt = rs.getTimestamp("deleted_at");
                Products product = new Products(Id, Code, Name, Description, Price, category, form, brand, material, group, createdAt, Edited_at, deletedAt);
                ProductsList.add(product);
            }
        } catch (Exception e) {
            System.out.println("getProductsList: " + e.getMessage());
        }
        return ProductsList;
    }

    public ArrayList<Products> selectProductbyForm(String cat_id) {
        ArrayList<Products> ProductsList = new ArrayList<>();
        try {
            String sql = "select * from products where form_id = " + cat_id;
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                String Code = rs.getString("code");
                String Name = rs.getString("name");
                String Description = rs.getString("description");
                String Price = String.valueOf(rs.getInt("price"));
                String Category_id = String.valueOf(rs.getInt("category_id"));
                CategoryDAO cDAO = new CategoryDAO();
                Category category = cDAO.getCaByID(Category_id);
                String Form_id = String.valueOf(rs.getInt("form_id"));
                FormDAO fDAO = new FormDAO();
                Form form = fDAO.selectFormByID(Form_id);
                String Brand_id = String.valueOf(rs.getInt("brand_id"));
                BrandsDAO bDAO = new BrandsDAO();
                Brands brand = bDAO.selectbrandByID(Brand_id);
                String Material_id = String.valueOf(rs.getInt("material_id"));
                MaterialsDAO mDAO = new MaterialsDAO();
                materials material = mDAO.selectMaterialsByID(Material_id);
                String Group_id = String.valueOf(rs.getInt("group_id"));
                GroupsDAO gDAO = new GroupsDAO();
                Groups group = gDAO.selectGroupsByID(Group_id);
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp Edited_at = rs.getTimestamp("edited_at");
                Timestamp deletedAt = rs.getTimestamp("deleted_at");
                Products product = new Products(Id, Code, Name, Description, Price, category, form, brand, material, group, createdAt, Edited_at, deletedAt);
                ProductsList.add(product);
            }
        } catch (Exception e) {
            System.out.println("getProductsList: " + e.getMessage());
        }
        return ProductsList;
    }

    public ArrayList<Products> selectProductbyBrand(String brand_id) {
        ArrayList<Products> ProductsList = new ArrayList<>();
        try {
            String sql = "select * from products where brand_id = " + brand_id;
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                String Code = rs.getString("code");
                String Name = rs.getString("name");
                String Description = rs.getString("description");
                String Price = String.valueOf(rs.getInt("price"));
                String Category_id = String.valueOf(rs.getInt("category_id"));
                CategoryDAO cDAO = new CategoryDAO();
                Category category = cDAO.getCaByID(Category_id);
                String Form_id = String.valueOf(rs.getInt("form_id"));
                FormDAO fDAO = new FormDAO();
                Form form = fDAO.selectFormByID(Form_id);
                String Brand_id = String.valueOf(rs.getInt("brand_id"));
                BrandsDAO bDAO = new BrandsDAO();
                Brands brand = bDAO.selectbrandByID(Brand_id);
                String Material_id = String.valueOf(rs.getInt("material_id"));
                MaterialsDAO mDAO = new MaterialsDAO();
                materials material = mDAO.selectMaterialsByID(Material_id);
                String Group_id = String.valueOf(rs.getInt("group_id"));
                GroupsDAO gDAO = new GroupsDAO();
                Groups group = gDAO.selectGroupsByID(Group_id);
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp Edited_at = rs.getTimestamp("edited_at");
                Timestamp deletedAt = rs.getTimestamp("deleted_at");
                Products product = new Products(Id, Code, Name, Description, Price, category, form, brand, material, group, createdAt, Edited_at, deletedAt);
                ProductsList.add(product);
            }
        } catch (Exception e) {
            System.out.println("getProductsList: " + e.getMessage());
        }
        return ProductsList;
    }

    public ArrayList<Products> selectProductbyPrice(String min_price, String max_price) {
        ArrayList<Products> ProductsList = new ArrayList<>();
        try {
            String sql = "select * from products where price BETWEEN " + min_price + "AND " + max_price;
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                String Code = rs.getString("code");
                String Name = rs.getString("name");
                String Description = rs.getString("description");
                String Price = String.valueOf(rs.getInt("price"));
                String Category_id = String.valueOf(rs.getInt("category_id"));
                CategoryDAO cDAO = new CategoryDAO();
                Category category = cDAO.getCaByID(Category_id);
                String Form_id = String.valueOf(rs.getInt("form_id"));
                FormDAO fDAO = new FormDAO();
                Form form = fDAO.selectFormByID(Form_id);
                String Brand_id = String.valueOf(rs.getInt("brand_id"));
                BrandsDAO bDAO = new BrandsDAO();
                Brands brand = bDAO.selectbrandByID(Brand_id);
                String Material_id = String.valueOf(rs.getInt("material_id"));
                MaterialsDAO mDAO = new MaterialsDAO();
                materials material = mDAO.selectMaterialsByID(Material_id);
                String Group_id = String.valueOf(rs.getInt("group_id"));
                GroupsDAO gDAO = new GroupsDAO();
                Groups group = gDAO.selectGroupsByID(Group_id);
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp Edited_at = rs.getTimestamp("edited_at");
                Timestamp deletedAt = rs.getTimestamp("deleted_at");
                Products product = new Products(Id, Code, Name, Description, Price, category, form, brand, material, group, createdAt, Edited_at, deletedAt);
                ProductsList.add(product);
            }
        } catch (Exception e) {
            System.out.println("getProductsList: " + e.getMessage());
        }
        return ProductsList;
    }

    public ArrayList<Products> selectProductbyMaterial(String min_price) {
        ArrayList<Products> ProductsList = new ArrayList<>();
        try {
            String sql = "select * from products where material_id = " + min_price;
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                String Code = rs.getString("code");
                String Name = rs.getString("name");
                String Description = rs.getString("description");
                String Price = String.valueOf(rs.getInt("price"));
                String Category_id = String.valueOf(rs.getInt("category_id"));
                CategoryDAO cDAO = new CategoryDAO();
                Category category = cDAO.getCaByID(Category_id);
                String Form_id = String.valueOf(rs.getInt("form_id"));
                FormDAO fDAO = new FormDAO();
                Form form = fDAO.selectFormByID(Form_id);
                String Brand_id = String.valueOf(rs.getInt("brand_id"));
                BrandsDAO bDAO = new BrandsDAO();
                Brands brand = bDAO.selectbrandByID(Brand_id);
                String Material_id = String.valueOf(rs.getInt("material_id"));
                MaterialsDAO mDAO = new MaterialsDAO();
                materials material = mDAO.selectMaterialsByID(Material_id);
                String Group_id = String.valueOf(rs.getInt("group_id"));
                GroupsDAO gDAO = new GroupsDAO();
                Groups group = gDAO.selectGroupsByID(Group_id);
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp Edited_at = rs.getTimestamp("edited_at");
                Timestamp deletedAt = rs.getTimestamp("deleted_at");
                Products product = new Products(Id, Code, Name, Description, Price, category, form, brand, material, group, createdAt, Edited_at, deletedAt);
                ProductsList.add(product);
            }
        } catch (Exception e) {
            System.out.println("getProductsList: " + e.getMessage());
        }
        return ProductsList;
    }

    public ArrayList<Products> selectProductbySearch(String search) {
        ArrayList<Products> ProductsList = new ArrayList<>();
        try {
            String sql = "SELECT  [id]\n"
                    + "      ,[code]\n"
                    + "      ,[name]\n"
                    + "      ,[description]\n"
                    + "      ,[price]\n"
                    + "      ,[category_id]\n"
                    + "      ,[form_id]\n"
                    + "      ,[brand_id]\n"
                    + "      ,[material_id]\n"
                    + "      ,[group_id]\n"
                    + "      ,[created_at]\n"
                    + "      ,[edited_at]\n"
                    + "      ,[deleted_at] \n"
                    + "FROM [OSS].[dbo].[products]\n"
                    + "WHERE code LIKE '%" + search + "%' "
                    + "OR [name] LIKE '%" + search + "%' "
                    + "OR [description] like '%" + search + "%' ";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                String Code = rs.getString("code");
                String Name = rs.getString("name");
                String Description = rs.getString("description");
                String Price = String.valueOf(rs.getInt("price"));
                String Category_id = String.valueOf(rs.getInt("category_id"));
                CategoryDAO cDAO = new CategoryDAO();
                Category category = cDAO.getCaByID(Category_id);
                String Form_id = String.valueOf(rs.getInt("form_id"));
                FormDAO fDAO = new FormDAO();
                Form form = fDAO.selectFormByID(Form_id);
                String Brand_id = String.valueOf(rs.getInt("brand_id"));
                BrandsDAO bDAO = new BrandsDAO();
                Brands brand = bDAO.selectbrandByID(Brand_id);
                String Material_id = String.valueOf(rs.getInt("material_id"));
                MaterialsDAO mDAO = new MaterialsDAO();
                materials material = mDAO.selectMaterialsByID(Material_id);
                String Group_id = String.valueOf(rs.getInt("group_id"));
                GroupsDAO gDAO = new GroupsDAO();
                Groups group = gDAO.selectGroupsByID(Group_id);
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp Edited_at = rs.getTimestamp("edited_at");
                Timestamp deletedAt = rs.getTimestamp("deleted_at");
                Products product = new Products(Id, Code, Name, Description, Price, category, form, brand, material, group, createdAt, Edited_at, deletedAt);
                ProductsList.add(product);
            }
        } catch (Exception e) {
            System.out.println("getProductsList: " + e.getMessage());
        }
        return ProductsList;
    }

    public Products selectProductByID(String productId) {
        Products product = null;
        try {
            String sql = "SELECT * FROM products WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String id = String.valueOf(rs.getInt("id"));
                String code = rs.getString("code");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String price = String.valueOf(rs.getInt("price"));
                String categoryId = String.valueOf(rs.getInt("category_id"));
                CategoryDAO cDAO = new CategoryDAO();
                Category category = cDAO.getCaByID(categoryId);
                String formId = String.valueOf(rs.getInt("form_id"));
                FormDAO fDAO = new FormDAO();
                Form form = fDAO.selectFormByID(formId);
                String brandId = String.valueOf(rs.getInt("brand_id"));
                BrandsDAO bDAO = new BrandsDAO();
                Brands brand = bDAO.selectbrandByID(brandId);
                String materialId = String.valueOf(rs.getInt("material_id"));
                MaterialsDAO mDAO = new MaterialsDAO();
                materials material = mDAO.selectMaterialsByID(materialId);
                String groupId = String.valueOf(rs.getInt("group_id"));
                GroupsDAO gDAO = new GroupsDAO();
                Groups group = gDAO.selectGroupsByID(groupId);
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp editedAt = rs.getTimestamp("edited_at");
                Timestamp deletedAt = rs.getTimestamp("deleted_at");
                product = new Products(id, code, name, description, price, category, form, brand, material, group, createdAt, editedAt, deletedAt);
            }
        } catch (Exception e) {
            System.out.println("selectProductByID: " + e.getMessage());
        }
        return product;
    }

    public void createNewProduct(String code, String name, String description, String price, String categoryId, String formId, String brandId, String materialId, String groupId) {
        try {
            String sql = "INSERT INTO products (code, name, description, price, category_id, form_id, brand_id, material_id, group_id, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            st.setString(2, name);
            st.setString(3, description);
            st.setString(4, price);
            st.setString(5, categoryId);
            st.setString(6, formId);
            st.setString(7, brandId);
            st.setString(8, materialId);
            st.setString(9, groupId);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("New product created successfully.");
            } else {
                System.out.println("Failed to create new product.");
            }
        } catch (Exception e) {
            System.out.println("createNewProduct: " + e.getMessage());
        }
    }

    public void updateProduct(String id, String code, String name, String description, String price, String categoryId, String formId, String brandId, String materialId, String groupId) {
        try {
            String sql = "UPDATE products SET code = ?, name = ?, description = ?, price = ?, category_id = ?, form_id = ?, brand_id = ?, material_id = ?, group_id = ?, edited_at = CURRENT_TIMESTAMP WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            st.setString(2, name);
            st.setString(3, description);
            st.setString(4, price);
            st.setString(5, categoryId);
            st.setString(6, formId);
            st.setString(7, brandId);
            st.setString(8, materialId);
            st.setString(9, groupId);
            st.setString(10, id);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Failed to update product.");
            }
        } catch (Exception e) {
            System.out.println("updateProduct: " + e.getMessage());
        }
    }

    public void softDeleteProduct(String productId) {
        try {
            String sql = "UPDATE products SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productId);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product soft deleted successfully.");
            } else {
                System.out.println("Failed to soft delete product.");
            }
        } catch (Exception e) {
            System.out.println("softDeleteProduct: " + e.getMessage());
        }
    }

    public void restoreProduct(String productId) {
        try {
            String sql = "UPDATE products SET deleted_at = NULL WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productId);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product restored successfully.");
            } else {
                System.out.println("Failed to restore product.");
            }
        } catch (Exception e) {
            System.out.println("restoreProduct: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ProductsDAO p = new ProductsDAO();
        ArrayList<Products> data = p.selectAllProducts();
        //System.out.println(data);
//        Products pro = p.selectProductByID("3");
//        System.out.println(pro);
//        p.createNewProduct("NK123", "Nike co cao", "kieu dang co cao", "9000", "2", "4", "1", "40", "1");
//        System.out.println(data.get(1).toString());
    }

}
