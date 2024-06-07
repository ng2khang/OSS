<%-- 
    Document   : VeiwSizes
    Created on : Feb 25, 2024, 3:11:58 PM
    Author     : lucdu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản Lý Kích Cỡ Giày</title>
        <!-- Include Bootstrap stylesheet -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
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
        </style>

    </head>
    <body>
        <jsp:include page="../Header and footer/HeaderSale.jsp"></jsp:include>
            <div class="row prd-detail-img hidden-xs hidden-sm"></div>
            <div class="container mt-5">

                <h1>Quản Lý Kích Cỡ Giày</h1>
                <div><a href="sizes?mod=1" class="btn btn-primary mb-3">Tạo kích cỡ giày mới</a></div>

                <div class="btn-group mb-3">
                    <a href="sizes" class="btn btn-secondary">Tất cả kích cỡ giày</a>
                    <a href="sizes?show=active" class="btn btn-success">Kích cỡ giày hoạt động</a>
                    <a href="sizes?show=updated" class="btn btn-warning">Kích cỡ giày vừa cập nhật</a>
                    <a href="sizes?show=deleted" class="btn btn-danger">Kích cỡ giày không hoạt động</a>
                </div>

                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>NO</th>
                            <th>Kích cỡ</th>
                            <th>Mô tả kích cỡ</th>
                            <th>Ngày tạo</th>
                            <th>Ngày cập nhật</th>
                            <th>Ngày xóa</th>
                            <th>Chức Năng</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Loop through the sizes and display their information -->
                    <c:forEach var="size" items="${data}">
                        <!-- Check if the size should be displayed based on the 'show' parameter -->
                        <c:choose>
                            <c:when test="${empty param.show || param.show eq 'all'}">
                                <!-- Display all sizes -->
                                <tr>
                                    <td>${size.id}</td>
                                    <td>${size.name}</td>
                                    <td>${size.description}</td>
                                    <td>${size.created_at}</td>
                                    <td>${size.updated_at}</td>
                                    <td>${size.deleted_at}</td>
                                    <td>
                                        <a href="sizes?mod=2&id=${size.getId()}" class="btn btn-info btn-sm">Sửa</a>
                                        <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDeleteModal${size.getId()}">
                                            Xóa
                                        </button>
                                        <div class="modal fade" id="confirmDeleteModal${size.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="confirmDeleteModalLabel">Xác Nhận Xóa</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        Bạn chắc chắn muốn xóa ?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                        <a href="sizes?mod=3&id=${size.getId()}" class="btn btn-danger">Xóa</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:when>
                            <c:when test="${param.show eq 'active' && empty size.deleted_at}">
                                <!-- Display only active sizes -->
                                <tr>
                                    <td>${size.id}</td>
                                    <td>${size.name}</td>
                                    <td>${size.description}</td>
                                    <td>${size.created_at}</td>
                                    <td>${size.updated_at}</td>
                                    <td>${size.deleted_at}</td>
                                    <td>
                                        <a href="sizes?mod=2&id=${size.getId()}" class="btn btn-info btn-sm">Sửa</a>
                                        <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDeleteModal${size.getId()}">
                                            Xóa
                                        </button>
                                        <div class="modal fade" id="confirmDeleteModal${size.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="confirmDeleteModalLabel">Xác Nhận Xóa</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        Bạn chắc chắn muốn xóa
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                        <a href="sizes?mod=3&id=${size.getId()}" class="btn btn-danger">Xóa</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:when>
                            <c:when test="${param.show eq 'updated' && not empty size.updated_at}">
                                <!-- Display only updated sizes -->
                                <tr>
                                    <td>${size.id}</td>
                                    <td>${size.name}</td>
                                    <td>${size.description}</td>
                                    <td>${size.created_at}</td>
                                    <td>${size.updated_at}</td>
                                    <td>${size.deleted_at}</td>
                                    <td>
                                        <a href="sizes?mod=2&id=${size.getId()}" class="btn btn-info btn-sm">Sửa</a>
                                        <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDeleteModal${size.getId()}">
                                            Xóa
                                        </button>
                                        <div class="modal fade" id="confirmDeleteModal${size.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="confirmDeleteModalLabel">Xác Nhận Xóa</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                       Bạn chắc chắn muốn xóa?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                        <a href="sizes?mod=3&id=${size.getId()}" class="btn btn-danger">Xóa</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:when>
                            <c:when test="${param.show eq 'deleted' && not empty size.deleted_at}">
                                <!-- Display only deleted sizes -->
                                <tr>
                                    <td>${size.id}</td>
                                    <td>${size.name}</td>
                                    <td>${size.description}</td>
                                    <td>${size.created_at}</td>
                                    <td>${size.updated_at}</td>
                                    <td>${size.deleted_at}</td>
                                    <td>
                                        <!-- Add a button to restore the size -->
                                        <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#confirmRestoreModal${size.id}">
                                            Khôi phục
                                        </button>
                                        <!-- Restore Confirmation Modal -->
                                        <div class="modal fade" id="confirmRestoreModal${size.id}" tabindex="-1" role="dialog" aria-labelledby="confirmRestoreModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="confirmRestoreModalLabel">Xác Nhận Xóa</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        Bạn chắc chắn muốn khôi phục?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                        <form method="POST" action="sizes">
                                                            <input type="hidden" name="id" value="${size.id}">
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
    </body>
</html>
