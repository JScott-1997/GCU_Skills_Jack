<%-- 
    Document   : login
    Created on : 4 Mar 2021, 06:48:10
    Author     : jackb
--%>

<%@page contentType = "text/html" pageEncoding = "UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
    <head>
        <title>Login | GCU_Skills</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon32.png" sizes="32x32"/>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon16.png" sizes="16x16">      
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <c:import url="Navbar" />  
        <section class="image-section" id="login">
            <div class="form-box-content">
                <h2>Log in to your Student account</h2>
                <form action="${pageContext.servletContext.contextPath}/login" method="POST">    
                    <label>     
                        <input type="text" class="input" name="emailaddress" placeholder="Email"/>                   
                        <div class="line-box">          
                            <div class="line"></div>        
                        </div>    
                    </label>
                    <label>     
                        <input type="password" class="input" name="password" placeholder="Password"/>        
                        <div class="line-box">          
                            <div class="line"></div>        
                        </div>    
                    </label>         
                    <button type="submit">Log in</button>
                    <label><small style="color:red">${requestScope.error}</small></label>
                    <label>
                        <small>Don't have an account? <a href="register.jsp">Register</a></small>
                    </label>
                </form> 
                <small><a href="tutorlogin.jsp" class="bottomlink">Tutor login</a></small>
                <small><a href="adminlogin.jsp" class="bottomlink">Administrator login</a></small>
            </div>
        </section>
    </body>
</html>

