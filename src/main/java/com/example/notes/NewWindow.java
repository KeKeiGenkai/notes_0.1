package com.example.notes;

import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.awt.SystemColor.text;
/*public class NewWindow extends Stage {
    public NewWindow() {
        StackPane root = new StackPane();
        Label label = new Label("А ну пиши свою хуйню");
        root.getChildren().add(label);

        Scene scene = new Scene(root, 300, 200);
        setScene(scene);
    }
}*/
// Пример использования редактора JTextPane

public class NewWindow extends Stage {
    public NewWindow() {
        setTitle("text redactor");
        HBox root = new HBox();
        TextArea textArea = new TextArea();
        Button saveButton = new Button("Save");

        saveButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File file = fileChooser.showSaveDialog(this);

            if (file != null) {
                saveTextToFile(file, textArea.getText());
            }
            // Добавьте здесь код для сохранения текста
        });


        root.getChildren().addAll(textArea, saveButton);

        Scene scene = new Scene(root, 600, 400);
        setScene(scene);
    }
    private void saveTextToFile(File file, String text) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
