<%-- 
    Document   : ProductDetail
    Created on : Mar 10, 2024, 5:19:38 PM
    Author     : lucdu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chi tiết sản phẩm</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <style>
            /* Thi?t l?p font ch? và margin */
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }

            /* Canh gi?a tiêu ?? s?n ph?m */
            .pro-d-title {
                text-align: center;
                margin-bottom: 20px;
            }

            /* Thi?t l?p kích th??c và padding cho các c?t */
            .col-md-6 {
                padding: 0 15px;
            }

            /* Canh gi?a hình ?nh chính */
            .pro-img-details {
                margin-bottom: 20px;
                text-align: center;
                overflow: hidden; /* ?n ph?n th?a */
                position: relative; /* Cho phép ??nh v? tuy?t ??i cho các ph?n t? con */
            }

            /* Kích th??c và canh gi?a ?nh chính */
            .pro-img-details img {
                width: 100%;
                height: 100%;
                object-fit: cover; /* Zoom ra v?a v?i kích th??c c? ??nh */
            }

            /* M?i tên chuy?n ??i ?nh */
            .arrow {
                position: absolute;
                top: 50%;
                transform: translateY(-50%);
                background: rgba(255, 255, 255, 0.5); /* Màu n?n ??c */
                color: #000; /* Màu ch? */
                padding: 10px;
                cursor: pointer;
                font-size: 1.5em;
                border: none;
                outline: none;
                transition: background 0.3s, color 0.3s;
            }

            /* M?i tên chuy?n ??i ?nh: Hover */
            .arrow:hover {
                background: rgba(0, 0, 0, 0.5); /* Màu n?n ??c khi hover */
                color: #fff; /* Màu ch? khi hover */
            }

            /* M?i tên chuy?n ??i ?nh bên trái */
            .arrow.left {
                left: 0;
            }

            /* M?i tên chuy?n ??i ?nh bên ph?i */
            .arrow.right {
                right: 0;
            }

            /* Canh gi?a danh sách các ?nh nh? */
            .pro-img-list {
                display: flex;
                justify-content: center;
                flex-wrap: wrap;
                margin-bottom: 20px;
            }

            /* Kích th??c và canh gi?a các ?nh nh? */
            .pro-img-list a {
                display: block;
                margin: 0 10px 10px 0;
                width: 100px; /* Kích th??c c? ??nh */
                height: 100px; /* Kích th??c c? ??nh */
                overflow: hidden; /* ?n ph?n th?a */
            }

            /* Kích th??c và canh gi?a các ?nh nh? */
            .pro-img-list img {
                width: 100%; /* Kích th??c t? ??ng */
                height: 100%; /* Kích th??c t? ??ng */
                object-fit: cover; /* Zoom ra v?a v?i kích th??c c? ??nh */
            }

            /* Button Add to Cart */
            .btn-round {
                border-radius: 20px;
            }

            /* Thi?t l?p kích th??c input quantity */
            .form-group input {
                width: 50px;
                padding: 5px;
                text-align: center;
            }

            /* CSS ?? ?n/m? r?ng ph?n mô t? s?n ph?m */
            .product-description {
                display: none; /* ?n m?c ??nh */
            }

            /* Thi?t l?p các nút t?ng/gi?m s? l??ng s?n ph?m */
            .quantity-controls {
                display: flex;
                align-items: center;
                margin-top: 10px;
            }

            .quantity-controls button {
                background: none;
                border: none;
                cursor: pointer;
                font-size: 1.2em;
            }

            .quantity-input {
                width: 50px;
                padding: 5px;
                text-align: center;
            }

            /* Thi?t l?p margin gi?a các c?t */
            .column-margin {
                margin-bottom: 20px;
            }

            /* Thi?t l?p hi?n th? hàng ngang */
            .horizontal-line {
                border-top: 1px solid #ccc;
                margin-top: 20px;
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>

        <div class="container">
            
            <div class="row">

                <div class="col-md-6 column-margin">
                    <div class="pro-img-details">
                        <div class="arrow left" onclick="prevImage()">&#10094;</div>
                        <img id="mainImage" src="images/${pd.getImage_url_1()}" alt="ảnh">
                        <div class="arrow right" onclick="nextImage()">&#10095;</div>
                    </div>
                    <div class="pro-img-list">
                        <a onclick="changeImage('images/${pd.getImage_url_1()}')">
                            <img src="images/${pd.getImage_url_1()}" alt="ảnh 1">
                        </a>
                        <a onclick="changeImage('images/${pd.getImage_url_2()}')">
                            <img src="images/${pd.getImage_url_2()}" alt="ảnh 2">
                        </a>
                        <a onclick="changeImage('images/${pd.getImage_url_3()}')">
                            <img src="images/${pd.getImage_url_3()}" alt="ảnh 3">
                        </a>
                        <a onclick="changeImage('images/${pd.getImage_url_4()}')">
                            <img src="images/${pd.getImage_url_4()}" alt="ảnh 4">
                        </a>
                    </div>
                </div>
                <div class="col-md-6 column-margin">
                    <h2 class="pro-d-title">${pd.getProduct().getName()}</h2>
                    <p>
                        <strong>Mã sản phẩm: </strong>${pd.getProduct().getCode()}<br>
                        <strong>Tình trạng: </strong>${pd.getProduct().getGroup().getName()}<br>
                        <strong>Kích thước: </strong>${pd.getSize().getName()}<br>
                        <strong>Còn: </strong>${pd.getInventory_number()} <span>sản phẩm.</span><br>
                        <strong>Giá tiền: </strong>${pd.getProduct().getPrice()}<br>
                        <strong>Màu Sắc: </strong>
                    <div style="display: inline-block;
                         width: 20px;
                         height: 20px;
                         background-color: ${pd.getColor().getColor_code()};
                         margin-right: 5px;
                         border: 1px solid black"></div>
                    </p>
                    <form action="AddToCartServlet">
                        <input type="hidden" name="id" value="${pd.id}">
                        <div class="quantity-controls">
                            <button onclick="decreaseQuantity()" type="button">-</button>
                            <input type="number" class="quantity-input" id="quantity" value="1" name="quantity">
                            <button onclick="increaseQuantity()" type="button">+</button>
                        </div>
                        <p>
                            <button class="btn btn-round btn-danger" type="submit"><i class="fa fa-shopping-cart"></i> Thêm vào giỏ hàng</button>
                        </p>
                        <% String message = (String) session.getAttribute("addToCartSuccess"); %>
                        <% if(message != null){ %>
                        <% session.removeAttribute("addToCartSuccess"); %>
                        <div id="snackbar" style="color:green"><%= message %></div>
                        <% } %>
                        
                        <% String message1 = (String) session.getAttribute("itemIsExist"); %>
                        <% if(message1 != null){ %>
                        <% session.removeAttribute("itemIsExist"); %>
                        <div id="snackbar" style="color:red"><%= message1 %></div>
                        <% } %>
                    </form>

                    <strong class="toggle-btn" onclick="toggleDescription()">Thông tin sản phẩm</strong>
                    <div class="product-description">
                        ${pd.getProduct().getDescription()}
                    </div>
                </div>
            </div>
            <div class="row horizontal-line"></div>
            <div class="row">
                <div class="col-md-6 offset-md-3 column-margin">
                    <!-- ??t n?i dung d??i ?ây cho ph?n ?nh nh? -->
                </div>
            </div>
        </div>

        <script>
            // Bi?n l?u tr? ???ng d?n c?a hình ?nh hi?n t?i
            var currentImageUrl = 'images/${pd.getImage_url_1()}';
            var imageIndex = 0;
            var imageUrls = [
                'images/${pd.getImage_url_1()}',
                'images/${pd.getImage_url_2()}',
                'images/${pd.getImage_url_3()}',
                'images/${pd.getImage_url_4()}'
            ];

            // Function ?? thay ??i hình ?nh chính
            function changeImage(imageUrl) {
                document.getElementById('mainImage').src = imageUrl;
                currentImageUrl = imageUrl; // C?p nh?t giá tr? c?a bi?n ???ng d?n hi?n t?i
            }

            // Function ?? chuy?n ??n ?nh tr??c ?ó
            function prevImage() {
                imageIndex = (imageIndex - 1 + imageUrls.length) % imageUrls.length;
                changeImage(imageUrls[imageIndex]);
            }

            // Function ?? chuy?n ??n ?nh k? ti?p
            function nextImage() {
                imageIndex = (imageIndex + 1) % imageUrls.length;
                changeImage(imageUrls[imageIndex]);
            }

            // Thi?t l?p hình ?nh ??u tiên là hình ?nh m?c ??nh
            window.onload = function () {
                document.getElementById('mainImage').src = currentImageUrl;
            };
        </script>
        <script>
            // JavaScript ?? toggle hi?n th? mô t? s?n ph?m
            function toggleDescription() {
                var description = document.querySelector('.product-description');
                if (description.style.display === 'none') {
                    description.style.display = 'block'; // Hi?n th? khi ?n
                } else {
                    description.style.display = 'none'; // ?n khi hi?n th?
                }
            }

            // JavaScript ?? t?ng gi?m s? l??ng s?n ph?m
            function increaseQuantity() {
                var quantityInput = document.getElementById('quantity');
                quantityInput.value = parseInt(quantityInput.value) + 1;
            }

            function decreaseQuantity() {
                var quantityInput = document.getElementById('quantity');
                if (parseInt(quantityInput.value) > 1) {
                    quantityInput.value = parseInt(quantityInput.value) - 1;
                }
            }
        </script>
    </body>
</html>

