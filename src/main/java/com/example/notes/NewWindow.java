package com.example.notes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
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
        setTitle("text redactor");
        StackPane root = new StackPane();
        root.getStyleClass().add("main-root"); // Применение стиля к корневому контейнеру

        TextArea textArea = new TextArea();
        textArea.getStyleClass().add("text-area"); // Применение стиля к текстовому полю

        Button saveButton = new Button("Save");
        saveButton.getStyleClass().add("button"); // Применение стиля к кнопке

        StackPane.setAlignment(saveButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(saveButton, new Insets(10));

        saveButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("Saves"));

            // Запрос имени файла у пользователя
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Окно сохранения файла");
            dialog.setHeaderText(null);
            dialog.setContentText("Введите имя файла:");
            DialogPane dialogPane = dialog.getDialogPane();
            dialogPane.getStylesheets().add("styles.css"); // Укажите путь к вашему CSS-файлу
            dialogPane.getStyleClass().add("custom-dialog"); // Примените селектор из CSS

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String fileName = result.get();
                String fileExtension = ".txt"; // Расширение файла

                // Создаем объект File с указанным именем в указанной директории
                File file = new File("Saves", fileName + fileExtension);

                saveTextToFile(file, textArea.getText());
                close();
            }
        });

        root.getChildren().addAll(textArea, saveButton);

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add("styles.css"); // Применение стилей из CSS файла к сцене
        setScene(scene);
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