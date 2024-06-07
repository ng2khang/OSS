<%-- 
    Document   : VeiwGroups
    Created on : Jan 13, 2024, 10:30:17 AM
    Author     : lucdu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bảng màu sắc</title>
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
        <div><jsp:include page="../Header and footer/HeaderSale.jsp"></jsp:include></div>
        <div class="row prd-detail-img hidden-xs hidden-sm"></div>
            <div class="container mt-5">

                <h1>Bảng màu sắc</h1>
                <div><a href="colors?mod=1" class="btn btn-primary mb-3">Thêm màu mới</a></div>

                <div class="btn-group mb-3">
                    <a href="colors" class="btn btn-secondary">Tất cả các bảng màu</a>
                    <a href="colors?show=active" class="btn btn-success">Các bảng màu đang sử dụng</a>
                    <a href="colors?show=updated" class="btn btn-warning">Các bảng màu đã cập nhật</a>
                    <a href="colors?show=deleted" class="btn btn-danger">Các bảng màu đã xóa</a>
                </div>

                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Tên màu</th>
                            <th>Mã màu</th>
                            <th>Màu</th>
                            <th>Ngày tạo</th>
                            <th>Ngày cập nhật</th>
                            <th>Ngày xóa</th>
                            <th>Hoạt động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Loop through the groups and display their information -->
                    <c:forEach var="color" items="${Cdata}">
                        <!-- Check if the group should be displayed based on the 'show' parameter -->
                        <c:choose>
                            <c:when test="${empty param.show || param.show eq 'all'}">
                                <!-- Display all groups -->
                                <tr>
                                    <td>${color.id}</td>
                                    <td>${color.name}</td>
                                    <td>${color.color_code}</td>
                                    <td>
                                        <div style="width: 20px; height: 20px; background-color: ${color.color_code}; border: 1px solid black"></div>
                                    </td>
                                    <td>${color.created_at}</td>
                                    <td>${color.updated_at}</td>
                                    <td>${color.deleted_at}</td>
                                    <td>
                                        <a href="colors?mod=2&id=${color.getId()}" class="btn btn-info btn-sm">Cập nhật</a>
                                        <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDeleteModal${color.getId()}">
                                            Xóa
                                        </button>
                                        <div class="modal fade" id="confirmDeleteModal${color.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="confirmDeleteModalLabel">Xác nhận xóa</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        Bạn có chắc muốn xóa mã màu này ?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy bỏ</button>
                                                        <a href="colors?mod=3&id=${color.getId()}" class="btn btn-danger">Xóa</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:when>
                            <c:when test="${param.show eq 'active' && empty color.deleted_at}">
                                <!-- Display only active groups -->
                                <tr>
                                    <td>${color.id}</td>
                                    <td>${color.name}</td>
                                    <td>${color.color_code}</td>
                                    <td>
                                        <div style="width: 20px; height: 20px; background-color: ${color.color_code}; border: 1px solid black"></div>
                                    </td>
                                    <td>${color.created_at}</td>
                                    <td>${color.updated_at}</td>
                                    <td>${color.deleted_at}</td>
                                    <td>
                                        <a href="colors?mod=2&id=${color.getId()}" class="btn btn-info btn-sm">Cập nhật</a>
                                        <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDeleteModal${color.getId()}">
                                            Xóa
                                        </button>
                                        <div class="modal fade" id="confirmDeleteModal${color.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="confirmDeleteModalLabel">Xác nhận xóa</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        Bạn có chắc muốn xóa mã màu này ?                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy bỏ</button>
                                                        <a href="colors?mod=3&id=${color.getId()}" class="btn btn-danger">Xóa</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:when>
                            <c:when test="${param.show eq 'updated' && not empty color.updated_at}">
                                <!-- Display only updated groups -->
                                <tr>
                                    <td>${color.id}</td>
                                    <td>${color.name}</td>
                                    <td>${color.color_code}</td>
                                    <td>
                                        <div style="width: 20px; height: 20px; background-color: ${color.color_code}; border: 1px solid black"></div>
                                    </td>
                                    <td>${color.created_at}</td>
                                    <td>${color.updated_at}</td>
                                    <td>${color.deleted_at}</td>
                                    <td>
                                        <a href="colors?mod=2&id=${color.getId()}" class="btn btn-info btn-sm">Cập nhật</a>
                                        <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDeleteModal${color.getId()}">
                                            Xóa
                                        </button>
                                        <div class="modal fade" id="confirmDeleteModal${color.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="confirmDeleteModalLabel">Xác nhận xóa</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        Bạn có chắc muốn xóa mã màu này ?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy bỏ</button>
                                                        <a href="colors?mod=3&id=${color.getId()}" class="btn btn-danger">Xóa</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:when>
                            <c:when test="${param.show eq 'deleted' && not empty color.deleted_at}">
                                <!-- Display only deleted groups -->
                                <tr>
                                    <td>${color.id}</td>
                                    <td>${color.name}</td>
                                    <td>${color.color_code}</td>
                                    <td>
                                        <div style="width: 20px; height: 20px; background-color: ${color.color_code}; border: 1px solid black"></div>
                                    </td>
                                    <td>${color.created_at}</td>
                                    <td>${color.updated_at}</td>
                                    <td>${color.deleted_at}</td>
                                    <td>
                                        <!-- Add a button to restore the group -->
                                        <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#confirmRestoreModal${color.id}">
                                            phục hồi
                                        </button>
                                        <!-- Restore Confirmation Modal -->
                                        <div class="modal fade" id="confirmRestoreModal${color.id}" tabindex="-1" role="dialog" aria-labelledby="confirmRestoreModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="confirmRestoreModalLabel">Xác nhận phục hồi</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        Bạn có chắc muốn phục hồi mã màu này ?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy bỏ</button>
                                                        <form method="POST" action="colors">
                                                            <input type="hidden" name="id" value="${color.id}">
                                                            <button type="submit" class="btn btn-warning btn-sm" name="restore">Phục hồi</button>
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