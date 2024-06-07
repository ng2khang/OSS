<%-- 
    Document   : PDetailCustomer
    Created on : Mar 16, 2024, 9:47:32 AM
    Author     : lucdu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel='stylesheet' id='fe-bootstrap-css-css'  href='ananas.vn/wp-content/themes/ananas/fe-assets/css/bootstrap.min261b.css' type='text/css' media='all' />
        <link rel='stylesheet' id='fe-ie10-viewport-bug-workaround-css-css'  href='ananas.vn/wp-content/themes/ananas/fe-assets/css/ie10-viewport-bug-workaround261b.css' type='text/css' media='all' />
        <link rel='stylesheet' id='fe-jquery-simple-mobilemenu-css'  href='ananas.vn/wp-content/themes/ananas/fe-assets/css/jquery-simple-mobilemenu261b.css' type='text/css' media='all' />
        <link rel='stylesheet' id='fe-slick-css'  href='ananas.vn/wp-content/themes/ananas/fe-assets/css/slick261b.css' type='text/css' media='all' />
        <link rel='stylesheet' id='fe-slick-theme-css'  href='ananas.vn/wp-content/themes/ananas/fe-assets/css/slick-theme261b.css' type='text/css' media='all' />
        <link rel='stylesheet' id='fe-bootstrap-select-css'  href='ananas.vn/wp-content/themes/ananas/fe-assets/css/bootstrap-select.min261b.css' type='text/css' media='all' />
        <link rel='stylesheet' id='fe-jBox-css'  href='ananas.vn/wp-content/themes/ananas/fe-assets/css/jBox261b.css' type='text/css' media='all' />
        <link rel='stylesheet' id='fe-style-css'  href='ananas.vn/wp-content/themes/ananas/fe-assets/css/style261b.css' type='text/css' media='all' />
        <link rel='stylesheet' id='ananas-style-css'  href='ananas.vn/wp-content/themes/ananas/style5fba.css' type='text/css' media='all' />
        <style type="text/css">
            img.wp-smiley,
            img.emoji {
                display: inline !important;
                border: none !important;
                box-shadow: none !important;
                height: 1em !important;
                width: 1em !important;
                margin: 0 .07em !important;
                vertical-align: -0.1em !important;
                background: none !important;
                padding: 0 !important;
            }
        </style>
        <style>
            .hidden {
    display: none;
}

            .color {
                display: flex; /* Sử dụng flexbox */
                justify-content: flex-start; /* Căn trái */
                align-items: center; /* Căn dọc giữa */
            }

            .nav.tree {
                display: flex; /* Sử dụng flexbox */
                list-style-type: none; /* Loại bỏ các dấu hiệu danh sách */
                padding: 0; /* Loại bỏ padding mặc định */
                margin: 0; /* Loại bỏ margin mặc định */
            }

            .cb-color-fixed {
                margin-right: 10px; /* Khoảng cách giữa các mục */
            }

            .bg-color {
                width: 20px; /* Độ rộng của mỗi màu */
                height: 20px; /* Chiều cao của mỗi màu */
                display: inline-block; /* Hiển thị là khối */
                margin-right: 5px; /* Khoảng cách giữa mỗi màu */
            }
            /* CSS cho phần size và số lượng */
            .selectpicker {
                width: calc(50% - 10px); /* Đặt chiều rộng của selectpicker là 50% của container cộng với khoảng cách giữa các cột */
                margin-right: 10px; /* Khoảng cách giữa phần size và số lượng */
            }

            .grp-btn1 {
                margin-top: 10px; /* Khoảng cách giữa phần số lượng và button Thêm vào giỏ hàng */
            }

        </style>
        <script>
            // Định nghĩa JavaScript
            function updateURLSize(selectElement) {
                var productId = document.getElementById('productID').value;
                var colorId = getUrlParameter('colorId');
                var sizeId = selectElement.value;
                var newURL = 'CustomerProductss?productId=' + productId + '&colorId=' + colorId + '&sizeId=' + sizeId;
                window.location.href = newURL;
            }

            function getUrlParameter(name) {
                name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
                var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
                var results = regex.exec(location.search);
                return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
            }
        </script>
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
        <div class="prd-detail container-fluid">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-7 col-lg-7">
    <div class="wrapper-slide">
        <div class="prd-detail-main-img">
            <img class="main-img" id="mainImage" src="images/${pd.getImage_url_1()}">
        </div>
        <div class="prd-detail-slide1 slick-initialized slick-slider" id="thumbnailSlider">
  
            <div class="slick-list draggable">
                <div class="slick-track" style="opacity: 1; width: 1600px; transform: translate3d(0px, 0px, 0px);">
                    <div class="slick-slide slick-current slick-active" data-slick-index="0" aria-hidden="false" style="width: 160px;">
                        <div>
                            <div class="thumbnail" style="width: 100%; display: inline-block;">
                                <div class="cont-item">
                                    <img src="images/${pd.getImage_url_1()}" onclick="changeMainImage('images/${pd.getImage_url_1()}')">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="slick-slide slick-active" data-slick-index="1" aria-hidden="false" style="width: 160px;">
                        <div>
                            <div class="thumbnail" style="width: 100%; display: inline-block;">
                                <div class="cont-item">
                                    <img src="images/${pd.getImage_url_2()}" onclick="changeMainImage('images/${pd.getImage_url_2()}')">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="slick-slide slick-active" data-slick-index="2" aria-hidden="false" style="width: 160px;">
                        <div>
                            <div class="thumbnail" style="width: 100%; display: inline-block;">
                                <div class="cont-item">
                                    <img src="images/${pd.getImage_url_3()}" onclick="changeMainImage('images/${pd.getImage_url_3()}')">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="slick-slide slick-active" data-slick-index="3" aria-hidden="false" style="width: 160px;">
                        <div>
                            <div class="thumbnail" style="width: 100%; display: inline-block;">
                                <div class="cont-item">
                                    <img src="images/${pd.getImage_url_4()}" onclick="changeMainImage('images/${pd.getImage_url_4()}')">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
         
        </div>
    </div>
    <div class="row prd-detail-img hidden-xs hidden-sm"></div>
