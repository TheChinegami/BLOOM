package com.example.bloom.controller;

import com.example.bloom.Helper;
import com.example.bloom.Model.Donor;
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
    private TextField donorTextFieldSearch;

    @FXML
    private Button donorsBtnAddNewDonor;

    @FXML
    private Button donorsBtnSearch;

    @FXML
    private TableColumn<Donor,String> donorsTableColumnBirthDay;

    @FXML
    private TableColumn<Donor,String> donorsTableColumnCin;

    @FXML
    private TableColumn<Donor,String> donorsTableColumnFirstName;

    @FXML
    private TableColumn<Donor,Integer> donorsTableColumnId;

    @FXML
    private TableColumn<Donor,String> donorsTableColumnLastName;

    @FXML
    private TableColumn<Donor,String> donorsTableColumnPhoneNumber;

    @FXML
    private TableView<Donor> donorsTableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        donorsTableColumnId.setCellValueFactory(new PropertyValueFactory<Donor,Integer>("id"));
        donorsTableColumnCin.setCellValueFactory(new PropertyValueFactory<Donor,String>("cin"));
        donorsTableColumnFirstName.setCellValueFactory(new PropertyValueFactory<Donor,String>("firstName"));
        donorsTableColumnLastName.setCellValueFactory(new PropertyValueFactory<Donor,String>("lastName"));
        donorsTableColumnBirthDay.setCellValueFactory(new PropertyValueFactory<Donor,String>("birthDate"));
        donorsTableColumnPhoneNumber.setCellValueFactory(new PropertyValueFactory<Donor,String>("phoneNumber"));

        ObservableList list;
        try {
             list = Helper.getHelper().getDonors();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        donorsTableView.setItems(list);

        FilteredList<Donor> filteredList = new FilteredList<>(list, b -> true);
        donorTextFieldSearch.textProperty().addListener((observable,oldValue,newValue)->{
            filteredList.setPredicate(donor->{
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
        sortedData.comparatorProperty().bind(donorsTableView.comparatorProperty());
        donorsTableView.setItems(sortedData);
    }

    @FXML
    public void onItemClick(MouseEvent mouseEvent) {
        index = donorsTableView.getSelectionModel().getSelectedIndex();

        Donor donor = new Donor(
                Integer.parseInt(donorsTableColumnId.getCellData(index).toString()),
                donorsTableColumnCin.getCellData(index).toString(),
                donorsTableColumnFirstName.getCellData(index).toString(),
                donorsTableColumnLastName.getCellData(index).toString(),
                donorsTableColumnPhoneNumber.getCellData(index).toString(),
                donorsTableColumnBirthDay.getCellData(index).toString()
        );

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("I WON!");
        alert.setContentText(donor.toString());
        alert.showAndWait();
    }
}