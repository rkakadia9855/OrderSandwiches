/**
 * This class represents an order
 * @author John Juarez, Rudra Kakadia
 */
package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Order implements Customizable {

  public static int lineNumber; //reset for each new order;
  private ArrayList<OrderLine> orderlines;
  
  /**
   * Initializes the arraylist of orderline and linenumber
   */
  public Order() {
    lineNumber = 0;
    orderlines = new ArrayList<OrderLine>();
  }
  
  /**
   * gets the number of orders in the orderline arraylist
   * @return the number of orders
   */
  public int numOrders() {
    return orderlines.size();
  }
  
  /**
   * gets the serial number of the order at a particular index
   * @param index - the index of the order in arraylist
   * @return - the serial number of order
   */
  public int getSerialNumber(int index) {
    for(int i = 0; i < orderlines.size(); i++) {
      String temp = orderlines.get(i).sandwichToString();
      Integer tempIndex = Integer.parseInt(temp.charAt(0) + "");
      if(tempIndex == index)
        return i;
    }
    return -1;
  }
  
  /**
   * gets the orderline at a index
   * @param index - the index of the orderline requested
   * @return - the orderline
   */
  public OrderLine getOrderLine(int index) {
    return orderlines.get(index);
  }
  
  @Override
  /**
   * adds an orderline to the arraylist
   * @param obj - the orderline to be added
   * @return true if successfull else false
   */
  public boolean add(Object obj) {
    if(orderlines.add((OrderLine) obj)) {
      lineNumber++;
      return true;
    }
    else 
      return false;
  }

  @Override
  public boolean remove(Object obj) {
    if(orderlines.remove((OrderLine) obj)) {
      lineNumber--;
      return true;
    }
    else
      return false;
  }
  
  /**
   * gets the order total
   * @return the total amount of order
   */
  public double getOrderTotal() {
    double orderTotal = 0.0;
    for(int i = 0; i < lineNumber; i++) {
      orderTotal += orderlines.get(i).orderTotal();
    }
    return orderTotal;
  }
  
  /**
   * gets the details of an orderline
   * @param index - the index of the orderline whose details are needed
   * @return the details
   */
  public String getDetails(int index) {
    return orderlines.get(index).sandwichToString();
  }
   
  /**
   * returns the line that is displayed in viewlist of order summary
   * @return - the text of an orderline
   */
  public String printOrderLine() {
    String outputString = "";
    for(int i = 0; i < lineNumber; i++) {
      outputString += orderlines.get(i).sandwichToString();
    }
    return outputString; 
  }

}
