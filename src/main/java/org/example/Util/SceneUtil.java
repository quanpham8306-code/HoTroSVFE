package org.example.Util;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneUtil {

    public static void switchToApp(Node node, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(
                    SceneUtil.class.getResource(fxmlPath)
            );

            Stage stage = (Stage) node
                    .getScene()
                    .getWindow();

            Scene scene = stage.getScene();

            stage.setResizable(true);

            if (scene == null) {
                stage.setScene(new Scene(root));
            } else {
                scene.setRoot(root);
            }

            stage.setMaximized(true);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException("Cannot load scene: " + fxmlPath, e);
        }
    }
    public static void switchScene(Node node, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(
                    SceneUtil.class.getResource(fxmlPath)
            );

            Stage stage = (Stage) node
                    .getScene()
                    .getWindow();

            Scene currentScene = stage.getScene();

            if (currentScene == null) {
                stage.setScene(new Scene(root));
            } else {
                currentScene.setRoot(root);
            }

            stage.setMaximized(true);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException("Cannot load scene: " + fxmlPath, e);
        }
    }
}