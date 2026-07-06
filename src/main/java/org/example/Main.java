package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.Util.SceneUtil;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

       FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource("/fxml/Login.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.getIcons().add(
                new Image(SceneUtil.class.getResourceAsStream("/images/logo-app.png"))
        );
        stage.show();
    }
    public static void main(String[] args) {launch();
    }
}