package org.example.Controller.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Config.AppSession;
import org.example.Model.LopHocPhan;
import org.example.Service.Admin.AdminClassService;
import org.example.Util.ApiEndpoint;
import org.example.Util.ExcelUtil;
import org.example.Util.SceneUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ClassManagementController {
    @FXML private TableView<LopHocPhan> tableClass;
    @FXML private TableColumn<LopHocPhan,String> colMaLop;
    @FXML private TableColumn<LopHocPhan,String> colMonHoc;
    @FXML private TableColumn<LopHocPhan,String> colGiangVien;
    @FXML private TableColumn<LopHocPhan,String> colPhong;
    @FXML private TableColumn<LopHocPhan,String> colThu;
    @FXML private TableColumn<LopHocPhan,String> colNgayBatDau;
    @FXML private TableColumn<LopHocPhan,String> colNgayKetThuc;
    @FXML private TableColumn<LopHocPhan,String> colGioBatDau;
    @FXML private TableColumn<LopHocPhan,String> colGioKetThuc;
    @FXML private TableColumn<LopHocPhan,String> colSiSoToiDa;
    @FXML private TableColumn<LopHocPhan,String> colHocKy;
    @FXML private TableColumn<LopHocPhan,String> colNamHoc;
    @FXML private TableColumn<LopHocPhan,String> colNganh;
    @FXML private TableColumn<LopHocPhan,String> colKhoa;

    @FXML private TextField txtMaLop;
    @FXML private TextField txtMonHoc;
    @FXML private TextField txtGiangVien;
    @FXML private TextField txtPhong;
    @FXML private TextField txtThu;
    @FXML private TextField txtGioBatDau;
    @FXML private TextField txtGioKetThu;
    @FXML private TextField txtNgayBatDau;
    @FXML private TextField txtNgayKetThuc;
    @FXML private TextField txtSiSoToiDa;
    @FXML private TextField txtHocKy;
    @FXML private TextField txtNamHoc;
    @FXML private TextField txtNganh;
    @FXML private TextField txtKhoa;
    @FXML private TextField txtSearch;

    @FXML private Button btnHomeAdmin;
    @FXML private Button btnSV;
    @FXML private Button importClass;
    @FXML private Button btnMh;
    @FXML private Button btnScore;
    @FXML private Button btnLogout;
    @FXML private Button btnRegisteredClass;
    @FXML private Button btnAccount;

    private ObservableList<LopHocPhan> classList;

    private final AdminClassService service =
            new AdminClassService();

    @FXML
    public void initialize() {
        setUpTable();
        loadData();

        txtSearch.textProperty()
                .addListener((obs, oldV, newV)
                        -> search(newV));

        tableClass.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldV, lhp) -> {

                    if (lhp != null) {
                        setLopHocPhan(lhp);
                    }
                });
    }

    private void setLopHocPhan(LopHocPhan lhp) {
        txtMaLop.setText(lhp.getMaLopHP());
        txtMonHoc.setText(lhp.getTenMonHoc());
        txtGiangVien.setText(lhp.getGiangVien());
        txtPhong.setText(lhp.getPhongHoc());
        txtThu.setText(String.valueOf(lhp.getThu()));

        txtGioBatDau.setText(String.valueOf(lhp.getGioBatDau()));
        txtGioKetThu.setText(String.valueOf(lhp.getGioKetThuc()));
        txtNgayBatDau.setText(String.valueOf(lhp.getNgayBatDau()));
        txtNgayKetThuc.setText(String.valueOf(lhp.getNgayKetThuc()));
        txtSiSoToiDa.setText(String.valueOf(lhp.getSiSoToiDa()));

        txtHocKy.setText(String.valueOf(lhp.getHocKy()));
        txtNamHoc.setText(lhp.getNamHoc());
        txtNganh.setText(lhp.getNganh());
        txtKhoa.setText(lhp.getKhoa());
    }

    private void setUpTable()
    {
        colMaLop.setCellValueFactory(
                new PropertyValueFactory<>("maLopHP"));

        colMonHoc.setCellValueFactory(
                new PropertyValueFactory<>("tenMonHoc"));

        colGiangVien.setCellValueFactory(
                new PropertyValueFactory<>("giangVien"));

        colPhong.setCellValueFactory(
                new PropertyValueFactory<>("phongHoc"));

        colThu.setCellValueFactory(
                new PropertyValueFactory<>("thu"));
        colGioBatDau.setCellValueFactory(
                new PropertyValueFactory<>("gioBatDau")
        );
        colGioKetThuc.setCellValueFactory(
                new PropertyValueFactory<>("gioKetThuc")
        );
        colNgayBatDau.setCellValueFactory(
                new PropertyValueFactory<>("ngayBatDau")
        );
        colNgayKetThuc.setCellValueFactory(
                new PropertyValueFactory<>("ngayKetThuc")
        );
        colSiSoToiDa.setCellValueFactory(
                new PropertyValueFactory<>("siSoToiDa")
        );
        colHocKy.setCellValueFactory(
                new PropertyValueFactory<>("hocKy"));

        colNamHoc.setCellValueFactory(
                new PropertyValueFactory<>("namHoc"));

        colNganh.setCellValueFactory(
                new PropertyValueFactory<>("nganh"));

        colKhoa.setCellValueFactory(
                new PropertyValueFactory<>("khoa"));
    }
    private void loadData() {

        classList =
                FXCollections.observableArrayList(
                        service.getAll());

        tableClass.setItems(classList);
        tableClass.refresh();
    }

    @FXML
    private void addClass() {

        LopHocPhan lhp = getLopHocPhan();

        if (service.add(lhp)) {

            loadData();
            refreshForm();

            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION);

            alert.setHeaderText(null);
            alert.setContentText("Thêm lớp học phần thành công!");
            alert.showAndWait();
        }
    }

    @FXML
    private void editClass() {

        LopHocPhan lhp = tableClass.getSelectionModel().getSelectedItem();

        if (lhp == null)
            return;

        lhp.setMaLopHP(txtMaLop.getText());
        lhp.setTenMonHoc(txtMonHoc.getText());
        lhp.setGiangVien(txtGiangVien.getText());
        lhp.setPhongHoc(txtPhong.getText());
        lhp.setThu(Integer.parseInt(txtThu.getText()));
        lhp.setGioBatDau(LocalTime.parse(txtGioBatDau.getText()));
        lhp.setGioKetThuc(LocalTime.parse(txtGioKetThu.getText()));
        lhp.setNgayBatDau(LocalDate.parse(txtNgayBatDau.getText()));
        lhp.setNgayKetThuc(LocalDate.parse(txtNgayKetThuc.getText()));
        lhp.setSiSoToiDa(Integer.parseInt(txtSiSoToiDa.getText()));
        lhp.setHocKy(Integer.parseInt(txtHocKy.getText()));
        lhp.setNamHoc(txtNamHoc.getText());
        lhp.setNganh(txtNganh.getText());
        lhp.setKhoa(txtKhoa.getText());

        if (service.update(lhp)) {
            loadData();
            refreshForm();
        }
    }

    @FXML
    private void deleteClass() {

        LopHocPhan lhp =
                tableClass.getSelectionModel()
                        .getSelectedItem();

        if (lhp == null)
            return;

        Alert alert =
                new Alert(Alert.AlertType.CONFIRMATION);

        alert.setHeaderText(null);
        alert.setContentText(
                "Bạn có chắc muốn xóa lớp "
                        + lhp.getMaLopHP()
                        + " ?"
        );

        if (alert.showAndWait().get()
                == ButtonType.OK) {

            if (service.delete(
                    lhp.getMaLopHP())) {

                loadData();
                refreshForm();
            }
        }
    }

    @FXML
    private void refreshForm() {

        txtMaLop.clear();
        txtMonHoc.clear();
        txtGiangVien.clear();
        txtPhong.clear();
        txtThu.clear();
        txtGioBatDau.clear();
        txtGioKetThu.clear();
        txtNgayBatDau.clear();
        txtNgayKetThuc.clear();
        txtSiSoToiDa.clear();
        txtHocKy.clear();
        txtNamHoc.clear();
        txtNganh.clear();
        txtKhoa.clear();

        tableClass.getSelectionModel()
                .clearSelection();
    }

    private void search(String keyword) {

        FilteredList<LopHocPhan> filtered =
                new FilteredList<>(classList);

        filtered.setPredicate(lhp -> {

            if (keyword == null
                    || keyword.isBlank())
                return true;

            String key =
                    keyword.toLowerCase();

            return lhp.getMaLopHP()
                    .toLowerCase()
                    .contains(key)

                    ||

                    lhp.getTenMonHoc()
                            .toLowerCase()
                            .contains(key);
        });

        tableClass.setItems(filtered);
    }
    @FXML public void showRegisteredClass() throws IOException {
        SceneUtil.switchScene(btnRegisteredClass,"/fxml/Admin/RegisteredClass.fxml");}
    @FXML
    public void showHomeAd() throws IOException {
        SceneUtil.switchScene(btnHomeAdmin, "/fxml/Admin/AdminHome.fxml");
    }

    @FXML
    public void showMh() throws IOException {
        SceneUtil.switchScene(btnMh, "/fxml/Admin/SubjectManagement.fxml");
    }

    @FXML
    public void showSV() throws IOException {
        SceneUtil.switchScene(btnSV, "/fxml/Admin/StudentManagement.fxml");
    }
    @FXML
    public void showAccount() throws IOException {
        SceneUtil.switchScene(btnAccount, "/fxml/Admin/AdminAcount.fxml");}
    @FXML
    public void showScore() throws IOException {
        SceneUtil.switchScene(btnScore, "/fxml/Admin/ScoreManagement.fxml");
    }

    private LopHocPhan getLopHocPhan() {
        LopHocPhan lhp = new LopHocPhan();
        lhp.setMaLopHP(txtMaLop.getText());
        lhp.setTenMonHoc(txtMonHoc.getText());
        lhp.setGiangVien(txtGiangVien.getText());
        lhp.setPhongHoc(txtPhong.getText());
        lhp.setThu(Integer.parseInt(txtThu.getText()));
        lhp.setGioBatDau(LocalTime.parse(txtGioBatDau.getText()));
        lhp.setGioKetThuc(LocalTime.parse(txtGioKetThu.getText()));
        lhp.setNgayBatDau(LocalDate.parse(txtNgayBatDau.getText()));
        lhp.setNgayKetThuc(LocalDate.parse(txtNgayKetThuc.getText()));
        lhp.setSiSoToiDa(Integer.parseInt(txtSiSoToiDa.getText()));
        lhp.setHocKy(Integer.parseInt(txtHocKy.getText()));
        lhp.setNamHoc(txtNamHoc.getText());
        lhp.setNganh(txtNganh.getText());
        lhp.setKhoa(txtKhoa.getText());
        return lhp;
    }
    @FXML
    public void handleLogout() {
        AppSession.clear();
        SceneUtil.switchToLogin(btnLogout);
    }


    @FXML
    private void handleImportClass() {
        ExcelUtil.handleImportExcel(
                importClass,
                ApiEndpoint.ADMIN_CLASS_IMPORT,
                "danhSachLop",
                this::loadData
        );
    }
}
