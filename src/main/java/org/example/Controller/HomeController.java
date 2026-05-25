package org.example.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML
    Button btnScore;
    public void showScore()
    {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/Score.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) btnScore.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void showSchedule()
    {

    }
}
