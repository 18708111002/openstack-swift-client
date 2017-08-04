/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Config;

/**
 *
 * @author Zj
 */
import java.util.*;  
import java.io.*;  
import java.util.logging.Level;
import java.util.logging.Logger;
  
public class ParseProperties {  
    public static void config(AppConfig appconfig) throws IOException {  
        // 生成输入流  
        InputStream ins;  
        Properties p = new Properties(); 
        try {
            ins = new FileInputStream(ClassLoader.getSystemResource("Config/config.properties").getPath());
            p.load(ins);  
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ParseProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
        // 生成properties对象  
        
 
        // 输出properties文件的内容  
        
        appconfig.MAX_THREADS = Integer.parseInt(p.getProperty("MAX_THREADS"));
        appconfig.RECREATE_TREE = Boolean.parseBoolean(p.getProperty("RECREATE_TREE"));
        appconfig.SYNC_PATH = p.getProperty("SYNC_PATH");
        
        
        System.out.println("MAX_THREADS:" + p.getProperty("MAX_THREADS"));  
        System.out.println("RECREATE_TREE:" + p.getProperty("RECREATE_TREE"));
        System.out.println("SYNC_PATH:" + p.getProperty("SYNC_PATH"));
    }  
}  
