/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import java.lang.String;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.InvModelTable;
import model.Invoices;
import model.Linee;
import model.LineModelTable;
import view.Dialog_of_Line;
import view.Invoice_of_Dialog;
import view.NewJFrame;

public class Controller implements ActionListener,ListSelectionListener {
    private Invoice_of_Dialog invdialog;
    private Dialog_of_Line linedialog;
    private NewJFrame frame;
    
public Controller(NewJFrame frame){
    this.frame=frame;
}
    @Override
    public void actionPerformed(ActionEvent e) {
        String action=e.getActionCommand();
    System.out.println("action"+action);
    switch(action){
    case"Load File":
        LoadFile();
    break;
    case"Save File":
        SaveFile();
    break;
    case"Create New Invoice":
        CreateNewInvoice();
    break;
    case"Delete Invoice":
    DeleteInvoice();
        break;
    case"CreateNewItem":
        CreateNewItem();
    break;
    case"DeleteItem":
        DeleteItem();
    break;
   
        case"CreateInvoiceOk":
            CreateInvoiceOk();
            break;
            
            
            case"CreateInvoiceCancel":
                CreateInvoiceCancel();
    break;
         case"CreateLineOk":
                CreateLineOk();
    break;
    
        case"CreateLineCancel":
                CreateLineCancel();
    break;
    }
    }
  public void valueChanged(ListSelectionEvent e) {
      int Index=frame.getI_Table().getSelectedRow();
      if(Index!=-1){
      
System.out.println(Index);
Invoices currInv=frame.getInvoicess().get(Index);
frame.getInvoiceNum_lbl().setText(""+currInv.getNumber());
frame.getInvoiceDate_lbl().setText(currInv.getDate());
frame.getCustomerName_lbl().setText(currInv.getCustomer());
frame.getInvoicTotal_lbl().setText(""+currInv.getInvoiceTot());
LineModelTable lineModTable=new LineModelTable(currInv.getLines());
frame.getL_Table().setModel(lineModTable);
lineModTable.fireTableDataChanged(); 
      }
  
  }
  
  
    private void LoadFile() {
        JFileChooser FileChooser=new JFileChooser();
      try {
      int res=  FileChooser.showOpenDialog(frame);
      if(res==JFileChooser.APPROVE_OPTION){
      File headFile=FileChooser.getSelectedFile();
      Path headPath=Paths.get(headFile.getAbsolutePath());
      List<String> headLines =Files.readAllLines(headPath);
      
      ArrayList <Invoices> invoicesArray=new ArrayList<>(); 
      for(String headLine :headLines){
          String [] headParts=headLine.split(",");
          int invoiceNum=Integer.parseInt(headParts[0]);
          String invoiceDate=headParts[1];
          String customer=headParts[2];
          Invoices invoice= new Invoices(invoiceNum,invoiceDate,customer);
          invoicesArray.add(invoice);
      }
      res=FileChooser.showOpenDialog(frame);
      if(res==JFileChooser.APPROVE_OPTION){
      File lineFile =FileChooser.getSelectedFile();
       Path linePath=Paths.get(lineFile.getAbsolutePath());
    List<String> lineLines =Files.readAllLines(linePath);
    for(String lineLine :lineLines){
          String [] lineParts=lineLine.split(",");
         int invoiceNum=Integer.parseInt(lineParts[0]);
           String name=lineParts[1];
           double price=Double.parseDouble(lineParts[2]);
             int count=Integer.parseInt(lineParts[3]);
             Invoices inv=null; 
      for(Invoices invoice:invoicesArray){
          if(invoice.getNumber()==invoiceNum){
              inv=invoice;
          break;
          }
      }
      Linee line=new Linee(invoiceNum,name,price,count,inv);
        inv.getLines().add(line);
    }
      }
      frame.setInvoicess(invoicesArray);
      InvModelTable invoicesTableModel=new InvModelTable(invoicesArray);
      frame.setInvTableModel(invoicesTableModel);
      frame.getI_Table().setModel(invoicesTableModel);
      frame.getInvTableModel().fireTableDataChanged();
            }
    }catch (IOException ex) {
                ex.printStackTrace();
            }
 
              }
        
    
    

    private void SaveFile() {
         ArrayList<Invoices> invoices = frame.getInvoicess();
        String headers = "";
        String lines = "";
        for (Invoices invoice : invoices) {
            String invCSV = invoice.getCSV();
            headers += invCSV;
            headers += "\n";

            for (Linee line : invoice.getLines()) {
                String lineCSV = line.getCSV();
                lines += lineCSV;
                lines += "\n";
            }
        }
        try {
            JFileChooser fc = new JFileChooser();
            int result = fc.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                FileWriter hfw = new FileWriter(headerFile);
                hfw.write(headers);
                hfw.flush();
                hfw.close();
                result = fc.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();
                    FileWriter lfw = new FileWriter(lineFile);
                    lfw.write(lines);
                    lfw.flush();
                    lfw.close();
                }
            }
        } catch (Exception ex) {

        }
    }
    
     

    private void CreateNewInvoice() {
        invdialog= new Invoice_of_Dialog(frame);
        invdialog.setVisible(true);
        
    }
private void DeleteInvoice() {
       int row= frame.getI_Table().getSelectedRow();
       if(row!= -1){
       frame.getInvoicess().remove(row);
       frame.getInvTableModel().fireTableDataChanged();
               }
    }

    private void DeleteItem() {
        int SelectInv=frame.getI_Table().getSelectedRow();
        int row= frame.getL_Table().getSelectedRow();
       if(SelectInv!=-1 && row!= -1){
           Invoices invoice=frame.getInvoicess().get(SelectInv);
           invoice.getLines().remove(row);
       LineModelTable LineModTable=new LineModelTable(invoice.getLines());
       frame.getL_Table().setModel(LineModTable);
       LineModTable.fireTableDataChanged();
       frame.getInvTableModel().fireTableDataChanged();
       
               }
    }
    

    private void CreateNewItem() {
         linedialog=new Dialog_of_Line(frame);
        linedialog.setVisible(true);
    }

    private void CreateInvoiceCancel() {
         invdialog.setVisible(false);
          invdialog.dispose();
           invdialog=null;
    } 

    private void CreateInvoiceOk() {
    String date = invdialog.getDateField().getText();
        String  customer = invdialog.getNameField().getText();
        int num = frame.getNextInvNum(); 
        Invoices invoice = new Invoices(num, date, customer);
              
                    frame.getInvoicess().add(invoice);
                    frame.getInvTableModel().fireTableDataChanged();
                    invdialog.setVisible(false);
                    invdialog.dispose();
                    invdialog = null;
       
    }


    private void CreateLineOk() {
        String item = linedialog.getItemField().getText();
        String countStr = linedialog.getCountField().getText();
        String priceStr = linedialog.getPriceField().getText();
        int count = Integer.parseInt(countStr);
        double price = Double.parseDouble(priceStr);
        int selectedInvoice = frame.getI_Table().getSelectedRow();
        if (selectedInvoice != -1) {
            Invoices invoice = frame.getInvoicess().get(selectedInvoice);
            Linee line = new Linee(item, price, count, invoice);
            invoice.getLines().add(line);
            LineModelTable linesTableModel = (LineModelTable) frame.getL_Table().getModel();
            linesTableModel.fireTableDataChanged();
            frame.getInvTableModel().fireTableDataChanged();
        }
        linedialog.setVisible(false);
        linedialog.dispose();
        linedialog = null;
    } 
   
    
    
    private void CreateLineCancel() {
        linedialog.setVisible(false);
        linedialog.dispose();
       linedialog=null;
       
    }

    
    
}
