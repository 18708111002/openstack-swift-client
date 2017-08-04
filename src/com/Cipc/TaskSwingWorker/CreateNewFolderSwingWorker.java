/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.TaskSwingWorker;

import com.Cipc.Bean.CloudsTreeeNode;
import com.Cipc.Bean.Common;
import com.Cipc.Bean.DisplayCipcTree;
import static com.Cipc.Bean.DisplayCipcTree.DisplayTree;
import com.Cipc.Bean.SQLiteJDBC;
import com.Cipc.JClouds.JCloudsSwift;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Zj
 */
public class CreateNewFolderSwingWorker extends SwingWorker<Boolean, Integer>{
    
    private String selectPath;
    private String folderName;
    private JTree File_jTree;
   

    private void createContainer(){
        
        new JCloudsSwift(Common.swiftinfo).createContainer(folderName.trim());
    }
   
    private String getParentName(){
        String parentName = "";
        String[] split = selectPath.split("/");
        int index = split.length - 1;
        
        parentName = split[index];
        
        
        return parentName;
    }
        
     
       
    
   private boolean  syncLocalDB(){
       
        CloudsTreeeNode cn = null;
        String parentName = getParentName();
        //JOptionPane.showMessageDialog(null,parentName, "PLAIN_MESSAGE", JOptionPane.PLAIN_MESSAGE);
        SQLiteJDBC sql = new SQLiteJDBC();
        
     
                //sql.realese()
        if(parentName.equals("")){
            
            cn = new CloudsTreeeNode(Common.treeNodeId++,folderName.trim(),0,false);
        }
        else{
            cn = sql.getTreeNode(parentName.trim(), folderName.trim());
        }
        
        if(!sql.dirNotExist(cn.Name.trim(),cn.ParentId)){
            
            JOptionPane.showMessageDialog(null,"已经存在该文件夹！", "PLAIN_MESSAGE", JOptionPane.PLAIN_MESSAGE);
            sql.realese();
            return false;
        }
        
        try {
                sql.Insert("insert into tree_tb  VALUES(" +
                                        String.valueOf(cn.ID)   +"," +
                                       "'"+ String.valueOf(cn.Name) + "'"+"," + 
                                        String.valueOf(cn.ParentId)  + 
                                    ");" 
                            );  
                    } catch (SQLException ex) {
            Logger.getLogger(CreateNewFolderSwingWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
       	
        sql.realese();
        
        return true;
   }
    private void createVirtualDir(){
        String path[] = selectPath.split("/");
        String container = path[0].trim();
        String fileName  = "";
        
        for(int i = 1; i < path.length ; i ++)
        {
            fileName += path[i] + "/";
        }
        
        fileName += folderName + "/";
        
  
        new JCloudsSwift(Common.swiftinfo).createVirtualDir(container.trim(), fileName.trim());
        
    }
    public CreateNewFolderSwingWorker(String selectPath,String folderName,JTree File_jTree){
        
        this.selectPath = selectPath;
        this.folderName = folderName;
        this.File_jTree = File_jTree;
        
    }
    

    @Override
    protected Boolean doInBackground() throws Exception {
        
   
        syncLocalDB();
        DisplayCipcTree.DisplayTree(0, 0, Common.cipcTreeNode);
        this.File_jTree.updateUI();
       
        
        if(selectPath.equals("")){
            createContainer();
        }
        else{
            createVirtualDir();
        }
        
        return true;
    }
    
}
