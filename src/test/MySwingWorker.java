/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.BorderLayout;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.util.concurrent.ExecutionException;  
  
import javax.swing.JButton;  
import javax.swing.JLabel;  
import javax.swing.JOptionPane;  
import javax.swing.JPanel;  
import javax.swing.JProgressBar;  
import javax.swing.SwingWorker;  
  
/*** 
 * SwingWorker可以帮助我们在后台执行耗时的任务，而避免阻塞我们的应用程序，以让用户感觉不爽。 SwingWorker有2个参数T , V 
 * T：为最终结果集 【<T> the result type returned by this SwingWorker's doInBackground 
 * and get methods】， 由文档的介绍可以知道这个结果可以被doInBackground和get方法返回。 V：为中间结果集【<V> the 
 * type used for carrying out intermediate results by this SwingWorker's publish 
 * and process methods】 
 *  
 * @author zhangyue 
 * 
 */  
public class MySwingWorker extends SwingWorker<Boolean, Integer> {  
  
    private final static int max = 1000;  
  
    // 显示进度条状态的label  
    private JLabel status;  
    // 进度条  
    private JProgressBar jpb;  
  
    public static void main(String[] args) {  
        TestFrame frame = new TestFrame();  
  
        final JLabel label = new JLabel();  
//      JScrollPane sp = new JScrollPane(panel);  
//      sp.setSize(new Dimension(700, 500));  
//      frame.add(sp, BorderLayout.CENTER);  
  
        JPanel stp = new JPanel();  
        final JProgressBar jpb = new JProgressBar();  
        jpb.setMinimum(1);  
        jpb.setMaximum(max);  
        stp.add(jpb);  
        stp.add(label);  
        frame.add(stp, BorderLayout.SOUTH);  
  
        JButton button = new JButton("Begin");  
        button.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                MySwingWorker msw = new MySwingWorker(label, jpb);  
                msw.execute();  
            }  
        });  
  
        frame.add(button, BorderLayout.NORTH);  
  
        frame.setVisible(true);  
    }  
  
    public MySwingWorker(JLabel status, JProgressBar jpb) {  
        super();  
        this.status = status;  
        this.jpb = jpb;  
    }  
  
    /* 
     * doInBackground是工作线程，他可以明确调用publich方法（注意publish方法只在SwingWorker类中实现）， 
     * 以发送中间结果V，然后这个中间结果有被发送到在EDT（事件派发线程）中的 process方法中进行处理。 
     */  
    @Override  
    protected Boolean doInBackground() throws Exception {  
        // TODO Auto-generated method stub  
        for (int i = 1; i <= max; i++) {  
            jpb.setValue(i);  
            status.setText(i + " / " + max);  
            try {  
                Thread.sleep(0);  
            } catch (InterruptedException e1) {  
                // TODO Auto-generated catch block  
                e1.printStackTrace();  
            }  
        }  
        return true;  
    }  
  
    /* 
     * 当doInBackground处理完后，会自动调用done方法，由T类型的描述那里可以知道，在这个方法中可以调用get方法获取最终结果集 
     */  
    @Override  
    protected void done() {  
        try {  
            Boolean result = get();  
            if (result) {  
                JOptionPane.showMessageDialog(null, "Success!");  
            } else {  
                JOptionPane.showMessageDialog(null, "Failed!");  
            }  
        } catch (InterruptedException | ExecutionException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
  
}  