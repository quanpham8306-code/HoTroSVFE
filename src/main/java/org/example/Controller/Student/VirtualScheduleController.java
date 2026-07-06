package org.example.Controller.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.Config.AppSession;
import org.example.Model.*;
import org.example.Service.Student.LichAoService;
import org.example.Service.Student.LopHocPhanService;
import org.example.Service.Student.MonHocService;
import org.example.Service.Student.SinhVienService;
import org.example.Util.AlertUtil;
import org.example.Util.SceneUtil;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class VirtualScheduleController {
    @FXML private Button btnHome;
    @FXML private Button btnScore;
    @FXML private Button btnSchedule;
    @FXML private Button btnNote;
    @FXML private Button btnSupport;
    @FXML private Button btnLogout;

    @FXML private TableView<LopHocPhan> lopHocPhanTable;
    @FXML private TableColumn<LopHocPhan, String> colLop;
    @FXML private TableColumn<LopHocPhan, String> colGiangVien;
    @FXML private TableColumn<LopHocPhan, String> colDiaDiem;
    @FXML private TableColumn<LopHocPhan, String> colThoiGian;
    @FXML private TableColumn<LopHocPhan, String> colNgayHoc;

    @FXML private TableView<LopHocPhan> virtualScheduleTable;
    @FXML private TableColumn<LopHocPhan, String> colLop1;
    @FXML private TableColumn<LopHocPhan, String> colGiangVien1;
    @FXML private TableColumn<LopHocPhan, String> colDiaDiem1;
    @FXML private TableColumn<LopHocPhan, String> colThoiGian1;
    @FXML private TableColumn<LopHocPhan, String> colNgayHoc1;

    @FXML private Label lblThu2;
    @FXML private Label lblThu3;
    @FXML private Label lblThu4;
    @FXML private Label lblThu5;
    @FXML private Label lblThu6;
    @FXML private Label lblThu7;
    @FXML private Label lblCN;


    @FXML private Label lblMonthYear;
    @FXML private Button btnPrevMonth;
    @FXML private Button btnNextMonth;

    @FXML private GridPane scheduleVirtualGrid;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML private ComboBox<String> cbKhoa;
    @FXML private ComboBox<MonHoc> cbMon;

    private final LopHocPhanService lopHocPhanService = new LopHocPhanService();
    private final SinhVienService sinhVienService = new SinhVienService();
    private final MonHocService monHocService = new MonHocService();
    private final LichAoService lichAoService = new LichAoService();

    private LocalDate currentMonthStart;
    private LichAo virtualSchedule;

    @FXML
    public void initialize() {
        virtualSchedule  = new LichAo();
        loadComboKhoa();
        setupTable();
        setupVirtualTable();
        getLichAo();
        setupMonthButtons();
        loadMonth(currentMonthStart);

        cbKhoa.setOnAction(event -> {
            loadComboMon();
        });
        cbMon.setOnAction(event -> {
            lopHocPhanTable.getItems().clear();
            loadClassTable();
        });
        lopHocPhanTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {

            if (newValue == null) return;
            handleThemLop(newValue);
        });
    }
    @FXML
    public void showScore() throws IOException {
        SceneUtil.switchScene(btnScore, "/fxml/Student/Score.fxml");
    }

    @FXML
    public void showSchedule() throws IOException {
        SceneUtil.switchScene(btnSchedule, "/fxml/Student/Schedule.fxml");
    }
    @FXML
    public void showSupport() throws IOException {
        SceneUtil.switchScene(btnSupport, "/fxml/Student/Support.fxml");
    }

    @FXML
    public void showHome() throws IOException {
        SceneUtil.switchScene(btnHome, "/fxml/Student/Home.fxml");
    }
    @FXML
    public void showNote() throws IOException {
        SceneUtil.switchScene(btnNote, "/fxml/Student/Note.fxml");
    }

    @FXML
    public void handleLogout() {
        AppSession.clear();
        SceneUtil.switchToLogin(btnLogout);
    }
    @FXML
    private void saveVirtualSchedule(){
        String respose = lichAoService.save(virtualSchedule);
        if(respose.equals("SUCCESS"))
            AlertUtil.showAlert("Lưu lịch thành công.");
        else
            AlertUtil.showAlert(respose);
    }
    private void setupTable() {
        colLop.setCellValueFactory(new PropertyValueFactory<>("maLopHP"));
        colDiaDiem.setCellValueFactory(new PropertyValueFactory<>("phongHoc"));
        colGiangVien.setCellValueFactory(new PropertyValueFactory<>("giangVien"));
        colNgayHoc.setCellValueFactory(new PropertyValueFactory<>("khoangNgayHoc"));
        colThoiGian.setCellValueFactory(new PropertyValueFactory<>("khoangThoiGian"));
    }
    private void setupVirtualTable() {
        colLop1.setCellValueFactory(new PropertyValueFactory<>("maLopHP"));
        colDiaDiem1.setCellValueFactory(new PropertyValueFactory<>("phongHoc"));
        colGiangVien1.setCellValueFactory(new PropertyValueFactory<>("giangVien"));
        colNgayHoc1.setCellValueFactory(new PropertyValueFactory<>("khoangNgayHoc"));
        colThoiGian1.setCellValueFactory(new PropertyValueFactory<>("khoangThoiGian"));
    }
    private void loadVirtualClassTable()
    {
        virtualScheduleTable.getItems().clear();
        ObservableList<LopHocPhan> lopHocPhanObservableList = FXCollections.observableArrayList();
        lopHocPhanObservableList.addAll(virtualSchedule.getLopHocPhanDTOList());
        virtualScheduleTable.setItems(lopHocPhanObservableList);
    }
    private void loadClassTable() {
        ObservableList<LopHocPhan> lopHocPhanObservableList = FXCollections.observableArrayList();
        List<LopHocPhan> lopHocPhanList = lopHocPhanService.getPickedClass(getKhoaSelected(),getMonKhoaSelected());
        lopHocPhanObservableList.addAll(lopHocPhanList);
        lopHocPhanTable.setItems(lopHocPhanObservableList);
    }
    private void loadComboKhoa() {

        try {
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
        } catch (Exception e) {
            cbKhoa.setItems(null);
            cbMon.setItems(null);
        }
    }
    private String getKhoaSelected() {
        return cbKhoa.getValue();
    }
    private void loadComboMon() {
        try {
            cbMon.getItems().clear();
            List<MonHoc> monHocList = monHocService.getMonHocByKhoaAndNganh(getKhoaSelected());
            cbMon.setItems(FXCollections.observableArrayList(monHocList));
        } catch (Exception e) {
            cbMon.setItems(null);
        }
    }
    private String getMonKhoaSelected() {
        return cbMon.getValue().getMaMon();
    }
    private void handleThemLop(LopHocPhan lopMoi) {

        if (lopMoi == null) {
            AlertUtil.showAlert("Vui lòng chọn lớp học phần");
            return;
        }

        LopHocPhan lopBiTrung = lichAoService.timLopBiTrung(virtualSchedule.getLopHocPhanDTOList(), lopMoi);

        if (lopBiTrung != null) {
            AlertUtil.showAlert("Lớp " + lopMoi.getMaLopHP()
                    + " bị trùng lịch với lớp " + lopBiTrung.getMaLopHP());
            return;
        }

        virtualSchedule.getLopHocPhanDTOList().add(lopMoi);
        loadVirtualClassTable();
        AlertUtil.showAlert("Thêm lớp thành công");
    }
    private void getLichAo(){
        if(lichAoService.getLichAo() != null)
        {
            virtualSchedule = lichAoService.getLichAo();
            loadVirtualClassTable();
        }
    }
    private void setupMonthButtons() {
        btnPrevMonth.setOnAction(e -> {
            currentMonthStart = currentMonthStart.minusMonths(1);
            loadMonth(currentMonthStart);
        });

        btnNextMonth.setOnAction(e -> {
            currentMonthStart = currentMonthStart.plusMonths(1);
            loadMonth(currentMonthStart);
        });
    }

    private void loadMonth(LocalDate date) {
        currentMonthStart = getStartOfMonth(date);

        updateMonthHeader(currentMonthStart);
        updateDayHeader();
        loadScheduleToGrid(virtualSchedule);
    }

    private void updateMonthHeader(LocalDate monthStart) {
        lblMonthYear.setText(
                "Tháng " + monthStart.getMonthValue() + " năm " + monthStart.getYear()
        );
    }

    private void updateDayHeader() {
        lblThu2.setText("Thứ 2");
        lblThu3.setText("Thứ 3");
        lblThu4.setText("Thứ 4");
        lblThu5.setText("Thứ 5");
        lblThu6.setText("Thứ 6");
        lblThu7.setText("Thứ 7");
        lblCN.setText("Chủ nhật");
    }

    private void loadScheduleToGrid(LichAo lichAo) {
        clearOldSchedule();

        if (lichAo == null || lichAo.getLopHocPhanDTOList() == null) {
            return;
        }

        int[] rowByColumn = new int[8];

        for (LopHocPhan lich : lichAo.getLopHocPhanDTOList()) {
            int col = getColumnByThu(lich.getThu());

            if (col == -1) {
                continue;
            }

            int row = rowByColumn[col];
            rowByColumn[col]++;

            VBox card = createScheduleCard(lich);
            card.setMaxWidth(Double.MAX_VALUE);
            GridPane.setFillWidth(card, true);
            GridPane.setFillHeight(card, false);

            scheduleVirtualGrid.add(card, col, row);
        }
    }

    private void clearOldSchedule() {
        scheduleVirtualGrid.getChildren().removeIf(node ->
                "schedule-card".equals(node.getUserData())
        );
    }

    private int getColumnByThu(int thu) {
        return switch (thu) {
            case 2 -> 0;
            case 3 -> 1;
            case 4 -> 2;
            case 5 -> 3;
            case 6 -> 4;
            case 7 -> 5;
            case 8 -> 6;
            default -> -1;
        };
    }

    private VBox createScheduleCard(LopHocPhan lich) {
        Label tenMon = new Label(lich.getTenMonHoc());
        tenMon.getStyleClass().add("schedule-subject");

        Label maLop = new Label("Lớp: " + lich.getMaLopHP());
        maLop.getStyleClass().add("schedule-code");

        Label phong = new Label("Phòng: " + lich.getPhongHoc());
        phong.getStyleClass().add("schedule-info");

        Label gio = new Label(lich.getGioBatDau() + " - " + lich.getGioKetThuc());
        gio.getStyleClass().add("schedule-time-cell");

        Label gv = new Label("GV: " + lich.getGiangVien());
        gv.getStyleClass().add("schedule-info");

        VBox card = new VBox(5);
        card.getChildren().addAll(tenMon, maLop, phong, gio, gv);

        card.getStyleClass().add("schedule-class-card");
        card.setUserData("schedule-card");

        card.setMaxWidth(Double.MAX_VALUE);
        GridPane.setFillWidth(card, true);

        return card;
    }
    private LocalDate getStartOfMonth(LocalDate date) {
        return date.withDayOfMonth(1);
    }
}
