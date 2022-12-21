/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class LineModelTable extends AbstractTableModel{
private ArrayList<Line> lines;
private String [] cols={"Number","Item No.","Item Price","Count","Total"};

    public LineModelTable(ArrayList<Line> lines) {
        this.lines = lines;
    }

    

    @Override
    public int getRowCount() {
        return lines.size();
    }

    
    public int getColumnCount() {
        return cols.length;
    }
    @Override
    public String getColumnName(int column){
return cols[column];
        }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Line line= lines.get(rowIndex);
        switch(columnIndex){
        case 0:
            return line.getNumber();
        case 1:
            return line.getItem();
        case 2:
            return line.getPrice();
        case 3:
            return line.getCount();
        case 4:
            return line.getLineTot();
        default:
            return"";
    }
    }

   
    
}
