/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Frame;

import com.Cipc.Bean.Common;
import com.Cipc.JClouds.JCloudsSwift;
import com.Cipc.TaskSwingWorker.CheckLoginSwingWorker;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Zj
 */
public class LoginMouseListener implements MouseListener {

    LoginView loginView;
    public LoginMouseListener(LoginView loginView){
        this.loginView = loginView;

    }
    
    @Override
    public void mouseClicked(MouseEvent e) {  
                    
                        if(null == loginView.tenantTextField.getText() || "".equals(loginView.tenantTextField.getText())) {  
                            JOptionPane.showMessageDialog(null, "租户不能为空");  
                            return;  
                        }else
                            Common.tenant = loginView.tenantTextField.getText();

                        if(null == loginView.userTextField.getText() || "".equals(loginView.userTextField.getText())) {  
                            JOptionPane.showMessageDialog(null, "用户名不能为空");  
                            return;  
                        }else
                            Common.user = loginView.userTextField.getText();
                      
                        if(null == loginView.passTextField.getText()|| "".equals(loginView.passTextField.getText())) {  
                            JOptionPane.showMessageDialog(null, "密码不能为空");  
                            return;  
                        }else
                            Common.credential = loginView.passTextField.getText();
                         
                        loginView.glasspane.start();
                        
                        new CheckLoginSwingWorker(this.loginView).execute();
                          

                    }  

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
