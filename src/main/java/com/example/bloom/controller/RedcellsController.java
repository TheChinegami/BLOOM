package com.example.bloom.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class RedcellsController implements Initializable
{

    @FXML
    private TableColumn<?, ?> redcells_tc_bloodtype;

    @FXML
    private TableColumn<?, ?> redcells_tc_donationdate;

    @FXML
    private TableColumn<?, ?> redcells_tc_donorid;

    @FXML
    private TableColumn<?, ?> redcells_tc_id;

    @FXML
    private TextField redcells_tf_search;

    @FXML
    private TableView<?> redcells_tv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onItemClick(MouseEvent event) {

    }

}
