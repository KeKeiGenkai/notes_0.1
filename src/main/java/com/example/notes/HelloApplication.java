package com.example.notes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent vroot = FXMLLoader.load(getClass().getResource("HelloApplication.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("HelloApplication.fxml"));
        //HelloController controller = loader.getController();
        //Parent vroot = FXMLLoader.load(getClass().getResource("/HelloApplication.fxml"));
        //primaryStage.setTitle("Главное окно");

        StackPane root = new StackPane();
        Button btn = new Button("Написать о членах");
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Главное окно");

        btn.setOnAction(e -> {
            NewWindow newWindow = new NewWindow();
            newWindow.show();
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}