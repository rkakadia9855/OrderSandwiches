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
  
  public Order() {
    lineNumber = 0;
    orderlines = new ArrayList<OrderLine>();
  }
  
  public int numOrders() {
    return orderlines.size();
  }
  
  public int getSerialNumber(int index) {
    for(int i = 0; i < orderlines.size(); i++) {
      String temp = orderlines.get(i).sandwichToString();
      Integer tempIndex = Integer.parseInt(temp.charAt(0) + "");
      if(tempIndex == index)
        return i;
    }
    return -1;
  }
  
  public OrderLine getOrderLine(int index) {
    return orderlines.get(index);
  }
  
  @Override
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
  
  public double getOrderTotal() {
    double orderTotal = 0.0;
    for(int i = 0; i < lineNumber; i++) {
      orderTotal += orderlines.get(i).orderTotal();
    }
    return orderTotal;
  }
  
  public String getDetails(int index) {
    return orderlines.get(index).sandwichToString();
  }
   
  public String printOrderLine() {
    String outputString = "";
    for(int i = 0; i < lineNumber; i++) {
      outputString += orderlines.get(i).sandwichToString();
    }
   /* double orderTotal = 0.0;
    for(int i = 0; i < lineNumber; i++) {
      orderTotal += orderlines.get(i).orderTotal();
    }
    outputString += "Order Total: $" + String.format("%.2f", orderTotal); */
    return outputString; 
   /* FileChooser chooser = new FileChooser();
    chooser.setTitle("Open Target File for the Export");
    chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
            new ExtensionFilter("All Files", "*.*"));
    Stage stage = new Stage();
    File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
    //write code to write to the file.
    String exportData = outputString;
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile))) {
      if(writer != null) {
        writer.write(exportData);
        writer.flush();
        writer.close();
      }
    } catch (Exception e) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Warning!!");
      alert.setHeaderText("I/O Error");
      alert.setContentText("An error occured while trying to export the data: ."+e.getMessage());
      alert.showAndWait();
    } */
  }

}
