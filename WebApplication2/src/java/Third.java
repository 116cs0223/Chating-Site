
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class Third extends HttpServlet 
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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
    /*    response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Third</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Third at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } 
        */
        
        
        
        //String name=request.getParameter("name");
        // out.print("<ul style='background-color: pink;padding: 0px;'><li> <h2> Name: </h2> </li> <li>"+name+"</li></ul> </h2>");
        Connection con=null;
        HttpSession session=request.getSession();
        ResultSet rs=null;
        PreparedStatement pst=null;
        response.setContentType("text/html;charset=UTF-8");
       // System.out.println(uname);
        PrintWriter out = response.getWriter();
        
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
        
        
        
        if(session.getAttribute("message")!=null)
        {
            out.print("<h1>'" +session.getAttribute("message")+ "</h1>");
            
            session.removeAttribute("message");
            
        }
        
        if(session.getAttribute("id")==null)
          {
              out.print("<h1 style='background-color: red;width: 360px'>U are no longer connected</h1>");
              out.print("<a href=\"index.html\" ><h3>  Click to login agaion</h3></a>");
              
          }
        
        else
        {
            try  
            {
                 con=Provider.getConnection();
                 String str="select * from user_details where uname=?";
                 pst=con.prepareStatement(str);
                 String uname=(String)session.getAttribute("id");
                 pst.setString(1,uname);

                 rs=pst.executeQuery();

                 if(rs==null)
                 {
                     out.print("Error Occured");
                 }
                 else if(!rs.next())
                 { 
                        if(request.getAttribute("status")!=null)
                        {
                            out.print("<h1 style='color: #ff6600'>Complete Your Profile First</h1>");
                            
                        }
                        out.println("<h1>Your Profile is incomplete</h1>\n Give details");
                        out.print("<pre> \n"+
                    "<form action='Fourth' method='post'> \n" +
                        "<p>Usernmame : <input type='text' name='uname' value="+uname+ ">\n" +
                        "<p>Name :      <input  type='text' name='name'>\n" +
                        "<p>Age :       <input type='text' name='age'>" +" <p> must be inbetween 18 and 60 \n"+
                        "<p>Gender :    <input type='radio' name='gender' value='M'>Male <input type='radio' name='gender' value='F'>Female\n" +
                        "<p>DOB :       <input type='text' name='dob'>  dd-mm-yyyy format\n" +
                        "<p>City        <input type='text' name='city'>\n" +
                        "<p>Country     <input type='text' name='country'>\n" +
                        "<p>Submit      <input type='submit' value='submit'>\n" +
                     "</form>\n"+ 
                    "</pre>");

                        
            }
                 else
                 {
                     out.print("profile complete");

                     out.print("<pre><table >\n" +
    "			<tr >\n" +
    "				<th >\n" +
    "					Name---   \n" +
    "				</th>\n" +
    "				<th bgcolor=\"#33FF33\" width=\"200px\"  >\n" +rs.getString(2)+"</th>\n" +
    "			</tr>\n" +
    "			<tr >\n" +
    "				<th >\n" +
    "					Age---    \n" +
    "				</th>\n" +
    "				<th bgcolor=\"#33FF33\" width=\"200px\"  >\n" +rs.getInt(3)+"</th>\n" +
    "			</tr>\n" +
    "			<tr >\n" +
    "				<th >\n" +
    "					Gender--- \n" +
    "                                 </th>\n" +
    "				<th bgcolor=\"#33FF33\" width=\"200px\"  >\n" +rs.getString(4)+"</th>\n" +"</tr>\n" +
    "			<tr >\n" +
    "				<th >\n" +
    "					DOB---    \n" +
    "				</th>\n" +
    "				<th bgcolor=\"#33FF33\" width=\"200px\"  >\n" +rs.getDate(5).toString() +"</th>\n" +
    "			</tr>\n" +
    "			<tr >\n" +
    "				<th >\n" +
    "					City---   \n" +
    "				</th>\n" +
    "				<th bgcolor=\"#33FF33\" width=\"200px\"  >\n" +rs.getString(6) +"</th>\n" +
    "			</tr>\n" +
    "			<tr >\n" +
    "				<th >\n" +
    "					Country---\n" +
    "				</th>\n" +
    "				<th bgcolor=\"#33FF33\" width=\"200px\"  >\n" +rs.getString(7) +"</th>\n" +
    "			</tr>\n" +
    "\n" +
    "		</table></pre>  ");
                 }


            }


            catch(Exception e)
            {
                System.out.println(e.getStackTrace());

            }

        }

        
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
        processRequest(request, response);
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
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold
    

}
