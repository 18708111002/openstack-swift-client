/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Frame;

/**
 *
 * @author Zj
 */
import java.awt.BorderLayout;  
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

  
import javax.swing.ImageIcon;  
import javax.swing.JButton;  
import javax.swing.JCheckBox;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JOptionPane;
import javax.swing.JPanel;  
import javax.swing.JPasswordField;  
import javax.swing.JTextField;  
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

public class LoginView extends JFrame {  
  
    /** 
     *  
     */  
    public InfiniteProgressPanel glasspane ;
    public JTextField tenantTextField ;  
    public JTextField userTextField ; 
    public JPasswordField passTextField ;  
    
    private static final long serialVersionUID = -5665975170821790753L;  
  
    public LoginView() {  
        initComponent();  
    }  
      
    private void initComponent() {  
        setTitle("用户登录");  
        //设置LOGO  
      
        URL image = LoginView.class.getClassLoader().getResource("icon.png");//图片的位置  
        JLabel imageLogo = new JLabel(new ImageIcon(image));  
        JPanel imagep = new JPanel();
        imagep.add(imageLogo);
        add(imagep,BorderLayout.NORTH);  
        
          
        //登录信息  
        JPanel jp = new JPanel();  
        
        JPanel jpTenant = new JPanel();  
        jpTenant.add(new JLabel("租户"));  
        tenantTextField = new JTextField(15);  
        jpTenant.add(tenantTextField); 
        
        jp.add(jpTenant);
        
        JPanel jpAccount = new JPanel();  
        jpAccount.add(new JLabel("帐号"));  
        userTextField = new JTextField(15);  
        jpAccount.add(userTextField);  

        jp.add(jpAccount);  
        
        
          
        JPanel jpPass = new JPanel();  
        jpPass.add(new JLabel("密码"));  
        passTextField = new JPasswordField(15);  
        jpPass.add(passTextField);  
  
        jp.add(jpPass);  
          
        //登录设置  
        JPanel jpstatus = new JPanel();  
        jpstatus.add(new JCheckBox("记住密码"));  
        jpstatus.add(new JCheckBox("自动登录"));  
        jp.add(jpstatus);  
        add(jp);  
          
        //底部登录按钮  
        JPanel bottomPanel = new JPanel();  
        bottomPanel.setLayout(new GridLayout(1, 2));  
        //bottomPanel.setLayout(new BorderLayout());  
        //bottomPanel.add(new JButton("设置"),BorderLayout.WEST);  
        JButton reset = new JButton("重置");  
        reset.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));  
        reset.setForeground(Color.white);
        
        JButton login = new JButton("登录");
        login.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));  
        login.setForeground(Color.white);  
        
        
        
        login.addMouseListener(new LoginMouseListener(this));
        
        reset.addMouseListener(new MouseListener() {  
                @Override  
                public void mouseClicked(MouseEvent e) { 
                    tenantTextField.setText("");
                    userTextField.setText("");  
                    passTextField.setText("");  
                }  
                @Override  
                public void mousePressed(MouseEvent e) { }  
                @Override  
                public void mouseReleased(MouseEvent e) { }  
                @Override  
                public void mouseEntered(MouseEvent e) { }  
                @Override  
                public void mouseExited(MouseEvent e) { }  
            });  
                
                
         
        bottomPanel.add(login); 
        bottomPanel.add(reset);  
        
        add(bottomPanel,BorderLayout.SOUTH);  
        setSize(325,365);  
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        setLocationRelativeTo(null);  
        setResizable(false);
        
        glasspane = new InfiniteProgressPanel();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	glasspane.setBounds(50, 50, (dimension.width) / 200, (dimension.height) / 200);
        
        setGlassPane(glasspane);
    }  
  
    /** 
     * @param args 
     */  
       public static void main(String args[]) throws UnsupportedLookAndFeelException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */  
  
               //   pt.
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CIPC_Clouds.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CIPC_Clouds.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CIPC_Clouds.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CIPC_Clouds.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        

        try {
            /*
            UIManager.setLookAndFeel( new SubstanceSaharaLookAndFeel());
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            SubstanceSaharaLookAndFeel.setSkin(new CremeSkin());
            SubstanceSaharaLookAndFeel.setCurrentButtonShaper(new StandardButtonShaper());
            SubstanceSaharaLookAndFeel.setFontPolicy(new DefaultMacFontPolicy());
            SubstanceLookAndFeel.setCurrentWatermark(new SubstanceMetalWallWatermark());
            SubstanceLookAndFeel.setCurrentTitlePainter(new Glass3DTitlePainter());
            */
            
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
            
            UIManager.put("RootPane.setupButtonVisible",false);
            //</editor-fold>
        } catch (Exception ex) {
            Logger.getLogger(CIPC_Clouds.class.getName()).log(Level.SEVERE, null, ex);
        }

        new LoginView().setVisible(true);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                
              
             
            }
        });
    }
}  