<%-- 
    Document   : Edit
    Created on : 02-Jan-2024, 18:53:26
    Author     : MTD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
            .modal-footer {
                background: #03A9F4;
                border-radius: 0 0 3px 3px;
                
            }
            
        </style>
    </head>
    <body>
        <div id="editEmployeeModal" class="">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="editcategory" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Sửa thông tin loại giày</h4>
                            <button  type="button" class="close" data-dismiss="modal" aria-hidden="true"><a href="loadcategory">X</a></button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>ID</label>
                                <input value="${detail.id}" name="id" type="text" class="form-control" readonly required>
                            </div>
                            <div class="form-group">
                                <label>Tên</label>
                                <input value="${detail.name}" name="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Mô tả</label>
                                <textarea name="description" class="form-control" required>${detail.description}</textarea>
                            </div>

                            
                            
                        </div>
                             <input  type="submit" class="btn btn-success modal-footer"></input>
                        
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
