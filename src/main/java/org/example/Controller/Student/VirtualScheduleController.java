package org.example.Controller.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.example.Config.AppSession;
import org.example.Model.*;
import org.example.Service.Student.LichAoService;
import org.example.Service.Student.LopHocPhanService;
import org.example.Service.Student.MonHocService;
import org.example.Service.Student.SinhVienService;
import org.example.Util.AlertUtil;
import org.example.Util.ApiEndpoint;
import org.example.Util.ExcelUtil;
import org.example.Util.SceneUtil;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class VirtualScheduleController {
    @FXML
    private Button btnHome;
    @FXML
    private Button btnScore;
    @FXML
    private Button btnSchedule;
    @FXML
    private Button btnNote;
    @FXML
    private Button btnSupport;
    @FXML
    private Button btnLogout;

    @FXML
    private TableView<LopHocPhan> lopHocPhanTable;
    @FXML
    private TableColumn<LopHocPhan, String> colLop;
    @FXML
    private TableColumn<LopHocPhan, String> colGiangVien;
    @FXML
    private TableColumn<LopHocPhan, String> colDiaDiem;
    @FXML
    private TableColumn<LopHocPhan, String> colThoiGian;
    @FXML
    private TableColumn<LopHocPhan, String> colNgayHoc;

    @FXML
    private TableView<LopHocPhan> virtualScheduleTable;
    @FXML
    private TableColumn<LopHocPhan, String> colLop1;
    @FXML
    private TableColumn<LopHocPhan, String> colGiangVien1;
    @FXML
    private TableColumn<LopHocPhan, String> colDiaDiem1;
    @FXML
    private TableColumn<LopHocPhan, String> colThoiGian1;
    @FXML
    private TableColumn<LopHocPhan, String> colNgayHoc1;

    @FXML
    private Label lblMonthYear;
    @FXML
    private Button btnPrevMonth;
    @FXML
    private Button btnNextMonth;

    @FXML
    private GridPane scheduleVirtualGrid;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    private ComboBox<String> cbKhoa;
    @FXML
    private ComboBox<MonHoc> cbMon;
    @FXML
    private Button btnImport;
    @FXML
    private Button btnExport;

    private final LopHocPhanService lopHocPhanService = new LopHocPhanService();
    private final SinhVienService sinhVienService = new SinhVienService();
    private final MonHocService monHocService = new MonHocService();
    private final LichAoService lichAoService = new LichAoService();

    private LocalDate currentMonthStart = LocalDate.now();
    private LichAo virtualSchedule;
    private LopHocPhan selectedLop;

    @FXML
    public void initialize() {
        selectedLop = null;
        virtualSchedule = new LichAo();
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

        virtualScheduleTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> {
                    selectedLop = newValue;
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
    private void saveVirtualSchedule() {
        String respose = lichAoService.save(virtualSchedule);
        if (respose.equals("SUCCESS"))
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

    private void loadVirtualClassTable() {
        virtualScheduleTable.getItems().clear();
        ObservableList<LopHocPhan> lopHocPhanObservableList = FXCollections.observableArrayList();
        lopHocPhanObservableList.addAll(virtualSchedule.getLopHocPhanDTOList());
        virtualScheduleTable.setItems(lopHocPhanObservableList);
    }

    private void loadClassTable() {
        ObservableList<LopHocPhan> lopHocPhanObservableList = FXCollections.observableArrayList();
        List<LopHocPhan> lopHocPhanList = lopHocPhanService.getPickedClass(getKhoaSelected(), getMonKhoaSelected());
        lopHocPhanObservableList.addAll(lopHocPhanList);
        lopHocPhanTable.setItems(lopHocPhanObservableList);
    }

    private void loadComboKhoa() {

        try {
            cbKhoa.getItems().clear();
            int namNhapHoc = sinhVienService.getKhoa();
            int namHienTai;
            if (LocalDate.now().getMonth().getValue() < 9)
                namHienTai = LocalDate.now().getYear() - 1;
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
        loadMonth(currentMonthStart);
        AlertUtil.showAlert("Thêm lớp thành công");
    }

    private void getLichAo() {
        if (lichAoService.getLichAo() != null) {
            virtualSchedule = lichAoService.getLichAo();
            loadVirtualClassTable();
            loadMonth(currentMonthStart);
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

    private VBox createDayBox(LocalDate date, int currentMonth) {
        VBox box = new VBox(5);
        box.getStyleClass().add("calendar-day-box");
        box.setMinHeight(120);
        box.setPrefHeight(160);
        box.setMaxHeight(Double.MAX_VALUE);

        if (date.getMonthValue() != currentMonth) {
            box.getStyleClass().add("calendar-day-other-month");
        }

        Label dayLabel = new Label(String.valueOf(date.getDayOfMonth()));
        dayLabel.getStyleClass().add("calendar-day-number");

        if (date.getMonthValue() != currentMonth) {
            dayLabel.getStyleClass().add("calendar-day-other-number");
        }

        VBox cardBox = new VBox(5);
        cardBox.setFillWidth(true);

        if (virtualSchedule != null && virtualSchedule.getLopHocPhanDTOList() != null) {
            for (LopHocPhan lop : virtualSchedule.getLopHocPhanDTOList()) {
                if (isClassOnDate(lop, date)) {
                    cardBox.getChildren().add(createScheduleCard(lop));
                }
            }
        }

        ScrollPane scrollPane = new ScrollPane(cardBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);
        scrollPane.setMinHeight(0);
        scrollPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
        scrollPane.setMaxHeight(Double.MAX_VALUE);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.getStyleClass().add("calendar-day-scroll");

        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        box.getChildren().addAll(dayLabel, scrollPane);

        return box;
    }

    private boolean isClassOnDate(LopHocPhan lop, LocalDate date) {
        if (lop.getNgayBatDau() == null || lop.getNgayKetThuc() == null) {
            return false;
        }

        if (date.isBefore(lop.getNgayBatDau()) || date.isAfter(lop.getNgayKetThuc())) {
            return false;
        }

        int thu = date.getDayOfWeek().getValue() + 1;
        if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            thu = 8;
        }

        return lop.getThu() == thu;
    }

    private VBox createScheduleCard(LopHocPhan lich) {
        Label tenMon = new Label(lich.getTenMonHoc());
        tenMon.getStyleClass().add("schedule-subject");

        Label maLop = new Label("Lớp: " + lich.getMaLopHP());
        maLop.getStyleClass().add("schedule-code");

        Label gio = new Label(lich.getGioBatDau() + " - " + lich.getGioKetThuc());
        gio.getStyleClass().add("schedule-time-cell");

        VBox card = new VBox(5);
        card.getChildren().addAll(tenMon, maLop, gio);

        card.setMaxWidth(Double.MAX_VALUE);
        GridPane.setFillWidth(card, true);

        return card;
    }

    private void loadMonth(LocalDate date) {
        scheduleVirtualGrid.getChildren().clear();

        LocalDate firstDay = date.withDayOfMonth(1);

        // Bắt đầu từ thứ 2 của tuần chứa ngày đầu tháng
        LocalDate startDate = firstDay.minusDays(firstDay.getDayOfWeek().getValue() - 1);

        lblMonthYear.setText(
                "Tháng " + firstDay.getMonthValue() + " năm " + firstDay.getYear()
        );

        String[] headers = {
                "Thứ 2", "Thứ 3", "Thứ 4",
                "Thứ 5", "Thứ 6", "Thứ 7", "Chủ nhật"
        };

        for (int i = 0; i < headers.length; i++) {
            Label label = new Label(headers[i]);
            label.getStyleClass().add("calendar-week-header");

            label.setMaxWidth(Double.MAX_VALUE);
            label.setAlignment(Pos.CENTER);

            scheduleVirtualGrid.add(label, i, 0);
        }

        for (int i = 0; i < 42; i++) {
            LocalDate currentDate = startDate.plusDays(i);

            int col = i % 7;
            int row = i / 7 + 1;

            VBox dayBox = createDayBox(currentDate, firstDay.getMonthValue());

            scheduleVirtualGrid.add(dayBox, col, row);
        }
    }

    @FXML public void clearSchedule(){
        String response = lichAoService.deleteLichAo(virtualSchedule);

        if (response.equals("SUCCESS")) {
            virtualSchedule.getLopHocPhanDTOList().clear();
            loadMonth(LocalDate.now());
            reloadVirtualData();
            AlertUtil.showAlert("Xóa lịch ảo thành công.");
        }
        else
            AlertUtil.showError(response);
    }

    @FXML public void clear1Class(){
        virtualSchedule.getLopHocPhanDTOList().remove(selectedLop);
        loadMonth(LocalDate.now());
        reloadVirtualData();
        AlertUtil.showAlert("Xóa lớp thành công.");
    }

    @FXML public void importExcel(){
        ExcelUtil.handleImportExcel(btnImport,
                ApiEndpoint.STUDENT_VIRTUAL_SCHEDULE_IMPORT,
                "lichAo",
                this::getLichAo
                );
        reloadVirtualData();
    }

    @FXML public void exportExcel(){
        ExcelUtil.handleExport(btnExport.getScene().getWindow(),
                ApiEndpoint.STUDENT_VIRTUAL_SCHEDULE_EXPORT,
                "Lich_ao.xlsx");
    }
    private void reloadVirtualData(){
        loadVirtualClassTable();
        if (cbMon.getValue() == null) {
            cbMon.getItems().clear();
            return;
        }

        loadClassTable();

    }
}
