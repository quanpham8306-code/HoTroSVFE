package org.example.Controller.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.Config.AppSession;
import org.example.Service.Admin.AdminHomeService;
import org.example.Util.SceneUtil;

public class AdminHomeController {
    @FXML private Label lblStudent;
    @FXML private Label lblSubject;
    @FXML private Label lblClass;
    @FXML private Label lblScore;

    @FXML private Button btnSV;
    @FXML private Button btnMh;
    @FXML private Button btnLHP;
    @FXML private Button btnScore;
    @FXML private Button btnLogout;
    @FXML private Button btnRegisteredClass;

    private final AdminHomeService service =
            new AdminHomeService();

    @FXML
    public void initialize() {
        try {
            lblStudent.setText(String.valueOf(service.getTotalStudent()));
            lblSubject.setText(String.valueOf(service.getTotalSubject()));
            lblClass.setText(String.valueOf(service.getTotalClass()));
            lblScore.setText(String.valueOf(service.getTotalScore()));
        } catch (Exception e) {
            e.printStackTrace();

            lblStudent.setText("0");
            lblSubject.setText("0");
            lblClass.setText("0");
            lblScore.setText("0");
        }
    }


    @FXML
    public void showSV() {
        SceneUtil.switchScene(btnSV, "/fxml/Admin/StudentManagement.fxml");
    }

    @FXML
    public void showMh() {
        SceneUtil.switchScene(btnMh, "/fxml/Admin/SubjectManagement.fxml");
    }

    @FXML
    public void showLHP() {
        SceneUtil.switchScene(btnLHP, "/fxml/Admin/ClassManagement.fxml");
    }

    @FXML
    public void showScore() {
        SceneUtil.switchScene(btnScore, "/fxml/Admin/ScoreManagement.fxml");
    }
    @FXML
    public void handleLogout() {
        AppSession.clear();
        SceneUtil.switchScene(btnLogout, "/fxml/Login.fxml");
    }
    @FXML public void showRegisteredClass(){ SceneUtil.switchScene(btnRegisteredClass,"/fxml/Admin/RegisteredClass.fxml");}
}
