/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class Line {
    private int number;
    private String item;
    private double price;
    private int count;
    private Invoices invoice;

    public Line() {
    }

 public Line(String item, double price, int count, Invoices invoice) {
      
        this.item = item;
        this.price = price;
        this.count = count;
        this.invoice = invoice;
    }

    public Line(int number, String item, double price, int count, Invoices invoice) {
        this.number = number;
        this.item = item;
        this.price = price;
        this.count = count;
        this.invoice = invoice;
    }

   public double getLineTot(){
   return price*count;
}
    

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
 

 public String getCSV(){
    return invoice.getNumber() +"," +item +","+price +"," +count;
    }
}
