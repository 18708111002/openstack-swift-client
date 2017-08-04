/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zj
 */
public class SwiftInfo {
    
    public static final String TOKEN_URL = "http://203.0.113.2:5000/v2.0/";
    

    public static String container_name = "jclouds-example";
    public static String object_name    = "mytest/python.msi"; 
    
    public Map<String,CloudsTreeeNode> clouds_tree = new HashMap<String,CloudsTreeeNode>();
    public Map<String,List<String>> fileMap = new HashMap<String,List<String>>();
    
}
