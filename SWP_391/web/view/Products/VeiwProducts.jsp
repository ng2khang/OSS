<%-- 
    Document   : VeiwProducts
    Created on : Feb 6, 2024, 10:40:47 AM
    Author     : lucdu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List sản phẩm</title>
        <!-- Include Bootstrap stylesheet -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <style>
            .modal-title {
                color: #000 !important; /* Đặt màu chữ */
                font-size: 1.25rem !important; /* Đặt font size */
            }
            /* Đảm bảo rằng các thuộc tính modal không bị ẩn bởi CSS chồng lên */
            .modal.fade.show {
                display: block !important;
                opacity: 1 !important;
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
            <jsp:include page="../Header and footer/HeaderSale.jsp"></jsp:include>
                <div class="row prd-detail-img hidden-xs hidden-sm"></div>
                <div class="container mt-5">

                    <h1>Quản Lý Sản Phẩm</h1>
                    <div><a href="products?mod=1" class="btn btn-primary mb-3"><i class="fas fa-plus"></i> Tạo mới</a></div>
                    <div class="btn-group mb-3">
                        <a href="products" class="btn btn-secondary">Tất cả sản phẩm</a>
                        <a href="products?show=active" class="btn btn-success">Sản phẩm hoạt động</a>
                        <a href="products?show=updated" class="btn btn-warning">Sản phẩm vừa cập nhật</a>
                        <a href="products?show=deleted" class="btn btn-danger">Sản phẩm không hoạt động</a>
                    </div>

                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th style="text-align: center;">NO</th>
                                <th style="text-align: center;
                                    white-space: nowrap">Mã Sản Phẩm</th>
                                    <th style="text-align: center;">Tên Sản Phẩm</th>
                                <th style="text-align: center;">Mô Tả</th>
                                <th style="text-align: center;">Thông Tin Sản Phẩm</th>
                                <th style="text-align: center;
                                    white-space: nowrap">Chức Năng</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Loop through the products and display their information -->
                            <c:forEach var="product" items="${data}">
                                <!-- Check if the product should be displayed based on the 'show' parameter -->
                                <c:choose>
                                    <c:when test="${empty param.show || param.show eq 'all'}">
                                        <!-- Display all products -->
                                        <tr>
                                            <td>${product.id}</td>
                                            <td>${product.code}</td>
                                            <td style="white-space: nowrap">${product.name}</td>
                                            <td class="description">
                                                <span class="short-description">${fn:substring(product.description, 0, 100)}</span>
                                                <span class="full-description" style="display: none;">${product.description}</span>
                                            <span class="ellipsis" style="display: none;">...</span>
                                            <button class="btn btn-sm view-description"><i class="fas fa-eye"></i></button>

                                        </td>


                                        <td>
                                            <div>
                                                <strong>Giá Tiền:</strong> <fmt:formatNumber type="currency" currencySymbol="₫" value="${product.price}" maxFractionDigits="0" /><br>
                                                <strong>Loại Giày:</strong> ${product.getCategory().getName()}<br>
                                                <strong>Kiểu Dáng:</strong> ${product.getForm().getName()}<br>
                                                <strong>Hãng Giày:</strong> ${product.getBrand().getName()}<br>
                                                <strong>Chất Liệu:</strong> ${product.getMaterial().getName()}<br>
                                                <strong>Trạng Thái Sản Phẩm:</strong>${product.getGroup().getName()}<br>
                                                <strong>Trạng Thái Hoạt Động:</strong> ${empty product.deleted_at ? 'Hoạt động' : 'Không hoạt động'}<br>
                                                <strong>Ngày Tạo:</strong> ${product.created_at}<br>
                                                <strong>Ngày Chỉnh Sửa:</strong> ${product.edited_at}<br>
                                                <strong>Ngày Xóa:</strong> ${product.deleted_at}<br>
                                            </div>
                                        </td>
                                        <td>
                                            <a href="products?mod=2&id=${product.getId()}" class="btn btn-info btn-sm"><i class="fas fa-edit"></i></a>

                                            <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDeleteModal${product.getId()}">
                                                <i class="fas fa-trash-alt"></i>
                                            </button>
                                            <div class="modal fade" id="confirmDeleteModal${product.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="confirmDeleteModalLabel">Xác nhận xóa</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            Bạn có muốn xóa sản phẩm này không ?
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                            <a href="products?mod=3&id=${product.getId()}" class="btn btn-danger">Xóa</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:when test="${param.show eq 'active' && empty product.deleted_at}">
                                    <!-- Display only active products -->
                                    <tr>
                                        <td>${product.id}</td>
                                        <td>${product.code}</td>
                                        <td>${product.name}</td>
                                        <td class="description">
                                            <span class="short-description">${fn:substring(product.description, 0, 100)}</span>
                                            <span class="full-description" style="display: none;">${product.description}</span>
                                            <span class="ellipsis" style="display: none;">...</span>
                                            <button class="btn btn-sm view-description"><i class="fas fa-eye"></i></button>

                                        </td>


                                        <td>
                                            <div>
                                                <strong>Giá Tiền:</strong> <fmt:formatNumber type="currency" currencySymbol="₫" value="${product.price}" maxFractionDigits="0" /><br>
                                                <strong>Loại Giày:</strong> ${product.getCategory().getName()}<br>
                                                <strong>Kiểu Dáng:</strong> ${product.getForm().getName()}<br>
                                                <strong>Hãng Giày:</strong> ${product.getBrand().getName()}<br>
                                                <strong>Chất Liệu:</strong> ${product.getMaterial().getName()}<br>
                                                <strong>Trạng Thái Sản Phẩm:</strong>${product.getGroup().getName()}<br>
                                                <strong>Trạng Thái Hoạt Động:</strong> ${empty product.deleted_at ? 'Hoạt động' : 'Không hoạt động'}<br>
                                                <strong>Ngày Tạo:</strong> ${product.created_at}<br>
                                                <strong>Ngày Chỉnh Sửa:</strong> ${product.edited_at}<br>
                                                <strong>Ngày Xóa:</strong> ${product.deleted_at}<br>
                                            </div>
                                        </td>
                                        <td>
                                            <a href="products?mod=2&id=${product.getId()}" class="btn btn-info btn-sm"><i class="fas fa-edit"></i></a>

                                            <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDeleteModal${product.getId()}">
                                                <i class="fas fa-trash-alt"></i>
                                            </button>
                                            <div class="modal fade" id="confirmDeleteModal${product.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="confirmDeleteModalLabel">Xác nhận xóa</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            Bạn có muốn xóa sản phẩm này không ?
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                            <a href="products?mod=3&id=${product.getId()}" class="btn btn-danger">Xóa</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:when test="${param.show eq 'updated' && not empty product.edited_at}">
                                    <!-- Display only updated products -->
                                    <tr>
                                        <td>${product.id}</td>
                                        <td>${product.code}</td>
                                        <td>${product.name}</td>
                                        <td class="description">
                                            <span class="short-description">${fn:substring(product.description, 0, 100)}</span>
                                            <span class="full-description" style="display: none;">${product.description}</span>
                                            <span class="ellipsis" style="display: none;">...</span>
                                            <button class="btn btn-sm view-description"><i class="fas fa-eye"></i></button>

                                        </td>


                                        <td>
                                            <div>
                                                <strong>Giá Tiền:</strong> <fmt:formatNumber type="currency" currencySymbol="₫" value="${product.price}" maxFractionDigits="0" /><br>
                                                <strong>Loại Giày:</strong> ${product.getCategory().getName()}<br>
                                                <strong>Kiểu Dáng:</strong> ${product.getForm().getName()}<br>
                                                <strong>Hãng Giày:</strong> ${product.getBrand().getName()}<br>
                                                <strong>Chất Liệu:</strong> ${product.getMaterial().getName()}<br>
                                                <strong>Trạng Thái Sản Phẩm:</strong>${product.getGroup().getName()}<br>
                                                <strong>Trạng Thái Hoạt Động:</strong> ${empty product.deleted_at ? 'Hoạt động' : 'Không hoạt động'}<br>
                                                <strong>Ngày Tạo:</strong> ${product.created_at}<br>
                                                <strong>Ngày Chỉnh Sửa:</strong> ${product.edited_at}<br>
                                                <strong>Ngày Xóa:</strong> ${product.deleted_at}<br>
                                            </div>
                                        </td>
                                        <td>
                                            <a href="products?mod=2&id=${product.getId()}" class="btn btn-info btn-sm"><i class="fas fa-edit"></i></a>

                                            <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDeleteModal${product.getId()}">
                                                <i class="fas fa-trash-alt"></i>
                                            </button>
                                            <div class="modal fade" id="confirmDeleteModal${product.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="confirmDeleteModalLabel">Xác nhận xóa</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            Bạn có muốn xóa sản phẩm này không ?
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                            <a href="products?mod=3&id=${product.getId()}" class="btn btn-danger">Xóa</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:when test="${param.show eq 'deleted' && not empty product.deleted_at}">
                                    <!-- Display only deleted products -->
                                    <tr>
                                        <td>${product.id}</td>
                                        <td>${product.code}</td>
                                        <td>${product.name}</td>
                                        <td class="description">
                                            <span class="short-description">${fn:substring(product.description, 0, 100)}</span>
                                            <span class="full-description" style="display: none;">${product.description}</span>
                                            <span class="ellipsis" style="display: none;">...</span>
                                            <button class="btn btn-sm view-description"><i class="fas fa-eye"></i></button>

                                        </td>


                                        <td>
                                            <div>
                                                <strong>Giá Tiền:</strong> <fmt:formatNumber type="currency" currencySymbol="₫" value="${product.price}" maxFractionDigits="0" /><br>
                                                <strong>Loại Giày:</strong> ${product.getCategory().getName()}<br>
                                                <strong>Kiểu Dáng:</strong> ${product.getForm().getName()}<br>
                                                <strong>Hãng Giày:</strong> ${product.getBrand().getName()}<br>
                                                <strong>Chất Liệu:</strong> ${product.getMaterial().getName()}<br>
                                                <strong>Trạng Thái Sản Phẩm:</strong>${product.getGroup().getName()}<br>
                                                <strong>Trạng Thái Hoạt Động:</strong> ${empty product.deleted_at ? 'Hoạt động' : 'Không hoạt động'}<br>
                                                <strong>Ngày Tạo:</strong> ${product.created_at}<br>
                                                <strong>Ngày Chỉnh Sửa:</strong> ${product.edited_at}<br>
                                                <strong>Ngày Xóa:</strong> ${product.deleted_at}<br>
                                            </div>
                                        </td>
                                        <td>
                                            <!-- Add a button to restore the product -->
                                            <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#confirmRestoreModal${product.id}">
                                                <i class="fas fa-undo-alt"></i>
                                            </button>
                                            <!-- Restore Confirmation Modal -->
                                            <div class="modal fade" id="confirmRestoreModal${product.id}" tabindex="-1" role="dialog" aria-labelledby="confirmRestoreModalLabel" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="confirmRestoreModalLabel">Xác nhận Khôi phục</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            Bạn có muốn khôi phục sản phẩm này không ?
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                            <form method="POST" action="products">
                                                                <input type="hidden" name="id" value="${product.id}">
                                                                <button type="submit" class="btn btn-warning btn-sm" name="restore">Khôi phục</button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:when>
                            </c:choose>
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
