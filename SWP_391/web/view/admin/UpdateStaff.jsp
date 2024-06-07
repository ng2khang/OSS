<%-- 
    Document   : newjsp
    Created on : Feb 6, 2024, 10:14:24 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật nhân viên</title>
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
        </style>
    </head>
    <body>
        <div class="container">
            <div class="card mx-auto p-4">
                <h1 class="text-center mb-4">Cập nhật nhân viên</h1>
                <c:set var="c" value="${getStaffId}" />
                <form  action="/SWP_391/updateStaff" method="POST" onsubmit="return validateEmail();" >
                    <div class="mb-3">
                        <input type="hidden" name="id" value="${c.id}"  class="form-control" required readonly/><br>
                    </div>
                    <div class="mb-3">
                        Cập nhật tên: <input type="text" name="name" value="${c.name}"  class="form-control" required/><br>
                    </div>
                    <div class="mb-3">
                        Email: <input type="text" name="email"  value="${c.email}"  id="email" class="form-control" required readonly/><br>                    
                    </div>
                    <div class="mb-3">
                        Cập nhật số điện thoại: <input type="number" name="phone" value="${c.phone}" class="form-control" required /><br>
                    </div>
                    <div class="mb-3">
                        <label for="status">Cập nhật trạng thái</label>
                        <select id="status" name="status" class="form-control" required>

                            <option value="1" ${c.status == '1' ? 'selected' : ''}>Đang hoạt động</option>
                            <option value="0" ${c.status == '0' ? 'selected' : ''}>Không hoạt động</option>

                        </select>
                    </div>
                    <div class="mb-3" >
                        <label for="role">Vai trò:</label>
                        <select id="role" name="role" class="form-control" required >
                            <option value="2"${c.role == '2' ? 'selected' : ''}>Nhân viên bán hàng</option>
                        </select>
                    </div>
                            <br>
                    <button type="submit" class="btn btn-primary" >Cập nhật</button>
                    <a href="/SWP_391/staff" class="btn btn-secondary">Hủy bỏ</a>
                </form>
            </div>
        </div>
        <script>
            function validateEmail() {
                var emailInput = document.getElementById("email");
                var emailValue = emailInput.value.trim(); // Remove leading and trailing spaces

                 // Check if the email is not empty and matches the required format
                if (emailValue === "" || !/(^|\s)[a-zA-Z0-9._%+-]+@(gmail\.com|fpt\.edu\.vn)\s*$/.test(emailValue)) {
                    alert("Email phải được định dạng có'@gmail.com' hoặc '@fpt.edu.vn'.");
                    emailInput.focus();
                    return false;
                }

                return true;
            }
        </script>
    </body>
</html>
