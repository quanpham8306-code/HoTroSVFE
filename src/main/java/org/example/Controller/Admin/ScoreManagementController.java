package org.example.Controller.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Config.AppSession;
import org.example.Model.Diem;
import org.example.Service.Admin.AdminScoreService;
import org.example.Util.SceneUtil;

public class ScoreManagementController {

    @FXML private TextField txtMaSv;
    @FXML private TextField txtMaLopHP;
    @FXML private TextField txtMon;
    @FXML private TextField txtQT;
    @FXML private TextField txtCK;
    @FXML private TextField txtHP;
    @FXML private TextField txtTrangThai;

    @FXML private TableView<Diem> tableScore;
    @FXML private TableColumn<Diem,String> colMon;
    @FXML private TableColumn<Diem,String> colMasv;
    @FXML private TableColumn<Diem,String> colMaLopHP;
    @FXML private TableColumn<Diem,Double> colQT;
    @FXML private TableColumn<Diem,Double> colCK;
    @FXML private TableColumn<Diem,Double> colHP;
    @FXML private TableColumn<Diem,String> colTrangThai;

    @FXML private Button btnHomeAdmin;
    @FXML private Button btnSV;
    @FXML private Button btnLHP;
    @FXML private Button btnMh;
    @FXML private Button btnAccount;
    @FXML private Button btnLogout;
    @FXML private Button btnRegisteredClass;

    private ObservableList<Diem> scoreList;

    private final AdminScoreService service =
            new AdminScoreService();


    @FXML
    public void initialize() {

        colMasv.setCellValueFactory(
                new PropertyValueFactory<>("maSv"));

        colMaLopHP.setCellValueFactory(
                new PropertyValueFactory<>("maLopHP"));

        colMon.setCellValueFactory(
                new PropertyValueFactory<>("mon"));

        colQT.setCellValueFactory(
                new PropertyValueFactory<>("diemQuaTrinh"));

        colCK.setCellValueFactory(
                new PropertyValueFactory<>("diemCuoiKy"));

        colHP.setCellValueFactory(
                new PropertyValueFactory<>("diemHocPhan"));

        colTrangThai.setCellValueFactory(
                new PropertyValueFactory<>("trangThai"));

        loadData();

        txtQT.textProperty()
                .addListener((obs, o, n) -> tinhDiem());

        txtCK.textProperty()
                .addListener((obs, o, n) -> tinhDiem());

        tableScore.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldV, d) -> {

                    if (d != null) {

                        txtMon.setText(d.getMon());

                        txtMaSv.setText(d.getMaSv());

                        txtMaLopHP.setText(d.getMaLopHP());

                        txtQT.setText(String.valueOf(d.getDiemQuaTrinh()));

                        txtCK.setText(String.valueOf(d.getDiemCuoiKy()));

                        txtHP.setText(String.valueOf(d.getDiemHocPhan()));

                        txtTrangThai.setText(d.getTrangThai());
                    }
                });
    }

    private void loadData() {

        scoreList =
                FXCollections.observableArrayList(
                        service.getAll());

        tableScore.setItems(scoreList);
    }


    private void tinhDiem() {

        try {
            double qt =
                    Double.parseDouble(
                            txtQT.getText());

            double ck =
                    Double.parseDouble(
                            txtCK.getText());

            double hp =
                    qt * 0.4 + ck * 0.6;

            txtHP.setText(
                    String.format("%.2f", hp));

            txtTrangThai.setText(
                    hp >= 4
                            ? "Đạt"
                            : "Không đạt");
        }
        catch (Exception e) {

            txtHP.clear();
            txtTrangThai.clear();
        }
    }

    @FXML
    private void addScore() {

        Diem d = new Diem();

        d.setMon(txtMon.getText());
        d.setMaSv(txtMaSv.getText());
        d.setMaLopHP(txtMaLopHP.getText());
        d.setDiemQuaTrinh(Double.parseDouble(txtQT.getText()));
        d.setDiemCuoiKy(Double.parseDouble(txtCK.getText()));
        d.setDiemHocPhan(Double.parseDouble(txtHP.getText()));
        d.setTrangThai(txtTrangThai.getText());

        if (service.add(d)) {
            loadData();
            refreshForm();
        }
    }

    @FXML
    private void editScore() {

        Diem d = tableScore.getSelectionModel().getSelectedItem();

        if (d == null)
            return;

        d.setMaSv(txtMaSv.getText());
        d.setMaLopHP(txtMaLopHP.getText());
        d.setMon(txtMon.getText());
        d.setDiemQuaTrinh(Double.parseDouble(txtQT.getText()));
        d.setDiemCuoiKy(Double.parseDouble(txtCK.getText()));
        d.setDiemHocPhan(Double.parseDouble(txtHP.getText()));
        d.setTrangThai(txtTrangThai.getText());

        if (service.update(d)) {
            loadData();
            refreshForm();
        }
    }

    @FXML
    private void deleteScore() {

        Diem d = tableScore.getSelectionModel().getSelectedItem();

        if (d == null)
            return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText(
                "Bạn có chắc muốn xóa điểm của sinh viên "
                        + d.getMaSv()
                        + " lớp "
                        + d.getMaLopHP()
                        + " không?"
        );

        if (alert.showAndWait().get() == ButtonType.OK) {
            if (service.delete(d)) {
                loadData();
                refreshForm();
            }
        }
    }


    @FXML
    private void refreshForm() {

        txtMon.clear();
        txtQT.clear();
        txtCK.clear();
        txtHP.clear();
        txtTrangThai.clear();
        txtMaSv.clear();
        txtMaLopHP.clear();

        tableScore.getSelectionModel()
                .clearSelection();
    }


    @FXML
    public void showHomeAd() {
        SceneUtil.switchScene(btnHomeAdmin, "/fxml/Admin/AdminHome.fxml");
    }

    @FXML
    public void showMh() {
        SceneUtil.switchScene(btnMh, "/fxml/Admin/SubjectManagement.fxml");
    }

    @FXML
    public void showSV() {
        SceneUtil.switchScene(btnSV, "/fxml/Admin/StudentManagement.fxml");
    }

    @FXML public void showAccount() {SceneUtil.switchScene(btnAccount, "/fxml/Admin/AdminAcount.fxml");}

    @FXML
    public void showLHP() {
        SceneUtil.switchScene(btnLHP, "/fxml/Admin/ClassManagement.fxml");
    }
    @FXML
    public void handleLogout() {
        AppSession.clear();
        SceneUtil.switchScene(btnLogout, "/fxml/Login.fxml");
    }

    @FXML public void showRegisteredClass(){ SceneUtil.switchScene(btnRegisteredClass,"/fxml/Admin/RegisteredClass.fxml");}
}
