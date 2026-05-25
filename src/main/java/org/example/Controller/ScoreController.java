package org.example.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ScoreController {
    @FXML
    Button btnHome;
    public void showHome()
    {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/Home.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) btnHome.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
