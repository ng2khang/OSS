<%-- 
    Document   : profile
    Created on : Feb 18, 2024, 12:53:12 PM
    Author     : ifyou
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tra cứu đơn hàng</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/home.css" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" />
        <link rel="shortcut icon" type="image/png" href="img/favicon.png">
        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <!-- Favicon -->
        <link href="img/icons8-cofee-16.png" rel="icon" />

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet" />

        <!--Libraries Stylesheet-->
        <link href="js/animate/animate.min.css" rel="stylesheet" />
        <link href="js/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet" />

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet" />

        <style>
            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgb(0,0,0);
                background-color: rgba(0,0,0,0.4);
                padding-top: 60px;
            }

            .modal-content {
                background-color: #fefefe;
                margin: 5% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 80%;
                max-width: 500px;
            }

            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }

            .modal-header h2 {
                margin: 0;
            }

            .modal-body {
                padding: 2px 16px;
            }

            .modal-body form {
                display: flex;
                flex-direction: column;
            }

            .modal-body label,
            .modal-body select,
            .modal-body button {
                margin-top: 10px;
            }

            /* Button styling */
            .modal-body button {
                cursor: pointer;
                padding: 10px;
                margin-top: 20px;
                width: auto;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
            }

            .modal-body button:hover {
                background-color: #0056b3;
            }
            .modal .select {
                overflow-y: auto; /* Cho phép cuộn dọc nếu cần */
                max-height: 200px; /* Đặt chiều cao tối đa */
            }
            .address-container {
                display: flex;
                align-items: center; /* Căn chỉnh các phần tử theo trục dọc */
                justify-content: space-between; /* Phân bố khoảng cách giữa các phần tử */
                width: 100%; /* Hoặc bạn có thể đặt chiều rộng cố định nếu muốn */
                padding: 10px; /* Đặt padding cho container nếu cần */
            }


            .edit-link {
                font-size: 14px; /* Điều chỉnh kích thước font cho link chỉnh sửa */
                color: #007bff; /* Màu cho link, bạn có thể thay đổi */
                text-decoration: none; /* Gỡ bỏ gạch dưới của link */
                cursor: pointer; /* Hiển thị con trỏ chuột dạng pointer khi di chuột qua link */
            }

            .edit-link:hover {
                text-decoration: underline; /* Thêm gạch dưới khi hover để chỉ ra có thể click */
            }


            .btn-search-order {
                background-color: #ffc221;
                color: black;
            }

            .form-group {
                text-align: center; /* Đặt văn bản ở giữa */
            }

            #order-code {
                width: 40%; /* Đặt chiều rộng của input */
                margin: 0 auto; /* Đặt lề tự động để căn giữa */
            }
            .title-1 {
                margin-top: 20px; /* Điều chỉnh khoảng cách phía trên */
                margin-bottom: 10px; /* Điều chỉnh khoảng cách phía dưới */
                padding-left: 176px;

                /* Hoặc có thể sử dụng margin: 20px 50px 10px 50px; để đặt cả bốn giá trị cùng lúc */
            }

            .title-3 {
                text-align: center; /* Căn giữa nội dung bên trong */
                margin: 0 auto; /* Lùi vào giữa theo chiều ngang */
            }

        </style>

    </head>

    <body>
        <jsp:include page="view/Header and footer/HeaderCustomer.jsp"></jsp:include>
            <br>
            <br>
            <br>
            <br>
            <form action="searchOrder" method="post">
                <div class="row ">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 form-group">
                        <h1>TRA CỨU ĐƠN HÀNG</h1>
                        <br>
                        <div class="has-feedback">
                            <input type="text" name="orderCode" class="form-control text-uppercase" placeholder="Mã đơn hàng" id="order-code" required="" style="height: 40px;" value="${order}">
                        <span></span>
                        <p style="color: red;">${errorMessage}</p> 
                    </div>
                </div>       
                <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 title-3">
                    <button type="submit" class="btn btn-search-order" data-usersearchorder="" data-action="user-search-order">TRA CỨU ĐƠN HÀNG</button>
                </div>

            </div>
        </form>
        <br>
        <br>
        <br>
        <br>

        <jsp:include page="view/customer/footer.jsp"></jsp:include>
    </body>

</html>
