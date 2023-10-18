package com.example.notes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Главное очко");
        File folder = new File("D:\\scheduler_start\\JavaTest\\notes\\Saves");
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                String iconPath = "D:\\scheduler_start\\JavaTest\\notes\\src\\main\\icons\\txtFileIcon.png";
                ImageView icon = new ImageView(new Image(iconPath));
                Hyperlink link = new Hyperlink(file.getName());

                // Добавьте обработчик событий для ссылки на файл (например, открытие файла)
                link.setOnAction(event -> {
                    String fileName = link.getText();
                    File fileOpen = new File(fileName);

                    if (fileOpen.exists() && fileOpen.isFile()) {
                        try {
                            // Чтение содержимого файла
                            BufferedReader reader = new BufferedReader(new FileReader(fileOpen)); // Замените "file" на "fileOpen"
                            StringBuilder fileContents = new StringBuilder();
                            String line;
                            while ((line = reader.readLine()) != null) {
                                fileContents.append(line).append("\n");
                            }
                            reader.close();

                            // Создание нового окна для отображения содержимого файла
                            Stage fileStage = new Stage();
                            fileStage.setTitle("Содержимое файла: " + fileName);

                            StackPane fileRoot = new StackPane();
                            TextArea textArea = new TextArea();
                            textArea.setText(fileContents.toString());
                            fileRoot.getChildren().add(textArea);

                            Scene fileScene = new Scene(fileRoot, 600, 400);
                            fileStage.setScene(fileScene);

                            fileStage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Файл не найден: " + fileName);
                    }
                });

                // Добавьте иконку и ссылку на сцену или на ваш контейнер
                root.getChildren().addAll(icon, link);
            }
        }

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