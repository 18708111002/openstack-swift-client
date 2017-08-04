/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Config;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zj
 */
public class AppConfig {
    
   public  AppConfig(){
       try {
           ParseProperties.config(this);
       } catch (IOException ex) {
           Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public int     MAX_THREADS    = 5 ;    //最大任务线程数
    public String  SYNC_PATH      = null;  //同步文件目录
    public boolean RECREATE_TREE = true;  //是否需要重建目录tree
    
   
    public void SaveConfig() {
      try{
        OutputStream out=new FileOutputStream(ClassLoader.getSystemResource("Config/config.properties").getPath());  
        // 生成properties对象  
        Properties p = new Properties();  
        p.setProperty("SYNC_PATH", SYNC_PATH);
        p.setProperty("MAX_THREADS", String.valueOf(MAX_THREADS));
        p.setProperty("RECREATE_TREE",String.valueOf(RECREATE_TREE));
        p.store(out, "store file");
        out.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
    
}
