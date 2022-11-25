package com.example.bloom.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable
{

    @FXML
    private Button clients_btn_addnewclient;

    @FXML
    private TableColumn<?, ?> clients_tc_id;

    @FXML
    private TableColumn<?, ?> clients_tc_name;

    @FXML
    private TableColumn<?, ?> clients_tc_phonenumber;

    @FXML
    private TableView<?> clients_tv;

    @FXML
    private TextField plasma_tf_search;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onItemClick(MouseEvent event) {

    }

    @FXML
    void addnewclientAction(ActionEvent event) {

    }

}
