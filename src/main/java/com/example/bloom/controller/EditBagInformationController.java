package com.example.bloom.controller;
import com.example.bloom.functional.Helper;
import com.example.bloom.functional.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditBagInformationController implements Initializable {

    private PreparedStatement statement , statement2 , statement3 ;

    private ResultSet result , result2 , result3;

    @FXML
    private ComboBox<String> editbag_cb_bloodtype;

    @FXML
    private Button bags_btn_editbagedit;

    @FXML
    private Button bags_btn_editbagcancel;

    @FXML
    private ToggleGroup Treated;

    @FXML
    private RadioButton treated_rb_false;

    @FXML
    private DatePicker editbag_dp_donationdate;

    @FXML
    private RadioButton treated_rb_true;


    @Override
    public void initialize(URL location, ResourceBundle resources)  {


        try {

            ObservableList subList2 = FXCollections.observableArrayList();
            subList2.addAll(Helper.getHelper().getbloodtypes());

            editbag_cb_bloodtype.setItems(subList2);


        statement= MyConnection.getCon().prepareStatement("select * from bag where id =? and donationdate = ? and donorid = ?" );
        statement.setInt(1,BagsController.selectedBagid);
        statement.setString(2,BagsController.selectedBagdate.toString());
        statement.setInt(3,BagsController.selectedBagdonorid);
        result = statement.executeQuery();
        result.next();
        editbag_dp_donationdate.setValue(result.getDate("donationdate").toLocalDate());
        editbag_cb_bloodtype.setValue(Helper.getHelper().getbloodtypebyid(result.getInt("bloodtypeid")));
        if (result.getInt("bagtypeid") == 0){treated_rb_true.setSelected(true); treated_rb_false.setDisable(true);}
        else {treated_rb_false.setSelected(true);}


        }catch (SQLException e){}
        catch (ClassNotFoundException ex){}



    }
@FXML
    void editBagAction (ActionEvent event) throws SQLException , ClassNotFoundException{

        LocalDate bagdonationdate = editbag_dp_donationdate.getValue();
        String bagbloodtype = editbag_cb_bloodtype.getValue();
        Boolean treated = false ;

        if (treated_rb_true.isSelected()){treated = true ;}

        if (bagdonationdate == null || bagbloodtype.isEmpty() || bagbloodtype == " " || treated == false) {

            Helper.getHelper().showAlert("PLEASE FILL ALL THE FIELDS");
        }
        else {

            statement2=MyConnection.getCon().prepareStatement("Update bag set bagtypeid = ? where id = ?");
            statement2.setInt(1,0);
            statement2.setInt(2,BagsController.selectedBagid);
            statement2.executeUpdate();


            LocalDate donationdate = result.getDate("donationdate").toLocalDate();
            LocalDate temp = null;



            for (int i = 1 ; i < 4 ; i++) {
                switch (i){

                    case 1 :
                        temp= donationdate.plusDays(42);
                        break;
                    case 2 :
                        temp= donationdate.plusYears(1);
                        break;
                    case 3 :
                        temp = donationdate.plusDays(3);
                        break;
                }

                statement3 = MyConnection.getCon().prepareStatement("insert into bag (donorid , donationdate, bloodtypeid , billid , bagtypeid , expirationdate)\n" +
                        "   values (?, ? , ? , null , ? , ?)");

                statement3.setInt(1,result.getInt("donorid"));
                statement3.setString(2,editbag_dp_donationdate.getValue().toString());
                statement3.setInt(3,result.getInt("bloodtypeid"));
                statement3.setInt(4 , i);
                statement3.setString(5,temp.toString());
                statement3.executeUpdate();

            }
            Helper.getHelper().showSuccess("Success !");
        }










}
    @FXML
    void cancelAction (ActionEvent event){
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        stage.close();
    }
}
