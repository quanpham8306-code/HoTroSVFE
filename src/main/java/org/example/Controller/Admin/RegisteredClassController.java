package org.example.Controller.Admin;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Config.AppSession;
import org.example.Model.LopHocPhan;
import org.example.Service.Admin.AdminRegisteredClassService;
import org.example.Util.AlertUtil;
import org.example.Util.SceneUtil;

public class RegisteredClassController {
    @FXML private TextField txtMaSv;
    @FXML private TextField txtMaLopHP;

    @FXML private TableView<LopHocPhan> tableClass;
    @FXML private TableColumn<LopHocPhan, String> colMaLop;
    @FXML private TableColumn<LopHocPhan, String> colMonHoc;
    @FXML private TableColumn<LopHocPhan, String> colGiangVien;
    @FXML private TableColumn<LopHocPhan, String> colPhong;
    @FXML private TableColumn<LopHocPhan, Integer> colThu;
    @FXML private TableColumn<LopHocPhan, String> colGioBatDau;
    @FXML private TableColumn<LopHocPhan, String> colGioKetThuc;
    @FXML private TableColumn<LopHocPhan, String> colHocKy;
    @FXML private TableColumn<LopHocPhan, String> colNamHoc;

    @FXML private Button btnHomeAdmin;
    @FXML private Button btnSV;
    @FXML private Button btnMh;
    @FXML private Button btnAccount;
    @FXML private Button btnLHP;
    @FXML private Button btnScore;
    @FXML private Button btnLogout;
    @FXML private Button importStudent;

    private final AdminRegisteredClassService service =
            new AdminRegisteredClassService();

    @FXML
    public void initialize() {
        colMaLop.setCellValueFactory(new PropertyValueFactory<>("maLopHP"));
        colMonHoc.setCellValueFactory(new PropertyValueFactory<>("tenMonHoc"));
        colGiangVien.setCellValueFactory(new PropertyValueFactory<>("giangVien"));
        colPhong.setCellValueFactory(new PropertyValueFactory<>("phongHoc"));
        colThu.setCellValueFactory(new PropertyValueFactory<>("thu"));
        colGioBatDau.setCellValueFactory(new PropertyValueFactory<>("gioBatDau"));
        colGioKetThuc.setCellValueFactory(new PropertyValueFactory<>("gioKetThuc"));
        colHocKy.setCellValueFactory(new PropertyValueFactory<>("hocKy"));
        colNamHoc.setCellValueFactory(new PropertyValueFactory<>("namHoc"));

        tableClass.getSelectionModel().selectedItemProperty().addListener((obs, oldV, lhp) -> {
            if (lhp != null) {
                txtMaLopHP.setText(lhp.getMaLopHP());
            }
        });
    }

    @FXML
    private void searchByStudent() {
        String maSv = txtMaSv.getText();

        if (maSv == null || maSv.isBlank()) {
            AlertUtil.showAlert("Vui lòng nhập mã sinh viên");
            return;
        }

        tableClass.setItems(
                FXCollections.observableArrayList(
                        service.getByMaSv(maSv.trim())
                )
        );
    }

    @FXML
    private void addStudentToClass() {
        String maSv = txtMaSv.getText();
        String maLopHP = txtMaLopHP.getText();

        if (maSv == null || maSv.isBlank()
                || maLopHP == null || maLopHP.isBlank()) {
            AlertUtil.showAlert("Vui lòng nhập mã sinh viên và mã lớp học phần");
            return;
        }
        String respone = service.addStudentToClass(maSv.trim(), maLopHP.trim());
        if (respone.equals("SUCCESS")) {
            AlertUtil.showAlert("Thêm sinh viên vào lớp thành công");
            searchByStudent();
        }
        else {
            AlertUtil.showAlert("Thêm thất bại : " + respone);
        }
    }

    @FXML
    private void removeStudentFromClass() {
        String maSv = txtMaSv.getText();
        String maLopHP = txtMaLopHP.getText();

        if (maSv == null || maSv.isBlank()
                || maLopHP == null || maLopHP.isBlank()) {
            AlertUtil.showAlert("Vui lòng nhập mã sinh viên và mã lớp học phần");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText(null);
        confirm.setContentText("Xóa sinh viên " + maSv + " khỏi lớp " + maLopHP + "?");

        if (confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            if (service.removeStudentFromClass(maSv.trim(), maLopHP.trim())) {
                AlertUtil.showAlert("Xóa sinh viên khỏi lớp thành công");
                searchByStudent();
            } else {
                AlertUtil.showAlert("Xóa thất bại");
            }
        }
    }

    @FXML public void showHomeAd() {
        SceneUtil.switchScene(btnHomeAdmin, "/fxml/Admin/AdminHome.fxml");
    }

    @FXML public void showSV() {
        SceneUtil.switchScene(btnSV, "/fxml/Admin/StudentManagement.fxml");
    }

    @FXML public void showMh() {
        SceneUtil.switchScene(btnMh, "/fxml/Admin/SubjectManagement.fxml");
    }

    @FXML public void showLHP() {
        SceneUtil.switchScene(btnLHP, "/fxml/Admin/ClassManagement.fxml");
    }

    @FXML public void showScore() {
        SceneUtil.switchScene(btnScore, "/fxml/Admin/ScoreManagement.fxml");
    }
    @FXML public void showAccount() {SceneUtil.switchScene(btnAccount, "/fxml/Admin/AdminAcount.fxml");}
    @FXML public void handleLogout() {
        AppSession.clear();
        SceneUtil.switchScene(btnLogout, "/fxml/Login.fxml");
    }
    @FXML
    public void impoprtStudentToClass()
    {
        service.handleImportStudentToClass(
                importStudent,
                txtMaLopHP.getText(),
                null
        );
    }
}
