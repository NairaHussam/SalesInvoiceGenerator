/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Dialog_of_Line extends JDialog{
    private JTextField itemField;
    private JTextField countField;
    private JTextField priceField;
    private JLabel itemlbl;
    private JLabel countlbl;
    private JLabel pricelbl;
    private JButton okbtn;
    private JButton cancelbtn;
    
    public Dialog_of_Line(NewJFrame frame){
 
    itemField=new JTextField(20);
itemlbl=new JLabel("Item Name");
  countField=new JTextField(20);
countlbl=new JLabel("Item Count");
  priceField=new JTextField(20);
pricelbl=new JLabel("Item price");
okbtn=new JButton("Ok");
cancelbtn=new JButton("Cancel");
okbtn.setActionCommand("CreateLineOk");
cancelbtn.setActionCommand("CreateLineCancel");
okbtn.addActionListener(frame.getController());
cancelbtn.addActionListener(frame.getController());
setLayout(new GridLayout(4,2));
add(itemlbl);
add(itemField);
add(countlbl);
add(countField);
add(itemlbl);
add(pricelbl);
add(priceField);
add(okbtn);
add(cancelbtn);
pack();
}
    public JTextField getItemField(){
    return itemField;
    
    }
   public JTextField getCountField(){
   return countField;
   }
   public JTextField getPriceField(){
   return priceField;
   }
}
