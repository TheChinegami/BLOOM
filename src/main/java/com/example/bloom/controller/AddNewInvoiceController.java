package com.example.bloom.controller;

import com.example.bloom.functional.Helper;
import com.example.bloom.model.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddNewInvoiceController implements Initializable
{

    @FXML
    private Button addnewinvoice_btn_add;

    @FXML
    private Button addnewinvoice_btn_cancel;

    @FXML
    private ComboBox<String> addnewinvoice_cb_clientname;

    @FXML
    private DatePicker addnewinvoice_tf_invoicedate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ArrayList<Client> clientList = new ArrayList();
        try
        {
            clientList = Helper.getHelper().getAllClients();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        ObservableList subList = FXCollections.observableArrayList();
        for(Client client:clientList)
        {
            subList.add(client.getName());
        }
        addnewinvoice_cb_clientname.setItems(subList);
        addnewinvoice_cb_clientname.setValue(clientList.get(0).getName());
    }

    @FXML
    void addNewInvoiceAction(ActionEvent event)
    {
        if(addnewinvoice_tf_invoicedate.getValue() == null)
        {
            Helper.getHelper().showAlert("you should insert the date first");
        }else
        {
            LocalDate date = LocalDate.parse(addnewinvoice_tf_invoicedate.getValue().toString());
            try
            {
                Helper.getHelper().addNewInvoice(
                        addnewinvoice_cb_clientname.getValue(),
                        date
                        );
            } catch (SQLException e)
            {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e)
            {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
            stage.close();
            Helper.getHelper().showSuccess("you added a new invoice successfully");
        }
    }

    @FXML
    void cancelAction(ActionEvent event)
    {
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        stage.close();
    }

}
