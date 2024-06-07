<%-- 
    Document   : ThankPage
    Created on : Mar 9, 2024, 7:11:49 PM
    Author     : ifyou
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Thông tin đơn hàng</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">

        <!-- Bootstrap  -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <!-- Flexslider  -->


        <style>
            @media (min-width: 1025px) {
                .h-custom {
                    height: 100vh !important;
                }
            }

            .card-registration .select-input.form-control[readonly]:not([disabled]) {
                font-size: 1rem;
                line-height: 2.15;
                padding-left: .75em;
                padding-right: .75em;
            }

            .card-registration .select-arrow {
                top: 13px;
            }

            .bg-grey {
                background-color: #eae8e8;
            }

            @media (min-width: 992px) {
                .card-registration-2 .bg-grey {
                    border-top-right-radius: 16px;
                    border-bottom-right-radius: 16px;
                }
            }

            @media (max-width: 991px) {
                .card-registration-2 .bg-grey {
                    border-bottom-left-radius: 16px;
                    border-bottom-right-radius: 16px;
                }
            }
        </style>
    </head>
    <body>
        <jsp:include page="view/Header and footer/HeaderCustomer.jsp"></jsp:include>
            <section class="h-100 h-custom" style="background-color: #FAFAFA;">
                <div class="container py-5 h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-12">
                            <div class="card card-registration card-registration-2" style="border-radius: 15px;">
                                <div class="card-body p-0">
                                    <div class="row g-0">
                                        <div class="col-lg-12">
                                            <div class="p-5">
                                                <div class="d-flex justify-content-between align-items-center mb-5">
                                                    <h1 class="fw-bold mb-0 text-black">Cảm ơn bạn đã thực hiện mua hàng tại Online Shoes Store</h1>

                                                </div>
                                                <div class="d-flex justify-content-between align-items-center mb-5">
                                                    <h3 class="text-muted">Chi tiết đơn hàng</h3> 
                                                    <h4 class="text-muted">Mã đơn hàng: <strong>${newOrder.getOrderCode()}</strong></h4>   
                                            </div>
                                            <div class="justify-content-between align-items-center mb-5">
                                                <h5 class="text-muted">Người nhận hàng: ${newOrder.getCustomer().getName()}</strong></h5>   
                                                <h5 class="text-muted">Số điện thoại: ${newOrder.getCustomer().getPhone()}</strong></h5> 
                                                <h5 class="text-muted">Địa chỉ nhận hàng: ${newOrder.getDeliveryAddress().showAddress()}</strong></h5> 
                                                <h5 class="text-muted">Ngày đặt hàng: <fmt:formatDate value="${newOrder.getOrderDate()}" pattern="dd/MM/yyyy" /> </strong></h5> 
                                            </div>
                                            <table class="table table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>Tên sản phẩm</th>
                                                        <th>Màu sắc</th>
                                                        <th>Size</th>
                                                        <th>Số lượng</th>
                                                        <th>Giá tiền</th>
                                                        <th>Thành tiền</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${orderDetails}" var="c">
                                                        <tr>
                                                            <td>${c.getProductDetail().getProduct().getName()}</td>
                                                            <td>${c.getProductDetail().getColor().getName()}</td>
                                                            <td>${c.getProductDetail().getSize().getName()}</td>
                                                            <td>${c.getQuantity()}</td>
                                                            <td><fmt:formatNumber value="${c.getProductDetail().getProduct().getPrice()}" type="currency" currencyCode="VND" groupingUsed="true" pattern="###,###" /></td>
                                                            <td><fmt:formatNumber value="${c.getProductDetail().getProduct().getPrice() * c.getQuantity()}" type="currency" currencyCode="VND" groupingUsed="true" pattern="###,###" /></td>
                                                        </tr>
                                                        
                                                    </c:forEach>
                                                    <tr>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td>Tổng Cộng: 
                                                            <fmt:formatNumber value="${totalAmount}" type="currency" currencyCode="VND" groupingUsed="true" pattern="###,###" />
                                                            VND</td>
                                                </tbody>
                                            </table>
                                            <div class="pt-5">
                                                <h6 class="mb-0"><a href="CustomerProducts" ><i
                                                            class="fa fa-long-arrow-left me-2"></i>Về trang chủ</a></h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="js/jquery.min.js"></script>
        <!-- popper -->
        <script src="js/popper.min.js"></script>
        <!-- bootstrap 4.1 -->
        <script src="js/bootstrap.min.js"></script>
        <!-- jQuery easing -->
        <script src="js/jquery.easing.1.3.js"></script>
        <!-- Waypoints -->
        <script src="js/jquery.waypoints.min.js"></script>
        <!-- Flexslider -->
        <script src="js/jquery.flexslider-min.js"></script>
        <!-- Owl carousel -->
        <script src="js/owl.carousel.min.js"></script>
        <!-- Magnific Popup -->
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/magnific-popup-options.js"></script>
        <!-- Date Picker -->
        <script src="js/bootstrap-datepicker.js"></script>
        <!-- Stellar Parallax -->
        <script src="js/jquery.stellar.min.js"></script>
        <!-- Main -->
        <script src="js/main.js"></script>

    </body>
    <jsp:include page="view/customer/footer.jsp"></jsp:include>
</html>
