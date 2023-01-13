package com.example.bloom.controller;

import com.example.bloom.functional.Helper;
import com.example.bloom.model.Bag;
import com.example.bloom.model.Platelets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PlateletsController implements Initializable
{
    @FXML
    private TableColumn<Platelets, String> platelets_tc_bloodtype;

    @FXML
    private TableColumn<Platelets, LocalDate> platelets_tc_donationdate;

    @FXML
    private TableColumn<Platelets, Integer> platelets_tc_donorid;

    @FXML
    private TableColumn<Platelets, Integer> platelets_tc_id;

    @FXML
    private TableColumn<Platelets, LocalDate> platelets_tc_expirationdate;

    @FXML
    private TableView<Platelets> platelets_tv;

    @FXML
    private TextField platelets_tf_search;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        platelets_tc_id.setCellValueFactory(new PropertyValueFactory<Platelets,Integer>("id"));
        platelets_tc_donationdate.setCellValueFactory(new PropertyValueFactory<Platelets,LocalDate>("donationDate"));
        platelets_tc_donorid.setCellValueFactory(new PropertyValueFactory<Platelets,Integer>("donorId"));
        platelets_tc_bloodtype.setCellValueFactory(new PropertyValueFactory<Platelets,String>("bloodType"));
        platelets_tc_expirationdate.setCellValueFactory(new PropertyValueFactory<Platelets,LocalDate>("expirationDate"));


        ObservableList list ;

        try
        {
            list = FXCollections.observableArrayList(Helper.getHelper().getplatletsBags());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        platelets_tv.setItems(list);

    }


    @FXML
    void onItemClick(MouseEvent event) {

    }

}
