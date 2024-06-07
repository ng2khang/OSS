<%-- 
    Document   : cart
    Created on : Mar 4, 2024, 7:44:24 AM
    Author     : ifyou
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Giỏ hàng</title>
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
                                        <div class="col-lg-8">
                                            <div class="p-5">
                                                <div class="d-flex justify-content-between align-items-center mb-5">
                                                    <h1 class="fw-bold mb-0 text-black">Giỏ hàng</h1>
                                                </div>
                                            <c:forEach items="${cartItems}" var="c">
                                                <hr class="my-4">

                                                <div class="row mb-4 d-flex justify-content-between align-items-center">
                                                    <div class="col-md-2 col-lg-2 col-xl-2">
                                                        <img
                                                            src="images/${c.productDetail.image_url_1}"
                                                            class="img-fluid rounded-3" alt="Cotton T-shirt">
                                                    </div>
                                                    <div class="col-md-3 col-lg-3 col-xl-3">
                                                        <h6 class="text-muted">${c.productDetail.product.name}</h6>
                                                        <h6 class="text-black mb-0">Size: ${c.productDetail.size.name}</h6>
                                                        <h6 class="text-black mb-0">Màu sắc: ${c.productDetail.color.name}</h6>
                                                        <h6 class="text-black mb-0">Số lượng còn lại: ${c.productDetail.inventory_number}</h6>
                                                    </div>
                                                    <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                                                        <c:choose>
                                                            <c:when test="${c.quantity == 1}">
                                                                <a class="btn btn-link px-2" href="ReduceQuantityCartItem?id=${c.id}" style="visibility: hidden;">
                                                                    <i class="fa fa-minus" style="margin-top: 28px"></i>
                                                                </a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a class="btn btn-link px-2" href="ReduceQuantityCartItem?id=${c.id}">
                                                                    <i class="fa fa-minus" style="margin-top: 28px"></i>
                                                                </a>
                                                            </c:otherwise>
                                                        </c:choose>  

                                                        <input id="form1" min="1" name="quantity" value="${c.quantity}" type="number" style="width:75px; margin-top: 20px; background-color: white"
                                                               class="form-control form-control-sm" readonly />

                                                        <c:choose>
                                                            <c:when test="${c.quantity == c.productDetail.inventory_number}">
                                                                <a class="btn btn-link px-2" href="AddQuantityCartNumber?id=${c.id}" style="visibility: hidden;">
                                                                    <i class="fa fa-plus" style="margin-top: 28px"></i>
                                                                </a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a class="btn btn-link px-2" href="AddQuantityCartNumber?id=${c.id}">
                                                                    <i class="fa fa-plus" style="margin-top: 28px"></i>
                                                                </a>
                                                            </c:otherwise>
                                                        </c:choose>  

                                                    </div>
                                                    <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                                        <h6 class="mb-0">Giá Tiền(VND)</h6>
                                                        <h6 class="mb-0">
                                                            <fmt:formatNumber value="${c.productDetail.product.price}" type="currency" currencyCode="VND" groupingUsed="true" pattern="###,###" />
                                                        </h6>
                                                    </div>

                                                    <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                                        <a href="RemoveItemServlet?id=${c.id}" class="text-muted"><i class="fa fa-times"></i></a>
                                                    </div>
                                                    <c:choose>
                                                        <c:when test="${c.isSelected == 1}">
                                                            <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                                                <a href="RemoveSelectCartItem?id=${c.id}" class="text-muted"><input type="checkbox" checked></a>
                                                            </div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                                                <a href="SelectCartItem?id=${c.id}" class="text-muted"><input type="checkbox"></a>
                                                            </div>
                                                        </c:otherwise>
                                                    </c:choose>  

                                                </div>

                                            </c:forEach>
                                            <hr class="my-4">
                                            <h6 class="mb-0">Tổng tiền: <fmt:formatNumber value="${sumPrice}" type="currency" currencyCode="VND" groupingUsed="true" pattern="###,###" /> VND</h6>


                                            <div class="pt-5">
                                                <h6 class="mb-0"><a href="#!" ><i
                                                            class="fa fa-long-arrow-left me-2"></i>Về trang chủ</a></h6>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 bg-grey">
                                        <div class="p-5">
                                            <div class="d-flex justify-content-between mb-5">
                                                <h3 class="text-uppercase">Tổng Cộng</h3>
                                                <h5>
                                                    <fmt:formatNumber value="${checkoutPrice}" type="currency" currencyCode="VND" groupingUsed="true" pattern="###,###" /> VND
                                                </h5>
                                            </div>
                                            <hr class="my-4">



                                            <h5 class="text-uppercase mb-3">Phí vận chuyển</h5>
                                            <h6 class="mb-0">Miễn phí</h6>



                                            <hr class="my-4">


                                            <a href="CheckoutController">
                                                <button type="button" class="btn btn-dark btn-block btn-lg"
                                                        data-mdb-ripple-color="dark">Đặt hàng</button>
                                            </a>
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


