/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.controller.*;
/**
 *
 * @author hp
 */
public class Input_File extends HttpServlet 
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            HttpSession session=request.getSession();
            String uname=(String)session.getAttribute("id");
            String myname=request.getParameter("myname");
            String msg=request.getParameter("txt");
            out.print("<h1> your name "+uname+"    "+" myname "+myname+" msg "+ msg+"</h1>");
            
            //----------------------------------------------------------------------------
            
            //------------------------------------------------------------
            
            
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
                    str=uname+"_"+myname+".txt";
                }
                else 
                {
                    str=myname+"_"+uname+".txt";
                }
               //out.println("inside");
            }
            
            //------------------------------------------------------------
            Chat_Me chatme=new Chat_Me();
            chatme.set_msg(msg);
            chatme.set_uname(uname);
            chatme.set_myname(myname);
            
            
            Vector<Chat_Me> v=new Vector<>();
            
            File fl=new File("E:/", "chatting/"+str);
            if(fl.exists())
            {
                Vector<Chat_Me> vw;
                ObjectInputStream ois;
                ois = new ObjectInputStream(new FileInputStream(fl));
                Vector<Chat_Me> vv;
                vv = new Vector<Chat_Me>();
                
                vw=(Vector<Chat_Me>) ois.readObject();
                
                
                Iterator<Chat_Me> iter=vw.iterator();
                while(iter.hasNext())
                {
                    Chat_Me cht1=iter.next();
                    vv.add(cht1);
                }
                vv.add(chatme);
                ois.close();
                
                ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(fl));
                oos.writeObject(vv);
                oos.close();
            }
            else
            {
                v.add(chatme);
                ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(fl));
                oos.writeObject(v);
                oos.close();
                
            }
            
            
            
            
            
            
            
            
            //-----------------------------------------------------------------------------
            response.sendRedirect("chatting.jsp?myname="+myname);
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
    }// </editor-fold>

}
