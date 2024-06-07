<!DOCTYPE html>
<html lang="en">

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Quản lí nhân viên</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Varela Round', sans-serif;
                font-size: 13px;
            }
            .table-responsive {
                margin: 30px 0;
            }
            .table-wrapper {
                min-width: 1000px;
                background: #fff;
                padding: 20px 25px;
                border-radius: 3px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {
                padding-bottom: 15px;
                background:  #303030;
                color: #fff;
                padding: 16px 30px;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .table-title .btn {
                color: #566787;
                float: right;
                font-size: 13px;
                background: #fff;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                border: none;
                outline: none !important;
                margin-left: 10px;
            }
            .table-title .btn:hover, .table-title .btn:focus {
                color: #566787;
                background: #f2f2f2;
            }
            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
            }
            .table-title .btn span {
                float: left;
                margin-top: 2px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
                padding: 12px 10px;
                vertical-align: middle;
            }
            table.table tr th:first-child {
                width: 60px;
            }
            table.table tr th:last-child {
                width: 100px;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            table.table td:last-child i {
                opacity: 0.9;
                font-size: 22px;
                margin: 0 5px;
            }
            table.table td a {
                font-weight: bold;
                color: #566787;
                display: inline-block;
                text-decoration: none;
            }
            table.table td a:hover {
                color: #2196F3;
            }
            table.table td a.settings {
                color: #2196F3;
            }
            table.table td a.delete {
                color: #F44336;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table .avatar {
                border-radius: 50%;
                vertical-align: middle;
                margin-right: 10px;
            }
            .status {
                font-size: 30px;
                margin: 2px 2px 0 0;
                display: inline-block;
                vertical-align: middle;
                line-height: 10px;
            }
            .text-success {
                color: #10c469;
            }
            .text-info {
                color: #62c9e8;
            }
            .text-warning {
                color: #FFC107;
            }
            .text-danger {
                color: #ff5b5b;
            }
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 13px;
                min-width: 30px;
                min-height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 2px !important;
                text-align: center;
                padding: 0 6px;
            }
            .pagination li a:hover {
                color: #666;
            }
            .pagination li.active a, .pagination li.active a.page-link {
                background: #03A9F4;
            }
            .pagination li.active a:hover {
                background: #0397d6;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .hint-text {
                float: left;
                margin-top: 10px;
                font-size: 13px;
            }
            .search-bar {
                margin-bottom: 10px;
            }
        </style>
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("Bạn có muốn xóa nhân viên có id = " + id)) {
                    window.location = "deleteStaff?id=" + id;
                }

            }

        </script>
        <script>
            window.onload = function () {
                var currentUrl = window.location.href;

                if (currentUrl.indexOf('?') === -1) {
                    window.location.href = currentUrl + '?index=1&txtSearch=${txtSearch}';
                } else {
                    var params = new URLSearchParams(window.location.search);
                    if (!params.has('index')) {
                        params.set('index', '1');
                        params.set('txtSearch', '${txtSearch}');
                        window.location.href = currentUrl.split('?')[0] + '?' + params.toString();
                    }
                }
            }
        </script>

    </head>
    <body>
        <style>
            @keyframes blink {
                0% {
                    opacity: 1;
                }
                50% {
                    opacity: 0;
                }
                100% {
                    opacity: 1;
                }
            }
        </style>
        <jsp:include page="../Header and footer/HeaderAdmin.jsp"></jsp:include>
            <div class="container-xl">
                <div class="table-responsive">
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-5">
                                    <h2 style="color: white">Quản lí nhân viên</h2>
                                </div>
                                <div class="col-sm-7">
                                    <a href="view\\admin\\CreateStaff.jsp" class="btn btn-secondary"><i class="material-icons">&#xE147;</i> <span>Thêm nhân viên</span></a>               						
                                </div>
                            </div>
                        </div>
                        <table class="table table-striped table-hover">
                            <div class="float-right search-bar">
                                <form action="/SWP_391/staff" method="get">
                                    <div class="input-group">
                                        <input type="text" class="form-control rounded" name="txtSearch" placeholder="Tìm kiếm theo tên..." aria-label="Search" aria-describedby="search-addon" value="${txtSearch}" >                                                                           
                                    <div class="input-group-append">
                                        <button type="submit" class="btn btn-outline-primary">Tìm</button> 
                                    </div>
                                </div>
                            </form>
                        </div>
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Họ và tên</th>
                                <th>Email</th>
                                <th>Số điện thoại</th>
                                <th>Vai trò</th>
                                <th>Trạng thái</th>
                                <th>Ngày tạo</th>  
                                <!--             <th>Giới tính</th>  
                                <th>Ca làm</th>  
                                <th>Lương</th>  
                                
                                --> 
                                <th>Hoạt động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${selectStaff}" var="user">

                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.name}</td>
                                    <td>${user.email}</td>
                                    <td>${user.phone}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.role eq 2}">
                                                Nhân viên bán hàng
                                            </c:when>                                    
                                            <c:otherwise>
                                                Unknown
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.status eq 0}">
                                                <span class="status text-warning" style="animation: blink 1.8s infinite; display: inline-block;">&bull;</span> &nbsp; Không hoạt động  
                                            </c:when>
                                            <c:when test="${user.status eq 1}">
                                                <span class="status text-success" style="animation: blink 1.8s infinite; display: inline-block;">&bull;</span> &nbsp; Đang hoạt động
                                            </c:when>
                                            <c:otherwise>
                                                <span class="status text-danger" style="animation: blink 1.8s infinite; display: inline-block;">&bull;</span> &nbsp; Không rõ
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${user.created_at}</td>


                                    <td>
                                        <a href="updateStaff?id=${user.id}" class="settings" title="Cập nhật" data-toggle="tooltip"><i class="material-icons">&#xE8B8;</i></a>
                                        <a href="#" onclick="doDelete('${user.id}')" class="delete" title="Xóa" data-toggle="tooltip"><i class="material-icons">&#xE5C9;</i></a>
                                    </td>

                                </tr>


                            </c:forEach>
                        </tbody>
                    </table>     
                    <div class="clearfix">
                        <ul class="pagination">  
                            <c:choose>
                                <c:when test="${endPage > 1 && param.index != 1}">
                                    <li class="page-item"><a href="staff?index=${param.index - 1}&txtSearch=${txtSearch}" class="page-link"><</a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li class="page-item disabled"><span class="page-link"><</span></li>
                                    </c:otherwise>
                                </c:choose>

                            <c:forEach begin="1" end="${endPage}" var="i">
                                <c:choose>
                                    <c:when test="${param.index == i}">
                                        <li class="page-item active"><a href="staff?index=${i}&&txtSearch=${txtSearch}" class="page-link selected">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                        <li class="page-item"><a href="staff?index=${i}&&txtSearch=${txtSearch}" class="page-link">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>


                            <c:choose>
                                <c:when test="${endPage > 1 && param.index != endPage}">
                                    <li class="page-item"><a href="staff?index=${param.index + 1}&txtSearch=${txtSearch}" class="page-link">></a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li class="page-item disabled"><span class="page-link">></span></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </div>              
                </div>
            </div>
        </div>     
    </body>
</html>