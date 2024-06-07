<%-- 
    Document   : header
    Created on : Feb 21, 2024, 9:09:22 AM
    Author     : ifyou
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>lighten</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- bootstrap css -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- style css -->
        <link rel="stylesheet" href="css/style.css">
        <!-- Responsive-->
        <link rel="stylesheet" href="css/responsive.css">
        <!-- fevicon -->
        <link rel="icon" href="images/fevicon.png" type="image/gif" />
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
        <!-- Tweaks for older IEs-->
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
        <style>
            .span{
                color: #000;
                width: 250px;
                height: 50px;
                float: left;
                text-align: center;
                line-height: 50px;
                border-radius: 100%;
                font-size: 18px;
            }
            .link{
                
            }
        </style>
    </head>
    <body class="main-layout">
        <!-- loader  -->
        <!--        <div class="loader_bg">
                    <div class="loader"><img src="images/loading.gif" alt="#" /></div>
                </div>-->
        <!-- end loader --> 
        <!-- header -->
        <header>
            <!-- header inner -->
            <div class="header">
                <div class="head_top">
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12">
                                <div class="top-box">
                                    <ul class="sociel_link">
                                        <li> <a href="#"><i class="fa fa-facebook-f"></i></a></li>
                                        <li> <a href="#"><i class="fa fa-twitter"></i></a></li>
                                        <li> <a href="#"><i class="fa fa-instagram"></i></a></li>
                                        <li> <a href="#"><i class="fa fa-linkedin"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                            <c:choose>
                                <c:when test="${not empty user}">
                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12">
                                        <div class="top-box">
                                            <ul class="sociel_link">
                                                <li> <a href="#" style="display: inline-flex; align-items: center">
                                                        <i class="fa fa-shopping-cart" style="color: white"></i>
                                                        <span style="padding-left: 10px; align-items: center; color: white">Cart</span>

                                                    </a></li>
                                                <li> <a href="#" style="display: inline-flex; align-items: center; margin-left: 30px"><i class="fa fa-user" style="color: white"></i>
                                                        <span class="span" style="padding-left: 10px; align-items: center; color: white">${user.name}</span></li>
                                                </a>
                                            </ul>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <a href="login" style="display: inline-flex; align-items: center; margin-left: 30px"><i class="fa fa-login" style="color: white"></i>
                                        <span style="padding-left: 10px; align-items: center; color: white">Đăng nhập</span>
                                    </a>
                                </c:otherwise>
                            </c:choose> 
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-xl-3 col-lg-3 col-md-3 col-sm-3 col logo_section">
                            <div class="full">
                                <div class="center-desk">
                                    <div class="logo"> <a href="groups"><img src="images/logo2.jpg" alt="logo"/></a> </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-7 col-lg-7 col-md-9 col-sm-9">
                            <div class="menu-area">
                                <div class="limit-box">
                                    <nav class="main-menu">
                                        <ul class="menu-area-main">
                                            <!--                                            <li class="active"> <a href="index.html">Home</a> </li>-->
                                            <li> <a href="#">Trang chủ</a> </li>
                                            <li> <a href="#">Giỏ hàng</a> </li>
                                            <li> <a href="#">Thông tin cá nhân</a> </li>
                                            <li> <a href="#">Tra cứu đơn hàng</a> </li>
                                            <!--                                            <li class="mean-last"> <a href="#contact">signup</a> </li>-->
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                        <!--                        <div class="col-xl-2 col-lg-2 col-md-2 col-sm-2">
                                                    <li><a class="buy" href="#">Login</a></li>
                                                </div>-->
                    </div>
                </div>
                <!-- end header inner --> 
        </header>
        <script src="js/jquery.min.js"></script> 
        <script src="js/popper.min.js"></script> 
        <script src="js/bootstrap.bundle.min.js"></script> 
        <script src="js/jquery-3.0.0.min.js"></script> 
        <script src="js/plugin.js"></script> 
        <!-- sidebar --> 
        <script src="js/jquery.mCustomScrollbar.concat.min.js"></script> 
        <script src="js/custom.js"></script>
        <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
    </body>
</html>