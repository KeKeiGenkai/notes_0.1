package com.example.notes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 900, 600);

        // Применить глобальный CSS-файл ко всей сцене
        scene.getStylesheets().add("styles.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Главное окно");
        primaryStage.show();

        // Добавим кнопки с файлами в папке "Saves" в виде плиток
        File folder = new File("Saves");
        File[] files = folder.listFiles();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        int column = 0;
        int row = 0;

        for (File file : files) {
            if (file.isFile()) {
                Button fileButton = new Button(file.getName());
                fileButton.getStyleClass().add("file-button"); // Применить стили к кнопке

                fileButton.setOnAction(e -> {
                    String fileName = file.getName();
                    String filePath = "Saves" + File.separator + fileName;

                    try {
                        File fileToOpen = new File(filePath);
                        if (fileToOpen.exists() && Desktop.isDesktopSupported()) {
                            openFile(fileToOpen);
                        } else {
                            System.out.println("Файл не найден или не поддерживается открытие.");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        System.out.println("Ошибка открытия файла: " + ex.getMessage());
                    }
                });

                grid.add(fileButton, column, row);

                column++;
                if (column > 2) {
                    column = 0;
                    row++;
                }
            }
        }

        root.getChildren().add(grid);

        // Кнопка "Написать о членах"
        Button btn = new Button("Написать о членах");
        StackPane.setAlignment(btn, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(btn, new Insets(10));

        btn.setOnAction(e -> {
            NewWindow newWindow = new NewWindow();
            newWindow.show();
        });

        root.getChildren().add(btn);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void openFile(File file) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file);
        } else {
            System.out.println("Система не поддерживает открытие файла.");
        }
    }
}