</div>

                <form action="AddToCartServlet">
                    <div class="col-xs-12 col-sm-12 col-md-5 col-lg-5 prd-detail-right">
                        <input type="hidden" name="id" value="${pd.id}">
                        <h4>${pd.getProduct().getName()}</h4>
                        <input type="hidden" id="productID" value="${pd.getProduct().getId()}">
                        <h6 class="detail1">
                            <input type="hidden" id="productId" value="25705">
                            <span class="pull-left">Mã sản phẩm: <strong>${pd.getProduct().getCode()}</strong></span>
                            <span class="pull-right">Tình trạng: <strong>${pd.getProduct().getGroup().getName()}</strong></span>
                        </h6>

                        <h5 class="detail1">
                            <span class="saleprice"><fmt:formatNumber type="currency" currencySymbol="VND" value="${pd.getProduct().getPrice()}" maxFractionDigits="0" /></span>
                            <!--                        <span class="realprice">580.000 VND</span>-->

                        </h5>
                        <div class="divider"></div>
                        <h6 class="detail1">${pd.getProduct().getForm().getDescription()}</h6>
                        <div class="divider"></div>
                        <div class="color">
                            <ul class="nav tree">
                                <c:set var="previousColor" value="" />
                                <c:forEach var="c" items="${cls}">
                                    <c:if test="${c.color_code ne previousColor}">
                                        <li class="cb-color-fixed">
                                            <label data-link>
                                                <span class="bg-color" style="background-color: ${c.color_code};"
                                                      onclick="updateURL(${c.getId()})"></span>
                                            </label>
                                        </li>
                                        <c:set var="previousColor" value="${c.color_code}" />
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="divider"></div>

                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <h5>SIZE</h5>
                                <select class="selectpicker" onchange="updateURLSize(this)">
                                    <option value="0">Vui lòng chọn</option>
                                    <c:forEach var="sz" items="${si}">
                                        <option value="${sz.getId()}">${sz.getName()}</option>
                                    </c:forEach>
                                </select>


                            </div>
                        </div>
                        <div class="row grp-btn1">
                            <button class="btn btn-addcart" type="submit" style="width: 480px">THÊM VÀO GIỎ HÀNG</button>
                        </div>
                        <% String message = (String) session.getAttribute("addToCartSuccess"); %>
                        <% if(message != null){ %>
                        <% session.removeAttribute("addToCartSuccess"); %>
                        <br>
                        <div style="color:green !important"><%= message %></div>
                        <% } %>
                        <div class="row info-validate empty-error" style="display: none;">
                            Vui lòng chọn Size
                        </div>

                        <div class="panel-group" id="prdDetailInfor" role="tablist" aria-multiselectable="true">
    <div class="panel panel-default">
        <div class="panel-heading" role="tab" id="headingOne">
            <h4 class="panel-title">
                <a role="button" data-toggle="collapse" data-parent="#prdDetailInfor"
                   href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" onclick="toggleProductDescription()">
                    THÔNG TIN SẢN PHẨM
                </a>
            </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
             aria-labelledby="headingOne">
            <div class="divider-1"></div>
            <div class="panel-body" id="productDescription">
                <h6><p>${pd.getProduct().getDescription()}</p></h6>
                <div class="divider-1"></div>
            </div>
        </div>
        
    </div>
</div>

                    </div>
                </form>
            </div>
        </div>
        <script>
            function updateURL(colorId) {
// Lấy productId từ trường ẩn
                var productId = document.getElementById('productID').value;

// Cập nhật URL với colorId và productId mới
                var newURL = 'CustomerProducts?productId=' + productId + '&colorId=' + colorId;

// Chuyển hướng đến URL mới
                window.location.href = newURL;
            }
        </script>
        <script>
            window.onload = function () {
                // Lấy sizeId từ tham số truy vấn của URL
                var sizeId = getUrlParameter('sizeId');

                // Nếu có sizeId trong URL, cập nhật option tương ứng
                if (sizeId !== '') {
                    var select = document.querySelector('.selectpicker');
                    var options = select.querySelectorAll('option');
                    for (var i = 0; i < options.length; i++) {
                        var option = options[i];
                        if (option.value === sizeId) {
                            option.selected = true;
                            break;
                        }
                    }
                }
            };

            function getUrlParameter(name) {
                name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
                var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
                var results = regex.exec(location.search);
                return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
            }
        </script>
        <script>
            // Định nghĩa hàm để thay đổi ảnh lớn
function changeMainImage(imageUrl) {
    var mainImage = document.querySelector('.main-img');
    mainImage.src = imageUrl;
}

// Gắn sự kiện cho các button và ảnh nhỏ
document.addEventListener('DOMContentLoaded', function() {
    var thumbnailImages = document.querySelectorAll('.cont-item img');
    thumbnailImages.forEach(function(thumbnailImage) {
        thumbnailImage.addEventListener('click', function() {
            var imageUrl = this.src;
            changeMainImage(imageUrl);
        });
    });
});

        </script>
        <script>
            function toggleProductDescription() {
    var descriptionElement = document.getElementById("productDescription");
    if (descriptionElement.classList.contains("hidden")) {
        descriptionElement.classList.remove("hidden");
    } else {
        descriptionElement.classList.add("hidden");
    }
}

        </script>
    </body>
</html>
