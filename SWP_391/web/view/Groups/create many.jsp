<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm nhiều sản phẩm</title>
    <script>
        function addProductForm() {
            var container = document.getElementById("productForms");
            var newForm = document.createElement("div");
            newForm.innerHTML = "<table border='1'>" +
                                "<tr>" +
                                    "<th>Tên</th>" +
                                    "<th>Giá</th>" +
                                "</tr>" +
                                "<tr>" +
                                    "<td><input type='text' name='name'></td>" +
                                    "<td><input type='text' name='price'></td>" +
                                "</tr>" +
                            "</table>";
            container.appendChild(newForm);
        }
    </script>
</head>
<body>
    <h1>Thêm nhiều sản phẩm</h1>
    <form action="addProducts" method="post">
        <div id="productForms">
            <table border="1">
                <tr>
                    <th>Tên</th>
                    <th>Giá</th>
                </tr>
                <tr>
                    <td><input type="text" name="name"></td>
                    <td><input type="text" name="price"></td>
                </tr>
            </table>
        </div>
        <input type="button" value="Thêm sản phẩm" onclick="addProductForm()">
        <input type="submit" value="Thêm">
    </form>
</body>
</html>
