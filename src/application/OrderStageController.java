/**
 * This class is a controller for 1st stage
 * @author John Juarez, Rudra Kakadia
 */
package application;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class OrderStageController implements Initializable {
  
    ObservableList<CheckBox> extraList = FXCollections.observableArrayList();
    ObservableList<String> extraSelectedList = FXCollections.observableArrayList();
    ObservableList<String> sandwichList = FXCollections.observableArrayList();
    Order orderDB = new Order();

    @FXML
    private ComboBox<String> combo;

    @FXML
    private ImageView sandwichPhoto;

    @FXML
    private ListView<CheckBox> extraIngredients;
    
    @FXML
    private ListView<String> selectedExtra;

    @FXML
    private TextField price;

    @FXML
    private Button addSandwich;
    
    @FXML
    private TextArea basicIngredients;

    @FXML
    private Button viewDetails;
    
    FXMLLoader loader;
    Parent root;
    OrderSummaryController controller;
    Scene scene;
    Stage stage;
    
    @FXML
    private Button clearButton;
    
    private int extraItems = 0;
    
    /**
     * passes the order
     * @return the order to pass
     */
    public Order passOrder() {
      return orderDB;
    }
    
    /**
     * loads the extra ingredients in the listview
     */
    private void loadExtraIngredients() {
      extraList.removeAll(extraList);
      CheckBox tomatoes = new CheckBox("Tomatoes");
      CheckBox onions = new CheckBox("Onions");
      CheckBox olives = new CheckBox("Olives");
      CheckBox spinach = new CheckBox("Spinach");
      CheckBox mayo = new CheckBox("Mayonnaise");
      CheckBox honeymustard = new CheckBox("Honey Mustard");
      CheckBox chipotle = new CheckBox("Chipotle Southwest");
      CheckBox oilvinegar = new CheckBox("Oil and Vinegar");
      CheckBox moz = new CheckBox("Mozerella Cheese");
      CheckBox cheddar = new CheckBox("Cheddar Cheese");
      
      extraList.addAll(tomatoes, onions, olives, spinach, mayo, honeymustard,
          oilvinegar, moz, cheddar);
      extraIngredients.getItems().addAll(extraList);
      
      extraList.forEach(extra -> {
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

          @Override
          public void handle(ActionEvent arg0) {
             if(extra.isSelected()) {
               extraItems++;
               selectedExtra.getItems().add(extra.getText());
               extraSelectedList.add(extra.getText());
               if(extraItems == 6) {
                 extraList.forEach(subExtra -> {
                   if(!(subExtra.isSelected())) {
                     subExtra.setDisable(true);
                   }
                 });
               }
               double extraCost = extraItems * 1.99;
               double sandwichCost = 0.0;
               if(combo.getValue().equals("Chicken Sandwich")) {
                 sandwichCost = 8.99;
               }
               else if(combo.getValue().equals("Beef Sandwich")) {
                 sandwichCost = 10.99;
               }
               else if(combo.getValue().equals("Fish Sandwich")) {
                 sandwichCost = 12.99;
               }
               sandwichCost += extraCost;
               price.setText("$ "+ String.format("%.2f", sandwichCost));
             }
             else {
               if(extraItems == 6) {
                 extraList.forEach(subExtra -> {
                   if(!(subExtra.isSelected())) {
                     subExtra.setDisable(false);
                   }
                 });
               }
               selectedExtra.getItems().remove((String)extra.getText());
               extraItems--;
               double extraCost = extraItems * 1.99;
               double sandwichCost = 0.0;
               if(combo.getValue().equals("Chicken Sandwich")) {
                 sandwichCost = 8.99;
               }
               else if(combo.getValue().equals("Beef Sandwich")) {
                 sandwichCost = 10.99;
               }
               else if(combo.getValue().equals("Fish Sandwich")) {
                 sandwichCost = 12.99;
               }
               sandwichCost += extraCost;
               price.setText("$ "+String.format("%.2f", sandwichCost));
             }
          } 
          
        };
        
        extra.setOnAction(event); 
        
      });
      
    }
    
    /**
     * loads up the combobox and other fields
     */
    private void loadDefaultSandwich() {
      sandwichList.removeAll(sandwichList);
      sandwichList.addAll("Chicken Sandwich", "Beef Sandwich", "Fish Sandwich");
      combo.getItems().addAll(sandwichList);
      combo.getSelectionModel().selectFirst();
      price.setText("$ 8.99");
      basicIngredients.setText("* Fried Chicken\n* Spicy Sauce\n* Pickles");
      sandwichPhoto.setImage(new Image("Resources\\ChickenSandwich.JPG"));
    }

    @FXML
    /**
     * adds the sandwich to order
     * @param event
     */
    void addToOrder(ActionEvent event) {
      String selectedSandwich = combo.getValue();
      Sandwich selected =  null;
      double sandwichTotal = 0.0;
      if(selectedSandwich.equals("Chicken Sandwich")) {
        selected = new Chicken();
        sandwichTotal = 8.99;
      }
      else if(selectedSandwich.equals("Beef Sandwich")) {
        selected = new Beef();
        sandwichTotal = 10.99;
      }
      else if(selectedSandwich.equals("Fish Sandwich")) {
        selected = new Fish();
        sandwichTotal = 12.99;
      }
      if(selected != null) {
        for(int i = 0; i < extraList.size(); i++) {
          if(extraList.get(i).isSelected()) {
            selected.add(new Extra(extraList.get(i).getText()));
          }
        }
      }
      double extraCost = extraItems * 1.99;
      sandwichTotal += extraCost;
      OrderLine oline = new OrderLine(orderDB.lineNumber, selected, sandwichTotal);
      String updateString = oline.sandwichToString();
      if(orderDB.add(oline)) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Successfull!!");
        alert.setHeaderText("Sandwich Added!");
        alert.setContentText(combo.getValue()+" has been added to your order.");
        alert.showAndWait();
        if(loader != null) {
          controller.updateScreen(updateString);
        }
      }
      else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error!!");
        alert.setHeaderText("Sandwich couldn't be added!");
        alert.setContentText(combo.getValue()+" couldn't be added to order due to error.");
        alert.showAndWait();
      }
    }

    @FXML
    /**
     * triggers upon changing the selected sandwich
     * @param event
     */
    void sandwichSelected(ActionEvent event) {
      if(combo.getValue().equals("Beef Sandwich")) {
        try {
            Image image = new Image("Resources\\BeefSandwich.JPG");
            sandwichPhoto.setImage(image);
        } catch (Exception e) {
          Alert alert = new Alert(AlertType.ERROR);
          alert.setTitle("Error!!");
          alert.setHeaderText("Sandwich image not loaded!");
          alert.setContentText("Error while loading the image.");
          alert.showAndWait();
        }
        double extraCost = extraItems * 1.99;
        double sandwichTotal = extraCost + 10.99;
        price.setText("$ "+sandwichTotal);
        basicIngredients.setText("* Roast Beef\n* Provolone Cheese\n* Mustard");
      }
      else if(combo.getValue().equals("Chicken Sandwich")) {
        try {
          sandwichPhoto.setImage(new Image("Resources\\ChickenSandwich.JPG"));
        }
        catch(Exception e) {
          Alert alert = new Alert(AlertType.ERROR);
          alert.setTitle("Error!!");
          alert.setHeaderText("Sandwich image not loaded!");
          alert.setContentText("Error while loading the image.");
          alert.showAndWait();
        }
        double extraCost = extraItems * 1.99;
        double sandwichTotal = extraCost + 8.99;
        price.setText("$ "+sandwichTotal);
        basicIngredients.setText("* Fried Chicken\n* Spicy Sauce\n* Pickles");
      }
      else if(combo.getValue().equals("Fish Sandwich")) {
        try {
          sandwichPhoto.setImage(new Image("Resources\\FishSandwich.JPG"));
        }
        catch(Exception e) {
          Alert alert = new Alert(AlertType.ERROR);
          alert.setTitle("Error!!");
          alert.setHeaderText("Sandwich image not loaded!");
          alert.setContentText("Error while loading the image.");
          alert.showAndWait();
        }
        double extraCost = extraItems * 1.99;
        double sandwichTotal = extraCost + 12.99;
        price.setText("$ "+String.format("%.2f", sandwichTotal));
        basicIngredients.setText("* Grilled Snapper\n* Cilantro\n* Lime");
      }
    }
    
    @FXML
    /**
     * clears the selected options
     * @param event
     */
    void clearSelected(ActionEvent event) {
      combo.getSelectionModel().selectFirst();
      price.setText("$ 8.99");
      basicIngredients.setText("* Fried Chicken\n* Spicy Sauce\n* Pickles");
      sandwichPhoto.setImage(new Image("Resources\\ChickenSandwich.JPG"));
      extraList.forEach(extra -> {
        if(extra.isSelected())
          extra.setSelected(false);
        extra.setDisable(false);
      });
      extraItems = 0;
      selectedExtra.getItems().removeAll(extraSelectedList);
    }

    @FXML
    /**
     * opens another window to view order details
     * @param event
     */
    void viewOrder(ActionEvent event) {
      try {
            loader = new FXMLLoader(getClass().getResource("OrderSummary.FXML"));
    		root = loader.load();
    		
    		controller = loader.getController();
    		controller.selectOrder(orderDB);
        controller.setView1Controller(this);
        
    		scene = new Scene(root); 	  
    		stage = new Stage();
    		stage.setTitle("Order Summary");
    		stage.setScene(scene);
    		stage.show(); 
      }
      catch(Exception e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error!!");
        alert.setHeaderText("Sandwich image not loaded!");
        alert.setContentText("Error while loading the image.");
        alert.showAndWait();
      }
    }

    @Override
    /**
     * initializes this stage
     */
    public void initialize(java.net.URL arg0, ResourceBundle arg1) {
       loadExtraIngredients();
       loadDefaultSandwich();
    }

}
