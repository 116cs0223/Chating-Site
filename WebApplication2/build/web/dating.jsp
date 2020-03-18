<%-- 
    Document   : dating
    Created on : 22 Dec, 2017, 11:50:35 AM
    Author     : hp
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <style type="text/css">
             
        
	.l{
		width: 20%;
		float: left;
	}
	.m{
		width: 60%;
		float: left;
	}
	.r{
		width: 20%;
		float: right;
	}
	

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
        .tx
        {
            color: #555555;
            float: left;
            width: 20%;
            height: 200px;
            
        }


    </style>
    </head>
    <body>
    
        <ul>
            	<li style=""> <a href="home.jsp">HOME</a></li>
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
            try
            {
                
                //String uname=request.getParameter("uname");
                String uname=(String)session.getAttribute("id");
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("hello baby");
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","litindia");
                //out.print(con);
                String str="select * from user_details where uname=?";

                PreparedStatement pst=con.prepareStatement(str);
                pst.setString(1, uname);

                ResultSet rs=pst.executeQuery();
                System.out.println("result set is"+rs);
                if(!rs.next())
                {
                    RequestDispatcher rd=request.getRequestDispatcher("Third");
                    request.setAttribute("status", "incomplete");
                    rd.forward(request, response);
                    
                }
                else
                {
                    String str1="select uname,name from user_details where gender!=(select gender from user_details where uname=?)";
                    PreparedStatement pst1=con.prepareStatement(str1);
                    pst1.setString(1, uname);
                    ResultSet rs1=pst1.executeQuery();
                    out.print("<br><br>");
                    out.print("<div class=\"l\">\n" +
"&nbsp;\n" +
"	</div>\n" +
"	<div class=\"m\">");
                    out.print("<table border='2px' cellpadding='2px' cellspacing='2px' width='100%' bgcolor='#00ffff'>");
                    while(rs1.next())
                    {
                       
                        // action='Chat_Servlet.jsp' method='post'
                         out.print("<form action='Chat_Servlet.jsp' method='post'>\n" +
"			<tr  >\n" +
"				<td height='30px'>\n" +
"					<input type='hidden' value='"+rs1.getString(1)+"' name='myname' ></input>\n" +
"				</td>\n" +
"				<td width='50%' bgcolor='#ff0000'>\n" +
"					<label style='color: #33ff66;'>"+rs1.getString(2)+"</label>\n" +
"					\n" +
"				</td>\n" +
"\n" +
"				<td width='50%' bgcolor='#00ffff'>\n" +
"					<input  type='submit' ' value='ClickMeToChat' height='50px' style='background-color: #00ffff ;height: 30px'></button>\n" +
"				</td>\n" +
"			</tr>\n" +
"		</form>");
                        
                         
                    }
                    out.print("</table>\n");
                    
                    
                    
                    
                    
                    
                    
                }   
            }       
            catch(Exception e)
            {
                e.printStackTrace();
            
            }
            
              
                
              
                
            
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
     
      
    <!--   <textarea id='txtarea'  class='tx'>
      
      
      
     </textarea>
      
      
      <script>
          var txt=document.getElementById('txtarea');
          var a="hihi  ";
          function hihi()
          {   
              
              txt.value=txt.value+a;
              
          }   
              
             
          
      </script>   -->
     
    </body>
</html>

