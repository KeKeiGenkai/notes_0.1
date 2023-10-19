package com.example.notes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;

import java.io.File;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Главное очко");
        File folder = new File("D:\\scheduler_start\\JavaTest\\notes\\Saves");
        File[] files = folder.listFiles();

        Button btn = new Button("Написать о членах");
        btn.setStyle("-fx-background-color: gray; -fx-text-fill: white;"); // Установка стилей для кнопки

        StackPane.setAlignment(btn, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(btn, new Insets(10));

        btn.setOnAction(e -> {
            NewWindow newWindow = new NewWindow();
            newWindow.show();
        });

        root.getChildren().add(btn);

        // Установка цвета фона для корневого контейнера
        root.setStyle("-fx-background-color: darkgray;");

        // Установка цвета фона для сцены
        scene.setFill(Color.DARKGREY);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}