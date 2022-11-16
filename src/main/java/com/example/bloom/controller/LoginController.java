package com.example.bloom.controller;

import com.example.bloom.HelloApplication;
import com.example.bloom.MyConnection;
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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            try {
                PreparedStatement st = MyConnection.getCon().prepareStatement("select username,password from user");
                ResultSet rs = st.executeQuery();
                String username = "",password = "";
                rs.next();
                username = rs.getString(1);
                password = rs.getString(2);

                if(loginTextFieldUserName.getText().equals(username) && loginTextFieldPassword.getText().equals(password))
                {
                    openLoginSuccess(event);
                }else
                {
                    showAlert("the username or the password is uncorrecte");
                }
            }catch (SQLException ex)
            {
                System.out.println("a problem in the login process");
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("a problem in the class of the connection");
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/main.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        } catch (Exception e) {
            System.out.println("a problem while opening the main stage");
            e.printStackTrace();
        }
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
