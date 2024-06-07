<%-- 
    Document   : CreateProducts
    Created on : Feb 6, 2024, 7:28:23 PM
    Author     : lucdu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo sản phẩm mới</title>
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
                <h1 class="text-center mb-4">Tạo sản phẩm mới</h1>
                <form name="form" action="products" method="POST" onsubmit="return validateForm();">
                    <div class="mb-3">
                        <label for="code" class="form-label"><span style="color: red;">*</span>Mã sản phẩm:</label>
                        <input type="text" class="form-control" id="code" name="code">
                        <span id="error-message-code" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="name" class="form-label"><span style="color: red;">*</span>Tên sản phẩm:</label>
                        <input type="text" class="form-control" id="name" name="name">
                        <span id="error-message-name" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label"><span style="color: red;">*</span>Mô tả sản phẩm:</label>
                        <textarea class="form-control form-control-lg" id="description" name="description"></textarea>
                        <span id="error-message-description" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="price" class="form-label"><span style="color: red;">*</span>Giá tiền:</label>
                        <input type="text" class="form-control" id="price" name="price">
                        <span id="error-message-price" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="category_id" class="form-label"><span style="color: red;">*</span>Loại giày:</label>
                        <select id="category_id" name="category_id" class="form-select" required>
                            <option value="0">Xin hãy chọn loại giày</option>                    
                            <c:forEach items="${data1}" var="c">
                                <option value="${c.getId()}">${c.getName()}</option>
                            </c:forEach>
                        </select>
                        <span id="error-message-category" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="form_id" class="form-label"><span style="color: red;">*</span>Kiểu dáng:</label>
                        <select id="form_id" name="form_id" class="form-select" required>
                            <option value="0">Xin hãy chọn kiểu dáng</option>                    
                            <c:forEach items="${data2}" var="c">
                                <option value="${c.getId()}">${c.getName()}</option>
                            </c:forEach>
                        </select>
                        <span id="error-message-form" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="brand_id" class="form-label"><span style="color: red;">*</span>Hãng giày:</label>
                        <select id="brand_id" name="brand_id" class="form-select" required>
                            <option value="0">Xin hãy chọn hãng giày</option>                    
                            <c:forEach items="${data3}" var="c">
                                <option value="${c.getId()}">${c.getName()}</option>
                            </c:forEach>
                        </select>
                        <span id="error-message-brand" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="material_id" class="form-label"><span style="color: red;">*</span>Chất liệu:</label>
                        <select id="material_id" name="material_id" class="form-select" required>
                            <option value="0">Xin hãy chọn chất liệu</option>                    
                            <c:forEach items="${data4}" var="c">
                                <option value="${c.getId()}">${c.getName()}</option>
                            </c:forEach>
                        </select>
                        <span id="error-message-material" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="group_id" class="form-label"><span style="color: red;">*</span>Trạng thái sản phẩm:</label>
                        <select id="group_id" name="group_id" class="form-select" required>
                            <option value="0">Xin hãy chọn trạng thái sản phẩm</option>                    
                            <c:forEach items="${data5}" var="c">
                                <option value="${c.getId()}">${c.getName()}</option>
                            </c:forEach>
                        </select>
                        <span id="error-message-group" style="color: red;"></span>
                    </div>
                    <button type="submit" class="btn btn-primary" name="add">Lưu</button>
                    <a href="products" class="btn btn-secondary">Hủy</a>
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
