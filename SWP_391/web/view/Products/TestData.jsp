<%-- 
    Document   : TestData
    Created on : Feb 21, 2024, 8:45:30 AM
    Author     : ifyou
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <tr>
        <c:forEach var="product" items="${data}">

            <td>${product.id}</td>
            <td>${product.code}</td>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td>${product.getCategory().getName()}</td>

        </c:forEach>
    </tr>
</body>
</html>
