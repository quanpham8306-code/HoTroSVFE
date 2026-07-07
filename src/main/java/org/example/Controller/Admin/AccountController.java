package org.example.Controller.Admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.Config.AppSession;
import org.example.Service.Admin.AccountService;
import org.example.Util.AlertUtil;
import org.example.Util.SceneUtil;

import java.io.IOException;
import java.util.Optional;

public class AccountController {

    @FXML private Button btnSV;
    @FXML private Button btnMh;
    @FXML private Button btnLHP;
    @FXML private Button btnScore;
    @FXML private Button btnLogout;
    @FXML private Button btnRegisteredClass;
    @FXML private Button btnHomeAdmin;

    AccountService  accountService = new AccountService();

    @FXML
    public void showHomeAd() throws IOException {
        SceneUtil.switchScene(btnHomeAdmin, "/fxml/Admin/AdminHome.fxml");
    }

    @FXML
    public void showSV() throws IOException {
        SceneUtil.switchScene(btnSV, "/fxml/Admin/StudentManagement.fxml");
    }

    @FXML
    public void showMh() throws IOException {
        SceneUtil.switchScene(btnMh, "/fxml/Admin/SubjectManagement.fxml");
    }

    @FXML
    public void showLHP() throws IOException {
        SceneUtil.switchScene(btnLHP, "/fxml/Admin/ClassManagement.fxml");
    }

    @FXML
    public void showScore() throws IOException {
        SceneUtil.switchScene(btnScore, "/fxml/Admin/ScoreManagement.fxml");
    }
    @FXML
    public void handleLogout() {
        AppSession.clear();
        SceneUtil.switchToLogin(btnLogout);
    }
    @FXML public void showRegisteredClass() throws IOException {
        SceneUtil.switchScene(btnRegisteredClass,"/fxml/Admin/RegisteredClass.fxml");}

    @FXML
    public void handleChangePws() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/ChangePassword.fxml")
        );

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();
        stage.setTitle("Đổi mật khẩu");
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);

        stage.showAndWait();
    }

    @FXML
    public void handleResetPws()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Reset mật khẩu");
        dialog.setHeaderText("Nhập tên đăng nhập cần reset");
        dialog.setContentText("Username:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(username -> {
            username = username.trim();

            if (username.isEmpty()) {
                AlertUtil.showAlert("Username không được để trống");
                return;
            }
            String message = accountService.resetPws(username);

            if(message.equals("SUCCESS"))
                AlertUtil.showAlert("Reset mật khẩu thành công");
            else
                AlertUtil.showError(message);
        });
    }
}

