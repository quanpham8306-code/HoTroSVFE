package org.example.Controller.Auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.Config.AppSession;
import org.example.Service.TaiKhoanService;
import org.example.Util.SceneUtil;

public class LoginController {
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;

    @FXML private Button btnLogin;

    private final TaiKhoanService taiKhoanService = new TaiKhoanService();

    @FXML
    public void login(ActionEvent actionEvent) {
        String username = txtUsername.getText() == null ? "" : txtUsername.getText().trim();
        String password = txtPassword.getText() == null ? "" : txtPassword.getText();

        if (username.isBlank() || password.isBlank()) {
            showAlert("Thiếu thông tin", "Bạn cần nhập username và password.");
            return;
        }

        try {
            if (taiKhoanService.login(username, password)) {
                if(AppSession.getRole().equals("ADMIN")){
                    SceneUtil.switchToApp(btnLogin, "/fxml/Admin/AdminHome.fxml");
                }
                if(AppSession.getRole().equals("STUDENT")){
                    SceneUtil.switchToApp(btnLogin, "/fxml/Student/Home.fxml");
                }

            } else {
                showAlert("Login fail", "Sai tài khoản hoặc mật khẩu.");
            }
        } catch (Exception e) {
            showAlert("Lỗi kết nối", "Không thể kết nối tới server.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
