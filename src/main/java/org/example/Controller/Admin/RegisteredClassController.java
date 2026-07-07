package org.example.Controller.Admin;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Config.AppSession;
import org.example.Model.LopHocPhan;
import org.example.Model.SinhVien;
import org.example.Service.Admin.AdminRegisteredClassService;
import org.example.Util.AlertUtil;
import org.example.Util.SceneUtil;

import java.io.IOException;
import java.util.Optional;

public class RegisteredClassController {
    @FXML private TextField txtMaSv;
    @FXML private TextField txtMaLopHP;

    @FXML private TableView<SinhVien> tableClass;
    @FXML private TableColumn<LopHocPhan, String> colMaSv;
    @FXML private TableColumn<LopHocPhan, String> colName;
    @FXML private TableColumn<LopHocPhan, String> colSex;
    @FXML private TableColumn<LopHocPhan, String> colLop;
    @FXML private TableColumn<LopHocPhan, Integer> colNganh;
    @FXML private TableColumn<LopHocPhan, String> colKhoa;

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
        setupTable();

        tableClass.getSelectionModel().selectedItemProperty().addListener((obs, oldV, sv) -> {
            if (sv != null) {
                txtMaSv.setText(sv.getMaSv());
            }
        });
    }

    @FXML
    private void searchBy() {
        if(!txtMaLopHP.getText().isEmpty()){
            String maLop = txtMaLopHP.getText();

            tableClass.setItems(
                    FXCollections.observableArrayList(
                            service.getByMaLopHP(maLop.trim())
                    )
            );
        }
        else
            AlertUtil.showError("Vui lòng nhập dữ liệu ở ô mã lớp.");
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
            searchBy();
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
            if (service.removeStudentFromClass(maSv.trim(), maLopHP.trim()).equals("SUCCESS")) {
                AlertUtil.showAlert("Xóa sinh viên khỏi lớp thành công");
                searchBy();
            } else {
                AlertUtil.showAlert("Xóa thất bại");
            }
        }
    }

    @FXML public void showHomeAd() throws IOException {
        SceneUtil.switchScene(btnHomeAdmin, "/fxml/Admin/AdminHome.fxml");
    }

    @FXML public void showSV() throws IOException {
        SceneUtil.switchScene(btnSV, "/fxml/Admin/StudentManagement.fxml");
    }

    @FXML public void showMh() throws IOException {
        SceneUtil.switchScene(btnMh, "/fxml/Admin/SubjectManagement.fxml");
    }

    @FXML public void showLHP() throws IOException {
        SceneUtil.switchScene(btnLHP, "/fxml/Admin/ClassManagement.fxml");
    }

    @FXML public void showScore() throws IOException {
        SceneUtil.switchScene(btnScore, "/fxml/Admin/ScoreManagement.fxml");
    }
    @FXML public void showAccount() throws IOException {
        SceneUtil.switchScene(btnAccount, "/fxml/Admin/AdminAcount.fxml");
    }
    @FXML public void handleLogout() {
        AppSession.clear();
        SceneUtil.switchToLogin(btnLogout);
    }
    @FXML
    public void impoprtStudentToClass()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nhập mã lớp");
        dialog.setHeaderText("Import danh sách sinh viên vào lớp học phần");
        dialog.setContentText("Nhập mã lớp:");

        Optional<String> result = dialog.showAndWait();

        if (result.isEmpty()) {
            return;
        }

        String maLopHP = result.get().trim();

        if (maLopHP.isEmpty()) {
            AlertUtil.showAlert("Vui lòng nhập mã lớp học phần");
            return;
        }
        service.handleImportStudentToClass(
                importStudent,
                maLopHP,
                null
        );
    }
    private void setupTable(){
        colMaSv.setCellValueFactory(new PropertyValueFactory<>("maSv"));
        colLop.setCellValueFactory(new PropertyValueFactory<>("lop"));
        colName.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        colSex.setCellValueFactory(new PropertyValueFactory<>("kieuGioiTinh"));
        colKhoa.setCellValueFactory(new PropertyValueFactory<>("khoa"));
        colNganh.setCellValueFactory(new PropertyValueFactory<>("nganh"));
    }
}
