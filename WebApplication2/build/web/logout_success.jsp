<%-- 
    Document   : logout_success
    Created on : 21 Dec, 2017, 9:10:30 PM
    Author     : hp
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
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
        
        
        <%!
            Connection con2=null;
        %>
        <%
            
            if((session.getAttribute("id")==null))
            {
                out.print("<h1 style='background-color: red;width: 360px'>U are no longer connected</h1>");
                RequestDispatcher rd=request.getRequestDispatcher("index.html");
                rd.include(request, response);
            }
            else
            {
               /* try
                {
                    con2=Provider.getConnection();
                    String strng="update log set choice=0 where uname=?";
                    PreparedStatement pstmnt=con2.prepareStatement(strng);
                    pstmnt.setString(1, (String)session.getAttribute("id"));
                    int choice=pstmnt.executeUpdate();
                    session.removeAttribute("id");
                    pstmnt.close();
                    con2.close();
                }
                catch(SQLException e)
                {
                    //-------------------
                    
                }*/
                response.sendRedirect("Servlet_Logout");
                
            }
            
            
            %>
            
            
    </body>
</html>
