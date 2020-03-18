<%-- 
    Document   : logout
    Created on : 21 Dec, 2017, 9:04:35 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <style type="text/css">
	li
	{
		float: left;
		
	}
	ul
	{
		margin: 0px;
		padding: 0px;
		overflow: hidden;
		list-style-type: none;background-color: #333;

	}
	li a
	{
		display: inline-block;
		color: white;
		text-align: center;
		padding: 14px 16px;
		text-decoration: none;
	}
	li a:HOVER {
		background-color: aqua;
	}


    </style>
    </head>
   
    <body>
        
        <%
            if(session.getAttribute("id")==null)
            {
                out.print("<h1 style='background-color: red;width: 360px'>U are no longer connected</h1>");
                RequestDispatcher rd=request.getRequestDispatcher("index.html");
                rd.include(request, response);
            }
            else
            {
            %>
        <ul>
		<li> <a href="home.jsp">HOME</a></li>
		<li> <a href="logout.jsp">LOGOUT</a></li>
		<li> <a href="profile.jsp">PROFILE</a></li>
		
        </ul>
        
        <h2 style='background-color: palegoldenrod; width: 500px'>Do u really want to <i>log out</i> from your account?</h2>
	<a href='logout_success.jsp'>
	<input type='button' value='Yes' style='background-color: red;width: 60px'></a>
	<a href='home.jsp'>
	<input type='button' value='No' style='background-color: green;width: 60px'></a>
        <%
            }
            %>
        
    </body>
</html>
