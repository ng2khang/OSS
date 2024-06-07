<%-- 
    Document   : ListOrder
    Created on : Mar 9, 2024, 10:23:56 PM
    Author     : ifyou
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý đơn hàng</title>
        <!-- Include Bootstrap stylesheet -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <style>
            .modal-title {
                color: #000 !important; /* Đặt màu chữ */
                font-size: 1.25rem !important; /* Đặt font size */
            }


            td.description {
                max-width: 350px; /* Đặt độ rộng tối đa là 200px */
            }
            /* Custom button style */
            .btn-custom {
                padding: 8px 16px;
                font-size: 14px;
            }

            /* Remove blue background from button */
            .btn-custom:focus, .btn-custom.focus {
                box-shadow: none !important;
                /* Style for table */
                table {
                    width: 100%;
                    border-collapse: collapse;
                }

                th, td {
                    padding: 8px;
                    text-align: left;
                    border-bottom: 1px solid #ddd;
                }

                th {
                    background-color: #f2f2f2;
                }

                /* Style for modal dialogs */
                .modal-dialog {
                    max-width: 400px;
                }

                .modal-body {
                    padding: 20px;
                }

                .modal-footer {
                    padding: 10px 20px;
                }

                /* Custom button style */
                .btn-custom {
                    padding: 8px 16px;
                    font-size: 14px;
                }
            </style>
        </head>
        <body>
            <c:choose>
                <c:when test="${user.role == 2}">
                    <jsp:include page="../Header and footer/HeaderSale.jsp"></jsp:include>
                </c:when>

                <c:when test="${user.role == 3}">
                    <jsp:include page="../Header and footer/HeaderAdmin.jsp"></jsp:include>
                </c:when>
            </c:choose>

            <div class="row prd-detail-img hidden-xs hidden-sm"></div>

            <div class="container mt-5">
                <h1>Quản lý đơn hàng</h1>
                <div>
                    <!--                        <a href="products?mod=1" class="btn btn-primary mb-3"><i class="fas fa-plus"></i> Tạo mới</a></div>-->
                    <!--                    <div class="btn-group mb-3">
                                            <a href="products" class="btn btn-secondary">Tất cả sản phẩm</a>
                                            <a href="products?show=active" class="btn btn-success">Sản phẩm hoạt động</a>
                                            <a href="products?show=updated" class="btn btn-warning">Sản phẩm vừa cập nhật</a>
                                            <a href="products?show=deleted" class="btn btn-danger">Sản phẩm không hoạt động</a>
                                        </div>-->

                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th style="text-align: center;">Mã đơn hàng</th>
                                <th style="text-align: center;
                                    white-space: nowrap">Ngày đặt hàng</th>
                                    <th style="text-align: center;">Thông tin đơn hàng</th>
                                <th style="text-align: center;">Thông tin khách hàng</th>
                                <th style="text-align: center;">Trạng thái</th>
                                <th style="text-align: center;
                                    white-space: nowrap">Chức Năng</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Loop through the products and display their information -->
                                <c:forEach var="c" items="${orders}">
                                    <!-- Check if the product should be displayed based on the 'show' parameter -->
                                    <!-- Display all products -->
                                    <tr>
                                        <td style="width: 150px">${c.orderCode}</td>
                                        <td style="width: 150px"><fmt:formatDate value="${c.orderDate}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
                                        <td style="width: 250px">
                                            <c:forEach var="orderDetail" items="${c.orderDetail}">
                                                <strong>Thông tin sản phẩm:</strong> 
                                                <br>Tên sản phẩm: ${orderDetail.productDetail.product.name} 
                                                <br>Màu sắc: ${orderDetail.productDetail.color.name} 
                                                <br>Size:${orderDetail.productDetail.size.name}
                                                <br>Số lượng: ${orderDetail.quantity}
                                                <br>Đơn giá: <fmt:formatNumber value="${orderDetail.unitPrice}" type="currency" currencyCode="VND" groupingUsed="true" pattern="###,###" />
                                                <br>
                                            </c:forEach>
                                            <br>Tổng tiền: <fmt:formatNumber value="${c.total}" type="currency" currencyCode="VND" groupingUsed="true" pattern="###,###" />
                                        </td>
                                        <td class="description" style="width: 250px">
                                            <strong>Tên khách hàng:</strong> ${c.customer.name}<br>
                                            <strong>Địa chỉ giao hàng:</strong> ${c.deliveryAddress.showAddress()}<br>
                                            <strong>Số điện thoại:</strong>  ${c.customer.phone}<br>
                                            <strong>Email: </strong>${c.customer.email}<br>
                                        </td>
                                        <td style="width: 150px">
                                            <div>
                                                <c:choose>
                                                    <c:when test="${c.orderStatus == 1}">
                                                        <strong>Đang chờ xử lý</strong><br>
                                                    </c:when>
                                                    <c:when test="${c.orderStatus == 2}">
                                                        <strong>Đóng gói xong và đã giao cho Shipper</strong><br>
                                                    </c:when>
                                                    <c:when test="${c.orderStatus == 3}">
                                                        <strong>Shipper đã nhận đơn và đang giao hàng</strong><br>
                                                    </c:when>
                                                    <c:when test="${c.orderStatus == 4}">
                                                        <strong>Đơn hàng được giao thành công</strong><br>
                                                    </c:when>
                                                    <c:when test="${c.orderStatus == 5}">
                                                        <strong>Khách hàng không nhận hàng</strong><br>
                                                    </c:when>
                                                    <c:when test="${c.orderStatus == 6}">
                                                        <strong>Đơn hàng bị hủy</strong><br>
                                                    </c:when>
                                                </c:choose> 
                                            </div>
                                        </td>
                                        <td>
                                            <a href="EditOrderController?id=${c.id}" class="btn btn-info btn-sm"><i class="fas fa-edit"></i></a>

                                            <!--                                        <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDeleteModal">
                                                                                        <i class="fas fa-trash-alt"></i>
                                                                                    </button>-->

                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <jsp:include page="../Groups/footer.jsp"></jsp:include>
                    <!-- Include Bootstrap's JavaScript and Popper.js (optional) -->
                    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
                    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
                    <script>
                        $(document).ready(function () {
                            $('.view-description').click(function () {
                                var shortDescription = $(this).siblings('.short-description');
                                var fullDescription = $(this).siblings('.full-description');
                                var ellipsis = $(this).siblings('.ellipsis');
                                shortDescription.toggle();
                                fullDescription.toggle();
                                ellipsis.toggle();
                                if (shortDescription.is(":visible")) {
                                    ellipsis.show();
                                } else {
                                    ellipsis.hide();
                                }
                                $(this).html(function (i, html) {
                                    return html === '<i class="fas fa-eye"></i>' ? '<i class="fas fa-eye-slash"></i>' : '<i class="fas fa-eye"></i>';
                                });
                            });
                        });

                    </script>

            </body>
        </html>

