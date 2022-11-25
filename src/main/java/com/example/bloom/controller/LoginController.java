package com.example.bloom.controller;

import com.example.bloom.HelloApplication;
import com.example.bloom.functional.Helper;
import com.example.bloom.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button loginButtonExit;

    @FXML
    private Button loginButtonLogin;

    @FXML
    private TextField loginTextFieldPassword;

    @FXML
    private TextField loginTextFieldUserName;

    @FXML
    void loginAction(ActionEvent event) {
        if(loginTextFieldUserName.getText().equals(""))
        {
            showAlert("you didn't insert the username");
        }else if(loginTextFieldPassword.getText().equals(""))
        {
            showAlert("you didn't insert the password");
        }else
        {
            try
            {
                User user = Helper.getHelper().getAdministrator();

                if(loginTextFieldUserName.getText().equals(user.getPassword())
                        && loginTextFieldPassword.getText().equals(user.getPassword()))
                {
                    openLoginSuccess(event);
                }else
                {
                    showAlert("the username or the password is uncorrecte");
                }
            }catch (SQLException ex)
            {
                ex.printStackTrace();
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
    @FXML
    void exitAction(ActionEvent event) {
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        stage.close();
    }

    // if the username and the password is correct
    public void openLoginSuccess(ActionEvent event)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/main.fxml"));
        Parent root = null;

        try
        {
            root = fxmlLoader.load();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.show();
    }

    // this method invoke the alert dialog
    private void showAlert(String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("warning");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
