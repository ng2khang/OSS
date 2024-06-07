<%-- 
    Document   : SetNewPassword
    Created on : Jan 21, 2024, 3:50:38 PM
    Author     : ifyou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Set New Password</title>
        
        <style>
            body {
                margin: 0;
                display: flex;
                align-items: center;
                justify-content: center;
                min-height: 100vh;
                background: #f5f5f5;
                color: #333;
            }

            .container {
                width: 100%;
                max-width: 400px;
            }

            .card {
                width: 100%;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            h2 {
                text-align: center;
                color: #333;
            }

            form {
                display: flex;
                flex-direction: column;
            }

            input {
                padding: 10px;
                margin-bottom: 12px;
                border: 1px solid #ddd;
                border-radius: 4px;
                transition: border-color 0.3s ease-in-out;
                outline: none;
                color: #333;
            }

            input:focus {
                border-color: #ff4500;
            }

            button {
                background-color: #ff4500;
                color: #fff;
                padding: 10px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s ease-in-out;
            }

            button:hover {
                background-color: #e63900;
            }
            .links {
                display: flex;
                flex-direction: row;
                justify-content: space-between;
            }
            a:link,
            a:visited,
            a:hover,
            a:active {
                text-decoration: none;
            }

            a {
                color: #000;
                font-size: 0.88rem;
                font-weight: 600;
                letter-spacing: -1px;
                transition: all 0.4s ease;
            }

            a:hover {
                color: rgb(13, 133, 185);
            }
             .form-field{
                font-size: 14px;
                padding-bottom: 12px;
                justify-content: center;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="card">
                <h2>Đặt mật khẩu mới</h2>
                <form action="/SWP_391/SetNewPassword" method="post">
                    <input type="password" id="password" name="password" placeholder="Enter new password" value="${password}" required>
                    <div class="form-field">
                        <input type="checkbox" onclick="showPassword()">
                        <label>Hiển thị mật khẩu</label>
                    </div>
                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Enter password(confirm)"  value="${confirmPassword}" required>
                    <div class="form-field">
                        <input type="checkbox" onclick="showConfirmPassword()">
                        <label>Hiển thị xác nhận mật khẩu</label>
                    </div>
                    <c:if test="${not empty message}">
                        <p style="color: red;">${message}</p> 
                    </c:if>
                    <input type="hidden" name="token" value="${token}">
                    <button type="submit">Đặt lại mật khẩu</button>
                    <br>
                </form>
            </div>
        </div>
    </body>
    <script>
        function showPassword() {
            var x = document.getElementById("password");
            if (x.type === "password") {
                x.type = "text";
            } else {
                x.type = "password";
            }
        }
        function showConfirmPassword() {
            var x = document.getElementById("confirmPassword");
            if (x.type === "password") {
                x.type = "text";
            } else {
                x.type = "password";
            }
        }
    </script>
</html>
