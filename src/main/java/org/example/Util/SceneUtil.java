package org.example.Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneUtil {

    public static void switchScene(Node node, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(SceneUtil.class.getResource(fxmlPath));
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Cannot load scene: " + fxmlPath, e);
        }
    }
}