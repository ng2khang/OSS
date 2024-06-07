<%-- 
    Document   : editaddress
    Created on : Feb 25, 2024, 6:34:35 PM
    Author     : ifyou
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chỉnh sửa địa chỉ</title>
        <style>
            body {
                /*font-family: "Comic Sans MS", cursive;*/
                margin: 0;
                display: flex;
                align-items: center;
                justify-content: center;
                min-height: 100vh;
                background: #f5f5f5;
                color: #333;
            }

            .container {
                width: 100%;
                max-width: 400px;
            }

            .card {
                width: 100%;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            h2 {
                text-align: center;
                color: #333;
            }

            form {
                display: flex;
                flex-direction: column;
            }

            input, select, textarea{
                padding: 10px;
                margin-bottom: 12px;
                border: 1px solid #ddd;
                border-radius: 4px;
                transition: border-color 0.3s ease-in-out;
                outline: none;
                color: #333;
            }

            input:focus {
                border-color: #ff4500;
            }

            textarea:focus {
                border-color: #ff4500;
            }

            select:focus {
                border-color: #ff4500;
            }

            button {
                background-color: #ff4500;
                color: #fff;
                padding: 10px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s ease-in-out;
            }

            button:hover {
                background-color: #e63900;
            }
            .links {
                display: flex;
                flex-direction: row;
                justify-content: space-between;
            }
            a:link,
            a:visited,
            a:hover,
            a:active {
                text-decoration: none;
            }

            a {
                color: #000;
                font-size: 0.88rem;
                font-weight: 600;
                letter-spacing: -1px;
                transition: all 0.4s ease;
            }

            a:hover {
                color: rgb(13, 133, 185);
            }
            .form-field{
                font-size: 14px;
                padding-bottom: 12px;
                justify-content: center;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="card">
                <h2>Chỉnh sửa địa chỉ</h2>
                <form action="/SWP_391//editAddressController" method="post">
                    <input type="hidden" name="id" value="${id}">
                    <label for="province"><span style="color: red;">*</span>Tỉnh/Thành:</label>
                    <select id="province" name="province" required>
                        <c:choose>
                            <c:when test="${not empty userAddress}">
                                <option value="${userAddress.getProvince_code()}">${userAddress.getProvince_name()}</option>
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
                        <option value="${userAddress.getDistrict_code()}">${userAddress.getDistrict_name()}</option>
                        <c:forEach items="${districts}" var="c">
                            <option value="${c.code}">${c.name}</option>
                        </c:forEach>
                    </select>

                    <label for="ward"><span style="color: red;">*</span>Phường/Xã:</label>
                    <select id="ward" name="ward" required>
                        <option value="${userAddress.getWard_code()}">${userAddress.getWard_name()}</option>
                        <c:forEach items="${wards}" var="c">
                            <option value="${c.code}">${c.name}</option>
                        </c:forEach>
                    </select>
                    <label for="address"><span style="color: red;">*</span>Địa Chỉ:</label>
                    <textarea id="address" name="address" rows="4" value="" required>${userAddress.getAddress()}</textarea>
                    <div class="form-field">
                        <input type="checkbox" name="defaultAddress">
                        <label>Đặt làm địa chỉ mặc định</label>
                    </div>
                    <button type="submit">Lưu địa chỉ</button>
                    <br>
                </form>
            </div>
        </div>
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
