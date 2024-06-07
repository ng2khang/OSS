<%-- 
    Document   : ManagerProduct
    Created on : Dec 28, 2020, 5:19:02 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Quản lý loại giày</title>

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
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

    <body>
        <jsp:include page="../Header and footer/HeaderSale.jsp"></jsp:include>
        <div class="row prd-detail-img hidden-xs hidden-sm"></div>
            <div class="container mt-5">
                <div >

                    <div>

                        <h1>Quản lý loại giày</h1>

                        <div><a href="#addcategory" class="btn btn-primary mb-3" data-toggle="modal"> Tạo Mới</a></div>
                        <!--                        <div class="btn-group mb-3">
                                                    <a href="groups" class="btn btn-secondary">All Groups</a>
                                                    <a href="groups?show=active" class="btn btn-success">Active Groups</a>
                                                    <a href="groups?show=updated" class="btn btn-warning">Updated Groups</a>
                                                    <a href="groups?show=deleted" class="btn btn-danger">Deleted Groups</a>
                                                </div>-->
                        <!--                        <div class="col-sm-6">
                                                    <a href="#addcategory"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Category</span></a>
                                                                                <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>						
                                                </div>-->
                    </div>

                    <table class="table table-bordered ">
                        <thead>
                            <tr>

                                <th>ID</th>
                                <th>Tên</th>
                                <th>Mô tả</th>
                                <th>Ngà tạo</th>
                                <th>Hoạt động</th>
                            </tr>
                        </thead>
                        <tbody >
                        <c:forEach items="${listCC}" var="o">
                            <tr style="padding: 20%">

                                <td>${o.id}</td>
                                <td>${o.name}</td>
                                <td>${o.description}</td>
                                <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                                <td><fmt:formatDate value="${o.created_at}" pattern="yyyy-MM-dd" /></td>
                                <td>
                                    <a href="loadedit?cid=${o.id}" class="btn btn-info btn-sm">Sửa</a>
                                    <!--                                        <a href="deletecategory?cid=" class="btn btn-danger" >Delete</a>-->

                                    <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDeleteModal${o.id}">
                                        Xóa
                                    </button>
                                    <div class="modal fade" id="confirmDeleteModal${o.id}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="confirmDeleteModalLabel">Confirm Delete</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    Bạn có chắc chắn muốn xóa sản phẩm?
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                    <a href="deletecategory?cid=${o.id}" class="btn btn-danger">Xóa</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
            <!-- add Modal HTML -->
            <div id="addcategory" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="addcategory" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Tạo mới loại giày</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Tên</label>
                                    <input name="name" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Mô tả</label>
                                    <textarea name="description" class="form-control" required></textarea>
                                </div>
                                <!--                            <div class="form-group">
                                                                <label>Price</label>
                                                                <input name="price" type="text" class="form-control" required>
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Title</label>
                                                                <textarea name="title" class="form-control" required></textarea>
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Description</label>
                                                                <textarea name="description" class="form-control" required></textarea>
                                                            </div>-->


                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
                                <input type="submit" class="btn btn-success" value="Lưu">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <jsp:include page="../Groups/footer.jsp"></jsp:include>
            <script src="js/manager.js" type="text/javascript"></script>
            <script>

            </script>
    </body>
</html>