package com.example.bloom.controller;

import com.example.bloom.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private VBox menu;

    @FXML
    private Button menuBtnBags;

    @FXML
    private Button menuBtnClients;

    @FXML
    private Button menuBtnDonors;

    @FXML
    private Button menuBtnExit;

    @FXML
    private Button menuBtnInvoices;

    @FXML
    private Button menuBtnPlasma;

    @FXML
    private Button menuBtnPlatelets;

    @FXML
    private Button menuBtnRedcells;

    @FXML
    private BorderPane myBorderPane;

    @FXML
    private StackPane myCenter;

    @FXML
    void onClickDonors(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/donors.fxml"));
            Parent root = fxmlLoader.load();
            myCenter.getChildren().removeAll();
            myCenter.getChildren().setAll(root);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    void onClickBags(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/bags.fxml"));
            Parent root = fxmlLoader.load();
            myCenter.getChildren().removeAll();
            myCenter.getChildren().setAll(root);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    void onClickClients(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/clients.fxml"));
            Parent root = fxmlLoader.load();
            myCenter.getChildren().removeAll();
            myCenter.getChildren().setAll(root);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    void onClickInvoices(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/invoices.fxml"));
            Parent root = fxmlLoader.load();
            myCenter.getChildren().removeAll();
            myCenter.getChildren().setAll(root);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    void onClickPlasma(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/plasma.fxml"));
            Parent root = fxmlLoader.load();
            myCenter.getChildren().removeAll();
            myCenter.getChildren().setAll(root);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    void onClickPlatelets(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/platelets.fxml"));
            Parent root = fxmlLoader.load();
            myCenter.getChildren().removeAll();
            myCenter.getChildren().setAll(root);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    void onClickRedcells(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/redcells.fxml"));
            Parent root = fxmlLoader.load();
            myCenter.getChildren().removeAll();
            myCenter.getChildren().setAll(root);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    void onClickExit(ActionEvent event) {
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        stage.close();
    }

}
