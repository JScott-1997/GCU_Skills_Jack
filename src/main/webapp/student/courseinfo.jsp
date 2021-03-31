<%-- 
    Document   : courseinfo
    Created on : 12 Mar 2021, 11:16:29
    Author     : seanl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Course Information | GCU_Skills</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon32.png" sizes="32x32"/>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon16.png" sizes="16x16">  
        <meta name="viewport" content="width=device-width, initial-scale=1.0">     
    </head>
    <body>
        <c:import url="../Navbar" /> 
        <section class="image-section" id="courseinfo">
            <div class="image-box-content">
                <h2>Course Information</h2>    
                <table id="courseinfotable">
                    <tr>
                        <th>Course Title: </th>
                        <td id="studentid">Introduction to Microsoft Office</td>
                    </tr>
                    <tr>
                        <th>Tutor: </th>
                        <td>Tony Soprano</td>
                    </tr>
                    <tr>
                        <th>Start Date/Time: </th>
                        <td>11/03/2021 15:00</td>
                    </tr>
                    <tr>
                        <th>Current Status: </th>
                        <td><i>Ongoing</i></td>
                    </tr>  
                    <tr>
                        <th>No. of Lessons: </th>
                        <td>3</td>
                    </tr>
                    <tr>
                        <th>Description: </th>
                        <td><p>Microsoft Office, or simply Office, is a family of client software, server software, and services developed by Microsoft. It was first announced by Bill Gates on August 1, 1988, at COMDEX in Las Vegas. 
                                Initially a marketing term for an office suite (bundled set of productivity applications), the first version of Office contained Microsoft Word, Microsoft Excel, and Microsoft PowerPoint. 
                                Over the years, Office applications have grown substantially closer with shared features such as a common spell checker, OLE data integration and Visual Basic for Applications scripting language. 
                                Microsoft also positions Office as a development platform for line-of-business software under the Office Business Applications brand. 
                                On July 10, 2012, Softpedia reported that Office was being used by over a billion people worldwide.
                            </p>
                        </td>
                    </tr>  
                </table>
                <a href="yourlessons.jsp" class="bottomlink">&#x21a9; Back to Your Lessons</a>
                <a href="#" class="bottomlink"  style="color: red">End Lessons</a>
            </div>
        </section>
    </body>
</html>

