/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zj
 */
public class SQLiteInit {
    
    private  Connection c = null;
    private  Statement stmt = null;
    
    public SQLiteInit(){
         try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:cipc_clouds.db");
          c.setAutoCommit(false);
          System.out.println("Opened database successfully");
          } catch ( Exception e ) {

          System.err.println( e.getClass().getName() + ": " + e.getMessage() );         
        }
    }
    
    
    public boolean   DropTable      (String sql)throws SQLException{
       
        try {    
          stmt = c.createStatement();
          stmt.executeUpdate(sql);
          stmt.close();
        } catch ( Exception e ) {

          System.err.println( e.getClass().getName() + ": " + e.getMessage() );    
          return false;
        }
        System.out.println("Table Drop successfully");

        return true;
    }   
    public boolean   CreateTable    (String sql)throws SQLException{
        
        try {
          stmt = c.createStatement();
          stmt.executeUpdate(sql);
          stmt.close();
        } catch ( Exception e ) {

          System.err.println( e.getClass().getName() + ": " + e.getMessage() );    
          return false;
        }
        System.out.println("Table created successfully");

        return true;
  }   
    public boolean   Insert          (String sql)throws SQLException{

        try {
          stmt = c.createStatement();   
          stmt.executeUpdate(sql);
          stmt.close();
          //c.commit();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          
          return false;
        }
       // System.out.println("Records created successfully");
        
        return true;
    }   
    public ResultSet Select          (String sql)throws SQLException{
       
        ResultSet rs = null;
        try {
         
          stmt = c.createStatement();
          rs = stmt.executeQuery( sql ); 
          stmt.close();  

        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          return null;
        }
        //System.out.println("Operation done successfully");
        return rs;
    
    }
    public boolean   Update          (String sql)throws SQLException{

        try {
          stmt = c.createStatement();
          //String sql = "UPDATE COMPANY set SALARY = 25000.00 where ID=1;";
          stmt.executeUpdate(sql);          
          //c.commit();

        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          return false;
        }
       // System.out.println("Operation done successfully");
        return true;
    }
    public boolean   Delete          (String sql)throws SQLException{

        try {

          stmt = c.createStatement();
          //String sql = "DELETE from COMPANY where ID=2;";
          stmt.executeUpdate(sql);
          //c.commit();   
          stmt.close();

        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          return false;
        }
       // System.out.println("Operation done successfully");
        return true;
    }
    
     public void realese(){
        
        try {
            
            if(!c.isClosed()){            
                    c.commit();
                    c.close();
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            System.out.println("Connection Release !");
            
      
    }
}
