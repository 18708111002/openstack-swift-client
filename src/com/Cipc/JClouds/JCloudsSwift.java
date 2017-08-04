/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.JClouds;

/**
 *
 * @author Zj
 */
import com.Cipc.Bean.CloudsTreeeNode;
import com.Cipc.Bean.Common;
import com.Cipc.Bean.SwiftInfo;
import com.Cipc.Frame.LoginView;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import static com.google.common.io.ByteSource.wrap;
import com.google.common.io.Closeables;
import com.google.inject.Module;
import org.jclouds.ContextBuilder;
import org.jclouds.io.Payload;
import org.jclouds.logging.slf4j.config.SLF4JLoggingModule;
import org.jclouds.openstack.swift.v1.SwiftApi;
import org.jclouds.openstack.swift.v1.domain.Container;
import org.jclouds.openstack.swift.v1.features.ContainerApi;
import org.jclouds.openstack.swift.v1.features.ObjectApi;
import org.jclouds.openstack.swift.v1.options.CreateContainerOptions;
import org.jclouds.openstack.swift.v1.options.PutOptions;
import java.io.Closeable;
import java.io.IOException;
import java.util.Set;
import java.io.File;

import java.io.FileOutputStream;
import java.io.InputStream;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import static org.jclouds.io.Payloads.newByteSourcePayload;

import org.jclouds.io.payloads.InputStreamPayload;
import org.jclouds.openstack.swift.v1.domain.ObjectList;
import org.jclouds.openstack.swift.v1.domain.SwiftObject;

public class JCloudsSwift implements Closeable {
    
    static final String NO_ROUTE = "连接失败,请检查网络！";
    static final String CHECK_USER = "登录失败,请检查账户以及密码是否正确！";
   private SwiftInfo swiftinfo = null;
   
   /*for test */
   //public static final String CONTAINER_NAME = "jclouds-example";
   //public static final String OBJECT_NAME = "mytest/python.msi"; 
   private static final String TOKEN_URL = "http://162i3g7791.51mypc.cn:5000/v2.0/";
   private SwiftApi swiftApi;

   public static void main(String []argv) throws IOException {
       
      SwiftInfo swiftinfo = new SwiftInfo();
      JCloudsSwift jcloudsSwift = new JCloudsSwift(swiftinfo);

      try {
         
         
          jcloudsSwift.deleteObject("jclouds-example", "新建文件夹//");
         //jcloudsSwift.createContainer();
         //jcloudsSwift.uploadObjectFromString();
        //jcloudsSwift.uploadObjectFromStream(CONTAINER_NAME, OBJECT_NAME, new FileInputStream("f:\\20110808122856159.rar"));
        //jcloudsSwift.listContainers();
         //jcloudsSwift.listObject("9508-test");
         //jcloudsSwift.createCloudsTree();
        // UpdateCloudsTreeDB ctb = new UpdateCloudsTreeDB(swiftinfo.clouds_tree,swiftinfo.fileMap);
         jcloudsSwift.close();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         jcloudsSwift.close();
      }
   }

   public JCloudsSwift(SwiftInfo swiftinfo) {
       
      this.swiftinfo = swiftinfo;
      Iterable<Module> modules = ImmutableSet.<Module>of();
           // new SLF4JLoggingModule());

      
      String provider = "openstack-swift";
      String identity = Common.tenant + ":" + Common.user; //"demo:demo"; // tenantName:userName
      String credential = Common.credential;//"cipc0507";

      
      swiftApi = ContextBuilder.newBuilder(provider)
            .endpoint(TOKEN_URL)
            .credentials(identity, credential)
            .modules(modules)
            .buildApi(SwiftApi.class);
   }
   
