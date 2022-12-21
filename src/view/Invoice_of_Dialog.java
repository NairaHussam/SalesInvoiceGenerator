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


public class Invoice_of_Dialog extends JDialog{
        private JTextField NameField;
    private JTextField DateField;
    private JLabel Namelbl;
    private JLabel Datelbl;
    private JButton okbtn;
    private JButton cancelbtn;
    public  Invoice_of_Dialog(NewJFrame frame){
        Namelbl=new JLabel("Customer Name:");
         NameField=new JTextField(20);
Datelbl=new JLabel("Date:");
  DateField=new JTextField(20);
okbtn=new JButton("Ok");
cancelbtn=new JButton("Cancel");

okbtn.setActionCommand("CreateInvoiceOk");
cancelbtn.setActionCommand("CreateInvoiceCancel");
okbtn.addActionListener(frame.getController());
cancelbtn.addActionListener(frame.getController());
setLayout(new GridLayout(3,2));
add(Datelbl);
add(DateField);
add(NameField);
add(Namelbl);
add(okbtn);
add(cancelbtn);
pack();
    }
    public JTextField getNameField(){
    return NameField;}
    
public JTextField getDateField(){
    return DateField;}
    
}
