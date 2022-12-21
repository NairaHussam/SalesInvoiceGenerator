/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class InvModelTable extends AbstractTableModel {
   private ArrayList<Invoices> invoicess; 
private String [] cols={"Number","Date","Customer","Total"};

    public InvModelTable(ArrayList<Invoices> invoicess) {
        this.invoicess = invoicess;
    }

    @Override
    public int getRowCount() {
        return invoicess.size();
    }

    @Override
    public int getColumnCount() {
       return cols.length; 
    }
public String getColumnName(int Column){
    return cols[Column];
}
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Invoices invoice=invoicess.get(rowIndex);
    switch(columnIndex){
        case 0:
            return invoice.getNumber();
        case 1:
            return invoice.getDate();
        case 2:
            return invoice.getCustomer();
        case 3:
            return invoice.getInvoiceTot();
        default:
            return"";
    }
    }
}
