package com.example.notes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
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
        primaryStage.setTitle("Главное очко");
        primaryStage.show();
        File folder = new File("Saves");
        File[] files = folder.listFiles();

        Button btn = new Button("Написать о членах");

        StackPane.setAlignment(btn, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(btn, new Insets(10));

        btn.setOnAction(e -> {
            NewWindow newWindow = new NewWindow();
            newWindow.show();
        });

        root.getChildren().add(btn);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10); // Горизонтальный отступ между плитками
        grid.setVgap(10); // Вертикальный отступ между плитками
        grid.setMouseTransparent(true);

        int row = 0; // Инициализируйте начальную строку
        int column = 0; // Инициализируйте начальный столбец
        int numberOfColumns = 6; // Установите количество столбцов

        // Переберите файлы и создайте плитки
        for (File file : files) {
            if (file.isFile()) {
                Button tile = new Button(file.getName());
                //applyStyles(tile);

                tile.setOnAction(e -> {
                            // Откройте файл при нажатии на плитку
                            // Получите имя файла, связанное с этой кнопкой
                            String fileName = ((Button) e.getSource()).getText();

                            // Создайте путь к файлу, используя папку "Saves" и имя файла
                            String filePath = "Saves" + File.separator + fileName;

                            // Попытайтесь открыть файл
                            try {
                                File fileToOpen = new File(filePath);
                                if (fileToOpen.exists() && Desktop.isDesktopSupported()) {
                                    Desktop.getDesktop().open(fileToOpen);
                                } else {
                                    System.out.println("Файл не найден или не поддерживается открытие.");
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                                System.out.println("Ошибка открытия файла: " + ex.getMessage());
                            }
                        }
                );

                // Добавьте плитку в GridPane
                grid.add(tile, column, row);

                // Увеличьте столбец или перейдите на следующую строку, если достигнут предел столбцов
                column++;
                if (column == numberOfColumns) {
                    column = 0;
                    row++;
                }
            }
        }

        root.getChildren().add(grid); // Добавьте GridPane на корневой контейнер

    }

    public static void main(String[] args) {
        launch(args);
    }
}