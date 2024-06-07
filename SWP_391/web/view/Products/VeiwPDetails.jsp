<%-- 
    Document   : VeiwPDetails
    Created on : Feb 25, 2024, 7:33:00 PM
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
        <title>Chi tiết sản phẩm</title>
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

                    <h1>Quản Lý Chi Tiết Sản Phẩm</h1>
                    <div><a href="productdetails?mod=1" class="btn btn-primary mb-3">Tạo mới</a></div>

                    <div class="btn-group mb-3">
                        <a href="productdetails" class="btn btn-secondary">Tất cả sản phẩm</a>
                        <a href="productdetails?show=active" class="btn btn-success">Sản phẩm hoạt động</a>
                        <a href="productdetails?show=updated" class="btn btn-warning">Sản phẩm vừa cập nhật</a>
                        <a href="productdetails?show=deleted" class="btn btn-danger">Sản phẩm không hoạt động</a>
                    </div>

                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th style="text-align: center;">NO</th>
                                <th style="text-align: center;
                                    white-space: nowrap;">Tên sản phẩm</th>
                                <th style="text-align: center;">Ảnh 1</th>
                                <th style="text-align: center;">Ảnh 2</th>
                                <th style="text-align: center;">Ảnh 3</th>
                                <th style="text-align: center;">Ảnh 4</th>
                                <th style="text-align: center;">Thông Tin</th>
                                <th style="text-align: center;
                                    white-space: nowrap">Chức Năng</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Loop through the products and display their information -->
                            <c:forEach var="pd" items="${data}">
                                <!-- Check if the product should be displayed based on the 'show' parameter -->
                                <c:choose>
                                    <c:when test="${empty param.show || param.show eq 'all'}">
                                        <!-- Display all products -->
                                        <tr>
                                            <td>${pd.id}</td>
                                            <td style="white-space: nowrap;">${pd.getProduct().getName()}</td>
                                        <td>
                                            <!-- Hiển thị ảnh 1 -->
                                            <img src="images/${pd.getImage_url_1()}" alt="Image 1" style="max-width: 100px;
                                                 max-height: 100px;">
                                        </td>
                                        <td>
                                            <!-- Hiển thị ảnh 2 -->
                                            <img src="images/${pd.getImage_url_2()}" alt="Image 2" style="max-width: 100px;
                                                 max-height: 100px;">
                                        </td>
                                        <td>
                                            <!-- Hiển thị ảnh 3 -->
                                            <img src="images/${pd.getImage_url_3()}" alt="Image 3" style="max-width: 100px;
                                                 max-height: 100px;">
                                        </td>
                                        <td>
                                            <!-- Hiển thị ảnh 4 -->
                                            <img src="images/${pd.getImage_url_4()}" alt="Image 4" style="max-width: 100px;
                                                 max-height: 100px;">
                                        </td>
                                        <td>
                                            <div>
                                                <strong>Màu Sắc:</strong> 
                                                <div style="display: inline-block;
                                                     width: 20px;
                                                     height: 20px;
                                                     background-color: ${pd.getColor().getColor_code()};
                                                     margin-right: 5px;
                                                     border: 1px solid black"></div>
                                                     <br>
                                                     <strong>Kích Thước:</strong> ${pd.getSize().getName()}<br>
                                                     <strong>Số Lượng Hàng:</strong> ${pd.getInventory_number()}<br>
                                                     <strong>Trạng Thái Hoạt Động:</strong> ${empty product.deleted_at ? 'Hoạt động' : 'Không hoạt động'}<br>
                                                     <strong>Ngày Tạo:</strong> ${pd.created_at}<br>
                                                     <strong>Ngày Cập Nhật:</strong> ${pd.edited_at}<br>
                                                     <strong>Ngày Dừng Hoạt Động:</strong> ${pd.deleted_at}
                                                </div>
                                            </td>
                                            <td>
                                                <a href="productdetails?mod=2&id=${pd.getId()}" class="btn btn-info btn-sm"><i class="fas fa-edit"></i></a>
                                                <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDeleteModal${pd.getId()}">
                                                    <i class="fas fa-trash-alt"></i>
                                                </button>
                                                <div class="modal fade" id="confirmDeleteModal${pd.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="confirmDeleteModalLabel">Confirm Delete</h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                Are you sure you want to delete this product?
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                                <a href="productdetails?mod=3&id=${pd.getId()}" class="btn btn-danger">Delete</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:when test="${param.show eq 'active' && empty pd.deleted_at}">
                                        <!-- Display only active products -->
                                        <tr>
                                            <td>${pd.id}</td>
                                            <td style="white-space: nowrap;">${pd.getProduct().getName()}</td>
                                        <td>
                                            <!-- Hiển thị ảnh 1 -->
                                            <img src="images/${pd.getImage_url_1()}" alt="Image 1" style="max-width: 100px;
                                                 max-height: 100px;">
                                        </td>
                                        <td>
                                            <!-- Hiển thị ảnh 2 -->
                                            <img src="images/${pd.getImage_url_2()}" alt="Image 2" style="max-width: 100px;
                                                 max-height: 100px;">
                                        </td>
                                        <td>
                                            <!-- Hiển thị ảnh 3 -->
                                            <img src="images/${pd.getImage_url_3()}" alt="Image 3" style="max-width: 100px;
                                                 max-height: 100px;">
                                        </td>
                                        <td>
                                            <!-- Hiển thị ảnh 4 -->
                                            <img src="images/${pd.getImage_url_4()}" alt="Image 4" style="max-width: 100px;
                                                 max-height: 100px;">
                                        </td>
                                        <td>
                                            <div>
                                                <strong>Màu Sắc:</strong> 
                                                <div style="display: inline-block;
                                                     width: 20px;
                                                     height: 20px;
                                                     background-color: ${pd.getColor().getColor_code()};
                                                     margin-right: 5px;
                                                     border: 1px solid black"></div>
                                                     <br>
                                                     <strong>Kích Thước:</strong> ${pd.getSize().getName()}<br>
                                                     <strong>Số Lượng Hàng:</strong> ${pd.getInventory_number()}<br>
                                                     <strong>Ngày Tạo:</strong> ${pd.created_at}<br>
                                                     <strong>Ngày Cập Nhật:</strong> ${pd.edited_at}<br>
                                                     <strong>Ngày Dừng Hoạt Động:</strong> ${pd.deleted_at}
                                                </div>
                                            </td>
                                            <td>
                                                <a href="pds?mod=2&id=${product.getId()}" class="btn btn-info btn-sm"><i class="fas fa-edit"></i></a>
                                                <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDeleteModal${pd.getId()}">
                                                    <i class="fas fa-trash-alt"></i>
                                                </button>
                                                <div class="modal fade" id="confirmDeleteModal${pd.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="confirmDeleteModalLabel">Confirm Delete</h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                Are you sure you want to delete this product?
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                                <a href="productdetails?mod=3&id=${pd.getId()}" class="btn btn-danger">Delete</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:when test="${param.show eq 'updated' && not empty pd.edited_at}">
                                        <!-- Display only updated products -->
                                        <tr>
                                            <td>${pd.id}</td>
                                            <td style="white-space: nowrap;">${pd.getProduct().getName()}</td>
                                        <td>
                                            <!-- Hiển thị ảnh 1 -->
                                            <img src="images/${pd.getImage_url_1()}" alt="Image 1" style="max-width: 100px;
                                                 max-height: 100px;">
                                        </td>
                                        <td>
                                            <!-- Hiển thị ảnh 2 -->
                                            <img src="images/${pd.getImage_url_2()}" alt="Image 2" style="max-width: 100px;
                                                 max-height: 100px;">
                                        </td>
                                        <td>
                                            <!-- Hiển thị ảnh 3 -->
                                            <img src="images/${pd.getImage_url_3()}" alt="Image 3" style="max-width: 100px;
                                                 max-height: 100px;">
                                        </td>
                                        <td>
                                            <!-- Hiển thị ảnh 4 -->
                                            <img src="images/${pd.getImage_url_4()}" alt="Image 4" style="max-width: 100px;
                                                 max-height: 100px;">
                                        </td>
                                        <td>
                                            <div>
                                                <strong>Màu Sắc:</strong> 
                                                <div style="display: inline-block;
                                                     width: 20px;
                                                     height: 20px;
                                                     background-color: ${pd.getColor().getColor_code()};
                                                     margin-right: 5px;
                                                     border: 1px solid black"></div>
                                                     <br>
                                                     <strong>Kích Thước:</strong> ${pd.getSize().getName()}<br>
                                                     <strong>Số Lượng Hàng:</strong> ${pd.getInventory_number()}<br>
                                                     <strong>Ngày Tạo:</strong> ${pd.created_at}<br>
                                                     <strong>Ngày Cập Nhật:</strong> ${pd.edited_at}<br>
                                                     <strong>Ngày Dừng Hoạt Động:</strong> ${pd.deleted_at}
                                                </div>
                                            </td>
                                            <td>
                                                <a href="pds?mod=2&id=${pd.getId()}" class="btn btn-info btn-sm"><i class="fas fa-edit"></i></a>
                                                <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDeleteModal${pd.getId()}">
                                                    <i class="fas fa-trash-alt"></i>
                                                </button>
                                                <div class="modal fade" id="confirmDeleteModal${pd.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="confirmDeleteModalLabel">Confirm Delete</h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                Are you sure you want to delete this product?
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                                <a href="productdetails?mod=3&id=${pd.getId()}" class="btn btn-danger">Delete</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:when>
                                    <c:when test="${param.show eq 'deleted' && not empty pd.deleted_at}">
                                        <!-- Display only deleted products -->
                                        <tr>
                                            <td>${pd.id}</td>
                                            <td style="white-space: nowrap;">${pd.getProduct().getName()}</td>
                                        <td>
                                            <!-- Hiển thị ảnh 1 -->
                                            <img src="images/${pd.getImage_url_1()}" alt="Image 1" style="max-width: 100px;
                                                 max-height: 100px;">
                                        </td>
                                        <td>
                                            <!-- Hiển thị ảnh 2 -->
                                            <img src="images/${pd.getImage_url_2()}" alt="Image 2" style="max-width: 100px;
                                                 max-height: 100px;">
                                        </td>
                                        <td>
                                            <!-- Hiển thị ảnh 3 -->
                                            <img src="images/${pd.getImage_url_3()}" alt="Image 3" style="max-width: 100px;
                                                 max-height: 100px;">
                                        </td>
                                        <td>
                                            <!-- Hiển thị ảnh 4 -->
                                            <img src="images/${pd.getImage_url_4()}" alt="Image 4" style="max-width: 100px;
                                                 max-height: 100px;">
                                        </td>
                                        <td>
                                            <div>
                                                <strong>Màu Sắc:</strong> 
                                                <div style="display: inline-block;
                                                     width: 20px;
                                                     height: 20px;
                                                     background-color: ${pd.getColor().getColor_code()};
                                                     margin-right: 5px;
                                                     border: 1px solid black"></div>
                                                     <br>
                                                     <strong>Kích Thước:</strong> ${pd.getSize().getName()}<br>
                                                     <strong>Số Lượng Hàng:</strong> ${pd.getInventory_number()}<br>
                                                     <strong>Ngày Tạo:</strong> ${pd.created_at}<br>
                                                     <strong>Ngày Cập Nhật:</strong> ${pd.edited_at}<br>
                                                     <strong>Ngày Dừng Hoạt Động:</strong> ${pd.deleted_at}
                                                </div>
                                            </td>
                                            <td>
                                                <!-- Add a button to restore the product -->
                                                <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#confirmRestoreModal${pd.getId()}">
                                                    <i class="fas fa-undo-alt"></i>
                                                </button>
                                                <!-- Restore Confirmation Modal -->
                                                <div class="modal fade" id="confirmRestoreModal${pd.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmRestoreModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="confirmRestoreModalLabel">Xác nhận Khôi phục</h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                Are you sure you want to restore this product?
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                                <form method="POST" action="productdetails">
                                                                    <input type="hidden" name="id" value="${pd.getId()}">
                                                                    <button type="submit" class="btn btn-warning btn-sm" name="restore">Restore</button>
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
            </body>
        </html>
