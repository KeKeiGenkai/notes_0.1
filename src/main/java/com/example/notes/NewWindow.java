package com.example.notes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.awt.Desktop;

public class NewWindow extends Stage {
    private TextArea textArea;

    public NewWindow() {
        setTitle("Text Redactor");
        StackPane root = new StackPane();
        root.getStyleClass().add("main-root");

        textArea = new TextArea();
        textArea.getStyleClass().add("text-area");

        Button saveButton = new Button("Save");
        saveButton.getStyleClass().add("button");

        StackPane.setAlignment(saveButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(saveButton, new Insets(15));

        saveButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("Saves"));

            // Запрос имени файла у пользователя
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Сохранить файл");
            dialog.setHeaderText(null);
            dialog.setContentText("Введите имя файла:");
            DialogPane dialogPane = dialog.getDialogPane();
            dialogPane.getStylesheets().add("styles.css");
            dialogPane.getStyleClass().add("custom-dialog");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String fileName = result.get();
                String fileExtension = ".txt";

                File file = new File("Saves", fileName + fileExtension);

                saveTextToFile(file, textArea.getText());
                close();
            }
        });

        root.getChildren().addAll(textArea, saveButton);

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add("styles.css");
        setScene(scene);
    }

    private void saveTextToFile(File file, String text) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openFile(File file) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
