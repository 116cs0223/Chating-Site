/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class Fourth extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //-------------
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //--------------
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        
        PrintWriter out=response.getWriter();
        out.print("<ul style=\"margin: 0px;padding: 0px;overflow: hidden;list-style-type: none;background-color: #aaa\">\n" +
"		<li style=\"float: left; display: inline-block;color: white;text-align: center;padding: 14px 16px;text-decoration: none;\n" +
"		\"> <a href=\"home.jsp\">HOME</a></li>\n" +
"		<li style=\"float: left; display: inline-block;color: white;text-align: center;padding: 14px 16px;text-decoration: none;\n" +
"		\"> <a href=\"logout.jsp\">LOGOUT</a></li>\n" +
"		<li style=\"float: left; display: inline-block;color: white;text-align: center;padding: 14px 16px;text-decoration: none;\n" +
"		\"> <a href=\"profile.jsp\">PROFILE</a></li>\n" +
"		\n" +
"		<li style=\"float: left; display: inline-block;color: white;text-align: center;padding: 14px 16px;text-decoration: none;\n" +
"		\"> <a href=\"dating.jsp\">DATE</a></li>\n" +
"	</ul>	");
        
     
        
        HttpSession session=request.getSession();
        
        
        if(session.getAttribute("id")==null)
          {
             out.print("<h1 style='background-color: red;width: 360px'>U are no longer connected</h1>");
             out.print("<a href=\"index.html\" ><h3>  Click to login agaion</h3></a>");
          }
        else
        {
        
            String uname=request.getParameter("uname");
            String name=request.getParameter("name");
            int age=0,day,month,year;
            String city=null,country=null,gender=null,date=null; 
            Date dob=null;

            try
            {
                age=Integer.parseInt(request.getParameter("age"));


            gender=request.getParameter("gender");
            date=request.getParameter("dob");            
            day=Integer.parseInt(date.substring(0, 2));
             month=Integer.parseInt(date.substring(3,5));
             year=Integer.parseInt(date.substring(6,10));
            System.out.println(day+" "+month+" "+year+"\n");
             dob=new Date(year-1900, month-1,day);
            System.out.println(dob);
             city=request.getParameter("city");
             country=request.getParameter("country");
            }
            catch(Exception e)
            {

            }
            if(city==null)
                city="";
            if (country==null)
                country="";
            try
            {
              Connection con=Provider.getConnection();
              String str="insert into user_details values(?,?,?,?,?,?,?)";
              PreparedStatement pst=con.prepareStatement(str);
              pst.setString(1, uname);
              pst.setString(2, name);
              pst.setInt(3, age);
              pst.setString(4, gender);
              pst.setDate(5, dob);
              pst.setString(6, city);
              pst.setString(7, country);

              int check = pst.executeUpdate();




              if(check>0)
              {
                 
                session.setAttribute("message","<h1>Profile updated Successfully</h1>");

                 
                  RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
                  rd.forward(request, response);

              }


              else
              {

                  session.setAttribute("message", "<h1>Profile updation UnSuccessfully</h1>");
                  RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
                  rd.forward(request, response);
              }




            }


            catch(Exception e)
            {
                session.setAttribute("message", "Ooops Error");
                int i=0;
                
                //RequestDispatcher rd=request.getRequestDispatcher("Third");
                //rd.forward(request, response);
                RequestDispatcher rd=request.getRequestDispatcher("profile.jsp");
                rd.forward(request, response);


            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() 
    {
        
        return "Short description";
    }// </editor-fold

}






