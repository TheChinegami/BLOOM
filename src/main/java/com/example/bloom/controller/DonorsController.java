package com.example.bloom.controller;

import com.example.bloom.functional.Helper;
import com.example.bloom.model.Donor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DonorsController implements Initializable {
    private Integer index;


    @FXML
    private Button donors_btn_addnewdonor;

    @FXML
    private Button donors_btn_search;

    @FXML
    private TableColumn<Donor,Long> donors_tc_age;

    @FXML
    private TableColumn<Donor,String> donors_tc_cin;

    @FXML
    private TableColumn<Donor, Integer> donors_tc_donorId;

    @FXML
    private TableColumn<Donor,String> donors_tc_emergencynumber;

    @FXML
    private TableColumn<Donor,String> donors_tc_firstname;

    @FXML
    private TableColumn<Donor,String> donors_tc_lastname;

    @FXML
    private TableColumn<Donor,String> donors_tc_phonenumber;

    @FXML
    private TableColumn<Donor,String> donors_tc_sickness;

    @FXML
    private TextField donors_tf_search;

    @FXML
    private TableView<Donor> donors_tv;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        donors_tc_donorId.setCellValueFactory(new PropertyValueFactory<Donor,Integer>("id"));
        donors_tc_cin.setCellValueFactory(new PropertyValueFactory<Donor,String>("cin"));
        donors_tc_firstname.setCellValueFactory(new PropertyValueFactory<Donor,String>("firstName"));
        donors_tc_lastname.setCellValueFactory(new PropertyValueFactory<Donor,String>("lastName"));
        donors_tc_age.setCellValueFactory(new PropertyValueFactory<Donor,Long>("age"));
        donors_tc_phonenumber.setCellValueFactory(new PropertyValueFactory<Donor,String>("phoneNumber"));
        donors_tc_emergencynumber.setCellValueFactory(new PropertyValueFactory<Donor,String>("emergencyNumber"));
        donors_tc_sickness.setCellValueFactory(new PropertyValueFactory<Donor,String>("sickness"));

        ObservableList list;
        try {
             list = FXCollections.observableArrayList(Helper.getHelper().getDonors());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        donors_tv.setItems(list);

        FilteredList<Donor> filteredList = new FilteredList<>(list, b -> true);
        donors_tf_search.textProperty().addListener((observable,oldValue,newValue)->{
            filteredList.setPredicate(donor ->{
                if(newValue == null || newValue.isEmpty() || newValue.isBlank())
                {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if(Integer.toString(donor.getId()).toLowerCase().indexOf(lowerCaseFilter) > -1)
                {
                    return true;
                }
                return false;
            });
        });

        SortedList<Donor> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(donors_tv.comparatorProperty());
        donors_tv.setItems(sortedData);
    }

    @FXML
    public void onItemClick(MouseEvent mouseEvent) {
        index = donors_tv.getSelectionModel().getSelectedIndex();

        Donor donor = new Donor(
                Integer.parseInt(donors_tc_donorId.getCellData(index).toString()),
                donors_tc_cin.getCellData(index).toString(),
                donors_tc_firstname.getCellData(index).toString(),
                donors_tc_lastname.getCellData(index).toString(),
                donors_tc_phonenumber.getCellData(index).toString(),
                Integer.parseInt(donors_tc_age.getCellData(index).toString()),
                donors_tc_emergencynumber.getCellData(index).toString(),
//                donors_tc_sickness.getCellData(index).toString()
                false
        );

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("I WON!");
        alert.setContentText(donor.toString());
        alert.showAndWait();
    }
}