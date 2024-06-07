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
        <title>Tạo màu mới</title>
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
                <h1 class="text-center mb-4">Tạo màu mới</h1>
                <form name="form" action="colors" method="POST" onsubmit="return validateForm();">
                    <div class="mb-3">
                        <label for="name" class="form-label">Tên màu:</label>
                        <input type="text" class="form-control" id="name" name="name">
                        <span id="error-message-name" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="color_code" class="form-label">Mã màu:</label>
                        <input type="text" class="form-control" id="color_code" name="color_code">
                        <span id="error-message-colorcode" style="color: red;"></span>
                    </div>
                    <button type="submit" class="btn btn-primary" name="add">Tạo mới</button>
                    <a href="colors" class="btn btn-secondary">Hủy bỏ</a>
                </form>
            </div>
        </div>

        <!-- Thêm Bootstrap JS (nếu cần) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                    function validateForm() {
                        var name = document.getElementById("name").value.trim();
                        var colorcode = document.getElementById("colorcode").value.trim();
                        var errorMessageName = document.getElementById("error-message-name");
                        var errorMessageColorCode = document.getElementById("error-message-colorcode");

                        // Đặt thông báo lỗi thành trống trước khi kiểm tra
                        errorMessageName.innerHTML = "";
                        errorMessageColorCode.innerHTML = "";

                        if (name === "") {
                            errorMessageName.innerHTML = "Hãy nhập tên màu";
                            return false;
                        }

                        if (colorcode === "") {
                            errorMessageColorCode.innerHTML = "Hãy nhập mã màu";
                            return false;
                        }

                        return true;
                    }
        </script>
    </body>
</html>