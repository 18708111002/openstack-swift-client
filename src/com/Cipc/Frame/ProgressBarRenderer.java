/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Frame;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 *
 * @author Zj
 */
class ProgressBarRenderer extends DefaultTableCellRenderer{  
    private static final long serialVersionUID = 1L;  
    private final JProgressBar b;  
    public ProgressBarRenderer(){  
        super();  
        setOpaque(true);  
        b = new JProgressBar();  
        //是否显示进度字符串  
        b.setStringPainted(true);  
        b.setMinimum(0);  
        b.setMaximum(100);  
        //是否绘制边框  
        b.setBorderPainted(true);  
        b.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));  
    }  
    public Component getTableCellRendererComponent(JTable table, Object value,  
            boolean isSelected, boolean hasFocus, int row, int column) {  
        Integer i = (Integer) value;  
        b.setValue(i);  
        return b;  
    }  
}  
