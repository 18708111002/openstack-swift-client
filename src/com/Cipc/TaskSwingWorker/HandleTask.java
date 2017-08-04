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
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;

/**
 *
 * @author Zj
 */
public class HandleTask extends SwingWorker<Boolean, Integer> {
    
    private static final int COL_TASK_STATUS = 1;
    private static final int COL_TASK_ID = 4;
    private JTable taskTable;
    private JList fileList;
    
    private void  syncLocalDB(Task task){
       
       String filePath = task.desPath + "/";
       String fileName = "";
       
       String path [] = task.fileName.split("/");
       
            for(int i = 0; i < path.length - 1; i ++ ){
                 filePath += path[i] + "/";    
                }
        int index = path.length - 1;
        fileName = path[index];
        
        SQLiteJDBC  sql = new SQLiteJDBC();
        boolean notExist = sql.notExist(filePath, fileName);
        sql.realese();
        
        if(notExist){
            try {
                sql = new SQLiteJDBC();
              
                sql.Insert("INSERT INTO file_tb (PATH,FILE ) VALUES(" +
                        "'"+ filePath +"'"+ "," +
                                "'"+ fileName +"'" + ");");
                sql.realese();
          
            } catch (SQLException ex) {
                Logger.getLogger(HandleTask.class.getName()).log(Level.SEVERE, null, ex);
            }

            List<String> filelist = Common.swiftinfo.fileMap.get(filePath);
            filelist.add(path[index]);
            Common.swiftinfo.fileMap.put(filePath , filelist);
            this.fileList.updateUI();
        }
        
        if(Common.selectTreePath.equals(task.selectPath) && notExist){
             Common.listModel.addElement(fileName);   
        }
        
        
    }
    public HandleTask(JTable taskTable,JList fileList){
        this.taskTable = taskTable;
        this.fileList = fileList;
    }
    private void updateTaskStatus(String taskId) throws Exception{
        for(int i = 0; i < this.taskTable.getRowCount();i++){
            if(taskId.equals(taskTable.getValueAt(i, COL_TASK_ID))){
                taskTable.setValueAt("正在执行中...", i, COL_TASK_STATUS);     
                
            }
        }
    }
    private boolean download(Task task,JTable taskTable)throws Exception{
         JCloudsSwift jc = new JCloudsSwift(Common.swiftinfo);
        //System.out.println("srcpath:" + task.srcPath);  
       
        jc.downloadObject(task.desPath.replace(" ", ""), task.fileName.replace(" ", ""),  new File(task.srcPath));
        jc.close();
 
        for(int i = 0; i < this.taskTable.getRowCount(); i ++){
                if(task.taskID == this.taskTable.getValueAt(i, COL_TASK_ID))
                {
                    Common.taskMap.remove(task.taskID); 
                    this.taskTable.setValueAt("完成", i,COL_TASK_STATUS);
                }
        }  
        
        /*
        if(Common.taskMap.size() <= 0)
            new RefreshSWingWorker(Common.cipcTreeNode,this.taskTable,this.fileList).execute();

*/
        
        return true;
    }
    private boolean upload(Task task,JTable taskTable) throws Exception{
        
        JCloudsSwift jc = new JCloudsSwift(Common.swiftinfo);
        //System.out.println("srcpath:" + task.srcPath);       
        jc.uploadObjectFromStream(task.desPath.replace(" ", ""), task.fileName.replace(" ", ""),  new FileInputStream(task.srcPath));
        jc.close();
 
        for(int i = 0; i < this.taskTable.getRowCount(); i ++){
                if(task.taskID == this.taskTable.getValueAt(i, COL_TASK_ID))
                {
                    Common.taskMap.remove(task.taskID); 
                    this.taskTable.setValueAt("完成", i,COL_TASK_STATUS);
                    this.syncLocalDB(task);
                }
        }  
        
        
        
        return true;
    }
    @Override  
    protected void done() {  
        Common.sem_handleTask.release();
        Common.sem_updated.release();
       // JOptionPane.showMessageDialog(null,"Release！", "PLAIN_MESSAGE", JOptionPane.PLAIN_MESSAGE);
    }
    @Override
    protected Boolean doInBackground() throws Exception {
        
       Set<String> set = Common.taskMap.keySet();// 取得里面的key的集合

               for(String str: set){

                   if(Common.taskMap.get(str).inTable){

                      String taskID = Common.taskMap.get(str).taskID;
             
                      switch(Common.taskMap.get(str).type){
                           case Task.TYPE_UPLOAD:this.updateTaskStatus(taskID); upload(Common.taskMap.get(str),taskTable);break;
                           case Task.TYPE_DOWNLOAD:this.updateTaskStatus(taskID);download(Common.taskMap.get(str),taskTable);break;                 
                       }   
                    }
               }   
  
               return true;
    }

}
