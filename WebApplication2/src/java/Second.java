/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author hp
 */
public class Second extends HttpServlet 
{
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement pst=null;
        String str=null;
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            
            
            /* TODO output your page here. You may use following sample code. */
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Second</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Second at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
            
            String uname=request.getParameter("uname");
            String password=request.getParameter("password");
            String email=request.getParameter("email");
           
            str="select * from dateme where uname=?";
            con=Provider.getConnection();
            pst=con.prepareStatement(str);
            pst.setString(1,uname);
            rs=pst.executeQuery();
            if(rs.next() )
            {
                  out.print("<h2 style=\"color: red\">"+"USERNAME EXITS"+"</h2>");
                  //Thread.sleep(2000);
                  pst.close();
                  RequestDispatcher rd=request.getRequestDispatcher("register.html");
                  rd.include(request, response);
            }
            else 
            {
                if(password.length()<=9)
                {
                    out.print("<h2 style=' color: orange' >"+"OOPs--Error  PASSWORD LENGTH MINIMUM 10 "+"</h2>");
                //    Thread.sleep(1000);
                    RequestDispatcher rd=request.getRequestDispatcher("register.html");
                    rd.include(request, response);
                    System.out.println("10");
                    
                }
                else
                {
                    System.out.println("1");
                    str="insert into dateme values (?,?,?)";
                     System.out.println("2");
                   
                    pst=con.prepareStatement(str);
                    pst.setString(1,uname);
                    pst.setString(2,password);
                    pst.setString(3,email);
                    
                    System.out.println("3");
                   
                    int check=pst.executeUpdate();
                    System.out.println("4");
                   
                    if(check!=0)
                    {
                        out.print("<h1 style=' color: blue' >"+"Registered Successfully"+"</h1>");
                        
                        RequestDispatcher rd=request.getRequestDispatcher("index.html");
                        rd.include(request, response);

                    }
                     System.out.println("5");
                   
                }
            }
            pst.close();
            con.close();
            
            
            
        }
        catch(Exception e)
        {
           System.out.println(e.getStackTrace());
            
        }
    }
}


