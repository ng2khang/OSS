<%-- 
    Document   : UpdateOrder
    Created on : Mar 10, 2024, 1:55:05 PM
    Author     : ifyou
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Xử lý đơn hàng</title>
        <!-- Thêm Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
            }
            .container {
                min-height: 100vh;
                display: flex;
                align-items: center;
            }
            .card {
                border: none;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .btn-primary {
                background-color: #007bff;
                border-color: #007bff;
            }
            .btn-primary:hover {
                background-color: #0056b3;
                border-color: #0056b3;
            }
            .btn-secondary {
                background-color: #6c757d;
                border-color: #6c757d;
            }
            .btn-secondary:hover {
                background-color: #545b62;
                border-color: #4e555b;
            }
            #description {
                height: 150px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="card mx-auto p-4" style="width:650px">
                <h1 class="text-center mb-4">Xử lý đơn hàng</h1>
                <form name="form" action="EditOrderController" method="POST">
                    <input type="hidden" class="form-control" name="id" value="${order.id}">
                    <div class="mb-3">
                        <label for="code" class="form-label">Mã đơn hàng:</label>
                        <input type="text" class="form-control" id="code" name="code" value="${order.orderCode}" readonly>
                        <span id="error-message-code" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="code" class="form-label">Ngày đặt hàng</label>
                        <input type="text" class="form-control" id="code" name="code" value="${order.orderDate}" readonly>
                        <span id="error-message-code" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="name" class="form-label">Tên khách hàng:</label>
                        <input type="text" class="form-control" id="name" name="name" value="${order.customer.name}" readonly>
                        <span id="error-message-name" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Địa chỉ giao hàng:</label>
                        <textarea class="form-control form-control-lg" id="description" name="description" readonly style="font-size: 16px">${order.deliveryAddress.showAddress()}</textarea>
                        <span id="error-message-description" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="price" class="form-label">Số điện thoại khách hàng:</label>
                        <input type="text" class="form-control" id="price" name="price" value="${order.customer.phone}">
                        <span id="error-message-price" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Chi tiết đơn hàng:</label>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Tên sản phẩm</th>
                                    <th>Màu sắc</th>
                                    <th>Size</th>
                                    <th>Số lượng</th>
                                    <th>Giá tiền</th>
                                    <th>Thành tiền</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${order.orderDetail}" var="c">
                                    <tr>
                                        <td>${c.productDetail.product.name}</td>
                                        <td>${c.productDetail.color.name}</td>
                                        <td>${c.productDetail.size.name}</td>
                                        <td>${c.quantity}</td>
                                        <td><fmt:formatNumber value="${c.unitPrice}" type="currency" currencyCode="VND" groupingUsed="true" pattern="###,###" /></td>
                                        <td><fmt:formatNumber value="${c.unitPrice * c.quantity}" type="currency" currencyCode="VND" groupingUsed="true" pattern="###,###" /></td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>Tổng Cộng: 
                                        <fmt:formatNumber value="${order.total}" type="currency" currencyCode="VND" groupingUsed="true" pattern="###,###" /></td>

                            </tbody>
                        </table>

                    </div>

                    <div class="mb-3">
                        <label for="category_id" class="form-label">Hình thức thanh toán</label>
                        <select id="category_id" name="category_id" class="form-select" required>
                            <option value="0">Thanh toán khi nhận hàng</option>  
                        </select>
                        <span id="error-message-category" style="color: red;"></span>
                    </div>
                    <div class="mb-3">
                        <label for="brand_id" class="form-label">Công ty vận chuyển</label>
                        <select id="shippingCompany" name="shippingCompany" class="form-select" required>
                            <c:choose>
                                <c:when test="${not empty order.shippingCompany}">
                                    <option value="${order.shippingCompany.id}">${order.shippingCompany.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="c" items="${shippingCompanies}">
                                        <option value="${c.id}">${c.name}</option>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose> 
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="shippingCode" class="form-label">Mã vận đơn:</label>
                        <input type="text" class="form-control" id="shippingCode" name="shippingCode" value="${order.shippingCode}">
                    </div>
                    <div class="mb-3">
                        <label for="brand_id" class="form-label">Trạng thái đơn hàng</label>
                        <select id="status" name="status" class="form-select" required>
                            <c:choose>
                                <c:when test="${user.role == 2}">
                                    <option value="${orderStatus.id}" selected="selected">${orderStatus.status}</option>
                                    <c:forEach items="${orderStatusForSale}" var="status">
                                        <c:if test="${status.id != orderStatus.id}"> 
                                            <option value="${status.id}">${status.status}</option>
                                        </c:if>
                                    </c:forEach> 
                                </c:when>
                                <c:otherwise>
                                    <option value="${orderStatus.id}" selected="selected">${orderStatus.status}</option>
                                    <c:forEach items="${orderStatusForShipper}" var="status">
                                        <c:if test="${status.id != orderStatus.id}"> 
                                            <option value="${status.id}">${status.status}</option>
                                        </c:if>
                                    </c:forEach> 
                                </c:otherwise>
                            </c:choose> 
                        </select>
                        <span id="error-message-brand" style="color: red;"></span>
                    </div>
                    <c:choose>
                        <c:when test="${user.role == 2}">
                            <div class="mb-3">
                                <fmt:formatDate value="${order.deliveryDate}" pattern="yyyy-MM-dd" var="formattedDate"/>
                                <label for="description" class="form-label">Ngày giao hàng dự kiến</label>
                                <input type="date" class="form-control" id="expectedDeliveryDate" name="expectedDeliveryDate" value="${formattedDate}">
                                <span id="error-message-description" style="color: red;"></span>
                            </div> 
                        </c:when>
                        <c:otherwise>
                            <div class="mb-3">
                                <label for="description" class="form-label">Ngày giao hàng dự kiến</label>
                                <fmt:formatDate value="${order.deliveryDate}" pattern="yyyy-MM-dd" var="formattedDate"/>
                                <input type="date" class="form-control" id="expectedDeliveryDate" name="expectedDeliveryDate" value="${formattedDate}">
                                <span id="error-message-description" style="color: red;"></span>
                            </div> 
                        </c:otherwise>
                    </c:choose>   

                    <div class="mb-3">
                        <label for="description" class="form-label">Ghi Chú:</label>
                        <textarea class="form-control form-control-lg" id="note" name="note" style="font-size: 16px">${order.note}</textarea>
                        <span id="error-message-description" style="color: red;"></span>
                    </div>    
                    <button type="submit" class="btn btn-primary" name="update">Lưu</button>
                    <a href="OrderController" class="btn btn-secondary">Hủy</a>
            </div>

        </form>
    </div>
</div>

<!-- Thêm Bootstrap JS (nếu cần) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 1; //January is 0!
        var yyyy = today.getFullYear();
        if (dd < 10) {
            dd = '0' + dd;
        }
        if (mm < 10) {
            mm = '0' + mm;
        }
        today = yyyy + '-' + mm + '-' + dd;
        document.getElementById("expectedDeliveryDate").setAttribute("min", today);
    });
</script>

</body>
</html>

