package com.example.bloom.controller;

import com.example.bloom.functional.Helper;
import com.example.bloom.model.Bag;
import com.example.bloom.model.Plasma;
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
import java.util.Locale;
import java.util.ResourceBundle;

public class PlasmaController implements Initializable
{
    @FXML
    private TableColumn<Plasma, String> plasma_tc_bloodtype;

    @FXML
    private TableColumn<Plasma, LocalDate> plasma_tc_donationdate;

    @FXML
    private TableColumn<Plasma, Integer> plasma_tc_donorid;

    @FXML
    private TableColumn<Plasma, Integer> plasma_tc_id;

    @FXML
    private TableColumn<Plasma, LocalDate> plasma_tc_expirationdate;

    @FXML
    private TextField plasma_tf_search;

    @FXML
    private TableView<Plasma> plasma_tv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        plasma_tc_id.setCellValueFactory(new PropertyValueFactory<Plasma,Integer>("id"));
        plasma_tc_donationdate.setCellValueFactory(new PropertyValueFactory<Plasma,LocalDate>("donationDate"));
        plasma_tc_donorid.setCellValueFactory(new PropertyValueFactory<Plasma,Integer>("donorId"));
        plasma_tc_bloodtype.setCellValueFactory(new PropertyValueFactory<Plasma,String>("bloodType"));
        plasma_tc_expirationdate.setCellValueFactory(new PropertyValueFactory<Plasma,LocalDate>("expirationDate"));


        ObservableList list ;

        try
        {
            list = FXCollections.observableArrayList(Helper.getHelper().getplasmaBags());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        plasma_tv.setItems(list);

    }



    @FXML
    void onItemClick(MouseEvent event) {

    }
}
