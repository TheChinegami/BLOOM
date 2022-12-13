package com.example.bloom.controller;

import com.example.bloom.functional.Helper;
import com.example.bloom.model.Bag;
import com.example.bloom.model.Client;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BagsController implements Initializable {
    @FXML
    private Button bags_btn_addnewbag;

    @FXML
    private TableColumn<Bag, Integer> bags_tc_bagid;

    @FXML
    private TableColumn<Bag, LocalDate> bags_tc_donationdate;

    @FXML
    private TableColumn<Bag, Integer> bags_tc_donorid;


    @FXML
    private TableColumn<Bag, String> bags_tc_bloodtype;

    @FXML
    private TableView<Bag> bags_tv;

    @FXML
    private TextField donors_tf_search;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bags_tc_bagid.setCellValueFactory(new PropertyValueFactory<Bag,Integer>("id"));
        bags_tc_donationdate.setCellValueFactory(new PropertyValueFactory<Bag,LocalDate>("donationDate"));
        bags_tc_donorid.setCellValueFactory(new PropertyValueFactory<Bag,Integer>("donorid"));
        bags_tc_bloodtype.setCellValueFactory(new PropertyValueFactory<Bag,String>("bloodtypeid"));



            ObservableList list ;

        try
        {
            list = FXCollections.observableArrayList(Helper.getHelper().getBags());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        bags_tv.setItems(list);

    }
    @FXML
    void addNewBagAction(ActionEvent event) {

    }

    @FXML
    void onItemClick(MouseEvent event) {

    }


}
