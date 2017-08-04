/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.TaskSwingWorker;

import com.Cipc.Bean.Common;
import com.Cipc.Frame.CIPC_Clouds;
import com.Cipc.Frame.LoginView;
import com.Cipc.JClouds.JCloudsSwift;
import javax.swing.SwingWorker;

/**
 *
 * @author Zj
 */
public class CheckLoginSwingWorker extends SwingWorker<Boolean, Integer> {

    private LoginView loginView;
    
    public CheckLoginSwingWorker(LoginView loginView){
        this.loginView = loginView;
    }
    
    @Override
    protected Boolean doInBackground() throws Exception {
        
       if( new JCloudsSwift(Common.swiftinfo).checkLogin(loginView)){
                              
           
            
                           // JOptionPane.showMessageDialog(null, "successfule"); 
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {

                    CIPC_Clouds  cipc_clouds = new CIPC_Clouds();
                    cipc_clouds.setLocationRelativeTo(null);
                    cipc_clouds.setVisible(true);    
                    cipc_clouds.setResizable(false); 

                 }
            });
            
            loginView.dispose();
                            
        }
       
       
       return true;
    }
    
}
