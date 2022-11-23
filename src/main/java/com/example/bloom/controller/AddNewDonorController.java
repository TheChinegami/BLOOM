package com.example.bloom.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddNewDonorController
{
    @FXML
    private Button adddonor_btn_add;

    @FXML
    private Button adddonor_btn_cancel;

    @FXML
    private TextField adddonor_tf_birthDate;

    @FXML
    private TextField adddonor_tf_cin;

    @FXML
    private TextField adddonor_tf_emergencynumber;

    @FXML
    private TextField adddonor_tf_firstname;

    @FXML
    private TextField adddonor_tf_lastname;

    @FXML
    private TextField adddonor_tf_phonenumber;

    @FXML
    void addAction(ActionEvent event) {

    }

    @FXML
    void cancelAction(ActionEvent event) {
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        stage.close();
    }

}
