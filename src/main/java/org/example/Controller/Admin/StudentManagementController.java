package org.example.Controller.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Config.AppSession;
import org.example.Model.SinhVien;
import org.example.Service.Admin.AdminStudentService;
import org.example.Util.ApiEndpoint;
import org.example.Util.ExcelImportUtil;
import org.example.Util.SceneUtil;

import java.time.LocalDate;
import java.util.List;

public class StudentManagementController {

    @FXML private TableView<SinhVien> tableStudent;
    @FXML private TableColumn<SinhVien,String> colMaSv;
    @FXML private TableColumn<SinhVien,String> colHoTen;
    @FXML private TableColumn<SinhVien,String> colLop;
    @FXML private TableColumn<SinhVien,String> colNgaySinh;
    @FXML private TableColumn<SinhVien,String> colId;
    @FXML private TableColumn<SinhVien,String> colDiaChi;
    @FXML private TableColumn<SinhVien,String> colKhoa;
    @FXML private TableColumn<SinhVien,String> colEmail;
    @FXML private TableColumn<SinhVien,String> colPhone;
    @FXML private TableColumn<SinhVien,String> colNamNhapHoc;
    @FXML private TableColumn<SinhVien, String> colNganh;
    @FXML private TableColumn<SinhVien,String> colGioiTinh;

    @FXML private TextField txtSearch;
    @FXML private TextField txtMaSv;
    @FXML private TextField txtNgaySinh;
    @FXML private TextField txtLop;
    @FXML private TextField txtHoTen;
    @FXML private TextField txtId;
    @FXML private TextField txtDiaChi;
    @FXML private TextField txtKhoa;
    @FXML private TextField txtGioiTinh;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhone;
    @FXML private TextField txtNganh;
    @FXML private TextField txtNamNhapHoc;

    @FXML private Button btnHomeAdmin;

    @FXML private Button btnMh;
    @FXML private Button btnLHP;
    @FXML private Button btnScore;
    @FXML private Button importStudent;
    @FXML private Button btnLogout;
    @FXML private Button btnAccount;
    @FXML private Button btnRegisteredClass;

    private ObservableList<SinhVien> studentList;

