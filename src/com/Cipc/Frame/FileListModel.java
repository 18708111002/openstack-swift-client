/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Frame;
import java.util.ArrayList;  
import java.util.List;  
  
import javax.swing.AbstractListModel;  
/**
 *
 * @author Zj
 */
public class FileListModel extends AbstractListModel<String> {  
  
    private static final long serialVersionUID = 1L;  
      
    private List<String> imageFile = new ArrayList<String>();  
  
    public void addElement(String fn){  
        this.imageFile.add(fn);  
        
    }  
    
    public boolean contains(String fn){
        
        return this.imageFile.contains(fn);
    }
      
    public void remove(String fn){
        imageFile.remove(fn);
    }
    public int getSize() {  
        return imageFile.size();  
    }  
  
    public String getElementAt(int index) {  
        return imageFile.get(index);  
    }  
    
    public void clear(){
        imageFile.clear();
    }
  
}  
