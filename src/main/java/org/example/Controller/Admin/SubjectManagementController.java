package org.example.Controller.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Config.AppSession;
import org.example.Model.MonHoc;
import org.example.Service.Admin.AdminSubjectService;
import org.example.Util.SceneUtil;

public class SubjectManagementController {
    @FXML private TableView<MonHoc> tableSubject;
    @FXML private TableColumn<MonHoc,String> colMaMon;
    @FXML private TableColumn<MonHoc,String> colTenMon;
    @FXML private TableColumn<MonHoc,Integer> colSoTinChi;

    @FXML private TextField txtMaMon;
    @FXML private TextField txtTenMon;
    @FXML private TextField txtSoTinChi;
    @FXML private TextField txtSearch;

    @FXML private Button btnHomeAdmin;
    @FXML private Button btnSV;
    @FXML private Button btnLHP;
    @FXML private Button btnScore;
    @FXML private Button btnLogout;

    private ObservableList<MonHoc> subjectList;

    private final AdminSubjectService service =
            new AdminSubjectService();
    @FXML
    public void handleLogout() {
        AppSession.clear();
        SceneUtil.switchScene(btnLogout, "/fxml/Login.fxml");
    }

    @FXML
    public void initialize() {

        colMaMon.setCellValueFactory(
                new PropertyValueFactory<>("maMon"));

        colTenMon.setCellValueFactory(
                new PropertyValueFactory<>("tenMonHoc"));

        colSoTinChi.setCellValueFactory(
                new PropertyValueFactory<>("soTinChi"));

        loadData();

        txtSearch.textProperty()
                .addListener((obs, oldV, newV) ->
                        search(newV));

        tableSubject.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldV, mh) -> {

                    if (mh != null) {

                        txtMaMon.setText(
                                mh.getMaMon());

                        txtTenMon.setText(
                                mh.getTenMonHoc());

                        txtSoTinChi.setText(
                                String.valueOf(
                                        mh.getSoTinChi()));
                    }
                });
    }

    private void loadData() {

        subjectList =
                FXCollections.observableArrayList(
                        service.getAll()
                );

        tableSubject.setItems(subjectList);
    }

    @FXML
    private void addSubject() {

        MonHoc mh = new MonHoc();

        mh.setMaMon(txtMaMon.getText());
        mh.setTenMonHoc(txtTenMon.getText());
        mh.setSoTinChi(
                Integer.parseInt(
                        txtSoTinChi.getText()));

        if(service.add(mh)) {

            loadData();
            refreshForm();
        }
    }

    @FXML
    private void editSubject() {

        MonHoc mh =
                tableSubject.getSelectionModel()
                        .getSelectedItem();

        if(mh == null)
            return;

        mh.setTenMonHoc(
                txtTenMon.getText());

        mh.setSoTinChi(
                Integer.parseInt(
                        txtSoTinChi.getText()));

        if(service.update(mh)) {

            loadData();
            refreshForm();
        }
    }

    @FXML
    private void deleteSubject() {

        MonHoc mh =
                tableSubject.getSelectionModel()
                        .getSelectedItem();

        if(mh == null)
            return;

        if(service.delete(
                mh.getMaMon())) {

            loadData();
            refreshForm();
        }
    }

    private void search(String keyword) {

        FilteredList<MonHoc> filtered =
                new FilteredList<>(subjectList);

        filtered.setPredicate(mh -> {

            if(keyword == null
                    || keyword.isBlank())
                return true;

            String key =
                    keyword.toLowerCase();

            return mh.getMaMon()
                    .toLowerCase()
                    .contains(key)

                    ||

                    mh.getTenMonHoc()
                            .toLowerCase()
                            .contains(key);
        });

        tableSubject.setItems(filtered);
    }

    @FXML
    private void refreshForm() {

        txtMaMon.clear();
        txtTenMon.clear();
        txtSoTinChi.clear();

        tableSubject.getSelectionModel()
                .clearSelection();
    }

    @FXML
    public void showHomeAd() {
        SceneUtil.switchScene(btnHomeAdmin, "/fxml/Admin/AdminHome.fxml");
    }

    @FXML
    public void showSV() {
        SceneUtil.switchScene(btnSV, "/fxml/Admin/StudentManagement.fxml");
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
