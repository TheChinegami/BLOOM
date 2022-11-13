package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public  class ajouterPocheDeSang {


    @FXML
    private ComboBox  ComboBox;

    @FXML
    void Select(ActionEvent event) {
        String s = ComboBox.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void initialize() {
        ObservableList<String> liste = FXCollections.observableArrayList("A+","B+","AB","A-","B-","O+","O-");
        ComboBox.setItems(liste);
    }

}