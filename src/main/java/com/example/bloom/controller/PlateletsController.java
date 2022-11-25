package com.example.bloom.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PlateletsController implements Initializable
{
    @FXML
    private TableColumn<?, ?> platelets_tc_bloodtype;

    @FXML
    private TableColumn<?, ?> platelets_tc_donationdate;

    @FXML
    private TableColumn<?, ?> platelets_tc_donorid;

    @FXML
    private TableColumn<?, ?> platelets_tc_id;

    @FXML
    private TableView<?> platelets_tv;

    @FXML
    private TextField redcells_tf_search;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onItemClick(MouseEvent event) {

    }

}
