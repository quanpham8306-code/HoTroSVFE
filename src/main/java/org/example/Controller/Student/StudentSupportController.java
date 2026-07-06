package org.example.Controller.Student;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.Config.AppSession;
import org.example.Service.TaiKhoanService;
import org.example.Util.SceneUtil;

import java.io.IOException;


public class StudentSupportController {
    @FXML private Button btnHome;
    @FXML private Button btnSchedule;
    @FXML private Button btnScore;
    @FXML private Button btnLogout;
    @FXML private Button btnSubject;
    @FXML private Button btnNote;

    @FXML
    public void showHome() throws IOException {
        SceneUtil.switchScene(btnHome, "/fxml/Student/Home.fxml");
    }

    @FXML
    public void showSchedule() throws IOException {
        SceneUtil.switchScene(btnSchedule, "/fxml/Student/Schedule.fxml");
    }

    @FXML
    public void showSubject() throws IOException {
        SceneUtil.switchScene(btnSubject, "/fxml/Student/VitualSchedule.fxml");
    }

    @FXML
    public void showScore() throws IOException {
        SceneUtil.switchScene(btnScore, "/fxml/Student/Score.fxml");
    }

    @FXML
    public void showNote() throws IOException {
        SceneUtil.switchScene(btnNote, "/fxml/Student/Note.fxml");
    }

    @FXML
    public void handleLogout() {
        AppSession.clear();
        SceneUtil.switchToLogin(btnLogout);
    }

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
}
