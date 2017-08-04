/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Bean;

/**
 *
 * @author Zj
 */
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  
;  
import javax.swing.JTree;  
import javax.swing.tree.DefaultMutableTreeNode;  
  
public class DisplayCipcTree 
{  
    private static Connection con;  
    private static Statement ste = null;  
    ResultSet rs;  
    // 连接数据库  
    private static  void connectSqlite()
    {  
        String driver = "org.sqlite.JDBC";  
        String connection = "jdbc:sqlite:cipc_clouds.db";  
        try  
        {  
            Class.forName(driver);  
        }  
        catch (ClassNotFoundException e)  
        {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        try  
        {  
            con = DriverManager.getConnection(connection);  
        }  
        catch (SQLException e)  
        {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        try  
        {  
            ste = con.createStatement();  
        }  
        catch (SQLException e)  
        {  
            // TODO Auto-generated catch block  
            // e.printStackTrace();  
        }  
    }  
  

  public static void DisplayTree(int parentId,int level,DefaultMutableTreeNode parent){
                
                parent.removeAllChildren();
                connectSqlite();
                DefaultMutableTreeNode temp = null;  
		String sql = "select   ID,NAME,PARENTID from tree_tb where   PARENTID = "+ parentId;
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				for(int i = 0;i < level*2;i++){
					System.out.print("-");
				}
				//System.out.println(rs.getString("NAME"));
                                temp = new DefaultMutableTreeNode(rs.getString("NAME"));  
                                parent.add(temp);
				DisplayTree(rs.getInt("ID"),level+1,temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
}  
