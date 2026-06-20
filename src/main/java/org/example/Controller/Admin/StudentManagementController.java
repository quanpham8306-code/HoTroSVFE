package org.example.Controller.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Model.SinhVien;
import org.example.Service.Admin.AdminStudentService;
import org.example.Util.SceneUtil;

import java.util.List;

public class StudentManagementController {

    @FXML private TableView<SinhVien> tableStudent;
    @FXML private TableColumn<SinhVien,String> colMaSv;
    @FXML private TableColumn<SinhVien,String> colHoTen;
    @FXML private TableColumn<SinhVien,String> colLop;
    @FXML private TableColumn<SinhVien,String> colEmail;
    @FXML private TableColumn<SinhVien,String> colPhone;
    @FXML private TableColumn<SinhVien, String> colNganh;

    @FXML private TextField txtSearch;
    @FXML private TextField txtMaSv;
    @FXML private TextField txtHoTen;
    @FXML private TextField txtLop;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhone;
    @FXML private TextField txtNganh;
    @FXML private TextField txtNamNhapHoc;
    @FXML private Button btnHomeAdmin;

    @FXML private Button btnMh;
    @FXML private Button btnLHP;
    @FXML private Button btnScore;

    private ObservableList<SinhVien> studentList;

    private final AdminStudentService service = new AdminStudentService();

    @FXML
    public void initialize() {

        colMaSv.setCellValueFactory(
                new PropertyValueFactory<>("maSv"));

        colHoTen.setCellValueFactory(
                new PropertyValueFactory<>("hoTen"));

        colLop.setCellValueFactory(
                new PropertyValueFactory<>("lop"));

        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("email"));

        colPhone.setCellValueFactory(
                new PropertyValueFactory<>("soDienThoai"));

        colNganh.setCellValueFactory(
                new PropertyValueFactory<>("nganh")
        );

        loadData();

        txtSearch.textProperty().addListener((obs, oldV, newV) -> {
            search(newV);
        });

        tableStudent.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, sv) -> {

                    if (sv != null) {
                        txtMaSv.setText(sv.getMaSv());
                        txtHoTen.setText(sv.getHoTen());
                        txtLop.setText(sv.getLop());
                        txtEmail.setText(sv.getEmail());
                        txtPhone.setText(sv.getSoDienThoai());
                        txtNganh.setText(sv.getNganh());
                        txtNamNhapHoc.setText(
                                String.valueOf(sv.getNamNhapHoc())
                        );
                    }
                });

    }

    private void loadData() {

        try {
            List<SinhVien> list = service.getAll();

            if (list == null) {
                list = List.of();
            }
            studentList =
                    FXCollections.observableArrayList(list);
            tableStudent.setItems(studentList);

        } catch (Exception e) {

            e.printStackTrace();
            studentList =
                    FXCollections.observableArrayList();

            tableStudent.setItems(studentList);
        }
    }

    @FXML
    private void addStudent() {

        SinhVien sv = new SinhVien();

        sv.setMaSv(txtMaSv.getText());
        sv.setHoTen(txtHoTen.getText());
        sv.setLop(txtLop.getText());
        sv.setEmail(txtEmail.getText());
        sv.setSoDienThoai(txtPhone.getText());
        sv.setNganh(txtNganh.getText());
        sv.setNamNhapHoc(
                Integer.parseInt(
                        txtNamNhapHoc.getText()
                )
        );

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

        sv.setMaSv(txtMaSv.getText());
        sv.setHoTen(txtHoTen.getText());
        sv.setLop(txtLop.getText());
        sv.setEmail(txtEmail.getText());
        sv.setSoDienThoai(txtPhone.getText());
        sv.setNganh(txtNganh.getText());
        sv.setNamNhapHoc(
                Integer.parseInt(txtNamNhapHoc.getText())
        );

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

    @FXML
    public void showScore() {
        SceneUtil.switchScene(btnScore, "/fxml/Admin/ScoreManagement.fxml");
    }

}
