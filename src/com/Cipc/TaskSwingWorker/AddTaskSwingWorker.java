/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.TaskSwingWorker;

import com.Cipc.Bean.Common;
import java.util.Set;
import javax.swing.SwingWorker;

/**
 *
 * @author Zj
 */
public class AddTaskSwingWorker extends SwingWorker<Boolean, Integer>  {

    @Override
    protected Boolean doInBackground() throws Exception {
       
       Set<String> set = Common.taskMap.keySet();// 取得里面的key的集合
      
            for(String str: set){
                  
                if(!Common.taskMap.get(str).inTable){
                    
                    System.out.println(str);
                    Common.taskTableModel.addTask(Common.taskMap.get(str));
                    System.out.println(str);
                    Common.taskMap.get(str).inTable = true;   
                 }
                
            }
            
           Common.sem_addTask.release();
           
           return true;
    }
    
}
