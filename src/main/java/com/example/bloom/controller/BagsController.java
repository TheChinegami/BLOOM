package com.example.bloom.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class BagsController {
    @FXML
    private Button bags_btn_addnewbag;

    @FXML
    private TableColumn<?, ?> bags_tc_bagid;

    @FXML
    private TableColumn<?, ?> bags_tc_donationdate;

    @FXML
    private TableColumn<?, ?> bags_tc_donorid;

    @FXML
    private TableView<?> bags_tv;

    @FXML
    private TextField donors_tf_search;

    @FXML
    void addNewBagAction(ActionEvent event) {

    }

    @FXML
    void onItemClick(MouseEvent event) {

    }
}
