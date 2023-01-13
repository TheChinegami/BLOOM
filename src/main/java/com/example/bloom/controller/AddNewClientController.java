package com.example.bloom.controller;

import com.example.bloom.functional.Helper;
import com.example.bloom.functional.MyConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewClientController {

    private PreparedStatement statement;

        @FXML
        private TextField addnewclient_tf_clientname;

        @FXML
        private Button clients_btn_addnewbag;

        @FXML
        private Button clients_btn_addnewbagcancel;

        @FXML
        private TextField addnewclient_tf_clientphone;



        @FXML
        void addNewClientAction(ActionEvent event) throws SQLException, ClassNotFoundException {

            String cname = addnewclient_tf_clientname.getText() ;
            String cphone = addnewclient_tf_clientphone.getText() ;

            Pattern pphone = Pattern.compile("(06|07|05)?-\\d{2}-\\d{2}-\\d{2}-\\d{2}");
            Matcher mphone = pphone.matcher(cphone);
            Matcher mphonee ;
            Boolean phonecheck = false ;




            if ( mphone.find() && mphone.group().equals(cphone)) {phonecheck = true;}
            else {Helper.getHelper().showAlert("PLEASE ENTER A VALID PHONE NUMBER FORMAT");}

            if(cname != null && phonecheck) {
                statement = MyConnection.getCon().prepareStatement("insert into client (name,phone_number) values (?,?)");
                statement.setString(1, cname);
                statement.setString(2, cphone);

                statement.execute();
                Helper.getHelper().showSuccess("THE CLIENT HAS BEEN SUCCESSFULLY ADDED !");

            };



        }

        @FXML
        void cancelAction(ActionEvent event) {

        }

    }











