<%-- 
    Document   : login
    Created on : Jan 15, 2024, 10:48:09 AM
    Author     : ifyou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Đăng nhập</title>
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
                <h2>Đăng nhập</h2>
                <c:if test="${not empty message}">
                    <p style="color: blue;">${message}</p> 
                </c:if>
                <c:if test="${not empty passwordChange}">
                    <p style="color: blue;">${passwordChange}</p> 
                </c:if>
                <c:if test="${not empty loginMessage}">
                    <p style="color: blue;">${loginMessage}</p> 
                </c:if>
                <form action="/SWP_391/login" method="post">
                    <input type="text" id="email" name="email" placeholder="Email" value="${email}" required>
                    <input type="password" id="password" name="password" placeholder="Mật Khẩu"  value="${password}" required>
                    <div class="form-field">
                        <input type="checkbox" onclick="showPassword()">
                        <label>Hiện Password</label>
                    </div>
                    <c:if test="${not empty wrongLoginInfo}">
                        <p style="color: red;">${wrongLoginInfo}</p> 
                    </c:if>
                    <button type="submit">Đăng Nhập</button>
                    <br>
                    <div class="links">
                        <a href="/SWP_391/ForgotPasswordController">Quên mật khẩu?</a>
                        <a href="/SWP_391/register">Đăng ký</a>
                    </div>
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

    </script>
</html>
