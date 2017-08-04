/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Bean;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Zj
 */
public class UpdateCloudsTreeDB {
    
    private Map<String,CloudsTreeeNode> clouds_tree = null;
    Map<String,List<String>> fileMap  = null;
    private SQLiteInit sql ;
    
   public  UpdateCloudsTreeDB(Map<String,CloudsTreeeNode> clouds_tree,Map<String,List<String>> fileMap) {
        this.clouds_tree = clouds_tree;
        this.fileMap = fileMap;
        sql = new SQLiteInit();

        CreateFileTb();
        CreateTreeTb();
        sql.realese();
    }
    
    private void CreateFileTb(){
        try {
            sql.DropTable("drop table file_tb ");           
            sql.CreateTable("CREATE TABLE file_tb(\n" +
                    "   PATH           TEXT       NOT NULL,\n" +
                    "   FILE           CHAR(50)    NOT NULL "  +
                    ");");  } catch (SQLException ex) {
            Logger.getLogger(SQLiteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Set<String> set = this.fileMap.keySet();// 取得里面的key的集合
            	for (String path : set) {// 遍历set去出里面的的Key
                    List<String> file_list = fileMap.get(path);
                    
                    for(String file:file_list ){
                        
                        try { 
                            sql.Insert("INSERT INTO file_tb (PATH,FILE ) VALUES(" +
                                   "'"+ path +"'"+ "," +
                                   "'"+ file +"'" + ");"
                            );
                                    } catch (SQLException ex) {
                            Logger.getLogger(UpdateCloudsTreeDB.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
 
		}
        
    }
    private void CreateTreeTb(){
        
        try {
            sql.DropTable("drop table tree_tb ");
            
            sql.CreateTable("CREATE TABLE tree_tb(\n" +
                    "   ID INT PRIMARY KEY     NOT NULL,\n" +
                    "   NAME           CHAR(50)    NOT NULL,\n" +
                    "   PARENTID       INT     NOT NULL\n" +
                    ");");  } catch (SQLException ex) {
            Logger.getLogger(SQLiteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Set<String> set = this.clouds_tree.keySet();// 取得里面的key的集合
            	for (String str : set) {// 遍历set去出里面的的Key
                   // JOptionPane.showMessageDialog(null,str, "PLAIN_MESSAGE", JOptionPane.PLAIN_MESSAGE);
                    if(!clouds_tree.get(str).isLeaf){
                        CloudsTreeeNode cn = clouds_tree.get(str);
                        
                       
                        try {
                            
                            sql.Insert("insert into tree_tb  VALUES(" +
                                        String.valueOf(cn.ID)   +"," +
                                       "'"+ String.valueOf(cn.Name) + "'"+"," + 
                                        String.valueOf(cn.ParentId)  + 
                                    ");" 
                            );                          
                        } catch (SQLException ex) {
                            Logger.getLogger(UpdateCloudsTreeDB.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
			//System.out.println(swiftinfo.clouds_tree.get(str).toString());// 通过key,取得value打印出来
 
		}
    }
    
}
