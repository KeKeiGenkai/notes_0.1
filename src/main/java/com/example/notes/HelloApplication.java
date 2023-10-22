package com.example.notes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
        File folder = new File("Saves");
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

        // Установка цвета фона для сцены
        scene.setFill(Color.DARKGREY);

        // Создайте GridPane для отображения плиток
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
                tile.setStyle("-fx-background-color: gray; -fx-text-fill: white;");

                tile.setOnAction(e -> {
                    // Откройте файл при нажатии на плитку
                });

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

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}