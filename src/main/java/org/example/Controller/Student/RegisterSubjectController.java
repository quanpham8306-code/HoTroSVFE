package org.example.Controller.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Config.AppSession;
import org.example.Model.LopHocPhan;
import org.example.Model.MonHoc;
import org.example.Service.LopHocPhanService;
import org.example.Service.MonHocService;
import org.example.Service.SinhVienService;
import org.example.Util.SceneUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RegisterSubjectController {
    @FXML private Button btnHome;
    @FXML private Button btnScore;
    @FXML private Button btnSchedule;
    @FXML private Button btnNote;
    @FXML private Button btnLogout;

    @FXML private TableView<LopHocPhan> lopHocPhanTable;
    @FXML private TableColumn<LopHocPhan, String> colLop;
    @FXML private TableColumn<LopHocPhan, String> colGiangVien;
    @FXML private TableColumn<LopHocPhan, String> colDiaDiem;
    @FXML private TableColumn<LopHocPhan, String> colThoiGian;
    @FXML private TableColumn<LopHocPhan, String> colNgayHoc;

    @FXML private ComboBox<String> cbKhoa;
    @FXML private ComboBox<String> cbMon;

    private final LopHocPhanService lopHocPhanService = new LopHocPhanService();
    private final SinhVienService sinhVienService = new SinhVienService();
    private final MonHocService monHocService = new MonHocService();


    @FXML
    public void initialize() {

        loadComboKhoa();
        cbKhoa.setOnAction(event -> {
            loadComboMon();
        });
        cbMon.setOnAction(event -> {
            setupTable();
            loadListClass();
        });
        lopHocPhanTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {

            if (newValue == null) return;
            xuLySauKhiChonLop(newValue);
        });
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
    public void showNote() {
        SceneUtil.switchScene(btnNote, "/fxml/Note.fxml");
    }

    @FXML
    public void handleLogout() {
        AppSession.clear();
        SceneUtil.switchScene(btnLogout, "/fxml/Login.fxml");
    }

    private void xuLySauKhiChonLop(LopHocPhan newValue)
    {

    }
    private void setupTable() {
        colLop.setCellValueFactory(new PropertyValueFactory<>("maLopHP"));
        colDiaDiem.setCellValueFactory(new PropertyValueFactory<>("phongHoc"));
        colGiangVien.setCellValueFactory(new PropertyValueFactory<>("giangVien"));
        colNgayHoc.setCellValueFactory(new PropertyValueFactory<>("ngayBatDau"+"-"+"ngayKetThuc"));
        colThoiGian.setCellValueFactory(new PropertyValueFactory<>("gioBatDau"+"-"+"gioKetThuc"));
    }
    private void loadListClass() {
        ObservableList<LopHocPhan> lopHocPhanObservableList = FXCollections.observableArrayList();
        List<LopHocPhan> lopHocPhanList = lopHocPhanService.getPickedClass(getKhoaSelected(),getMonKhoaSelected());
        lopHocPhanObservableList.addAll(lopHocPhanList);
        lopHocPhanTable.setItems(lopHocPhanObservableList);
    }
    private void loadComboKhoa() {

        cbKhoa.getItems().clear();
        int namNhapHoc = sinhVienService.getKhoa();
        int namHienTai;
        if(LocalDate.now().getMonth().getValue() < 9)
            namHienTai = LocalDate.now().getYear()-1;
        else
            namHienTai = LocalDate.now().getYear();

        List<String> khoaList = new ArrayList<>();

        for (int nam = namNhapHoc; nam <= namHienTai; nam++) {
            khoaList.add("K" + String.valueOf(nam).substring(2));
        }

        cbKhoa.setItems(FXCollections.observableArrayList(khoaList));
        cbKhoa.getSelectionModel().selectFirst();
    }
    private String getKhoaSelected() {
        return cbKhoa.getValue();
    }
    private void loadComboMon() {
        cbMon.getItems().clear();
        List<MonHoc>monHocList = monHocService.getMonHocByKhoaAndNganh(getKhoaSelected());
        List<String> tenMonHocList = new ArrayList<>();
        for (MonHoc monHoc : monHocList) {
            tenMonHocList.add(monHoc.getTenMonHoc());
        }
        cbMon.setItems(FXCollections.observableArrayList(tenMonHocList));
    }
    private String getMonKhoaSelected() {
        return cbMon.getValue();
    }
}
