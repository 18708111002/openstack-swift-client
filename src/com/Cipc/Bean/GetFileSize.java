/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Bean;

import java.io.File;

/**
 *
 * @author Zj
 */
public class GetFileSize {
    
    public static String getFileSize(String path){
        //传入文件路径
        File file = new File(path);
        String fileSize;
        //测试此文件是否存在
        if(file.exists()){
            //如果是文件夹
            //这里只检测了文件夹中第一层 如果有需要 可以继续递归检测
            if(file.isDirectory()){
                int size = 0;
                for(File zf : file.listFiles()){
                    if(zf.isDirectory()) continue;
                    size += zf.length();
                }
                System.out.println("文件夹 "+file.getName()+" Size: "+(size/1024)+"kb");
                
                if((size/1024) < 1 )
                    return (size)+"b";
                else if((size/1024) >= 1024)
                    return (size/(1024*1024))+"Mb";
                else if((size/(1024*1024*1024)) >=1024 )
                    return (size/(1024*1024*1024))+"Gb";
                else
                    return (size/1024)+"Kb";
                
            }else{
                System.out.println(file.getName()+" Size: "+(file.length()/1024)+"kb");

                if((file.length()/1024) < 1 )
                    return (file.length())+"b";
                else if((file.length()/1024) >= 1024)
                    return (file.length()/(1024*1024))+"Mb";
                else if((file.length()/(1024*1024*1024)) >=1024 )
                    return (file.length()/(1024*1024*1024))+"Gb";
                else
                    return (file.length()/1024)+"Kb";
            }
        //如果文件不存在
        }
        
        return null;
    }
}
