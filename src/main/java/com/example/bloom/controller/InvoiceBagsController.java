package com.example.bloom.controller;

import com.example.bloom.functional.Helper;
import com.example.bloom.model.Bag;
import com.example.bloom.model.Donor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InvoiceBagsController implements Initializable {
    public int billId;

    @FXML
    private Button invoicebags_btn_showbags;

    @FXML
    private TextField invoicebags_tf_search;


    @FXML
    private TableColumn<Bag, Integer> invoicebags_tc_bagid;

    @FXML
    private TableColumn<Bag, String> invoicebags_tc_bagtype;

    @FXML
    private TableColumn<Bag, String> invoicebags_tc_bloodtype;

    @FXML
    private TableColumn<Bag, LocalDate> invoicebags_tc_donationdate;

    @FXML
    private TableColumn<Bag, String> invoicebags_tc_donorcin;

    @FXML
    private TableColumn<Bag, LocalDate> invoicebags_tc_expirationdate;

    @FXML
    private TableView<Bag> invoicebags_tv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        invoicebags_tc_bagid.setCellValueFactory(new PropertyValueFactory<Bag,Integer>("id"));
        invoicebags_tc_bagtype.setCellValueFactory(new PropertyValueFactory<Bag,String>("bagType"));
        invoicebags_tc_bloodtype.setCellValueFactory(new PropertyValueFactory<Bag,String>("bloodType"));
        invoicebags_tc_donationdate.setCellValueFactory(new PropertyValueFactory<Bag,LocalDate>("donationDate"));
        invoicebags_tc_donorcin.setCellValueFactory(new PropertyValueFactory<Bag,String>("donorCin"));
        invoicebags_tc_expirationdate.setCellValueFactory(new PropertyValueFactory<Bag,LocalDate>("expirationDate"));
    }
    @FXML
    void onItemClick(MouseEvent event) {

    }

    @FXML
    void showBagAction(ActionEvent event) {
        ObservableList list;
        try
        {
            list = FXCollections.observableArrayList(Helper.getHelper().getBagsOfBill(billId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        invoicebags_tv.setItems(list);
        FilteredList<Bag> filteredList = new FilteredList<>(list, b -> true);
        invoicebags_tf_search.textProperty().addListener((observable,oldValue,newValue)->{
            filteredList.setPredicate(bag ->{
                if(newValue == null || newValue.isEmpty() || newValue.isBlank())
                {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if(Integer.toString(bag.getId()).toLowerCase().indexOf(lowerCaseFilter) > -1)
                {
                    return true;
                }
                return false;
            });
        });
        SortedList<Bag> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(invoicebags_tv.comparatorProperty());
        invoicebags_tv.setItems(sortedData);
    }

}
