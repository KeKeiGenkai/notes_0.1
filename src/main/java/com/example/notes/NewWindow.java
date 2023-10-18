package com.example.notes;

import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import static java.awt.SystemColor.text;
import static javafx.application.Application.launch;

/*public class NewWindow extends Stage {
    public NewWindow() {
        setTitle("text redactor");
        HBox root = new HBox();
        TextArea textArea = new TextArea();
        Button saveButton = new Button("Save");

        saveButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            fileChooser.setInitialDirectory(new File("D:\\scheduler_start\\JavaTest\\notes\\Saves"));

            // Здесь мы запрашиваем имя файла, но не открываем диалоговое окно для выбора папки
            FileChooser.ExtensionFilter extFilter = fileChooser.getExtensionFilters().get(0);
            String fileExtension = extFilter.getExtensions().get(0).substring(2);  // Убираем "*."
            String fileName = "defaultFileName." + fileExtension;

            File file = new File(fileName);

            saveTextToFile(file, textArea.getText());
        });

        root.getChildren().addAll(textArea, saveButton);

        Scene scene = new Scene(root, 600, 400);
        setScene(scene);
    }

    private void saveTextToFile(File file, String text) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/
public class NewWindow extends Stage {
    public NewWindow() {
        setTitle("text redactor"); // Устанавливаем заголовок окна "text redactor"
        HBox root = new HBox(); // Создаем корневой контейнер HBox
        TextArea textArea = new TextArea(); // Создаем текстовую область (TextArea)
        Button saveButton = new Button("Save"); // Создаем кнопку "Save"

         /*saveButton.setOnAction(e -> { // Устанавливаем обработчик события при нажатии на кнопку "Save"
            FileChooser fileChooser = new FileChooser(); // Создаем объект FileChooser для выбора файла
            fileChooser.setInitialDirectory(new File("D:.scheduler_start.JavaTest.notes.Saves")); // Устанавливаем начальную директорию для сохранения файла

            // Здесь мы запрашиваем имя файла, но не открываем диалоговое окно для выбора папки
            FileChooser.ExtensionFilter extFilter = fileChooser.getExtensionFilters().get(0); // Получаем первый фильтр
            String fileExtension = extFilter.getExtensions().get(0).substring(2); // Извлекаем расширение файла из фильтра
            String fileName = "failick." + fileExtension; // Генерируем имя файла по умолчанию

            File file = new File(fileName); // Создаем объект File с указанным именем

            saveTextToFile(file, textArea.getText()); // Вызываем метод saveTextToFile для сохранения текста в файл
        });*/
        saveButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("D:\\scheduler_start\\JavaTest\\notes\\Saves"));

            // Запрос имени файла у пользователя
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Введите имя файла");
            dialog.setHeaderText(null);
            dialog.setContentText("Введите имя файла:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String fileName = result.get();
                String fileExtension = ".txt"; // Расширение файла

                // Создаем объект File с указанным именем в указанной директории
                File file = new File("D:\\scheduler_start\\JavaTest\\notes\\Saves", fileName + fileExtension);

                saveTextToFile(file, textArea.getText());
            }
        });


        root.getChildren().addAll(textArea, saveButton); // Добавляем текстовую область и кнопку в корневой контейнер

        Scene scene = new Scene(root, 600, 400); // Создаем сцену с корневым контейнером и размерами
        setScene(scene); // Устанавливаем сцену как сцену для данного окна
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