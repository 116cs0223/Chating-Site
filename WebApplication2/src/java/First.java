/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 *
 * @author hp
 */
public class First extends HttpServlet
{
    JButton b1=null,b2=null;
    JLabel l1=null;
    JFrame jf=null;
    HttpServletRequest rq=null;
    HttpServletResponse rs=null;
    HttpSession session=null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        rq=request;
        /*System.out.println(request.equals(rq));
        
        System.out.println("----------------------------");
        System.out.println(rq.getParameter("password"));
        System.out.println("----------------------------");
        */
        
        session = request.getSession(); 
        rs=response;
        PrintWriter out = response.getWriter();

        if(session.getAttribute("id")==null)
        {
            Connection con=null;
            ResultSet rs=null;
            String r_password=null;
            response.setContentType("text/html;charset=UTF-8");
            try 
            {
                
                 con=Provider.getConnection();
                /* TODO output your page here. You may use following sample code. */
               /* out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet First</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet First at " + request.getContextPath() + "</h1>");
                out.println("</body>");
                out.println("</html>");*/
                //Enumeration e= request.getParameterNames();
                /*java.util.Map mp=request.getParameterMap();
                java.util.Set key=mp.keySet();
                String a3[]=(String [])key.toArray();
                for(int i=0;i<a3.length;i++)
                {
                    System.out.print(a3[i]);
                }*/


                /*String a[]=request.getParameterValues("uname");

                int size=a.length;
                for(int i=0;i<size;i++)
                {
                    System.out.print(a[i]);
                }
                System.out.println();*/

                /*while(e.hasMoreElements())
                {
                    System.out.println(  request.getParameter(e.nextElement().toString()));
                }*/


                String uname=request.getParameter("uname");
                //System.out.println(uname);
                String password=request.getParameter("password");
                String str="select password from dateme where uname=?";
                PreparedStatement pst=con.prepareStatement(str);
                pst.setString(1,uname);
                rs=pst.executeQuery();
                if(rs.next())
                {
                    r_password=rs.getString(1);
                }
                
                if(r_password==null)
                {
                    pst.close();
                    con.close();
                    HttpSession session=request.getSession();
                    //session.setAttribute("my", 1);
                    
                    out.print("<h5 style=\"color: red\">"+"INVALID USERNAME\n\n"+"</h5>");
                    RequestDispatcher rd=request.getRequestDispatcher("index.html");
                    rd.include(request, response);
                    /*int i=0;
                    while(i<30)
                    {
                        
                        Thread.sleep(1000);
                        System.out.println(session.getAttribute("my"));
                        session.setAttribute("id", (Integer)session.getAttribute("my")+1);
                        i++;
                    }*/
                    
                    
                }

                else if(!r_password.equalsIgnoreCase(password))
                {
                    pst.close();
                    con.close();

                    out.print("<h5 style=\"color: orange\">"+"INVALID PASSWORD \n\n"+"</h5>");
                    RequestDispatcher rd=request.getRequestDispatcher("index.html");
                    rd.include(request, response);
                }


                else
                {

                    pst.close();
                   

                    //Enumeration e=session.getAttributeNames();
                    //int i=0;
                    /*while(e.hasMoreElements())
                    {
                        e.nextElement();
                        i++;
                    }
                     String id="id"+i;
                        */
                    session.setAttribute("id", uname);
                    
                    String strng="update log set choice=1 where uname=?";
                    PreparedStatement pstmnt=con.prepareStatement(strng);
                    pstmnt.setString(1, uname);
                    int choice=pstmnt.executeUpdate();
                    pstmnt.close();
                    con.close();
                     //session.setAttribute("id", "1");
                     //out.println (session.getAttribute("id"));
                     //session.invalidate();
                     //out.println (session.getAttribute("id"));

                     //String a=(String)session.getAttribute("id");
                     //System.out.println(a);
                     
                    
                    
                     
                     RequestDispatcher rd=request.getRequestDispatcher("home.jsp");

                     rd.forward(request, response);


                    // out.print("<h1 color='blue'>"+"LOGIN SUCCESSFUL "+"</h1>");
                }

                

            }
            catch(Exception e)
            {

                System.out.println(e.getStackTrace());

            }
        }
        else
        {
            
            /*jf=new JFrame("LOGOUT");
            jf.setBounds(200, 200,300, 250);
            jf.setBackground(Color.yellow);
            l1=new JLabel("Do u want to log out from last logged in ?");
            l1.setBounds(25,20,200,30);
            b1=new JButton("Yes");
            b1.setBounds(50,70,80,20);
            b1.setBackground(Color.red);
            b1.addActionListener(this);
            b2=new JButton("No");
            b2.setBounds(170,70,80,20);
            b2.setBackground(Color.green);
            b2.addActionListener(this);
            jf.add(l1);
            jf.add(b1);
            jf.add(b2);
            jf.setLayout(null);
            jf.setVisible(true);*/
            out.print("<h2 style='background-color: red; width: 420px'>Do u want to <i>log out</i> from last account?</h2>\n" +
"	<a href='session_remove.jsp'>\n" +
"	<input type='button' value='Yes' style='background-color: red;width: 60px'></a>\n" +
"	<a href='home.jsp'>\n" +
"	<input type='button' value='No' style='background-color: green;width: 60px'>");
            
            
            
            
            
            
            
            
           
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
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
   
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold

    
    
    

}


