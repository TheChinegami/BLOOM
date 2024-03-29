package com.example.bloom.controller;

import com.example.bloom.HelloApplication;
import com.example.bloom.functional.Helper;
import com.example.bloom.model.Donor;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Function;

public class DonorsController implements Initializable {

    public static int selectedDonor ;

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

    @FXML
    private Pane donors_pane;

    @FXML
    private AnchorPane donors_anchorpane;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        donors_tv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //donors_tv.getSelectionModel().setCellSelectionEnabled(true);

        donors_tc_donorId.setCellValueFactory(new PropertyValueFactory<Donor,Integer>("id"));
        donors_tc_cin.setCellValueFactory(new PropertyValueFactory<Donor,String>("cin"));
        donors_tc_firstname.setCellValueFactory(new PropertyValueFactory<Donor,String>("firstName"));
        donors_tc_lastname.setCellValueFactory(new PropertyValueFactory<Donor,String>("lastName"));
        donors_tc_age.setCellValueFactory(new PropertyValueFactory<Donor,Long>("age"));
        donors_tc_phonenumber.setCellValueFactory(new PropertyValueFactory<Donor,String>("phoneNumber"));
        donors_tc_emergencynumber.setCellValueFactory(new PropertyValueFactory<Donor,String>("emergencyNumber"));
        donors_tc_sickness.setCellValueFactory(new PropertyValueFactory<Donor,String>("sickness"));

        ObservableList list;
        try
        {
             list = FXCollections.observableArrayList(Helper.getHelper().getDonors());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        donors_tv.setItems(list);

      //that line doesnt matter i guess HH
        // donors_tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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
                }else if(donor.getCin().toLowerCase().indexOf(lowerCaseFilter) > -1)
                {
                    return true;
                }else if(Long.toString(donor.getAge()).toLowerCase().indexOf(lowerCaseFilter) > -1)
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
    public void onItemClick(MouseEvent mouseEvent)  {

//        TablePosition tablePosition = donors_tv.getSelectionModel().getSelectedCells().get(0);
//        tablePosition.getColumn();
//        TableColumn tableColumn = tablePosition.getTableColumn();
//        tableColumn.getCellObservableValue(0);

        index = donors_tv.getSelectionModel().getSelectedIndex();

        if (mouseEvent.getClickCount() == 2 && index != -1){


            selectedDonor=donors_tc_donorId.getCellData(index) ;


        Stage stagesrc = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
//        index = donors_tv.getSelectionModel().getSelectedIndex();
//
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/edit-donor-info.fxml"));
        Scene scene = null;
        Image icon = null;
        try
        {
            icon = new Image(HelloApplication.class.getResource("image/icon.png").openStream()); // set the header icon to the stage
            scene = new Scene(fxmlLoader.load(),400,700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stagesrc);
        stage.show();
        }




//        Donor donor = new Donor(
//                Integer.parseInt(donors_tc_donorId.getCellData(index).toString()),
//                donors_tc_cin.getCellData(index).toString(),
//                donors_tc_firstname.getCellData(index).toString(),
//                donors_tc_lastname.getCellData(index).toString(),
//                donors_tc_phonenumber.getCellData(index).toString(),
//                Integer.parseInt(donors_tc_age.getCellData(index).toString()),
//                donors_tc_emergencynumber.getCellData(index).toString(),
//                donors_tc_sickness.getCellData(index).toString()
//        );
//
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("I WON!");
//        alert.setContentText(donor.toString());
//        alert.showAndWait();
    }


    @FXML
    void addNewDonorAction(ActionEvent event) throws IOException {
        Stage stagesrc = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/add-new-donor.fxml"));


        Scene scene = null;
        Image icon = null;
        try {
            icon = new Image(HelloApplication.class.getResource("image/icon.png").openStream()); // set the header icon to the stage
            scene = new Scene(fxmlLoader.load(), 400, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try{
        Stage stage = new Stage();
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(stagesrc);
        stage.show();

        }
        catch (Exception ie) {System.out.println(ie);}
    }



    }

