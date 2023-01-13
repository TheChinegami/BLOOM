package com.example.bloom.controller;

import com.example.bloom.functional.Helper;
import com.example.bloom.functional.MyConnection;
import com.example.bloom.model.Client;
import com.example.bloom.model.Donor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddNewBagController implements Initializable {



    private PreparedStatement statement;


    @FXML
    private Button addnewbag_btn_add;

    @FXML
    private Button addnewbag_btn_cancel;

    @FXML
    private ComboBox<Integer> addnewbag_cb_donorid;

    @FXML
    private ComboBox<String> addnewbag_cb_bloodtype;


    @FXML
    private DatePicker addnewbag_dp_donationdate;


    @FXML
    private RadioButton treated_rb_true;


    @FXML
    private RadioButton treated_rb_false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ArrayList<Donor> donorslist = new ArrayList();
        try
        {
            donorslist = Helper.getHelper().getHealthyDonors();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        ObservableList subList = FXCollections.observableArrayList();
        for(Donor donor:donorslist)
        {
            subList.add(donor.getId());
        }
        addnewbag_cb_donorid.setItems(subList);
        addnewbag_cb_donorid.setValue(donorslist.get(0).getId());

        ObservableList subList2 = FXCollections.observableArrayList();
        subList2.addAll(Helper.getHelper().getbloodtypes());

        addnewbag_cb_bloodtype.setItems(subList2);

    }
    @FXML
    void addNewBagAction(ActionEvent event) throws SQLException, ClassNotFoundException {


    int cbdonorid = addnewbag_cb_donorid.getValue() ;
    String cbbloodtype = addnewbag_cb_bloodtype.getValue() ;
    int bloodtpid = 0 ;
    LocalDate dpdonationdate = addnewbag_dp_donationdate.getValue() ;

    int bagtypeid = 0 ;
    if (treated_rb_false.isSelected()){bagtypeid=4;}

        switch(addnewbag_cb_bloodtype.getValue()){
            case "A+":
                bloodtpid = 1;
                break;

            case "A-":
                bloodtpid = 2;
                break;

            case "B+":
                bloodtpid = 3;
                break;

            case "B-":
                bloodtpid = 4;
                break;

            case "AB+":
                bloodtpid = 5;
                break;

            case "AB-":
                bloodtpid = 6;
                break;

            case "O+":
                bloodtpid = 7;
                break;

            case "O-":
                bloodtpid = 8;
                break;
        }

        if (bagtypeid==4) {
            statement = MyConnection.getCon().prepareStatement("insert into bag (donorid,bloodtypeid,donationdate,bagtypeid) values (?,?,?,?)");
            statement.setInt(1, cbdonorid);
            statement.setInt(2, bloodtpid);
            statement.setString(3, dpdonationdate.toString());
            statement.setInt(4,bagtypeid);
            statement.execute();
            Helper.getHelper().showSuccess("THE BAG HAS BEEN SUCCESSFULLY ADDED !");
        }

        else {
            for(bagtypeid=0;bagtypeid<4;bagtypeid++){

                LocalDate temp = dpdonationdate;

                switch (bagtypeid){

                    case 1 :
                        temp= dpdonationdate.plusDays(42);
                        break;
                    case 2 :
                        temp= dpdonationdate.plusYears(1);
                        break;
                    case 3 :
                        temp = dpdonationdate.plusDays(3);
                        break;
                }


                statement = MyConnection.getCon().prepareStatement("insert into bag (donorid,bloodtypeid,donationdate,bagtypeid,expirationdate) values (?,?,?,?,?)");
                statement.setInt(1, cbdonorid);
                statement.setInt(2, bloodtpid);
                statement.setString(3, dpdonationdate.toString());
                statement.setInt(4,bagtypeid);
                statement.setString(5,temp.toString());
                statement.execute();



            }

            Helper.getHelper().showSuccess("THE BAG HAS BEEN SUCCESSFULLY ADDED !");
        }




    }

    @FXML
    void cancelAction(ActionEvent event)
    {
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        stage.close();
    }

}