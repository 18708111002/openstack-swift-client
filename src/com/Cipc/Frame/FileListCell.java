/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Frame;

import java.awt.Color;
import java.awt.Component;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


/**
 *
 * @author Zj
 */
/** 
 * 定制swing里面dblist的cell单元格如何显示 
 * */  

class FileListCell extends JLabel implements ListCellRenderer<String>{  
   public ImageIcon _fileIcon;  
   
   private URL getImage(String file){
       
       String t[] = file.split("\\.");
       int index  = file.split("\\.").length - 1;
       
       if(index < 0)
           return ClassLoader.getSystemResource("document_file.png");
           
       String type = file.split("\\.")[index];
       
       switch(type.toLowerCase()){
           case "pdf": return ClassLoader.getSystemResource("pdf.png");
           case "txt": return ClassLoader.getSystemResource("text.png");
           case "db" : return ClassLoader.getSystemResource("db.png");
           case "jpg": return ClassLoader.getSystemResource("jpg.png");
           case "doc": return ClassLoader.getSystemResource("doc.png");
           case "zip": return ClassLoader.getSystemResource("zip.png");
           case "rar": return ClassLoader.getSystemResource("rar.png");
           case "png": return ClassLoader.getSystemResource("png.png");   
           default :   return ClassLoader.getSystemResource("document_file.png");
       }
       
       

}
@Override  
public Component getListCellRendererComponent(  
        JList<? extends String> list, String value,  
        int index, boolean isSelected, boolean cellHasFocus) {  
    
     
    _fileIcon = new ImageIcon(this.getImage(value));  
    setText(value); 
    setIcon(_fileIcon);  
    //判断是否选中  

    
    if (isSelected) {  
            setBackground(Color.GRAY);  
            setForeground(Color.WHITE);  
          }  
   else {  
            setBackground(list.getBackground());  
            setForeground(list.getForeground());  
          }  

    setEnabled(list.isEnabled());  
    setFont(list.getFont());  
    setOpaque(true);  
    
    return this;  
}  
   
}  
