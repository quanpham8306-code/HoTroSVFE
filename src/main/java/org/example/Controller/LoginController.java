package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.API.TaiKhoanAPI;

import java.io.IOException;


public class LoginController {
    @FXML
    private TextField txtusername;
    @FXML
    private PasswordField txtppassword;
    @FXML
    private Button btnLogin;
    @FXML
    public void submit(ActionEvent actionEvent) {
        String usn = txtusername.getText();
        String pwd = txtppassword.getText();
        String result = TaiKhoanAPI.logIn(usn, pwd);
        System.out.println(result);
        if(result.contains("\"ok\""))
        {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/fxml/Home.fxml")
                );

                Parent root = loader.load();

                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login fail");
            alert.setHeaderText("Login Fail");
            alert.showAndWait();
        }
    }
}
