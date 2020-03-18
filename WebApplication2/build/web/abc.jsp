 
<%-- 
    Document   : abc
    Created on : 6 Dec, 2017, 10:40:47 AM
    Author     : hp
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
   <style type="text/css">
	ul
	{
		padding: 3px;
		background-color: #ffbb00;
                
                
	}
        
        li
        {
            
            
            width: 250px;
            
            
        }
        
    </style>
    
        
    </head>
    <body>
        
        <ul>
            <li>
                    successful
            </li> 
        
        </ul>
        
        <%
            String uname=request.getParameter("uname");
            String password=request.getParameter("password");
            
            out.print("<h1> uname ="+uname +" password "+password+"  </h1>" );
            
            
            %>
            
    </body>
</html>


