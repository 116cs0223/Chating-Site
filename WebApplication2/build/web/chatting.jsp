<%-- <%@page import="java.sql.ResultSet"%>

    Document   : chatting
    Created on : 27 Dec, 2017, 8:09:24 PM
    Author     : hp
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.controller.Provider"%>
<%@page import="com.controller.Chat_Me"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.ObjectInputStream"%>
<%@page import="java.util.Vector"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.ObjectOutputStream"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="15" />
        <title>Chatting page</title>
        <style type="text/css">
        #div1
        {
        
            height: 400px;
            width: 50%;
            background-color: silver;
            padding-left: 20px;
            padding-top: 20px;
          <%--   margin-left: 300px; --%>
            margin-top: 20px;
            margin-bottom: 20px;
            overflow: scroll;
            border-radius: 20px;
            border-color: white;
            color: red;
            font-size: 19px;
            
        }
        #l
   	{
                
   		background-color: #990099;
                text-align: left;
   	}
   	#r
   	{
                
   		background-color: white;
                text-align: right;
   	}
        
        label{
            padding-left: 300px;
            color: blue;
            width: 100px;}
        #txt
        {
            width: 300px;
        }
            
       #btn
       {
           color: blue;
           width: 50px;
       }
       
        </style>
        
        
    </head>
    
    <body>
        <div id="div1">
            
        </div>
        
        
        <p onclick="some()">Refresh</p>
        
    
            <%
            
            try
            {
                
                String uname=(String)session.getAttribute("id");
                String myname=request.getParameter("myname");
                out.print("<form action='Input_File' method='post'> \n" +
    "        <input type='hidden' name='myname' value=" +myname+ ">\n" +
    "        <input type='text' id='txtt'  name='txt' size='40' style='height: 30px;'>\n" +
    "        <input type='submit'   value='send' style='height: 30px;'>\n" +
    "    </form>");


               // out.print("<h1> Uname:   </h1>"+uname);
               // out.print("<h1> Myname:   </h1>"+myname);

                
                
               out.print("<input type='hidden' id='uname' value= " +uname+">");
               out.print("<input type='hidden' id='myname' value= " +myname+">");
               //-------------------------------------------------------------------
                String str="";
                Connection con=Provider.getConnection();

                String str1="select gender from user_details where uname=?";
                PreparedStatement pst=con.prepareStatement(str1);
                pst.setString(1, uname);
                ResultSet rs=pst.executeQuery();
                while(rs.next())
                {
                    if(rs.getString(1).equalsIgnoreCase("f"))
                    {
                        str+=uname+"_"+myname+".txt";
                    }
                    else 
                    {
                        str+=myname+"_"+uname+".txt";
                    }
                }
                
               File f1=new File("E:/", "chatting/"+str);
               //-------------------------------------------------------------------
               if(f1.exists())
               {
                    ObjectInputStream ois1=new ObjectInputStream(new FileInputStream(f1));
                    Vector<Chat_Me> des=(Vector<Chat_Me>)ois1.readObject();
                    Iterator<Chat_Me> iter1=des.iterator();
                    String strr="";
                    while(iter1.hasNext())
                    {
                        Chat_Me ch=iter1.next();
                        if(ch.get_uname().equalsIgnoreCase(uname))
                        {
                            strr="<p id='r'>"+   ch.get_msg() +"</p>"+strr;//
                        }
                        
                        else
                        {
                            strr="<p  id='l'>"+ ch.get_msg() +"</p>"+strr;//
                        }
                    }
                    String asd="i love you";
                    out.print("<input type='hidden' id='hd' value="+"\""+strr+"\""+" >"); 
                   //out.print(" abc\"");
                   //out.print(strr);
                    
                    
               }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
           
           %>
            
           
          <script>
               function some()
               {
                   window.location.reload();
               }
               window.onload=function ()
               {
                   
                   var a=sessionStorage.getItem("ite");
                   if(a!=null)
                   {
                       // alert(a)
                        document.getElementById("txtt").value=a;
                        //document.getElementById('txtt').value="true onload";
                   }
                   else
                   {
                        //document.getElementById('txtt').value="false onload";
                   }
               }
               window.onbeforeunload=function ()
               {
                   var k=document.getElementById("txtt").value;
                   sessionStorage.setItem("ite",k);
                   //document.getElementById('txtt').value="onbeforeload";
               }
               
                var div = document.getElementById('div1');
                var input = document.getElementById('hd').value;
		div.innerHTML += input;
                
                
           </script>
            
            
            
    </body> 
    
   
</html>