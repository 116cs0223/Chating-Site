<%-- 
    Document   : newjsp
    Created on : 2 Dec, 2017, 8:04:13 AM
    Author     : hp
--%>


<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Enumeration"%>
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
        
      <%--    <%
          //String uname=request.getParameter("uname");
          //String password=request.getParameter("password");
          //out.println ("<a href='abc.jsp?uname="+uname+"&password="+password+"'>CLICK to see profile </a>");
          
          RequestDispatcher rd=request.getRequestDispatcher("abc.jsp");
          rd.include(request, response);
          
	%>    --%>
      <ul>
            	<li> <a href="home.jsp">HOME</a></li>
            	<li> <a href="logout.jsp">LOGOUT</a></li>
            	<li> <a href="profile.jsp">PROFILE</a></li>
                <li> <a href="dating.jsp">DATE</a></li>
            </ul>   	
                
      <%
          if(session.getAttribute("id")==null)
          {
              
      %>
          
        <h1 style="background-color: red;width: 360px">U are no longer connected</h1>
        <a href="index.html" ><h3>  Click to login agaion</h3></a>
          <%
              
          }
          else
          {
              
             if(session.getAttribute("message")!=null)
            {
                out.print("<h1>" +session.getAttribute("message")+ "</h1>");

                session.removeAttribute("message");
            
            }   
            //String uname=request.getParameter("uname");
            /*try
            {
                String uname=(String)session.getAttribute("id");
                Class.forName("oracle.jdbc.OracleDriver");

                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","litindia");
                //out.print(con);
                String str="select uname from user_details where gender!=(select gender from user_details where uname=?)";
                PreparedStatement pst=con.prepareStatement(str);
                pst.setString(1, (String)session.getAttribute("id"));

                ResultSet rs=pst.executeQuery();
                while(rs.next())
                {
                    out.print("<p>"+rs.getString(1)+"</p>");
                    
                    
                    
                }
                
                
                pst.close();
                con.close();
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
                
            }*/
            
            
            
            
            
            
           
            //-----------------------------------------
                  /*Enumeration e=session.getAttributeNames();
                  while(e.hasMoreElements())
                  {
                      out.println(session.getAttribute((String)e.nextElement()));
                  }*/
            //------------------------------------------

            //String str=(String)session.getAttribute("id");
            //out.print("<h1>"+uname+"</h1>");
            //out.println(" <a href='Third?uname="+uname+"' > " + " <ul   style='opacity:1 ; background-color: aqua;padding: 0px;width: 200px;'><li><h1>Your profile</h1></li></ul></a> ");
          }
      %>
      
      
    </body>
    
</html>