   public boolean checkLogin(LoginView loginView)
   {
      
      
        try{
          swiftApi.getContainerApi("regionOne").list();  
        }catch(org.jclouds.http.HttpResponseException  e){
            
             JOptionPane.showMessageDialog(null,NO_ROUTE, "提示", JOptionPane.PLAIN_MESSAGE); 
             loginView.glasspane.stop();
             return false;
             
        }catch(org.jclouds.rest.AuthorizationException e){
            JOptionPane.showMessageDialog(null,CHECK_USER, "提示", JOptionPane.PLAIN_MESSAGE);  
            loginView.glasspane.stop();
            return false;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,CHECK_USER, "提示", JOptionPane.PLAIN_MESSAGE);  
            loginView.glasspane.stop();
            return false;
        }
        loginView.glasspane.stop();
        return true;
  
   }
   
   public void createVirtualDir(String container,String name ){
       
       ObjectApi objectApi = swiftApi.getObjectApi("regionOne", container);
       Payload payload = newByteSourcePayload(wrap("not delete it!".getBytes()));
       objectApi.put(name.replace(" ", ""),payload, PutOptions.Builder.metadata(ImmutableMap.of("X-Object-Meta-key1", "value3", "X-Object-Meta-key2", "test")));
       //objectApi.put(name + ".dir",payload, PutOptions.Builder.metadata(ImmutableMap.of("X-Object-Meta-key1", "value3", "X-Object-Meta-key2", "test")));
   }
   public void createContainer(String container) {
      System.out.println("Create Container");
      ContainerApi containerApi = swiftApi.getContainerApi("regionOne");
     
      CreateContainerOptions options = CreateContainerOptions.Builder
            .metadata(ImmutableMap.of(
                  "key1", "value1",
                  "key2", "value2"));

      containerApi.create(container, options);

     // System.out.println("  " + container);
   }

   public void deleteObject(String container,String name){
       ObjectApi objectApi = swiftApi.getObjectApi("regionOne", container);
       objectApi.delete(name);
   }
   public void uploadObjectFromStream(String container, String name, InputStream stream) {
   
    //createContainerIfAbsent(container, swiftApi); 
    ObjectApi objectApi = swiftApi.getObjectApi("regionOne", container);
    Payload payload = new InputStreamPayload(stream);
    objectApi.put(name, payload, PutOptions.Builder.metadata(ImmutableMap.of("X-Object-Meta-key1", "value3", "X-Object-Meta-key2", "test")));

    }  
   public void inputstreamtofile(InputStream ins,File file) {
        try {
         OutputStream os = new FileOutputStream(file);
         int bytesRead = 0;
         byte[] buffer = new byte[8192];
         while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
          os.write(buffer, 0, bytesRead);
         }
         os.close();
         ins.close();
        } catch (Exception e) {
         e.printStackTrace();
        }
 }
   public void downloadObject(String container,String name,File file){
        ObjectApi objectApi = swiftApi.getObjectApi("regionOne", container);
        SwiftObject swiftObejct = objectApi.get(name);

        Payload payload = swiftObejct.getPayload();  
        InputStream stream = payload.getInput();
        inputstreamtofile(stream,file);  
   }
   
   private Set<Container> getContainers(){
       
        ContainerApi containerApi = swiftApi.getContainerApi("regionOne");
        
        Set<Container> containers = containerApi.list().toSet();
        return containers;
     
        
      
   }
   private ObjectList getObjects(String container){
      ObjectApi objectApi = swiftApi.getObjectApi("regionOne", container);
      ObjectList objects = objectApi.list();
      
      return objects;
   }
   
   
   public  void createCloudsTree(){
       int levelID = 0,rootID = 0;
       int leafID = 10000;
       int parentID;
       
       String parentName;
       String nodeName = null;
       String filePath = "";
       
       Set<Container> containers = this.getContainers();
       ObjectList     objects = null;
       
       for (Container container : containers) {
            levelID ++;
            String containerName = container.getName();
            this.swiftinfo.clouds_tree.put(containerName.trim(), new CloudsTreeeNode(levelID,containerName.trim(),rootID,false));
            
            objects = this.getObjects(containerName);
            
            for (SwiftObject object : objects) {
               
                filePath += containerName + "/";
                parentName = containerName;
                
                //System.out.println(object.getName());
                String path [] = object.getName().split("/");
                
                for(int i = 0; i < path.length - 1  ; i ++ ){
              
                 nodeName = path[i];
                 filePath += path[i] + "/";
                 
                 if(this.swiftinfo.clouds_tree.containsKey(nodeName.trim())){
                     parentName = nodeName;
                     continue;
                 }
                 
                 
                 parentID = this.swiftinfo.clouds_tree.get(parentName).ID;
                 
                 parentName = nodeName;
                 levelID++;
                 
                 this.swiftinfo.clouds_tree.put(nodeName.trim(), new CloudsTreeeNode(levelID,nodeName.trim(),parentID,false));
                }
                
                parentID = this.swiftinfo.clouds_tree.get(parentName.trim()).ID;
       
                if(object.getName().matches(".*/") ){
                    int index = path.length - 1;
                   // JOptionPane.showMessageDialog(null,object.getName(), "PLAIN_MESSAGE", JOptionPane.PLAIN_MESSAGE);
                    this.swiftinfo.clouds_tree.put(path[index].trim(), new CloudsTreeeNode(++levelID,path[index].trim(),parentID,false));
                    filePath = "";
                    continue;
                }
                int index = path.length - 1;
                
               
                this.swiftinfo.clouds_tree.put(path[index], new CloudsTreeeNode(leafID,path[index],parentID,true));
                
                if(this.swiftinfo.fileMap.containsKey(filePath)){
                    List<String> filelist = this.swiftinfo.fileMap.get(filePath);
                    filelist.add(path[index].trim());
                    this.swiftinfo.fileMap.put(filePath.trim() , filelist);
                }
                else{
                    List<String> filelist = new ArrayList<String>();
                    filelist.add(path[index].trim());
                    this.swiftinfo.fileMap.put(filePath.trim() , filelist); 
                }
                
                //System.out.println(filePath + nodeName);
                filePath = "";
            }
            
            Common.treeNodeId = ++levelID;
            //this.swiftinfo.clouds_tree.
            
            /*
            Set<String> set = this.swiftinfo.clouds_tree.keySet();// 取得里面的key的集合
            	for (String str : set) {// 遍历set去出里面的的Key
                    if(swiftinfo.clouds_tree.get(str).isLeaf == false &&swiftinfo.clouds_tree.get(str).ParentId == 0)
			System.out.println(swiftinfo.clouds_tree.get(str).toString());// 通过key,取得value打印出来
 
		}
*/
   
           
      }
       

       
   }
   public  void close() throws IOException {
      Closeables.close(swiftApi, true);
   }
}