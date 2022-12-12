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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class EditDonorInformationController implements Initializable {

        private Integer id ;
        private PreparedStatement statement;


        @FXML
        private ToggleGroup Sickness;

    @FXML
    private Button edirdonor_btn_cancel;

    @FXML
    private Button editdonor_btn_edit;

    @FXML
    private DatePicker editdonor_dp_birthdate;

    @FXML
    private RadioButton editdonor_rb_false;

    @FXML
    private RadioButton editdonor_rb_true;

    @FXML
    private TextField editdonor_tf_cin;

    @FXML
    private TextField editdonor_tf_emergencynumber;

    @FXML
    private TextField editdonor_tf_firstname;

    @FXML
    private TextField editdonor_tf_lastname;

    @FXML
    private TextField editdonor_tf_phonenumber;
    private ResultSet result;





        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            try {

            statement = MyConnection.getCon().prepareStatement("select * from donor where id ="+(DonorsController.rowselected+1));
            result= statement.executeQuery();
            result.next();
            editdonor_tf_firstname.setText(result.getString("first_name"));
            editdonor_tf_lastname.appendText(result.getString("last_name"));
            editdonor_tf_cin.appendText(result.getString("CIN"));
            editdonor_tf_phonenumber.appendText(result.getString("phone_number"));
            editdonor_dp_birthdate.setValue(result.getDate("Birth_date").toLocalDate());
            editdonor_tf_emergencynumber.appendText(result.getString("emergency_number"));
            editdonor_rb_false.setSelected(Boolean.parseBoolean(result.getString("Sickness")));


             }catch (SQLException e){}
            catch (ClassNotFoundException ex){}

        }

//        void Load() throws SQLException, ClassNotFoundException {
//
//            ArrayList<Donor> list = new ArrayList<>();
//            statement = MyConnection.getCon().prepareStatement("select * from donor where id ="+DonorsController.rowselected+1);
//            result = statement.executeQuery();
//            LocalDate d1;
//            LocalDate d2;
//            long age;
//            Boolean sickcheck;
//            String Sick= "true" ;
//
//            sickcheck = result.getBoolean("Sickness");
//            // if (sickcheck.toString() == " "){Sick =" Unknown"; System.out.println("this condition is working "+sickcheck+" "+Sick);}else {Sick=sickcheck.toString();System.out.println("this condition is FUCKED "+sickcheck+" "+Sick);};
//
//            editdonor_tf_cin.setText(result.getString("CIN"));
//            editdonor_tf_firstname.setText(result.getString("first_name"));
//            editdonor_tf_lastname.setText(result.getString("last_name"));
//            editdonor_tf_phonenumber.setText(result.getString("phone_number"));
//            editdonor_dp_birthdate.setValue(result.getDate("Birth_date").toLocalDate());
//            editdonor_tf_emergencynumber.setText(result.getString("emergency_number"));
//            editdonor_rb_false.setSelected(Boolean.parseBoolean(result.getString("Sickness")));
//
//
//
//
//        }

        @FXML
        void editAction(ActionEvent event) throws SQLException, ClassNotFoundException {
            try {


                String dfname = editdonor_tf_firstname.getText();
                String dlname = editdonor_tf_lastname.getText();
                LocalDate dbdate = editdonor_dp_birthdate.getValue();
                String dcin = editdonor_tf_cin.getText();
                String dpnumber = editdonor_tf_phonenumber.getText();
                String denumber = editdonor_tf_emergencynumber.getText();



                // condition checkers
                Boolean phonecheck = false,emegencycheck = false,emptycheck = false,datecheck = false;


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
                    statement = MyConnection.getCon().prepareStatement("update donor set (first_name,last_name,Birth_date,CIN,phone_number,Emergency_number) values (?,?,?,?,?,?)");
                    statement.setString(1, dfname);
                    statement.setString(2, dlname);
                    statement.setString(3, dbdate.toString());
                    statement.setString(4, dcin);
                    statement.setString(5, dpnumber);
                    statement.setString(6, denumber);
                    statement.execute();
                    Helper.getHelper().showSuccess("THE DONOR'S INFORMATIONS HAS BEEN SUCCESSFULLY EDITED !");

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
