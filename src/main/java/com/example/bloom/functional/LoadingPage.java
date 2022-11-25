package com.example.bloom.functional;

import com.example.bloom.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadingPage implements Runnable
{
    private static Stage stage;
    public static void show()
    {
        new Thread(new LoadingPage()).start();
    }
    public static void close()
    {
        if(stage != null)
            stage.close();
    }

    @Override
    public void run() {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 320);

            stage = new Stage();
            stage.setTitle("bloom");

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
