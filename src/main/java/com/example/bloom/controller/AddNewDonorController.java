package com.example.bloom.controller;

import com.example.bloom.functional.Helper;
import com.example.bloom.functional.MyConnection;
import com.example.bloom.model.Donor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AddNewDonorController
{

    private PreparedStatement statement;
    @FXML
    private Button adddonor_btn_add;

    @FXML
    private Button adddonor_btn_cancel;

    @FXML
    private DatePicker adddonor_dp_birthdate;

    @FXML
    private TextField adddonor_tf_cin;

    @FXML
    private TextField adddonor_tf_emergencynumber;

    @FXML
    private TextField adddonor_tf_firstname;

    @FXML
    private TextField adddonor_tf_lastname;

    @FXML
    private TextField adddonor_tf_phonenumber;


    private class date extends DateTimeParseException {

        public date(String message, CharSequence parsedData, int errorIndex) {
            super(message, parsedData, errorIndex);

        }
    }
    @FXML
    void KeytypeDatepicker(){

        try {}catch (java.time.format.DateTimeParseException datee){
            System.out.println("HELPPPPPPP !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            Helper.getHelper().showAlert("TEST");

        }

        adddonor_dp_birthdate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if(observable.getClass().getSimpleName()=="String"){
                    System.out.println(observable.getClass());
                    System.out.println("Please use the picker");
                    Helper.getHelper().showAlert("PLEASE USE THE PICKER ON THE RIGHT");}

            }
        });

    }


    @FXML
    void addAction(ActionEvent event) throws SQLException, ClassNotFoundException {
    try {

        String dfname = adddonor_tf_firstname.getText();
        String dlname = adddonor_tf_lastname.getText();
        LocalDate dbdate = adddonor_dp_birthdate.getValue();
        String dcin = adddonor_tf_cin.getText();
        String dpnumber = adddonor_tf_phonenumber.getText();
        String denumber = adddonor_tf_emergencynumber.getText();


        // condition checkers
        Boolean phonecheck = false,emegencycheck = false,emptycheck = false,datecheck = false;









        ArrayList<Donor> newadded = new ArrayList<>();

        if (dfname.isEmpty() || dlname.isEmpty() || dbdate==null || dcin.isEmpty() || dpnumber.isEmpty()) {
            Helper.getHelper().showAlert("PLEASE FILL ALL THE STAR SIGNED FIELDS !");
        }
        else {
            emptycheck=true;


            Pattern pdate = Pattern.compile("\\d{4}-[0-1][0-9]-[0-3][0-9]");
            Matcher mdate= pdate.matcher(dbdate.toString());
            Pattern pphone = Pattern.compile("(06|07|05)?-\\d{2}-\\d{2}-\\d{2}-\\d{2}");
            Matcher mphone = pphone.matcher(dpnumber);
            Matcher mphonee ;


            if ( mdate.find() && mdate.group().equals(dbdate.toString())) {datecheck = true;}
            else {Helper.getHelper().showAlert("PLEASE ENTER A VALID DATE FORMAT");}

            if ( mphone.find() && mphone.group().equals(dpnumber)) {phonecheck = true;}
            else {Helper.getHelper().showAlert("PLEASE ENTER A VALID PHONE NUMBER FORMAT");}

        if (denumber.isEmpty()){emegencycheck=true;}
        else{ mphonee = pphone.matcher(denumber);
            if(mphonee.find() && mphonee.group().equals(denumber)){emegencycheck= true;}
            else {Helper.getHelper().showAlert("PLEASE ENTER A VALID EMERGENCY NUMBER FORMAT"); emegencycheck=false;}}
    }



        if(phonecheck && emptycheck && emegencycheck && datecheck) {
            statement = MyConnection.getCon().prepareStatement("insert into donor (first_name,last_name,Birth_date,CIN,phone_number,Emergency_number) values (?,?,?,?,?,?)");
            statement.setString(1, dfname);
            statement.setString(2, dlname);
            statement.setString(3, dbdate.toString());
            statement.setString(4, dcin);
            statement.setString(5, dpnumber);
            statement.setString(6, denumber);
            statement.execute();
            Helper.getHelper().showSuccess("THE DONOR HAS BEEN SUCCESSFULLY ADDED !");

        };





        ObservableList CheckList = FXCollections.observableArrayList(Helper.getHelper().getDonors());


    }catch (SQLException sqle){
        if (sqle.toString().contains("donor.phone_number_UNIQUE") ){
            Helper.getHelper().showAlert("THIS PHONE NUMBER IS ALREADY IN USE ! PLEASE CHECK .");
        }
        else if (sqle.toString().contains("donor.CIN_UNIQUE")){
            Helper.getHelper().showAlert("THIS CIN CODE IS ALREADY IN USE ! PLEASE CHECK .");
        }
    }


    }

    @FXML
    void cancelAction(ActionEvent event) {

        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        stage.close();
    }
    public void clearfields(){
        adddonor_tf_firstname.clear();
        adddonor_tf_lastname.clear();
        adddonor_dp_birthdate.setValue(null);
        adddonor_tf_cin.clear();
        adddonor_tf_phonenumber.clear();
        adddonor_tf_emergencynumber.clear();

    }
}
