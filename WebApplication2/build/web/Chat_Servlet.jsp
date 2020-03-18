<%-- 
    Document   : Chat_Servlet
    Created on : 24 Dec, 2017, 7:30:35 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
 <!--       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
 
        <meta http-equiv="refresh" content="10" />   -->
        <title>JSP Page</title>
    </head>
    <body>
         
        <%
            response.setContentType("text/html;charset=UTF-8");
            session=request.getSession();
        try
        {
            if(session.getAttribute("id")==null)
            {   
                out.print("<h1 style='background-color: red;width: 360px'>U are no longer connected</h1>");
                out.print("<a href=\"index.html\" ><h3>  Click to login agaion</h3></a>");
                
            }   
            else
            {   
                //String myname=(String)session.getAttribute("myname");
                
                String myname=request.getParameter("myname");
                session.setAttribute("myname", myname);
                
                
                out.print("<input type='hidden' id='pp' value=" + myname+  ">");
                
                
                //String uname=(String)session.getAttribute("id");
                
                
                //session.removeAttribute("myname");
                
                
                
                out.print("");
                
                
                
                //RequestDispatcher rd=request.getRequestDispatcher("dating.jsp");
                //rd.forward(request, response);
                
                
                
                
            }   
                
                
                
        }  
        catch(Exception e)
        {
            
        }
            
        
            %>
            
            <script>
                
                var myname=document.getElementById("pp").value;
                
                window.open("chatting.jsp?myname="+myname);
                
           </script>
            
           
    </body>
</html>
