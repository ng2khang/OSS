/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.ProductDAO;

import DAO.ColorsDAO.ColorsDAO;
import DAO.DBContext;
import DAO.GroupsDAO.BrandsDAO;
import DAO.GroupsDAO.CategoryDAO;
import DAO.GroupsDAO.FormDAO;
import DAO.GroupsDAO.GroupsDAO;
import DAO.MaterialsDAO.MaterialsDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Categories.Category;
import model.Colors.Colors;
import model.Groups.Brands;
import model.Groups.Groups;
import model.Materials.materials;
import model.Product.ProductDetails;
import model.Product.Products;
import model.Product.Sizes;
import model.Sale.Form;

/**
 *
 * @author lucdu
 */
public class ProductDetailDAO extends DBContext {

    public ArrayList<ProductDetails> selectAllProductDetails() {
        ArrayList<ProductDetails> ProductDetailsList = new ArrayList<>();
        try {
            String sql = "select * from product_details";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                String Product_id = String.valueOf(rs.getInt("product_id"));
                ProductsDAO pDAO = new ProductsDAO();
                Products product = pDAO.selectProductByID(Product_id);
                String Color_id = String.valueOf(rs.getInt("color_id"));
                ColorsDAO cDAO = new ColorsDAO();
                Colors color = cDAO.selectColorByID(Color_id);
                String Size_id = String.valueOf(rs.getInt("size_id"));
                SizeDAO sDAO = new SizeDAO();
                Sizes size = sDAO.selectSizeByID(Size_id);
                String InventoryNumber = String.valueOf(rs.getInt("inventory_number"));
                String Image_url_1 = rs.getString("image_url_1");
                String Image_url_2 = rs.getString("image_url_2");
                String Image_url_3 = rs.getString("image_url_3");
                String Image_url_4 = rs.getString("image_url_4");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp editedAt = rs.getTimestamp("edited_at");
                Timestamp deletedAt = rs.getTimestamp("deleted_at");

                ProductDetails productDetail = new ProductDetails(Id, product, color, size, InventoryNumber, Image_url_1, Image_url_2, Image_url_3, Image_url_4, createdAt, editedAt, deletedAt);
                ProductDetailsList.add(productDetail);
            }
        } catch (Exception e) {
            System.out.println("getProductDetailsList: " + e.getMessage());
        }
        return ProductDetailsList;
    }

    public ArrayList<Products> selectProductDetailsbyColor(String color_id) {
        ArrayList<Products> ProductsList = new ArrayList<>();
        try {
            String sql = "SELECT TOP (1000) b.[id]\n"
                    + "      ,b.[code]\n"
                    + "      ,b.[name]\n"
                    + "      ,b.[description]\n"
                    + "      ,b.[price]\n"
                    + "      ,b.[category_id]\n"
                    + "      ,b.[form_id]\n"
                    + "      ,b.[brand_id]\n"
                    + "      ,b.[material_id]\n"
                    + "      ,b.[group_id]\n"
                    + "      ,b.[created_at]\n"
                    + "      ,b.[edited_at]\n"
                    + "      ,b.[deleted_at]\n"
                    + "  FROM [OSS].[dbo].[product_details] as a \n"
                    + "  INNER JOIN products as b ON a.product_id = b.id\n"
                    + "  WHERE a.color_id = " + color_id;
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

    public boolean createNewProductDetails(String productId, String colorId, String sizeId, String inventoryNumber, String imageUrl1, String imageUrl2, String imageUrl3, String imageUrl4) {
        try {
            String sql = "INSERT INTO product_details (product_id, color_id, size_id, inventory_number, image_url_1, image_url_2, image_url_3, image_url_4, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, productId);
            statement.setString(2, colorId);
            statement.setString(3, sizeId);
            statement.setString(4, inventoryNumber);
            statement.setString(5, imageUrl1);
            statement.setString(6, imageUrl2);
            statement.setString(7, imageUrl3);
            statement.setString(8, imageUrl4);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new product detail was inserted successfully!");
                return true;
            }
        } catch (Exception e) {
            System.out.println("createNewProductDetails: " + e.getMessage());
        }
        return false;
    }

    public ProductDetails selectProductDetailById(String detailId) {
        try {
            String sql = "SELECT * FROM product_details WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, detailId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                String Product_id = String.valueOf(rs.getInt("product_id"));
                ProductsDAO pDAO = new ProductsDAO();
                Products product = pDAO.selectProductByID(Product_id);
                String Color_id = String.valueOf(rs.getInt("color_id"));
                ColorsDAO cDAO = new ColorsDAO();
                Colors color = cDAO.selectColorByID(Color_id);
                String Size_id = String.valueOf(rs.getInt("size_id"));
                SizeDAO sDAO = new SizeDAO();
                Sizes size = sDAO.selectSizeByID(Size_id);
                String InventoryNumber = String.valueOf(rs.getInt("inventory_number"));
                String Image_url_1 = rs.getString("image_url_1");
                String Image_url_2 = rs.getString("image_url_2");
                String Image_url_3 = rs.getString("image_url_3");
                String Image_url_4 = rs.getString("image_url_4");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp editedAt = rs.getTimestamp("edited_at");
                Timestamp deletedAt = rs.getTimestamp("deleted_at");

                return new ProductDetails(Id, product, color, size, InventoryNumber, Image_url_1, Image_url_2, Image_url_3, Image_url_4, createdAt, editedAt, deletedAt);
            }
        } catch (Exception e) {
            System.out.println("selectProductDetailById: " + e.getMessage());
        }
        return null;
    }

    public boolean updateProductDetail(String id, String productId, String colorId, String sizeId, String inventoryNumber, String imageUrl1, String imageUrl2, String imageUrl3, String imageUrl4) {
        try {
            String sql = "UPDATE product_details SET product_id = ?, color_id = ?, size_id = ?, inventory_number = ?, image_url_1 = ?, image_url_2 = ?, image_url_3 = ?, image_url_4 = ?, edited_at = CURRENT_TIMESTAMP WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, productId);
            statement.setString(2, colorId);
            statement.setString(3, sizeId);
            statement.setString(4, inventoryNumber);
            statement.setString(5, imageUrl1);
            statement.setString(6, imageUrl2);
            statement.setString(7, imageUrl3);
            statement.setString(8, imageUrl4);
            statement.setString(9, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product detail was updated successfully!");
                return true;
            }
        } catch (Exception e) {
            System.out.println("updateProductDetail: " + e.getMessage());
        }
        return false;
    }

    public boolean softDeleteProductDetail(String id) {
        try {
            String sql = "UPDATE product_details SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product detail was soft deleted successfully!");
                return true;
            }
        } catch (Exception e) {
            System.out.println("softDelete: " + e.getMessage());
        }
        return false;
    }

    public void addQuantity(String id, String quantity) {
        try {
            String sql = "UPDATE [product_details] SET inventory_number = inventory_number + ? WHERE [id] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, quantity);
            statement.setString(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void reduceQuantity(String id, String quantity) {
        try {
            String sql = "UPDATE [product_details] SET inventory_number = inventory_number - ? WHERE [id] = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, quantity);
            statement.setString(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean restoreProductDetail(String id) {
        try {
            String sql = "UPDATE product_details SET deleted_at = NULL WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product detail was restored successfully!");
                return true;
            }
        } catch (Exception e) {
            System.out.println("restore: " + e.getMessage());
        }
        return false;
    }

    // Phương thức lấy ra tất cả các màu liên quan đến một productID
    public ArrayList<Colors> getAllColorsByProductID(String productID) {
        ArrayList<Colors> colorsList = new ArrayList<>();
        try {
            String sql = "SELECT c.* "
                   + "FROM product_details pd "
                   + "INNER JOIN colors c ON pd.color_id = c.id "
                   + "WHERE pd.product_id = ? "
                   + "AND pd.inventory_number > 0 "
                   + "AND pd.deleted_at IS NULL";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, productID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String colorId = resultSet.getString("id");
                String name = resultSet.getString("name");
                String colorCode = resultSet.getString("color_code");
                String createdAt = resultSet.getString("created_at");
                String deletedAt = resultSet.getString("deleted_at");
                String updatedAt = resultSet.getString("updated_at");

                Colors color = new Colors(colorId, name, colorCode, createdAt, deletedAt, updatedAt);
                colorsList.add(color);
            }
        } catch (Exception e) {
            System.out.println("Error while fetching colors by product ID: " + e.getMessage());
        }
        return colorsList;
    }
    public ArrayList<Sizes> getAllSizesByColorIDAndProductID(String colorID, String productID) {
    ArrayList<Sizes> sizesList = new ArrayList<>();
    try {
        String sql = "SELECT s.* "
                + "FROM product_details pd "
                + "INNER JOIN sizes s ON pd.size_id = s.id "
                + "WHERE pd.color_id = ? AND pd.product_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, colorID);
        statement.setString(2, productID);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String sizeId = resultSet.getString("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String createdAt = resultSet.getString("created_at");
            String deletedAt = resultSet.getString("deleted_at");
            String updatedAt = resultSet.getString("updated_at");

            Sizes size = new Sizes(sizeId, name, description, createdAt, deletedAt, updatedAt);
            sizesList.add(size);
        }
    } catch (Exception e) {
        System.out.println("Error while fetching sizes by color ID and product ID: " + e.getMessage());
    }
    return sizesList;
}


    public ProductDetails selectProductDetailByProductIdColorIdAndSizeId(String productId, String colorId, String sizeId) {
        try {
            String sql = "SELECT * FROM product_details WHERE product_id = ? AND color_id = ? AND size_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productId);
            st.setString(2, colorId);
            st.setString(3, sizeId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String Id = String.valueOf(rs.getInt("id"));
                ProductsDAO pDAO = new ProductsDAO();
                Products product = pDAO.selectProductByID(productId);
                ColorsDAO cDAO = new ColorsDAO();
                Colors color = cDAO.selectColorByID(colorId);
                SizeDAO sDAO = new SizeDAO();
                Sizes size = sDAO.selectSizeByID(sizeId);
                String InventoryNumber = String.valueOf(rs.getInt("inventory_number"));
                String Image_url_1 = rs.getString("image_url_1");
                String Image_url_2 = rs.getString("image_url_2");
                String Image_url_3 = rs.getString("image_url_3");
                String Image_url_4 = rs.getString("image_url_4");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp editedAt = rs.getTimestamp("edited_at");
                Timestamp deletedAt = rs.getTimestamp("deleted_at");

                return new ProductDetails(Id, product, color, size, InventoryNumber, Image_url_1, Image_url_2, Image_url_3, Image_url_4, createdAt, editedAt, deletedAt);
            }
        } catch (Exception e) {
            System.out.println("selectProductDetailByProductIdColorIdAndSizeId: " + e.getMessage());
        }
        return null;
    }
public ProductDetails getAllProductDetailsByProductIdAndColorId(String productId, String colorId) {
    try {
        String sql = "SELECT * FROM product_details WHERE product_id = ? AND color_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, productId);
        statement.setString(2, colorId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String detailId = String.valueOf(resultSet.getInt("id"));
            ProductsDAO productsDAO = new ProductsDAO();
            Products product = productsDAO.selectProductByID(productId);
            ColorsDAO colorsDAO = new ColorsDAO();
            Colors color = colorsDAO.selectColorByID(colorId);
            String sizeId = String.valueOf(resultSet.getInt("size_id"));
            SizeDAO sizeDAO = new SizeDAO();
            Sizes size = sizeDAO.selectSizeByID(sizeId);
            String inventoryNumber = String.valueOf(resultSet.getInt("inventory_number"));
            String imageUrl1 = resultSet.getString("image_url_1");
            String imageUrl2 = resultSet.getString("image_url_2");
            String imageUrl3 = resultSet.getString("image_url_3");
            String imageUrl4 = resultSet.getString("image_url_4");
            Timestamp createdAt = resultSet.getTimestamp("created_at");
            Timestamp editedAt = resultSet.getTimestamp("edited_at");
            Timestamp deletedAt = resultSet.getTimestamp("deleted_at");

            return new  ProductDetails(detailId, product, color, size, inventoryNumber, imageUrl1, imageUrl2, imageUrl3, imageUrl4, createdAt, editedAt, deletedAt);
        }
    } catch (Exception e) {
        System.out.println("Error while fetching product details by product ID and color ID: " + e.getMessage());
    }
    return null;
}

    public static void main(String[] args) {
         ProductDetailDAO productDetailDAO = new ProductDetailDAO();

        // Thay đổi các giá trị productId, colorId, và sizeId theo yêu cầu của bạn
        String productId = "5";
        String colorId = "8";
        String sizeId = "1";

        // Gọi phương thức selectProductDetailByParams để lấy ProductDetail
//        ArrayList<ProductDetails> productDetail = productDetailDAO.getAllProductDetailsByProductIdAndColorId(productId, colorId);
//        System.out.println(productDetail);
// Sử dụng productDetail nhận được từ truy vấn để thực hiện các thao tác tiếp theo

    }
}
