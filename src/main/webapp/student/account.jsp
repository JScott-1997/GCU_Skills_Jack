<%-- 
    Document   : account
    Created on : 5 Mar 2021, 16:39:17
    Author     : seanl
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Your account | GCU_Skills</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon32.png" sizes="32x32"/>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon16.png" sizes="16x16">
    </head>
    <body>
        <c:import url="../Navbar" />
        <div id="section-container">
            <section class="image-section" id="register">
                <div class="image-box-content">
                    <h2>Welcome, ${student.firstName} ${student.lastName}</h2>      
                    <h3><a href="accountdetails.jsp" class="dashlink">Account Details</a></h3>
                    <h3><a href="yourlessons.jsp" class="dashlink">Your Lessons</a></h3>
                    <h3><a href="../courses.jsp" class="dashlink">View/Register for Courses</a></h3>
                </div>
            </section>
        </div>
    </body>
</html>

