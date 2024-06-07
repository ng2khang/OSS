<%-- 
    Document   : CreatePDetails
    Created on : Feb 25, 2024, 7:57:14 PM
    Author     : lucdu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo mới chi tiết sản phẩm</title>
        <!-- Thêm Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
            }
            .container {
                min-height: 100vh;
                display: flex;
                align-items: center;
            }
            .card {
                border: none;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .btn-primary {
                background-color: #007bff;
                border-color: #007bff;
            }
            .btn-primary:hover {
                background-color: #0056b3;
                border-color: #0056b3;
            }
            .btn-secondary {
                background-color: #6c757d;
                border-color: #6c757d;
            }
            .btn-secondary:hover {
                background-color: #545b62;
                border-color: #4e555b;
            }
            #description {
                height: 150px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="card mx-auto p-4">
                <h1 class="text-center mb-4">Tạo chi tiết sản phẩm mới</h1>
                <form name="form" action="productdetails" method="POST" enctype="multipart/form-data" onsubmit="return validateForm();">
                    <div class="mb-3">
                        <label for="product_id" class="form-label"><span style="color: red;">*</span>Tên Sản Phẩm:</label>
                        <select id="product_id" name="product_id" class="form-select" required>
                            <option value="0"><span style="color: red;">*</span>Xin hãy chọn sản phẩm</option>                    
                            <c:forEach items="${data1}" var="c">
                                <option value="${c.getId()}">${c.getName()}</option>
                            </c:forEach>
                        </select>
                        <span id="error-message-code" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="color_id" class="form-label"><span style="color: red;">*</span>Màu Sắc:</label>
                        <select id="color_id" name="color_id" class="form-select" required>
                            <option value="0">Xin hãy chọn màu sắc</option>                    
                            <c:forEach items="${data2}" var="c">
                                <option value="${c.getId()}" style="width: 20px; height: 20px; background-color: ${c.color_code}; border: 1px solid black">${c.getName()}</option>
                            </c:forEach>
                        </select>
                        <span id="error-message-name" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="size_id" class="form-label"><span style="color: red;">*</span>Kích Thước Sản Phẩm:</label>
                        <select id="size_id" name="size_id" class="form-select" required>
                            <option value="0"><span style="color: red;">*</span>Xin hãy chọn kích cỡ giày</option>                    
                            <c:forEach items="${data3}" var="c">
                                <option value="${c.getId()}">${c.getName()}</option>
                            </c:forEach>
                        </select>
                        <span id="error-message-description" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="inventory" class="form-label"><span style="color: red;">*</span>Nhập Số Lượng Sản Phẩm:</label>
                        <input type="text" class="form-control" id="inventory" name="inventory">
                        <span id="error-message-price" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="image1" class="form-label"><span style="color: red;">*</span>Ảnh 1</label>
                        <input type="file" class="form-control" id="image1" name="image1" accept="image/*" required>
                    </div>
                    <div class="mb-3">
                        <label for="image2" class="form-label"><span style="color: red;">*</span>Ảnh 2</label>
                        <input type="file" class="form-control" id="image2" name="image2" accept="image/*" required>
                    </div>
                    <div class="mb-3">
                        <label for="image3" class="form-label"><span style="color: red;">*</span>Ảnh 3</label>
                        <input type="file" class="form-control" id="image3" name="image3" accept="image/*" required>
                    </div>
                    <div class="mb-3">
                        <label for="image4" class="form-label"><span style="color: red;">*</span>Ảnh 4</label>
                        <input type="file" class="form-control" id="image4" name="image4" accept="image/*" required>
                    </div>
                    <button type="submit" class="btn btn-primary" name="add">Lưu</button>
                    <a href="productdetails" class="btn btn-secondary">Hủy</a>
                </form>
            </div>
        </div>

        <!-- Thêm Bootstrap JS (nếu cần) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                    function validateForm() {
                        var code = document.getElementById("code").value.trim();
                        var name = document.getElementById("name").value.trim();
                        var description = document.getElementById("description").value.trim();
                        var price = document.getElementById("price").value.trim();
                        var category_id = document.getElementById("category_id").value;
                        var form_id = document.getElementById("form_id").value;
                        var brand_id = document.getElementById("brand_id").value;
                        var material_id = document.getElementById("material_id").value;
                        var group_id = document.getElementById("group_id").value;
                        var errorMessageCode = document.getElementById("error-message-code");
                        var errorMessageName = document.getElementById("error-message-name");
                        var errorMessageDescription = document.getElementById("error-message-description");
                        var errorMessagePrice = document.getElementById("error-message-price");
                        var errorMessageCategory = document.getElementById("error-message-category");
                        var errorMessageForm = document.getElementById("error-message-form");
                        var errorMessageBrand = document.getElementById("error-message-brand");
                        var errorMessageMaterial = document.getElementById("error-message-material");
                        var errorMessageGroup = document.getElementById("error-message-group");
                        // Đặt thông báo lỗi thành trống trước khi kiểm tra
                        errorMessageCode.innerHTML = "";
                        errorMessageName.innerHTML = "";
                        errorMessageDescription.innerHTML = "";
                        errorMessagePrice.innerHTML = "";
                        errorMessageCategory.innerHTML = "";
                        errorMessageForm.innerHTML = "";
                        errorMessageBrand.innerHTML = "";
                        errorMessageMaterial.innerHTML = "";
                        errorMessageGroup.innerHTML = "";
                        if (code === "") {
                            errorMessageCode.innerHTML = "Please enter a code";
                            return false;
                        }

                        if (name === "") {
                            errorMessageName.innerHTML = "Please enter a name";
                            return false;
                        }

                        if (description === "") {
                            errorMessageDescription.innerHTML = "Please enter a description";
                            return false;
                        }
                        if (price === "") {
                            errorMessagePrice.innerHTML = "Please enter a price";
                            return false;
                        }
                        if (category_id === "0") {
                            errorMessageCategory.innerHTML = "Please select a category";
                            return false;
                        }

                        if (form_id === "0") {
                            errorMessageForm.innerHTML = "Please select a form";
                            return false;
                        }

                        if (brand_id === "0") {
                            errorMessageBrand.innerHTML = "Please select a brand";
                            return false;
                        }

                        if (material_id === "0") {
                            errorMessageMaterial.innerHTML = "Please select a material";
                            return false;
                        }

                        if (group_id === "0") {
                            errorMessageGroup.innerHTML = "Please select a group";
                            return false;
                        }
                        return true;
                    }
        </script>

    </body>
</html>
