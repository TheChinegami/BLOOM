package com.example.bloom.controller;

import com.example.bloom.HelloApplication;
import com.example.bloom.functional.Helper;
import com.example.bloom.model.Bill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class InvoicesController implements Initializable {

    private Integer index;
    @FXML
    private Button invoices_btn_addnewclient;

    @FXML
    private Button invoices_btn_seealltheinvoices;

    @FXML
    private Button invoices_btn_seethemonthinvoices;

    @FXML
    private TableColumn<Bill, Double> invoices_tc_amount;

    @FXML
    private TableColumn<Bill, Integer> invoices_tc_clientid;

    @FXML
    private TableColumn<Bill, LocalDate> invoices_tc_date;

    @FXML
    private TableColumn<Bill, Integer> invoices_tc_invoiceid;

    @FXML
    private TextField invoices_tf_search;

    @FXML
    private TableView<Bill> invoices_tv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        invoices_tc_invoiceid.setCellValueFactory(new PropertyValueFactory<Bill,Integer>("id"));
        invoices_tc_date.setCellValueFactory(new PropertyValueFactory<Bill,LocalDate>("billDate"));
        invoices_tc_amount.setCellValueFactory(new PropertyValueFactory<Bill,Double>("amount"));
        invoices_tc_clientid.setCellValueFactory(new PropertyValueFactory<Bill,Integer>("idClient"));

        ObservableList list;
        try
        {
            list = FXCollections.observableArrayList(Helper.getHelper().getInvoicesOfThisMonth());
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        invoices_tv.setItems(list);

        FilteredList<Bill> filteredList = new FilteredList<>(list, b -> true);
        invoices_tf_search.textProperty().addListener((observable,oldValue,newValue)->{
            filteredList.setPredicate(bill ->{
                if(newValue == null || newValue.isEmpty() || newValue.isBlank())
                {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(Integer.toString(bill.getId()).toLowerCase().indexOf(lowerCaseFilter) > -1)
                {
                    return true;
                }
                return false;
            });
        });

        SortedList<Bill> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(invoices_tv.comparatorProperty());
        invoices_tv.setItems(sortedData);
    }

    @FXML
    void addNewInvoiceAction(ActionEvent event)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/add_new_invoice.fxml"));
        Scene scene = null;
        Image icon = null;
        try
        {
            icon = new Image(HelloApplication.class.getResource("image/icon.png").openStream()); // set the header icon to the stage
            scene = new Scene(fxmlLoader.load(),477,416);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onItemClick(MouseEvent event)
    {
        index = invoices_tv.getSelectionModel().getSelectedIndex();
        System.out.println(index);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/invoice_details.fxml"));
        Scene scene = null;
        Image icon = null;
        try
        {
            icon = new Image(HelloApplication.class.getResource("image/icon.png").openStream()); // set the header icon to the stage
            scene = new Scene(fxmlLoader.load(),1126,690);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        InvoiceDetailsController controller = fxmlLoader.getController();
        controller.getInvoicedetails_t_invoiceid().setText(invoices_tc_invoiceid.getCellData(index).toString());
        Stage stage = new Stage();
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void seeAllInvoicesAction(ActionEvent event) {
        ObservableList list;
        try
        {
            list = FXCollections.observableArrayList(Helper.getHelper().getAllInvoices());
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        invoices_tv.setItems(list);
    }

    @FXML
    void seeTheMonthInvoicesAction(ActionEvent event)
    {
        ObservableList list;
        try
        {
            list = FXCollections.observableArrayList(Helper.getHelper().getInvoicesOfThisMonth());
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        invoices_tv.setItems(list);
    }

}
