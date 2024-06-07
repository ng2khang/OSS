<%-- 
    Document   : profile
    Created on : Feb 18, 2024, 12:53:12 PM
    Author     : ifyou
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thông tin cá nhân</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/home.css" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" />
        <link rel="shortcut icon" type="image/png" href="img/favicon.png">
        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <!-- Favicon -->
        <link href="img/icons8-cofee-16.png" rel="icon" />

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet" />

        <!--Libraries Stylesheet-->
        <link href="js/animate/animate.min.css" rel="stylesheet" />
        <link href="js/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet" />

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet" />

        <style>
            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgb(0,0,0);
                background-color: rgba(0,0,0,0.4);
                padding-top: 60px;
            }

            .modal-content {
                background-color: #fefefe;
                margin: 5% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 80%;
                max-width: 500px;
            }

            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }

            .modal-header h2 {
                margin: 0;
            }

            .modal-body {
                padding: 2px 16px;
            }

            .modal-body form {
                display: flex;
                flex-direction: column;
            }

            .modal-body label,
            .modal-body select,
            .modal-body button {
                margin-top: 10px;
            }

            /* Button styling */
            .modal-body button {
                cursor: pointer;
                padding: 10px;
                margin-top: 20px;
                width: auto;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
            }

            .modal-body button:hover {
                background-color: #0056b3;
            }
            .modal .select {
                overflow-y: auto; /* Cho phép cuộn dọc nếu cần */
                max-height: 200px; /* Đặt chiều cao tối đa */
            }
            .address-container {
                display: flex;
                align-items: center; /* Căn chỉnh các phần tử theo trục dọc */
                justify-content: space-between; /* Phân bố khoảng cách giữa các phần tử */
                width: 100%; /* Hoặc bạn có thể đặt chiều rộng cố định nếu muốn */
                padding: 10px; /* Đặt padding cho container nếu cần */
            }


            .edit-link {
                font-size: 14px; /* Điều chỉnh kích thước font cho link chỉnh sửa */
                color: #007bff; /* Màu cho link, bạn có thể thay đổi */
                text-decoration: none; /* Gỡ bỏ gạch dưới của link */
                cursor: pointer; /* Hiển thị con trỏ chuột dạng pointer khi di chuột qua link */
            }

            .edit-link:hover {
                text-decoration: underline; /* Thêm gạch dưới khi hover để chỉ ra có thể click */
            }
        </style>
        <script>
            function showModal() {
                document.getElementById('addAddressModal').style.display = 'block';
            }

            function closeModal() {
                document.getElementById('addAddressModal').style.display = 'none';
            }
        </script>
    </head>

    <body>
        <c:choose>
            <c:when test="${user.role == 4}">
                <jsp:include page="view/Header and footer/HeaderCustomer.jsp"></jsp:include>
            </c:when>
            <c:when test="${user.role == 2}">
                <jsp:include page="view/Header and footer/HeaderSale.jsp"></jsp:include>
            </c:when>
            <c:otherwise>
                <jsp:include page="view/Header and footer/HeaderCustomer.jsp"></jsp:include>
            </c:otherwise>
        </c:choose> 
        <div class="container rounded bg-white mt-5 mb-5">
            <div class="row">
                <div class="col-md-3 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                        <img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg">
                        <!--                            <form id="uploadForm" action="/SWP_391/uploadImageServlet" method="POST" enctype='multipart/form-data'>
                                                        <div class="input-image">
                                                            <label for="input">
                                                                Choose Image <br>
                                                                <input class="field image-name" id="input" type="file" name="image"
                                                                       style="display: none">
                                                            </label>
                                                             <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit">Sửa ảnh đại diện</button></div>
                                                        </div>
                                                    </form>
                                                    <br/>-->
                        <span class="font-weight-bold">${user.name}</span><span class="text-black-50"></span>
                        <a href="/SWP_391/changePassword" class="edit-link">Đổi mật khẩu</a>
                    </div>
                </div>
                <div class="col-md-5 border-right">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right" style="font-size: 25px; font-weight: bold">Chỉnh sửa thông tin cá nhân</h4>
                        </div>
                        <form action="/SWP_391/editUserProfile" method="post">
                            <div class="row mt-2">
                                <div class="col-md-12"><label class="labels">Họ tên</label><input type="text" class="form-control" placeholder="Xin hãy nhập họ và tên" value="${user.name}" id="name" name="name" required style="height: 50px; font-size: 14px"></div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-12"><label class="labels">Địa chỉ email</label><input type="text" class="form-control" value="${user.email}" disabled style="height: 50px; font-size: 14px"></div>
                                <div class="col-md-12"><label class="labels">Số điện thoại</label><input type="text" class="form-control" placeholder="Xin hãy nhập số điện thoại" value="${user.phone}" required id="phone" name="phone" pattern="[0-9]{10}" style="height: 50px; font-size: 14px"></div>
                            </div>   
                            <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit" style="height: 50px; width: 200px; font-size:16px">Lưu thông tin cá nhân</button></div>
                        </form>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center experience"><span>Thông tin địa chỉ</span>
                            <button class="btn btn-primary profile-button" type="button" style="height: 40px; width: 150px">
                                <span style="font-size: 13px" class="px-1 p-1 add-experience" onclick="showModal()"><i class="fa fa-plus"></i>&nbsp;Thêm mới địa chỉ</span>
                            </button>

                        </div>
                        <br>
                        <c:forEach items="${addresses}" var="c">
                            <div class="col-md-20 address-container">
                                <c:choose>
                                    <c:when test="${c.is_default == 1}">
                                        <label class="" style="font-weight: 400">${c.showAddress()}(Địa chỉ mặc định)</label>
                                    </c:when>
                                    <c:otherwise>
                                        <label class="" style="font-weight: 400">${c.showAddress()}</label>
                                    </c:otherwise>
                                </c:choose>  
                                <a href="editAddressController?id=${c.id}" class="edit-link">Chỉnh Sửa</a>
                                <c:choose>
                                    <c:when test="${totalAddressNumber != 1}">
                                        <a href="deleteUserAddress?id=${c.id}" class="edit-link">Xóa</a>
                                    </c:when>
                                    <c:otherwise>

                                    </c:otherwise>
                                </c:choose>  
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div id="addAddressModal" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <h2>Thêm mới địa chỉ</h2>
                    <span class="close" onclick="closeModal()">&times;</span>
                </div>
                <div class="modal-body">
                    <form action="/SWP_391/addUserAddress" method="post">
                        <label for="province"><span style="color: red;">*</span>Tỉnh/Thành:</label>
                        <select id="province" name="province" required>                
                            <c:choose>
                                <c:when test="${not empty selectedProvince}">
                                    <option value="${selectedProvince.code}">${selectedProvince.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="1">Xin hãy chọn Tỉnh/Thành</option>
                                </c:otherwise>
                            </c:choose>                     
                            <c:forEach items="${provinces}" var="c">
                                <option value="${c.code}">${c.name}</option>
                            </c:forEach>
                        </select>
                        <label for="district"><span style="color: red;">*</span>Quận/Huyện:</label>
                        <select id="district" name="district" required>
                            <option value=""></option>
                        </select>

                        <label for="ward"><span style="color: red;">*</span>Phường/Xã:</label>
                        <select id="ward" name="ward" required>
                            <option value=""></option>
                        </select>
                        <label for="address"><span style="color: red;">*</span>Địa Chỉ:</label>
                        <textarea id="address" name="address" rows="4" value="${address}" required></textarea>
                        <div class="form-field">
                            <input type="checkbox" name="defaultAddress">
                            <label>Đặt làm địa chỉ mặc định</label>
                        </div> 
                        <button type="submit">Đăng Ký</button>
                        <br>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer End -->
</div>
</div>
<jsp:include page="view/customer/footer.jsp"></jsp:include>
</body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
                        $(document).ready(function () {
                            $("#province").change(function () {
                                var selectedProvince = $(this).val();
                                $.ajax({
                                    url: "/SWP_391/DistrictServlet",
                                    method: "POST",
                                    data: {provinceCode: selectedProvince},
                                    success: function (data) {
                                        $("#district").html(data);
                                        $("#ward").html("<option value=''>Select Ward</option>");
                                    }
                                });
                            });
                            $("#district").change(function () {
                                var selectedDistrict = $(this).val();
                                $.ajax({
                                    url: "/SWP_391/WardServlet",
                                    method: "POST",
                                    data: {districtCode: selectedDistrict},
                                    success: function (data) {
                                        $("#ward").html(data);
                                    }
                                });
                            });
                        });
</script>
</html>
