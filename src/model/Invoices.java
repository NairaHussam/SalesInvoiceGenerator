/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
 
import java.util.ArrayList;

public class Invoices {
    private int number;
    private String date;
    private String customer;
 private ArrayList<Linee> Lines;
 
    public String getCSV;
 
    public Invoices() {
    }

    public Invoices(int number, String date, String customer) {
        this.number = number;
        this.date = date;
        this.customer = customer;
    }
      public double getInvoiceTot(){
     double tot=0.0;
      for(Linee Line:getLines()){
      tot+=Line.getLineTot();
}
        return tot;
}
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Linee> getLines() {
        if(Lines==null){
        Lines=new ArrayList<>();
        }
        return Lines;
    }

    public void setLines(ArrayList<Linee> Lines) {
        this.Lines = Lines;
    }
 

    @Override
    public String toString() {
        return "Invoices{" + "number=" + number + ", date=" + date + ", customer=" + customer + '}';
    }
    
    public String getCSV(){
    return number +"," +date +","+customer;
    }
}
