<%-- 
    Document   : Home
    Created on : Jan 10, 2024, 8:47:09 AM
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
        <h1>Hello World from Home page! The content is coming soon</h1>
    <c:if test="${not empty user}">
        <p style="">Hello ${user.name}</p>
        <p style="">Hello ${user.getAddress().toString()}</p>
        <a href="/SWP_391/logout">Logout</a>
    </c:if>
</body>
</html>
