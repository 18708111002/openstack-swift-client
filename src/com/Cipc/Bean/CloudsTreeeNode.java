/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Bean;

/**
 *
 * @author Zj
 */
public class CloudsTreeeNode {
   public  CloudsTreeeNode(int ID,String Name,int ParentId,boolean isLeaf){
        
        this.ID = ID;
        this.Name = Name;
        this.ParentId = ParentId;
        this.isLeaf = isLeaf;
    
    }
    public int ID;
    public String Name;
    public int ParentId;
    public boolean isLeaf;
    
   @Override
    public String toString()
    {
        
        String str ="id;"+String.valueOf(ID) + "\tname:"+Name + "\t pid:"+String.valueOf(ParentId) + "\n";
        
        if(!isLeaf)
         return  str;
        else
            return null;
    }    
    
}
