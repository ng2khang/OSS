<%-- 
    Document   : profile
    Created on : Feb 18, 2024, 12:53:12 PM
    Author     : ifyou
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                background-color:#303030;
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

            body {
                font-family: Arial, sans-serif;
            }

            .order-header {
                background-color: #303030;
                padding: 10px;
                margin: 0;
                width: 100%;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            h2 {
                margin: 0;
            }
            hr.custom-hr {
                border: 0;
                height: 2px;
                background-color: #303030;
                width: 70px;
                margin: 20px 0;
            }

            hr.custom-hr::after {
                content: '';
                display: block;
                margin-top: -2px;
                height: 2px;
                background-color: #303030;
                width: 749px;
            }
            .btn.btn-cont {
                background-color: #303030;
                color: white; /* Đặt màu chữ trắng để nổi bật trên nền đen */
                padding: 10px 20px; /* Tùy chỉnh kích thước và khoảng cách nút */
                border: none; /* Loại bỏ viền nút */
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                cursor: pointer;
            }
            .order-container {
                width: calc(50% - 10px); /* 50% của chiều rộng màn hình trừ đi khoảng trắng */
                margin-right: 10px; /* Khoảng trắng giữa div và cạnh phải màn hình */
                box-sizing: border-box; /* Kích thước tính cả padding và border */
                background-color: #f2f2f2; /* Màu nền để phân biệt */
            }
            .order-details,
            .additional-details {
                width: calc(50% - 10px); /* 50% của chiều rộng màn hình trừ đi khoảng trắng */
                box-sizing: border-box; /* Kích thước tính cả padding và border */
                background-color: #f2f2f2; /* Màu nền để phân biệt */
                padding: 20px; /* Khoảng cách giữa nội dung và viền */
            }
            .img-fluid {
                width: 50%; /* Thay đổi kích thước theo nhu cầu của bạn */
                height: auto;
            }
        </style>
        <script>
            function quayLaiMuaHang() {
                // Đường dẫn bạn muốn chuyển hướng đến
                var href = 'CustomerProducts';

                // Chuyển hướng đến trang mua hàng
                window.location.href = href;
            }
        </script>
        <script>
            window.onload = function () {
                var containers = document.querySelectorAll('.order-container');
                var maxHeight = 0;

                containers.forEach(function (container) {
                    var height = container.offsetHeight;
                    if (height > maxHeight) {
                        maxHeight = height;
                    }
                });

                containers.forEach(function (container) {
                    container.style.height = maxHeight + 'px';
                });
            };
        </script>
    </head>

    <body>
        <jsp:include page="view/Header and footer/HeaderCustomer.jsp"></jsp:include>
            <br>
            <br>
            <br>
            <br>

            <div class="d-flex justify-content-between align-items-center mb-5">
                <div class="order-container">
                    <div class="order-header">
                        <h2 style="color: white">ĐƠN HÀNG CỦA BẠN</h2>
                    </div>
                    <hr class="custom-hr">
                    <table class="table table-striped table-hover">    
                        <thead>
                            <tr>
                                <td><strong>Tên sản phẩm</strong></td>
                                <td><strong>Màu sắc</strong></td>
                                <td><strong>Kích thước</strong></td>
                                <td><strong>Số lượng</strong></td>
                                <td><strong>giá sản phẩm</strong></td>
                            </tr>
                        </thead>

                    <c:forEach items="${NewOrderDetails}" var="c">
                        <tr>
                            <td> <img
                                    src="images/${c.getProductDetail().getImage_url_2()}"
                                    class="img-fluid rounded-3" alt="Cotton T-shirt" >${c.getProductDetail().getProduct().getName()}</td>
                            <td>${c.getProductDetail().getColor().getName()}</td>
                            <td>${c.getProductDetail().getSize().getName()}</td>
                            <td>${c.getQuantity()}</td>
                            <td><fmt:formatNumber type="currency" currencySymbol="VND" value="${c.getProductDetail().getProduct().getPrice()}" maxFractionDigits="0" /></td>
                        </tr>
                    </c:forEach>

                </table>
                <strong>
                    <h1> Tổng số tiền cần thanh toán: <fmt:formatNumber type="currency" currencySymbol="VND" value="${total}" maxFractionDigits="0" /></h1>
                </strong>           

                <hr class="custom-hr">
                <button onclick="quayLaiMuaHang()" class="btn btn-cont" >QUAY LẠI MUA HÀNG</button>
            </div>
            <br>
            <br>
            <div class="order-container">  
                <div class="order-header">
                    <h2 style="color: white">THÔNG TIN KHÁCH HÀNG</h2>
                </div>
                <h4 class="text-muted"><strong>Tên khách hàng:</strong> ${order.customer.getName()}</h4> 
                <h4 class="text-muted"><strong>Số điện khách hàng:</strong> ${order.customer.getPhone()}</h4> 
                <h4 class="text-muted"><strong>ngày đặt hàng:</strong> ${order.orderDate}</h4> 
                <h4 class="text-muted"><strong>ngày vận chuyển:</strong> ${order.deliveryDate}</h4> 
                <c:choose>
                    <c:when test="${order.orderStatus eq 1}">
                        <h4 class="text-muted"><strong>Trạng thái:</strong>  Đang chờ xử lý </h4>
                    </c:when>
                    <c:when test="${order.orderStatus eq 2}">
                        <h4 class="text-muted"><strong>Trạng thái:</strong> Đóng gói xong và đang giao cho người vận chuyển</h4>
                    </c:when>
                    <c:when test="${order.orderStatus eq 3}">
                        <h4 class="text-muted"><strong>Trạng thái:</strong> Người vận chuyển đã nhận đơn và đang giao hàng</h4>
                    </c:when>
                    <c:when test="${order.orderStatus eq 4}">
                        <h4 class="text-muted"><strong>Trạng thái:</strong> Đơn hàng được giao thành công  </h4>
                    </c:when>
                    <c:when test="${order.orderStatus eq 5}">
                        <h4 class="text-muted"><strong>Trạng thái:</strong> Khách hàng không nhận hàng </h4>
                    </c:when>
                    <c:when test="${order.orderStatus eq 6}">
                        <h4 class="text-muted"><strong>Trạng thái:</strong> Khách hàng hủy đơn hàng </h4>
                    </c:when>
                    <c:otherwise>
                        <h4 class="text-muted"><strong>Trạng thái:</strong> Không xác định</h4>
                    </c:otherwise>
                </c:choose>                         
                <h4 class="text-muted"><strong>Bên vận chuyển:</strong> ${order.shippingCompany.name}</h4>   
                <c:choose>
                    <c:when test="${order.paymentMethod eq 1}">
                        <h4 class="text-muted"><strong>Cách thức thanh toán:</strong>  Thanh toán khi nhận hàng</h4> 
                    </c:when>               
                    <c:otherwise>
                        <h4 class="text-muted"><strong>Cách thức thanh toán:</strong> Không xác định</h4> 
                    </c:otherwise>
                </c:choose>
                <h4 class="text-muted"><strong>Ghi chú:</strong> ${order.note}</h4> 
                <h4 class="text-muted"><strong>Địa chỉ khách hàng:</strong> ${order.getDeliveryAddress().showAddress()}</h4> 
            </div>
        </div>    
        <br>
        <br>
        <jsp:include page="view/customer/footer.jsp"></jsp:include>
    </body>

</html>
