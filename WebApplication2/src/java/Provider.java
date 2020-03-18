/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hp
 */
import java.sql.*;
import java.util.*;
public class Provider 
{
    
    public static Connection getConnection()
    {
        Connection con=null;
        try
        {
        Class.forName("oracle.jdbc.OracleDriver");
        
        con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","litindia");
        if(con!=null)
            return con;
        else
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
    
}
