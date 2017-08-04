/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Frame;


import com.Cipc.Bean.GetFileSize;
import com.Cipc.Bean.Task;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Zj
 */
public class TaskTableModel extends DefaultTableModel {  
    public Object[] rowData = {"..", 24, "128 MB", "下载", "ID"};  
    public TaskTableModel() {  
        super();  
        addColumn("名字");  
        addColumn("进度");  
        addColumn("大小");  
        addColumn("类别");  
        addColumn("ID");     
    }  
    /** 
     * 设置为不可编辑 
     */  
    public boolean isCellEditable(int row, int column) {  
        return false;  
    }  
    /** 
     * 向状态区添加一个进度条 
     * 
     */  
    public void addTask(Task task) { 
        int index = task.fileName.split("/").length - 1; 

        rowData[0] = task.fileName.split("/")[index];
        rowData[1] = "等待执行...";
        rowData[2] = GetFileSize.getFileSize(task.srcPath);

        switch(task.type){
                         case Task.TYPE_UPLOAD:rowData[3] = "上传";break;
                         case Task.TYPE_DOWNLOAD:rowData[3] = "下载";break;               
                     }   
     
        rowData[4] = task.taskID;
        
        addRow(rowData);  
    }  
}  
