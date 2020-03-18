<%-- 
    Document   : session_remove
    Created on : 21 Dec, 2017, 6:20:46 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if(session.getAttribute("id")!=null)
            {
                session.removeAttribute("id");
                
            }
            
            response.sendRedirect("index.html");
            
            
            %>
    </body>
</html>
