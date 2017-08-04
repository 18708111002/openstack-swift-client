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
public class LoopAddTask extends SwingWorker<Boolean, Integer>{

   private final JTable jtb;
   private final JList  fileList;
   private LoopHandleTask dispatchSwingWork = null;
   public  LoopAddTask(JTable taskTable,JList fileList){
        
        this.jtb = taskTable;
        this.fileList = fileList;
       
       
       //taskTable.get
    }
   


    @Override
    protected Boolean doInBackground() throws Exception {     
         
        while(true){
            
           // System.out.println("running!");
            
            Common.sem_updated.acquire();
           // System.out.println("now add task!")        ;
            new AddTaskSwingWorker().execute();
            Common.sem_addTask.acquire();
           // System.out.println("now add finished!");
            
            }
    }
   
}
