<%-- 
    Document   : CreateGroups
    Created on : Jan 15, 2024, 9:25:58 AM
    Author     : lucdu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo kiểu dáng mới</title>
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
        </style>
    </head>
    <body>
      
        <div class="container">
            <div class="card mx-auto p-4">
                <h1 class="text-center mb-4">Tạo kiểu dáng mới</h1>
                <form name="form" action="Forms" method="POST" onsubmit="return validateForm();">
                    <div class="mb-3">
                        <label for="name" class="form-label">Nhập tên kiểu dáng:</label>
                        <input type="text" class="form-control" id="name" name="name">
                        <span id="error-message-name" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Nhập mô tả kiểu dáng:</label>
                        <input type="text" class="form-control" id="description" name="description">
                        <span id="error-message-description" style="color: red;"></span>
                    </div>
                    <button type="submit" class="btn btn-primary" name="add">Tạo</button>
                    <a href="Forms" class="btn btn-secondary">Hủy</a>
                </form>
            </div>
        </div>

        <!-- Thêm Bootstrap JS (nếu cần) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                    function validateForm() {
                        var name = document.getElementById("name").value.trim();
                        var description = document.getElementById("description").value.trim();
                        var errorMessageName = document.getElementById("error-message-name");
                        var errorMessageDescription = document.getElementById("error-message-description");

                        // Đặt thông báo lỗi thành trống trước khi kiểm tra
                        errorMessageName.innerHTML = "";
                        errorMessageDescription.innerHTML = "";

                        if (name === "") {
                            errorMessageName.innerHTML = "Hãy nhập tên kiểu dáng";
                            return false;
                        }

                        if (description === "") {
                            errorMessageDescription.innerHTML = "Hãy nhập mô tả kiểu dáng";
                            return false;
                        }

                        return true;
                    }
        </script>
    </body>
</html>