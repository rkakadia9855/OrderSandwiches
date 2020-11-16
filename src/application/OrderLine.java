/**
 * This class represents an orderline
 * @author John Juarez, Rudra Kakadia
 */
package application;

public class OrderLine {
  private int lineNumber; //a serial number created when a sandwich is added to the order
  private Sandwich sandwich;
  private double price;
  
  /**
   * Initializes the linenumber, sandwich and price
   */
  public OrderLine(int lineNumber, Sandwich sandwich, double price) {
    this.lineNumber = lineNumber;
    this.sandwich = sandwich;
    this.price = price;
  }
  
  /**
   * retreives the line number of this orderline
   * @return the line number 
   */
  public int getLineNumber() {
    return this.lineNumber;
  }
  
  /**
   * updates/edits the line number of this orderline
   * @param num - the new line number
   */
  public void setLineNumber(int num) {
    this.lineNumber = num;
  }
  
  /**
   * retreives the sandwich
   * @return sandwich in this orderline
   */
  public Sandwich getSandwich() {
    return this.sandwich;
  }
  
  /**
   * retreives the ordertotal
   * @return ordertotal of this orderline
   */
  public double orderTotal() {
    return this.sandwich.price();
  }
  
  /**
   * returns string representation of this orderline
   * @return
   */
 public String sandwichToString() {
   String rtn = this.lineNumber + " ";
   rtn += sandwich.toString();
   return rtn;
 }
}
