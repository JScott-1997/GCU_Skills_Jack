<%-- 
    Document   : register
    Created on : 2 Mar 2021, 11:59:14
    Author     : jackb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Register | GCU_Skills</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon32.png" sizes="32x32"/>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon16.png" sizes="16x16">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <c:import url="Navbar" /> 
        <div id="section-container">
            <section class="image-section" id="register">
                <div class="form-box-content">
                    <h2>Registration successful. You can now <a href="login.jsp">log in.</a></h2>
                    <p>Your student ID number is: <b>${studentid}</b></p>
                </div>
            </section>
        </div>
    </body>
</html>


