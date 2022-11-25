package com.example.bloom.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PlasmaController implements Initializable
{
    @FXML
    private TableColumn<?, ?> plasma_tc_bloodtype;

    @FXML
    private TableColumn<?, ?> plasma_tc_donationdate;

    @FXML
    private TableColumn<?, ?> plasma_tc_donorid;

    @FXML
    private TableColumn<?, ?> plasma_tc_id;

    @FXML
    private TextField plasma_tf_search;

    @FXML
    private TableView<?> plasma_tv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onItemClick(MouseEvent event) {

    }
}
