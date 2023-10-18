package com.example.notes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import static javafx.application.Application.launch;

public class NewWindow extends Stage {

    public NewWindow() {
        setTitle("text redactor"); // Устанавливаем заголовок окна "text redactor"
        StackPane root = new StackPane(); // Создаем корневой контейнер StackPane
        TextArea textArea = new TextArea(); // Создаем текстовую область (TextArea)

        Button saveButton = new Button("Save");// Создаем кнопку "Save"
        saveButton.setStyle("-fx-background-color: gray; -fx-text-fill: white;"); // Установка стилей для кнопки

        StackPane.setAlignment(saveButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(saveButton, new Insets(10));

        saveButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("D:\\scheduler_start\\JavaTest\\notes\\Saves"));

            // Запрос имени файла у пользователя
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Окно сохранения файла");
            dialog.setHeaderText(null);
            dialog.setContentText("Введите имя файла:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String fileName = result.get();
                String fileExtension = ".txt"; // Расширение файла

                // Создаем объект File с указанным именем в указанной директории
                File file = new File("D:\\scheduler_start\\JavaTest\\notes\\Saves", fileName + fileExtension);

                saveTextToFile(file, textArea.getText());
                close();
            }
        });


        root.getChildren().addAll(textArea, saveButton); // Добавляем текстовую область и кнопку в корневой контейнер

        Scene scene = new Scene(root, 900, 600); // Создаем сцену с корневым контейнером и размерами
        setScene(scene); // Устанавливаем сцену как сцену для данного окна
        root.setStyle("-fx-background-color: darkgray;");

        // Установка цвета фона для сцены
        scene.setFill(Color.DARKGREY);
    }

    private void saveTextToFile(File file, String text) { // Метод для сохранения текста в файл
        try (FileWriter writer = new FileWriter(file)) { // Создаем FileWriter для записи в файл
            writer.write(text); // Записываем текст в файл
        } catch (IOException e) { // Обрабатываем исключение, если произошла ошибка ввода-вывода
            e.printStackTrace(); // Выводим информацию об ошибке
        }
    }

    public static void main(String[] args) {
        launch(args); // Запускаем приложение JavaFX
    }
}