package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class OrderSummaryController {

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

    @FXML
    void backToOrder(ActionEvent event) {

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

}
