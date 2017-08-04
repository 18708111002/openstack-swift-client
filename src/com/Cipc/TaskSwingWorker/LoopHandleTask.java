/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.TaskSwingWorker;

import com.Cipc.Bean.Common;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.SwingWorker;

/**
 *
 * @author Zj
 */
public class LoopHandleTask extends SwingWorker<Boolean, Integer> {

    private final JTable taskTable;
    private final JList fileList;
         
    public LoopHandleTask(JTable taskTable,JList fileList){
        
        this.taskTable = taskTable;
        this.fileList = fileList;
    }
    
    @Override
    protected Boolean doInBackground() throws Exception {
        
     
      while(true){
          //System.out.println("running in  dispatch");
         

           new HandleTask(taskTable,fileList).execute();
           Common.sem_handleTask.acquire();
           Thread.sleep(1000);
        
          
      }
           
    }
   
    
}
