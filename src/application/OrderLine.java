package application;

public class OrderLine {
  private int lineNumber; //a serial number created when a sandwich is added to the order
  private Sandwich sandwich;
  private double price;
  
  public OrderLine(int lineNumber, Sandwich sandwich, double price) {
    this.lineNumber = lineNumber;
    this.sandwich = sandwich;
    this.price = price;
  }
  
  public double orderTotal() {
    return this.sandwich.price();
  }
  
 public String sandwichToString() {
   String rtn = "Serial Number: " + this.lineNumber + "\n";
   rtn += sandwich.toString();
   return rtn;
 }
}
