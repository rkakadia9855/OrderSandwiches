package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrderSummaryController implements Initializable{
	
	ObservableList<OrderLine> orderlist = FXCollections.observableArrayList();

    @FXML
    private ListView<OrderLine> receipt;

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
    
    Order list = new Order();
    
    public void selectOrder(Order order2) {
    	list = order2;
    	
    }
    
        
    @FXML
    void backToOrder(ActionEvent event) {
    	Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    @FXML
    void clearOrder(ActionEvent event) {

    }

    @FXML
    void exportOrder(ActionEvent event) {

    }

    @FXML
    void orderAgain(ActionEvent event) {

    }

    @FXML
    void removeSandwich(ActionEvent event) {

    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
