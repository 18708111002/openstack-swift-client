/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.TaskSwingWorker;

import com.Cipc.Bean.Common;
import com.Cipc.Bean.SQLiteJDBC;
import com.Cipc.Bean.Task;
import com.Cipc.JClouds.JCloudsSwift;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author Zj
 */
public class DeleteSwingWorker extends SwingWorker<Boolean, Integer>  {

    private   List<Task> deleteTask;
    private   JList fileList;
    
    public DeleteSwingWorker( List<Task> deleteTask,JList fileList){
        this.deleteTask = deleteTask;
        this.fileList = fileList;
        
    }
    
    private void  syncLocalDB(Task task){
       
       String filePath = task.desPath + "/";
       String fileName = "";
       
       String path [] = task.fileName.split("/");
       
            for(int i = 0; i < path.length - 1; i ++ ){
                 filePath += path[i] + "/";    
                }
        int index = path.length - 1;
        fileName = path[index];
        

        
        if(true){
            try {
                SQLiteJDBC sql = new SQLiteJDBC();
              
                sql.Delete("DELETE FROM file_tb  WHERE PATH=" +
                        "'"+ filePath +"'"+ "AND FILE=" +
                                "'"+ fileName +"'" + ";");
                
                sql.realese();
          
            } catch (SQLException ex) {
                Logger.getLogger(HandleTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
              
    }
    @Override
    protected Boolean doInBackground() throws Exception {
        
        for(Task task:deleteTask){
           if(Common.selectTreePath.equals(task.selectPath)){
              Common.listModel.remove(task.fileName);
             this.fileList.updateUI();
            }
        }
        for(Task task:deleteTask ){
               
            String container = task.desPath;
            String fileName = task.fileName;

           // JOptionPane.showMessageDialog(null,container, "提示", JOptionPane.PLAIN_MESSAGE);  
            
            new JCloudsSwift(Common.swiftinfo).deleteObject(container.trim(), fileName);
            syncLocalDB(task);
       
             } 

        return true;
    }
    
}
