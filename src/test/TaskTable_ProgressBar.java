/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.concurrent.ExecutionException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingWorker;

/**
 *
 * @author Zj
 */
public class TaskTable_ProgressBar extends SwingWorker<Boolean, Integer> {
    
    private int  rowIndex;
    private JTable jtb;
    private final static int max = 100;
    
    public TaskTable_ProgressBar(int rowIndex, JTable jtb) { 
        
        super();  
        this.rowIndex = rowIndex;  
        this.jtb = jtb;  
    }  
    @Override  
    protected Boolean doInBackground() throws Exception {  
        // TODO Auto-generated method stub  
        
        
        for (int i = 1; i <= max; i++) {  
            jtb.setValueAt(i, rowIndex, 1);
            //status.setText(i + " / " + max);  
            try {  
                Thread.sleep(50);  
            } catch (InterruptedException e1) {  
                // TODO Auto-generated catch block  
                e1.printStackTrace();  
            }  
        }  
        return true;  
    }  
    
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
