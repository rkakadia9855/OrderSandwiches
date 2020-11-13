package application;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class OrderStageController implements Initializable {
  
    ObservableList<CheckBox> extraList = FXCollections.observableArrayList();
    ObservableList<String> sandwichList = FXCollections.observableArrayList();
    Order orderDB = new Order();

    @FXML
    private ComboBox<String> combo;

    @FXML
    private ImageView sandwichPhoto;

    @FXML
    private ListView<CheckBox> extraIngredients;

    @FXML
    private TextField price;

    @FXML
    private Button addSandwich;

    @FXML
    private Button viewDetails;

    @FXML
    private Button submit;
    
    private int extraItems = 0;
    
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
               if(extraItems == 6) {
                 extraList.forEach(subExtra -> {
                   if(!(subExtra.isSelected())) {
                     subExtra.setDisable(true);
                   }
                 });
               }
             }
             else {
               if(extraItems == 6) {
                 extraList.forEach(subExtra -> {
                   if(!(subExtra.isSelected())) {
                     subExtra.setDisable(false);
                   }
                 });
               }
               extraItems--;
             }
          } 
          
        };
        
        extra.setOnAction(event); 
        
      });
      
    }
    
    private void loadDefaultSandwich() {
      sandwichList.removeAll(sandwichList);
      sandwichList.addAll("Chicken Sandwich", "Beef Sandwich", "Fish Sandwich");
      combo.getItems().addAll(sandwichList);
      combo.getSelectionModel().selectFirst();
    }

    @FXML
    void addToOrder(ActionEvent event) {

    }

    @FXML
    void sandwichSelected(ActionEvent event) {

    }

    @FXML
    void submitOrder(ActionEvent event) {

    }

    @FXML
    void viewOrder(ActionEvent event) {

    }

    @Override
    public void initialize(java.net.URL arg0, ResourceBundle arg1) {
       loadExtraIngredients();
       loadDefaultSandwich();
    }

}
