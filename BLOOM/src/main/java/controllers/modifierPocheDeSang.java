package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class modifierPocheDeSang {



    @FXML
    private ComboBox com;

    @FXML
    void EventActn(ActionEvent event) {
        String s = com.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void initialize() {
        ObservableList<String> liste = FXCollections.observableArrayList("A+","B+","AB","A-","B-","O+","O-");
        com.setItems(liste);
    }

}
