<%-- 
    Document   : HearderSale
    Created on : Mar 12, 2024, 11:25:58 AM
    Author     : ifyou
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
                    <c:choose>
                        <c:when test="${not empty user}">
                            <li><a href="profile"><img
                                        src="ananas.vn/wp-content/themes/ananas/fe-assets/images/svg/icon_dang_nhap.svg"> ${user.name}</a></li>
                            <li><a href="logout"><img src="ananas.vn/wp-content/themes/ananas/fe-assets/images/svg/logout.svg">Đăng xuất</a></li>
                                </c:when>
                                <c:otherwise>
                            <li><a href="login"><img
                                        src="ananas.vn/wp-content/themes/ananas/fe-assets/images/svg/icon_dang_nhap.svg"> Đăng nhập</a></li>
                                </c:otherwise>
                            </c:choose> 
                </ul>
            </div>
        </div>
        </body>
</html>

