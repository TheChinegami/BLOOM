package com.example.bloom.controller;

import com.example.bloom.functional.Helper;
import com.example.bloom.model.Bag;
import com.example.bloom.model.RedCells;
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

public class RedcellsController implements Initializable
{

    @FXML
    private TableColumn<RedCells, String > redcells_tc_bloodtype;

    @FXML
    private TableColumn<RedCells, LocalDate> redcells_tc_donationdate;

    @FXML
    private TableColumn<RedCells, LocalDate> redcells_tc_expirationdate;

    @FXML
    private TableColumn<RedCells, Integer> redcells_tc_donorid;

    @FXML
    private TableColumn<RedCells, Integer> redcells_tc_id;

    @FXML
    private TextField redcells_tf_search;

    @FXML
    private TableView<RedCells> redcells_tv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        redcells_tc_id.setCellValueFactory(new PropertyValueFactory<RedCells,Integer>("id"));
        redcells_tc_donationdate.setCellValueFactory(new PropertyValueFactory<RedCells,LocalDate>("donationDate"));
        redcells_tc_donorid.setCellValueFactory(new PropertyValueFactory<RedCells,Integer>("donorId"));
        redcells_tc_bloodtype.setCellValueFactory(new PropertyValueFactory<RedCells,String>("bloodType"));
        redcells_tc_expirationdate.setCellValueFactory(new PropertyValueFactory<RedCells,LocalDate>("expirationDate"));


        ObservableList list ;

        try
        {
            list = FXCollections.observableArrayList(Helper.getHelper().getredcellsBags());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        redcells_tv.setItems(list);

    }



    @FXML
    void onItemClick(MouseEvent event) {

    }

}