    private final AdminStudentService service = new AdminStudentService();
    @FXML
    public void handleLogout() {
        AppSession.clear();
        SceneUtil.switchScene(btnLogout, "/fxml/Login.fxml");
    }
    @FXML
    public void initialize() {
        setUpTableStudent();
        loadData();

        txtSearch.textProperty().addListener((obs, oldV, newV) -> {
            search(newV);
        });

        tableStudent.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, sv) -> {

                    if (sv != null) {
                       setSinhVien(sv);
                    }
                });

        System.out.println("INIT OK");

    }
    private void setUpTableStudent() {
        colMaSv.setCellValueFactory(
                new PropertyValueFactory<>("maSv"));
        colHoTen.setCellValueFactory(
                new PropertyValueFactory<>("hoTen"));
        colLop.setCellValueFactory(
                new PropertyValueFactory<>("lop"));
        colGioiTinh.setCellValueFactory(
                new PropertyValueFactory<>("kieuGioiTinh"));
        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("email"));
        colNgaySinh.setCellValueFactory(
                new PropertyValueFactory<>("ngaySinh"));
        colPhone.setCellValueFactory(
                new PropertyValueFactory<>("soDienThoai"));
        colNganh.setCellValueFactory(
                new PropertyValueFactory<>("nganh"));
        colId.setCellValueFactory(
                new PropertyValueFactory<>("cccd"));
        colDiaChi.setCellValueFactory(
                new PropertyValueFactory<>("diaChi"));
        colKhoa.setCellValueFactory(
                new PropertyValueFactory<>("khoa"));
        colNamNhapHoc.setCellValueFactory(
                new PropertyValueFactory<>("namNhapHoc"));
    }
    private void loadData() {
        try {
            List<SinhVien> list = service.getAll();

            if (list == null) list = List.of();

            studentList = FXCollections.observableArrayList(list);
            tableStudent.setItems(studentList);

        } catch (Exception e) {
            e.printStackTrace();

            studentList = FXCollections.observableArrayList();
            tableStudent.setItems(studentList);
        }
    }


    @FXML
    private void addStudent() {

        SinhVien sv = getSinhVien();

        if(service.add(sv)) {

            loadData();
            refreshForm();

            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION);

            alert.setHeaderText(null);
            alert.setContentText("Thêm sinh viên thành công!");
            alert.showAndWait();
        }
        else {

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText(null);
            alert.setContentText("Thêm thất bại!");
            alert.showAndWait();
        }
    }

    @FXML
    private void editStudent() {

        SinhVien sv =
                tableStudent.getSelectionModel()
                        .getSelectedItem();

        if (sv == null)
            return;
        sv = getSinhVien();
        if(service.update(sv)) {
            loadData();
            refreshForm();

            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION);

            alert.setHeaderText(null);
            alert.setContentText("Cập nhật thành công!");
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteStudent() {

        SinhVien sv =
                tableStudent.getSelectionModel()
                        .getSelectedItem();

        if (sv == null) {
            return;
        }

        Alert alert =
                new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Xóa");
        alert.setHeaderText(null);
        alert.setContentText(
                "Bạn có chắc muốn xóa sinh viên "
                        + sv.getMaSv() + " ?"
        );

        if (alert.showAndWait().get() == ButtonType.OK) {

            if (service.delete(sv.getMaSv())) {

                loadData();
                refreshForm();

                Alert success =
                        new Alert(Alert.AlertType.INFORMATION);

                success.setHeaderText(null);
                success.setContentText("Xóa thành công!");
                success.showAndWait();
            }
        }
        System.out.println("DELETE CLICKED");
        System.out.println("Selected SV = " + sv);
    }

    private void setSinhVien(SinhVien sv) {
        txtMaSv.setText(sv.getMaSv());
        txtHoTen.setText(sv.getHoTen());
        txtLop.setText(sv.getLop());
        txtEmail.setText(sv.getEmail());
        txtPhone.setText(sv.getSoDienThoai());
        txtNganh.setText(sv.getNganh());
        txtDiaChi.setText(sv.getDiaChi());
        txtNamNhapHoc.setText(String.valueOf(sv.getNamNhapHoc()));
        txtId.setText(sv.getCccd());
        txtKhoa.setText(sv.getKhoa());
        txtNgaySinh.setText(sv.getNgaySinh().toString());
        txtGioiTinh.setText(sv.getGioiTinh() ? "Nam" : "Nữ");
    }
    private SinhVien getSinhVien() {
        SinhVien sv = new SinhVien();
        sv.setMaSv(txtMaSv.getText());
        sv.setHoTen(txtHoTen.getText());
        sv.setLop(txtLop.getText());
        sv.setEmail(txtEmail.getText());
        sv.setSoDienThoai(txtPhone.getText());
        sv.setNganh(txtNganh.getText());
        sv.setDiaChi(txtDiaChi.getText());
        sv.setKhoa(txtKhoa.getText());
        sv.setCccd(txtId.getText());
        sv.setNamNhapHoc(Integer.parseInt(txtNamNhapHoc.getText()));
        sv.setNgaySinh(LocalDate.parse(txtNgaySinh.getText()));
        if (txtGioiTinh.getText().equals("nam")||txtGioiTinh.getText().equals("Nam")) {
            sv.setGioiTinh(true);
        }
        else {
            sv.setGioiTinh(false);
        }

        return sv;
    }
    private void search(String keyword) {

        FilteredList<SinhVien> filtered =
                new FilteredList<>(studentList);

        filtered.setPredicate(sv -> {

            if (keyword == null || keyword.isBlank())
                return true;

            String key = keyword.toLowerCase();

            return sv.getHoTen().toLowerCase().contains(key)
                    ||
                    sv.getMaSv().toLowerCase().contains(key);
        });

        tableStudent.setItems(filtered);
    }

    @FXML
    private void refreshForm() {
        clearForm();
        tableStudent.refresh();
        tableStudent.getSelectionModel().clearSelection();
    }

    private void clearForm() {
        txtMaSv.clear();
        txtHoTen.clear();
        txtLop.clear();
        txtEmail.clear();
        txtPhone.clear();
        txtNganh.clear();
        txtNamNhapHoc.clear();
        txtKhoa.clear();
        txtGioiTinh.clear();
        txtNgaySinh.clear();
        txtDiaChi.clear();
        txtId.clear();
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
    public void showLHP() {
        SceneUtil.switchScene(btnLHP, "/fxml/Admin/ClassManagement.fxml");
    }

    @FXML public void showAccount() {SceneUtil.switchScene(btnAccount, "/fxml/Admin/AdminAcount.fxml");}

    @FXML
    public void showScore() {
        SceneUtil.switchScene(btnScore, "/fxml/Admin/ScoreManagement.fxml");
    }
    @FXML
    private void handleImportStudent() {
        ExcelImportUtil.handleImportExcel(
                importStudent,
                ApiEndpoint.ADMIN_STUDENT_IMPORT,
                "danhSachSinhVien",
                this::loadData
        );
    }

    @FXML public void showRegisteredClass(){ SceneUtil.switchScene(btnRegisteredClass,"/fxml/Admin/RegisteredClass.fxml");}
}
