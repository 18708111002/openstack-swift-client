/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Cipc.Frame;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Zj
 */
public class TreeNodeRenderer extends DefaultTreeCellRenderer{
    
      private static final long serialVersionUID = 8532405600839140757L;
     
      
      @Override
      public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,int row, boolean hasFocus){
          super.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus);
           DefaultMutableTreeNode node=(DefaultMutableTreeNode)value;
           setIcon(new ImageIcon(ClassLoader.getSystemResource("treefolder.png")));
           
           return this;
      }
 }
    

