package com.example.notes;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class NewWindow extends Stage {
    public NewWindow() {
        StackPane root = new StackPane();
        Label label = new Label("А ну пиши свою хуйню");
        root.getChildren().add(label);

        Scene scene = new Scene(root, 300, 200);
        setScene(scene);
    }
}
