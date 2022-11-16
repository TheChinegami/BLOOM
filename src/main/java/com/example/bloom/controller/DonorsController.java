package com.example.bloom.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class DonorsController {

    @FXML
    private Button DonorButton;

    @FXML
    private Text DonorText;

    @FXML
    void onClickOnButton(ActionEvent event) {
        DonorText.setText("I WON!");
    }

}
