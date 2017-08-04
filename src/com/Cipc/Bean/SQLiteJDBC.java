/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Bean;

import com.Cipc.Frame.FileListModel;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Zj
 */
public class SQLiteJDBC {
    private  Connection c = null;
    private  Statement stmt = null;
    
    public SQLiteJDBC(){
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:cipc_clouds.db");
          c.setAutoCommit(true);
          System.out.println("Opened database successfully");
          } catch ( Exception e ) {

          System.err.println( e.getClass().getName() + ": " + e.getMessage() );         
        }
    }
    
     public  CloudsTreeeNode getTreeNode(String parentName,String nodeName){
         CloudsTreeeNode cn = null;
         String sql = "SELECT   ID,NAME,PARENTID FROM tree_tb WHERE   NAME ='"+ parentName.trim() + "';";
         try {     
		Statement statement = c.createStatement();
		ResultSet rs = statement.executeQuery(sql);
                cn = new CloudsTreeeNode(Common.treeNodeId ++ ,nodeName,rs.getInt("ID"),false);
              	
		} catch (SQLException e) {
			e.printStackTrace();
		}
         return cn;
     }
     public boolean dirNotExist(String dirName,int parentID){
         boolean notExist = false;
        int count = 0 ;
        
        String sql = "SELECT  COUNT(*) ID,NAME,PARENTID FROM tree_tb WHERE  NAME ='"+ dirName.trim() + "' AND PARENTID ="+parentID + ";";
                //System.out.println(sql);
		try {     
			Statement statement = c.createStatement();
			ResultSet rs = statement.executeQuery(sql);
                        count = rs.getInt(1);     		
		} catch (SQLException e) {
			e.printStackTrace();
		}

        if(count > 0)
            return false;
        else
            return true;
     }
     public boolean notExist(String path,String file){
        boolean notExist = false;
        int count = 0 ;
        
        String sql = "SELECT COUNT(*) PATH,FILE FROM file_tb WHERE   PATH ="+"'"+path.replace(" ", "")+ "'" +"AND FILE ="+ "'"+file.replace(" ", "")+ "'";
                //System.out.println(sql);
		try {     
			Statement statement = c.createStatement();
			ResultSet rs = statement.executeQuery(sql);
                        count = rs.getInt(1);     		
		} catch (SQLException e) {
			e.printStackTrace();
		}

        if(count > 0)
            return false;
        else
            return true;
    }
    
     public void setFileListModel(FileListModel listModel,String path){
        listModel.clear();
         
         DefaultMutableTreeNode temp = null;  
		String sql = "SELECT  PATH,FILE FROM file_tb WHERE   PATH ="+ "'"+path.replace(" ", "")+ "'";
                //System.out.println(sql);
		try {
			Statement statement = c.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
			
				//System.out.println(rs.getString("FILE"));
                                listModel.addElement(rs.getString("FILE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }
    public void realese(){
        
        try {
            
            if(!c.isClosed()){
                Common.sem_syncdb.acquire();
                    //c.commit();
                    c.close();
                Common.sem_syncdb.release();
            }
                
            } catch (InterruptedException ex) {
            Logger.getLogger(SQLiteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            System.out.println("Connection Release !");
            
      
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
    public ResultSet Select (String sql)throws SQLException{
       
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
 }

