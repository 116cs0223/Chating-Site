package com.controller;



import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hp
 */
public class Chat_Me implements Serializable
{
    private String uname;
    private String myname;
    private String msg;
    public void set_uname(String uname)
    {
        this.uname=uname;
    }
    public void set_myname(String uname)
    {
        this.myname=myname;
    }
    public void set_msg(String msg)
    {
        this.msg=msg;
    }
    public String get_msg()
    {
        return msg;
    }
    public String get_uname()
    {
        return uname;
        
    }
    public String get_myname()
    {
        return myname;
        
    }
    
}

