package com.example.bloom.controller;

import com.example.bloom.HelloApplication;
import com.example.bloom.functional.Helper;
import com.example.bloom.functional.MyConnection;
import com.example.bloom.model.Bag;
import com.example.bloom.model.Client;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.ResolverStyle;
import java.util.ResourceBundle;

public class BagsController implements Initializable {


    int index ;


    public static int selectedBagid,selectedBagdonorid ;
    public static LocalDate selectedBagdate;

    private PreparedStatement statement1;

    private ResultSet result1;

    private PreparedStatement statement2;

    private ResultSet result2;


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
        bags_tc_donorid.setCellValueFactory(new PropertyValueFactory<Bag,Integer>("donorId"));
        bags_tc_bloodtype.setCellValueFactory(new PropertyValueFactory<Bag,String>("bloodType"));



            ObservableList list ;

        try
        {
            list = FXCollections.observableArrayList(Helper.getHelper().getTreatedBags());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        bags_tv.setItems(list);

    }
    @FXML
    void addNewBagAction(ActionEvent event) {
        Stage stagesrc = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/add_new_bag.fxml"));


        Scene scene = null;
        Image icon = null;
        try {
            icon = new Image(HelloApplication.class.getResource("image/icon.png").openStream()); // set the header icon to the stage
            scene = new Scene(fxmlLoader.load(), 440, 430);
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

    @FXML
    void onItemClick(MouseEvent mouseEvent) {

        index = bags_tv.getSelectionModel().getSelectedIndex() ;



        if (mouseEvent.getClickCount() == 2 && index != -1){


            selectedBagid = bags_tc_bagid.getCellData(index);
            selectedBagdonorid = bags_tc_donorid.getCellData(index);
            selectedBagdate = bags_tc_donationdate.getCellData(index);


            Stage stagesrc = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
//        index = donors_tv.getSelectionModel().getSelectedIndex();
//
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/edit-bag-info.fxml"));
            Scene scene = null;
            Image icon = null;
            try
            {
                icon = new Image(HelloApplication.class.getResource("image/icon.png").openStream()); // set the header icon to the stage
                scene = new Scene(fxmlLoader.load(),420,420);
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
    void deleteBagAction (ActionEvent event) throws SQLException, ClassNotFoundException {

        Boolean check = true ;




        statement1 = MyConnection.getCon().prepareStatement("select * from bag where donationdate = ? and billid is not null and donorid = ?  ");
        statement1.setString(1,bags_tc_donationdate.getCellData(index).toString());
        statement1.setInt(2,bags_tc_donorid.getCellData(index));
        result1 = statement1.executeQuery();

        while (result1.next()){ check = false ; Helper.getHelper().showAlert("YOU CAN'T DELETE THIS BAG BECAUSE IT IS LINKED TO THE FOLLOWING INVOICE : " + result1.getInt("billid"));}

        if(check){

            statement2 = MyConnection.getCon().prepareStatement("delete from bag where donationdate = ? and donorid = ?");
            statement2.setString(1,bags_tc_donationdate.getCellData(index).toString());
            statement2.setInt(2,bags_tc_donorid.getCellData(index));
            statement2.executeUpdate();
            Helper.getHelper().showSuccess("THE BAG HAS BEEN SUCCESSFULLY DELETED !");

        }


    }


}
