/**
 * This class is a controller for 2nd stage
 * @author John Juarez, Rudra Kakadia
 */
package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class OrderSummaryController implements Initializable{
	
	ObservableList<String> orderlist = FXCollections.observableArrayList();

    @FXML
    private ListView<String> receipt;

    @FXML
    private Button sameOrderLine;

    @FXML
    private Button clear;

    @FXML
    private Button removeOrderLine;

    @FXML
    private Button back;

    @FXML
    private TextField total;

    @FXML
    private Button saveOrder;
    
    OrderStageController controller1;
    
    Order list;
    
    /**
     * initializes the order that was passed
     * @param order2
     */
    public void selectOrder(Order order2) {
    	list = order2;
    	fillListView();
    }
    
    /**
     * sets view controller 1 reference
     * @param arg - the 1st view controller
     */
    public void setView1Controller(OrderStageController arg) {
      this.controller1 = arg;
    }
    
    /**
     * fills the listview with order details
     */
    public void fillListView() {
      int numOrders = list.numOrders();
      for(int i = 0; i < numOrders; i++) {
        orderlist.add(list.getDetails(i));
      }
      receipt.getItems().addAll(orderlist);
      total.setText(String.format("%.2f", list.getOrderTotal()));
    }
    
    /**
     * updates the screen after a sandwich was added from 1st stage
     * @param updatedLine
     */
    public void updateScreen(String updatedLine) {
      orderlist.add(updatedLine);
      receipt.getItems().add(updatedLine);
      total.setText(String.format("%.2f", list.getOrderTotal()));
    }
    
    /**
     * updates the screen after a sandwich was removed from 1st stage
     * @param updatedLine
     */
     public void updateScreen2(String updatedLine) {
        orderlist.remove(updatedLine);
        receipt.getItems().remove(updatedLine);
        total.setText(String.format("%.2f", list.getOrderTotal()));
      }
	
    @FXML
    /**
     * closes 2nd stage window
     * @param event
     */
    void backToOrder(ActionEvent event) {
    	Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    @FXML
    /**
     * empties the order
     * @param event
     */
    void clearOrder(ActionEvent event) {
	 receipt.getItems().removeAll(orderlist);
    	 list = new Order();
    	 controller1.orderDB = list;
    	 total.setText(null);
    }

    @FXML
    /**
     * saves the order details to another file
     * @param event
     */
    void exportOrder(ActionEvent event) {
      FileChooser chooser = new FileChooser();
      chooser.setTitle("Open Target File for the Export");
      chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
              new ExtensionFilter("All Files", "*.*"));
      Stage stage = new Stage();
      File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
      //write code to write to the file.
      String exportData = list.printOrderLine();
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
      }
    }

    @FXML
    /**
     * repeats an order
     * @param event
     */
    void orderAgain(ActionEvent event) {
      ObservableList<String> orders = receipt.getSelectionModel().getSelectedItems();
      orders.forEach(order -> {
        Integer index = Integer.parseInt("" + order.charAt(0));
        replicatedOrder(index);
      });
    }
    
    /**
     *  replicates the order that needs to be repeated
     * @param index - the index of the order that needs to be repeated
     * @return true if success, false otherwise
     */
    public boolean replicatedOrder(int index) {
      OrderLine copy = null;
      int getIndex = list.getSerialNumber(index);
      OrderLine orig = list.getOrderLine(getIndex);
      Sandwich order = null;
      if(orig.getSandwich() instanceof Chicken)
        order = new Chicken();
      else if(orig.getSandwich() instanceof Beef)
        order = new Beef();
      else if(orig.getSandwich() instanceof Fish)
        order = new Fish();
      for(int i = 0; i < orig.getSandwich().extras.size(); i++) {
        order.extras.add(new Extra(orig.getSandwich().extras.get(i).name()));
      }
      copy = new OrderLine(list.lineNumber, order, order.price());
      if(list.add(copy)) {
        String updateString = copy.sandwichToString();
        updateScreen(updateString);
        return true;
      }
      else 
        return false;
    }

    @FXML
    /**
     * removes the sandwich
     * @param event
     */
    void removeSandwich(ActionEvent event) {
	
	final int selectedIdx = receipt.getSelectionModel().getSelectedIndex();
	            if (selectedIdx != -1) {
	           
	                receipt.getItems().remove(selectedIdx);       
	                OrderLine orig = list.getOrderLine(selectedIdx);
	         
	                if(list.remove(orig)) {   
	                	String update = orig.sandwichToString();
	                	updateScreen2(update);
	                }
	                for(int i = selectedIdx; i < list.numOrders(); i++) {
	                  list.getOrderLine(i).setLineNumber(list.getOrderLine(i).getLineNumber() - 1);
	                }
	                receipt.getItems().clear();
	                orderlist.clear();
	                fillListView();
	            }
    }


	@Override
	/**
	 * initializes the stage
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {

	    receipt.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
	}

}
