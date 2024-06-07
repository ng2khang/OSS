

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-3">
    <div class="card bg-light mb-3">
        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
        <ul class="list-group category_block">
            <c:forEach items="${data1}" var="o">
                <li class="list-group-item text-white ${tag == o.id ? "bg-warning":""}"><a href="CustomerProducts?filter=brand&id=${o.id}">${o.name}</a></li>
            </c:forEach>

        </ul>
        
        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Brands</div>
        <ul class="list-group category_block">
            <c:forEach items="${data2}" var="o">
                <li class="list-group-item text-white ${tag == o.id ? "bg-warning":""}"><a href="CustomerProducts?filter=brand&id=${o.id}">${o.name}</a></li>
            </c:forEach>

        </ul>
        
        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Colors</div>
        <ul class="nav tree">
            <c:forEach items="${data3}" var="o">
                <div style="display: inline-block;
                                                     width: 20px;
                                                     height: 20px;
                                                     background-color: ${o.getColor_code()};
                                                     margin-left: 5px;
                                                     margin-right: 5px;
                                                     margin-top: 5px;
                                                     margin-bottom: 5px;
                                                     border: 1px solid black"></div>
                
            </c:forEach>

        </ul>
        
        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Sizes</div>
        <ul class="list-group category_block">
            <c:forEach items="${data4}" var="o">
                <li class="list-group-item text-white ${tag == o.id ? "bg-warning":""}"><a href="CustomerProducts?filter=brand&id=${o.id}">${o.name}</a></li>
            </c:forEach>

        </ul>
        
    </div>
    
</div>