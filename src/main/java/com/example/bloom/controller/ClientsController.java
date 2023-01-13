package com.example.bloom.controller;

import com.example.bloom.HelloApplication;
import com.example.bloom.functional.Helper;
import com.example.bloom.model.Client;
import com.example.bloom.model.Donor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientsController implements Initializable
{

    private int index ;

    public static int selectedClient ;
    @FXML
    private Button clients_btn_addnewclient;

    @FXML
    private TableColumn<Client, Integer> clients_tc_id;

    @FXML
    private TableColumn<Client, String> clients_tc_name;

    @FXML
    private TableColumn<Client, String> clients_tc_phonenumber;

    @FXML
    private TableView<Client> clients_tv;

    @FXML
    private TextField clients_tf_search;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clients_tc_id.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id"));
        clients_tc_name.setCellValueFactory(new PropertyValueFactory<Client,String>("name"));
        clients_tc_phonenumber.setCellValueFactory(new PropertyValueFactory<Client,String>("phoneNumber"));

        ObservableList list ;

        try
        {
            list = FXCollections.observableArrayList(Helper.getHelper().getClients());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        clients_tv.setItems(list);


//        FilteredList<Client> filteredList = new FilteredList<>(list, b -> true);
//        clients_tf_search.textProperty().addListener((observable,oldValue,newValue)->{
//            filteredList.setPredicate(client ->{
//                if(newValue == null || newValue.isEmpty() || newValue.isBlank())
//                {
//                    return true;
//                }
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if(Integer.toString(client.getId()).toLowerCase().indexOf(lowerCaseFilter) > -1)
//                {
//                    return true;
//                }else if(client.getName().toLowerCase().indexOf(lowerCaseFilter) > -1)
//                {
//                    return true;
//                }
//
//                return false;
//            });
//        });

//        SortedList<Client> sortedData = new SortedList<>(filteredList);
//        sortedData.comparatorProperty().bind(clients_tv.comparatorProperty());
//        clients_tv.setItems(sortedData);


    }

    @FXML
    void onItemClick(MouseEvent mouseEvent) {

        index = clients_tv.getSelectionModel().getSelectedIndex();
        System.out.println(index);
        if (mouseEvent.getClickCount() == 2 && index != -1){


            selectedClient=clients_tc_id.getCellData(index) ;


            Stage stagesrc = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
//        index = donors_tv.getSelectionModel().getSelectedIndex();
//
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/edit-client-info.fxml"));
            Scene scene = null;
            Image icon = null;
            try
            {
                icon = new Image(HelloApplication.class.getResource("image/icon.png").openStream()); // set the header icon to the stage
                scene = new Scene(fxmlLoader.load(),420,400);
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







    }

    @FXML
    void addnewclientAction(ActionEvent event) {

        Stage stagesrc = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/add-new-client.fxml"));


        Scene scene = null;
        Image icon = null;
        try {
            icon = new Image(HelloApplication.class.getResource("image/icon.png").openStream()); // set the header icon to the stage
            scene = new Scene(fxmlLoader.load(), 420, 400);
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
