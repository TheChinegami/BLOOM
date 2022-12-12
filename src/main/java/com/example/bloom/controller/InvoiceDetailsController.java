package com.example.bloom.controller;

import com.example.bloom.HelloApplication;
import com.example.bloom.functional.Helper;
import com.example.bloom.model.Bill;
import com.example.bloom.model.Client;
import com.example.bloom.model.Sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InvoiceDetailsController implements Initializable
{
    public String temp;

    @FXML
    private Button invoicedetails_btn_apply;

    @FXML
    private Button invoicedetails_btn_refresh;


    @FXML
    private ComboBox<?> invoicedetails_cb_bagtype;

    @FXML
    private ComboBox<?> invoicedetails_cb_bloodtype;

    @FXML
    private ComboBox<?> invoicedetails_cb_operation;

    @FXML
    private Text invoicedetails_t_invoiceid;

    @FXML
    private TableColumn<Sample, Double> invoicedetails_tc_priceperrow;

    @FXML
    private TableColumn<Sample, Double> invoicedetails_tc_priceperunity;

    @FXML
    private TableColumn<Sample, Integer> invoicedetails_tc_quantity;

    @FXML
    private TableColumn<Sample, String> invoicedetails_tc_sample;

    @FXML
    private TextField invoicedetails_tf_quantity;

    @FXML
    private TableView<?> invoicedetails_tv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> bagList = new ArrayList();
        ArrayList<String> bloodList = new ArrayList();
        try
        {
            bagList = Helper.getHelper().getBagTypes();
            bloodList = Helper.getHelper().getBloodTypes();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        ObservableList subBagList = FXCollections.observableArrayList();
        for(String s:bagList)
        {
            subBagList.add(s);
        }
        invoicedetails_cb_bagtype.setItems(subBagList);

        ObservableList subBloodList = FXCollections.observableArrayList();
        for(String s:bloodList)
        {
            subBloodList.add(s);
        }
        invoicedetails_cb_bloodtype.setItems(subBloodList);

        ObservableList operations = FXCollections.observableArrayList("add","delete");
        invoicedetails_cb_operation.setItems(operations);

        // the table view
        invoicedetails_tc_sample.setCellValueFactory(new PropertyValueFactory<Sample,String>("sample"));
        invoicedetails_tc_priceperunity.setCellValueFactory(new PropertyValueFactory<Sample,Double>("pricePerUnity"));
        invoicedetails_tc_quantity.setCellValueFactory(new PropertyValueFactory<Sample,Integer>("quantity"));
        invoicedetails_tc_priceperrow.setCellValueFactory(new PropertyValueFactory<Sample,Double>("pricePerRow"));

    }

    @FXML
    void applyAction(ActionEvent event) {
        if(invoicedetails_cb_bloodtype.getValue() == null)
        {
            Helper.getHelper().showAlert("choose the blood type before applying the operation");
        }else if(invoicedetails_cb_bagtype.getValue() == null)
        {
            Helper.getHelper().showAlert("choose the bag type before applying the operation");
        }else if(invoicedetails_cb_operation.getValue() == null)
        {
            Helper.getHelper().showAlert("choose the operation type before applying the operation");
        }else if(invoicedetails_tf_quantity.getText().equals(""))
        {
            Helper.getHelper().showAlert("add the quantity before applying the operation");
        }else
        {
            if(invoicedetails_cb_operation.getValue().toString().equals("add"))
            {
                addAction();
            }else
            {
                deleteAction();
            }
        }
    }

    @FXML
    void refreshAction(ActionEvent event) {
        int billId = Integer.parseInt(invoicedetails_t_invoiceid.getText());
        ObservableList sampleList = null;
        try {
            sampleList = FXCollections.observableArrayList(Helper.getHelper().getBagsInBill(billId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        invoicedetails_tv.setItems(sampleList);
    }

    @FXML
    void onItemClick(MouseEvent event) {

    }

    @FXML
    void showBagsAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/invoice_bags.fxml"));
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
        InvoiceBagsController controller = fxmlLoader.getController();
        controller.billId = Integer.parseInt(getInvoicedetails_t_invoiceid().getText());
        Stage stage = new Stage();
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public Text getInvoicedetails_t_invoiceid() {
        return invoicedetails_t_invoiceid;
    }

    public void addAction()
    {
        try
        {
            int quantity = Integer.parseInt(invoicedetails_tf_quantity.getText());
            int count = Helper.getHelper().getNumberOfSample(invoicedetails_cb_bagtype.getValue().toString(),invoicedetails_cb_bloodtype.getValue().toString());
            if(count < quantity)
            {
                Helper.getHelper().showAlert("you do not have this much quantity of the demanding sample");
            }else
            {
                int billId = Integer.parseInt(invoicedetails_t_invoiceid.getText());
                ArrayList<Integer> list = Helper.getHelper().getIdsOfSample(
                        invoicedetails_cb_bagtype.getValue().toString(),
                        invoicedetails_cb_bloodtype.getValue().toString(),
                        quantity
                );
                for(Integer i:list)
                {
                    Helper.getHelper().addTheBagsToAnInvoice(billId,i);
                }
                Helper.getHelper().showSuccess("I WON !");
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void deleteAction()
    {
        try
        {
            int quantity = Integer.parseInt(invoicedetails_tf_quantity.getText());
            int billId = Integer.parseInt(invoicedetails_t_invoiceid.getText());
            int count = Helper.getHelper().getNumberOfSampleInBill(invoicedetails_cb_bagtype.getValue().toString(),invoicedetails_cb_bloodtype.getValue().toString(),billId);
            if(count < quantity)
            {
                Helper.getHelper().showAlert("you do not have this much quantity of the demanding sample in this bill");
            }else
            {
                ArrayList<Integer> list = Helper.getHelper().getIdsOfSampleInBill(
                        invoicedetails_cb_bagtype.getValue().toString(),
                        invoicedetails_cb_bloodtype.getValue().toString(),
                        billId,
                        quantity
                );
                for(Integer i:list)
                {
                    Helper.getHelper().deleteBagFromAnInvoice(i);
                }
                Helper.getHelper().showSuccess("I WON !");
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

}
