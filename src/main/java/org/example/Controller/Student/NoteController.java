package org.example.Controller.Student;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.Config.AppSession;
import org.example.Util.SceneUtil;

public class NoteController {
    @FXML private Button btnHome;
    @FXML private Button btnScore;
    @FXML private Button btnSchedule;
    @FXML private Button btnLogout;
    @FXML private Button btnAddNote;
    @FXML private Button btnEditNote;
    @FXML private Button btnDeleteNote;
    @FXML private Button btnSubject;

    @FXML
    public void initialize() {
    }
    @FXML
    public void showScore() {
        SceneUtil.switchScene(btnScore, "/fxml/Score.fxml");
    }

    @FXML
    public void showSchedule() {
        SceneUtil.switchScene(btnSchedule, "/fxml/Schedule.fxml");
    }

    @FXML
    public void showHome() {
        SceneUtil.switchScene(btnHome, "/fxml/Home.fxml");
    }
    @FXML
    public void showSubject() {
        SceneUtil.switchScene(btnSubject, "/fxml/RegisterSubject.fxml");
    }

    @FXML
    public void handleLogout() {
        AppSession.clear();
        SceneUtil.switchScene(btnLogout, "/fxml/Login.fxml");
    }
}
