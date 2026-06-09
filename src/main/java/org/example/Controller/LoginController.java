package org.example.Controller;
/*
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
*/

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import org.example.Model.Token;
import org.example.Model.UserSession;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button LoginButton;
    Token token=  new Token();
    @FXML
    public void submit(ActionEvent actionEvent) {
        String usn = txtUsername.getText().trim();
        String pwd = txtPassword.getText().trim();

        if (usn.isEmpty() || pwd.isEmpty()) {
            showAlert("Lỗi nhập liệu", "Vui lòng nhập đầy đủ tài khoản và mật khẩu!");
            return;
        }

        try {
            // Gọi API Đăng nhập
            String result = TaiKhoanAPI.logIn(usn, pwd);
            System.out.println("API Response: " + result);

            // Parse chuỗi JSON nhận được từ Backend
            JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();

            // Kiểm tra trạng thái đăng nhập thành công
            if (jsonObject.has("status") && "ok".equals(jsonObject.get("status").getAsString())) {

                JsonObject data = jsonObject.getAsJsonObject("data");

                token.setToken(data.get("token").getAsString());

     //           String studentId = jsonObject.get("token").getAsString();

                UserSession.initSession(token.getToken(), usn);

                // Chuyển Scene sang màn hình Home.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
                Parent root = loader.load();

              Stage stage = (Stage) LoginButton.getScene().getWindow();
              stage.setScene(new Scene(root));
               stage.show();
            } else {
                String errorMsg = jsonObject.has("message") ? jsonObject.get("message").getAsString() : "Tài khoản hoặc mật khẩu không chính xác!";
                showAlert("Đăng nhập thất bại", errorMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Lỗi ", "Đăng nhập thất bại!");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
