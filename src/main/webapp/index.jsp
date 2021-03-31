<%-- 
    Document   : index
    Created on : 5 Mar 2021, 14:17:35
    Author     : seanl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Home | GCU_Skills</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon32.png" sizes="32x32"/>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon16.png" sizes="16x16">
    </head>
    <body>
        <c:import url="Navbar" />
        <div class="section-container">
            <section class="welcome-image-section" id="welcome">
                    <h1>Welcome to GCU_Skills</h1>
                 <img src="images/GCU_SkillsLogoMed.png" alt="GCU_Skills"/>
                    <h2>Please create an account or login to register for lessons</h2>
            </section>  
        </div>
          <div class="pageline"></div>
            <div class="section-container">
                <section class="image-section" id="about">
                    <div class="image-box-content" id="apara">
                        <h2>About GCU_Skills</h2>
                            <p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent pretium enim ac aliquam tincidunt. Sed auctor libero eu semper rutrum. Vivamus non varius nisi. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Curabitur eu lectus quis leo malesuada accumsan. Nulla congue eu dolor et laoreet. Vivamus bibendum auctor eros. Etiam vitae urna fermentum, scelerisque neque eget, molestie quam. Nulla fermentum purus vel arcu feugiat convallis. Nulla commodo viverra massa, non volutpat orci vehicula ut. Aenean fermentum fringilla ultrices. Proin nec ex hendrerit, rutrum leo id, semper leo. Aliquam efficitur, eros sed pretium venenatis, felis nunc aliquam tortor, et porttitor leo risus at neque.</p>

                            <p>Mauris est augue, dictum sit amet fermentum et, semper vel tortor. Quisque sodales, velit vel posuere elementum, augue felis suscipit sapien, at molestie libero leo vel ligula. Curabitur at nunc diam. Etiam tempus mattis ullamcorper. Maecenas vitae risus velit. Aliquam erat volutpat. Sed pellentesque sagittis augue. Morbi fermentum urna id odio lobortis, ac dictum massa pellentesque. Mauris metus nunc, volutpat eget pharetra eu, accumsan vel libero. Sed dictum aliquet lobortis. Pellentesque in arcu cursus, interdum diam vel, tristique arcu. Morbi et lacus ut nulla dictum ultricies. Quisque elementum mi tortor, eget efficitur libero ultrices blandit. Aliquam vitae iaculis dolor, ultricies pharetra est. Praesent sollicitudin justo eget odio faucibus, sodales scelerisque justo sagittis. In sed ultrices justo.</p>

                            <p>Etiam lectus velit, feugiat ultricies metus eget, euismod pharetra lacus. Fusce a sagittis nisi. Vivamus tristique porttitor lectus eu ullamcorper. Phasellus eget iaculis leo. Vivamus sed lacus malesuada, convallis tellus nec, congue eros. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cras sed blandit dui. Vestibulum et leo non libero accumsan cursus. Cras ornare mauris id velit placerat, in consequat elit suscipit. Integer ultricies, nibh et tempus volutpat, mauris ligula volutpat tellus, porttitor egestas purus nunc a dolor. Integer dui diam, viverra in egestas eu, rhoncus id ipsum. Proin ex purus, molestie id molestie et, mattis non neque. Quisque euismod pretium luctus. Integer ex dolor, bibendum sit amet purus eget, iaculis blandit ipsum. Sed eget leo nec neque mollis placerat in id metus.</p>          
                    </div>
                </section> 
       </div>
          <div class="pageline"></div>
         <div class="section-container">
            <section class="image-section" id="contact">
            <div class="form-box-content"> 
                    <h2>Contact us</h2>
                    <p>Please fill out the form below, call us on 0141-123-45-67, or email support@gcuskills.co.uk.</p>
                    <p>We aim to respond to enquiries within 24 hours.</p>
                    <form action="contact.jsp" method="POST">    
                        <label>     
                          <input type="text" class="input" name="fullname" placeholder="Full name"/>                   
                          <div class="line-box">          
                            <div class="line"></div>        
                          </div>    
                        </label>
                        <label>     
                            <input type="email" class="input" name="contactemail" placeholder="Email address"/>        
                                <div class="line-box">          
                                <div class="line"></div>        
                            </div>    
                        </label>     
                        <label>     
                            <input type="text" class="input" name="conntactno" placeholder="Contact number (optional)"/>      
                            <div class="line-box">        
                            <div class="line"></div>      
                            </div>    
                      <label>     
                            <textarea name="enquiry" id="enquiry" class="input"/></textarea>      
                            <div class="line-box">        
                            <div class="line"></div>      
                            </div>    
                        </label> 
                        <button type="submit">Submit</button>  
                    </form> 
                </div>
            </section>
        </div>
    </body>
</html>
