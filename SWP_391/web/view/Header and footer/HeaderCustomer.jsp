<%-- 
    Document   : HeaderCustomer
    Created on : Mar 11, 2024, 11:33:28 AM
    Author     : lucdu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="ananas.vn/wp-content/themes/ananas/fe-assets/images/favicon.ico">
        <title>Vintas Mister - Low Top</title>
        <link rel="stylesheet" id="fe-bootstrap-css-css" href=ananas.vn/wp-content/themes/ananas/fe-assets/css/bootstrap.min261b.css?ver=20233105" type="text/css" media="all">


        <link rel="stylesheet" id="fe-ie10-viewport-bug-workaround-css-css" href="ananas.vn/wp-content/themes/ananas/fe-assets/css/ie10-viewport-bug-workaround261b.css?ver=20233105" type="text/css" media="all">


        <link rel="stylesheet" id="fe-jQuery-simple-mobilemenu-css" href="ananas.vn/wp-content/themes/ananas/fe-assets/css/jquery-simple-mobilemenu261b.css?ver=20233105" type="text/css" media="all">

        <link rel="stylesheet" id="fe-slick-css" href="ananas.vn/wp-content/themes/ananas/fe-assets/css/slick261b.css?ver=20233105" type="text/css" media="all">

        <link rel="stylesheet" id="fe-slick-theme-css" href=ananas.vn/wp-content/themes/ananas/fe-assets/css/slick-theme261b.css?ver=20233105" type="text/css" media="all">

        <link rel="stylesheet" id="fe-bootstrap-select-css" href="ananas.vn/wp-content/themes/ananas/fe-assets/css/bootstrap-select.min261b.css?ver=20233105" type="text/css" media="all">

        <link rel="stylesheet" id="fe-jBox-css" href="ananas.vn/wp-content/themes/ananas/fe-assets/css/jBox261b.css?ver=20233105" type="text/css" media="all">

        <link rel="stylesheet" id="fe-style-css" href="ananas.vn/wp-content/themes/ananas/fe-assets/css/style261b.css?ver=20233105" type="text/css" media="all">

        <link rel="stylesheet" id="ananas-style-css" href="ananas.vn/wp-content/themes/ananas/style5fba.css?ver=5.2" type="text/css" media="all">

        </head>
    <body>

        <div class="header container-fluid hidden-xs hidden-sm">
            <div class="row">
                <ul class="menu">
                    <li><a href="SearchOrder.jsp"><img src="ananas.vn/wp-content/themes/ananas/fe-assets/images/svg/icon_tra_cuu_don_hang.svg"> Tra cứu đơn hàng</a></li>
                            <c:choose>
                                <c:when test="${not empty user}">
                            <li><a href="profile"><img
                                        src="ananas.vn/wp-content/themes/ananas/fe-assets/images/svg/icon_dang_nhap.svg"> ${user.name}</a></li>
                            <li><a href="CartController"><img src="ananas.vn/wp-content/themes/ananas/fe-assets/images/svg/icon_gio_hang.svg">Giỏ hàng (<span class="countProduct">${total}</span>)</a></li>
                            <li><a href="logout"><img src="ananas.vn/wp-content/themes/ananas/fe-assets/images/svg/logout.svg">Đăng xuất</a></li>
                                </c:when>
                                <c:otherwise>
                            <li><a href="login"><img
                                        src="ananas.vn/wp-content/themes/ananas/fe-assets/images/svg/icon_dang_nhap.svg"> Đăng nhập</a></li>
                                </c:otherwise>
                            </c:choose> 
                </ul>
            </div>
            <div class="row d-flex justify-content-center align-items-center">
                <div class="navbar center" >

                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li class="dropdown mt-0">
                                <a href="CustomerProducts" class="py-0 d-flex align-items-center">TRANG CHỦ</a>
                            </li>

                            <li class="line" style="margin-top:28px"></li>
                            <li class="dropdown mt-0">
                                <a href="CustomerProducts" class="py-0 d-flex align-items-center">SẢN PHẨM MỚI</a>
                            </li>

                            <li class="line" style="margin-top:28px"></li>
                            <li class="dropdown mt-0">
                                <a href="CustomerProducts" class="py-0 d-flex align-items-center">BÁN CHẠY</a>

                            </li>
                        </ul>
                        <form action="" class="navbar-form navbar-right">
                            <form action="CustomerProducts" method="get" class="navbar-form navbar-right">
                            <div class="form-group">
                                <img class="icon-search" src="ananas.vn/wp-content/themes/ananas/fe-assets/images/svg/icon_tim_kiem.svg">
                                <input type="hidden" name="filter" value="search">
                                <input style="
                                       margin-bottom: 0;
                                       border: #ffc221 solid 2px !important;
                                       " type="text" name="id" class="form-control" value="" placeholder="Tìm kiếm" onkeydown="if (event.keyCode == 13)
                                            this.form.submit();">
                            </div>
                        </form>
                        </form>
                    </div>
                </div>
            </div>

        </div>
        </body>
</html>
