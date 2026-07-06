package org.example.Util;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneUtil {

    public static void switchScene(Node node, String fxmlPath) throws IOException {
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
            stage.setResizable(false);
            stage.setTitle("Hỗ trợ sinh viên");
            stage.getIcons().add(
                    new Image(SceneUtil.class.getResourceAsStream("/images/logo-app.png"))
            );
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException("Cannot load scene: " + fxmlPath, e);
        }
    }
    public static void switchToLogin(Node node) {
        try {
            Parent root = FXMLLoader.load(
                    SceneUtil.class.getResource("/fxml/Login.fxml")
            );

            Stage stage = (Stage) node.getScene().getWindow();

            stage.setFullScreen(false);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.setTitle("Login");
            stage.getIcons().add(
                    new Image(SceneUtil.class.getResourceAsStream("/images/logo-app.png"))
            );


            stage.setScene(new Scene(root, 900, 600));
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException("Cannot load login scene", e);
        }
    }
}