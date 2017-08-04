/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Bean;

import java.util.UUID;

/**
 *
 * @author Zj
 */
public class Task {
    
    public static final int TYPE_UPLOAD = 0xffff;
    public static final int TYPE_DOWNLOAD = 0xfffe;
    public static final int TYPE_DELETE = 0xfffd;
    
    public int type;
    public String srcPath;
    public String desPath;
    public String fileName;
    public String size;
    public String taskID;
    public boolean inTable;
    
    public String selectPath;
  
    
   public Task(int type,String srcPath,String desPath,String fileName,String selectPath){
    
        this.type = type;
        this.srcPath = srcPath;
        this.desPath = desPath;
        this.fileName = fileName;
        this.taskID = UUID.randomUUID().toString().substring(0,7);
        this.inTable = false;
        
        this.selectPath = selectPath;
        
    }
}
