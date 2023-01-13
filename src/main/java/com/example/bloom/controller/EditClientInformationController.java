package com.example.bloom.controller;

import com.example.bloom.functional.Helper;
import com.example.bloom.functional.MyConnection;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditClientInformationController implements Initializable {

    private PreparedStatement statement ;

    private ResultSet result;


    @FXML
    private Button clients_btn_editclientedit;

    @FXML
    private TextField editclientinfo_tf_clientphone;

    @FXML
    private Button clients_btn_editclientcancel;

    @FXML
    private TextField editclientinfo_tf_clientname;



    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            statement = MyConnection.getCon().prepareStatement("select * from client where id ="+(ClientsController.selectedClient));
            result= statement.executeQuery();
            result.next();
            editclientinfo_tf_clientname.setText(result.getString("name"));
            editclientinfo_tf_clientphone.appendText(result.getString("phone_number"));


        }catch (SQLException e){}
        catch (ClassNotFoundException ex){}

    }
    @FXML
    void editClientAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {


            String cname = editclientinfo_tf_clientname.getText();
            String cnumber = editclientinfo_tf_clientphone.getText();



            // condition checkers
            Boolean phonecheck = false,emegencycheck = false,emptycheck = false,datecheck = false;


            if (cname.isEmpty()  || cnumber.isEmpty()) {
                Helper.getHelper().showAlert("PLEASE FILL ALL FIELDS !");
            }
            else {
                emptycheck=true;



                Pattern pphone = Pattern.compile("(06|07|05)?-\\d{2}-\\d{2}-\\d{2}-\\d{2}");
                Matcher mphone = pphone.matcher(cnumber);
                Matcher mphonee ;



                if ( mphone.find() && mphone.group().equals(cnumber)) {phonecheck = true;}
                else {Helper.getHelper().showAlert("PLEASE ENTER A VALID PHONE NUMBER FORMAT");}

            }



            if(phonecheck && emptycheck) {
                statement = MyConnection.getCon().prepareStatement("update client set name = ? ,phone_number = ? where id = ?");
                statement.setString(1, cname);
                statement.setString(2, cnumber);
                statement.setInt(3,ClientsController.selectedClient);
                statement.execute();
                Helper.getHelper().showSuccess("THE CLIENT'S INFORMATIONS HAS BEEN SUCCESSFULLY EDITED !");

            };

        }catch (SQLException sqle){
            System.out.println(sqle);
        }


    }

    @FXML
    void cancelAction(ActionEvent event) {

        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        stage.close();
    }
}
