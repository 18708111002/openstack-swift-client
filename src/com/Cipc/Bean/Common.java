/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Bean;

import com.Cipc.Config.AppConfig;
import com.Cipc.Frame.FileListModel;
import com.Cipc.Frame.TaskTableModel;
import com.Cipc.JClouds.JCloudsSwift;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Zj
 */
public class Common {
    public Common(){
        System.out.println(taskMap);
        System.out.println(taskTableModel);
        System.out.println(swiftinfo);
       // System.out.println(appconfig);
    }
    
    public   static final Map<String,Task> taskMap = new HashMap<String,Task>();
    public   static final TaskTableModel taskTableModel = new TaskTableModel(); 
    public   static final SwiftInfo swiftinfo = new SwiftInfo();
    //public   static final AppConfig appconfig = new AppConfig();
  
    
    public   static final Semaphore sem_addTask = new Semaphore(0);
    public   static final Semaphore sem_updated = new Semaphore(1);
    public   static final Semaphore sem_updatedTree = new Semaphore(1);
    public   static final Semaphore sem_handleTask = new Semaphore(0);
    public   static final Semaphore sem_syncdb = new Semaphore(1);
    
    public   static final Semaphore sem_login = new Semaphore(0);
    
    public  static String tenant = "demo";
    public  static String user = "demo";
    public  static String credential = "cipc0507";
    


    public   static final DefaultMutableTreeNode cipcTreeNode = new DefaultMutableTreeNode("网盘");
    public   static String selectTreePath = null; 
    public   static FileListModel listModel = new FileListModel();
    
    public   static int treeNodeId;
    
}
