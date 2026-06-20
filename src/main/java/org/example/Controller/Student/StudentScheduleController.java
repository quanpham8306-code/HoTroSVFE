package org.example.Controller.Student;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.Config.AppSession;
import org.example.Model.LopHocPhan;
import org.example.Model.ThoiKhoaBieu;
import org.example.Service.ThoiKhoaBieuService;
import org.example.Util.SceneUtil;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StudentScheduleController {
    @FXML private Button btnScore;
    @FXML private Button btnLogout;
    @FXML private Button btnHome;
    @FXML private Button btnSubject;
    @FXML private Button btnNote;

    @FXML private Label lblThu2;
    @FXML private Label lblThu3;
    @FXML private Label lblThu4;
    @FXML private Label lblThu5;
    @FXML private Label lblThu6;
    @FXML private Label lblThu7;
    @FXML private Label lblCN;

    @FXML private GridPane scheduleGrid;
    @FXML private DatePicker weekPicker;


    private LocalDate currentWeekStart;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final ThoiKhoaBieuService thoiBieuService = new ThoiKhoaBieuService();

    @FXML
    public void initialize() {
        currentWeekStart = getStartOfWeek(LocalDate.now());

        weekPicker.setValue(LocalDate.now());

        setupDatePicker();

        loadWeek(currentWeekStart);
    }
    @FXML
    public void showScore() {
        SceneUtil.switchScene(btnScore, "/fxml/Score.fxml");
    }

    @FXML
    public void showHome() {
        SceneUtil.switchScene(btnHome, "/fxml/Home.fxml");
    }
    @FXML
    public void showSubject() {
        SceneUtil.switchScene(btnSubject, "/fxml/VirtualSchedule.fxml");
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
    @FXML
    private void goToCurrentWeek() {
        LocalDate today = LocalDate.now();

        currentWeekStart = getStartOfWeek(today);
        weekPicker.setValue(today);
        clearOldSchedule();

        loadWeek(currentWeekStart);
    }
    @FXML
    private void previousWeek() {
        currentWeekStart = currentWeekStart.minusWeeks(1);
        weekPicker.setValue(currentWeekStart);
        clearOldSchedule();

        loadWeek(currentWeekStart);
    }

    @FXML
    private void nextWeek() {
        currentWeekStart = currentWeekStart.plusWeeks(1);
        weekPicker.setValue(currentWeekStart);
        clearOldSchedule();

        loadWeek(currentWeekStart);
    }
    @FXML
    private void exportSchedule(){
        thoiBieuService.exportSchedule();
    }
    private void loadWeek(LocalDate date) {
        LocalDate fdate = getStartOfWeek(date);

        updateWeekHeader(fdate);

        ThoiKhoaBieu tkb = thoiBieuService.getScheduleByWeek(fdate);

        loadScheduleToGrid(tkb);
    }
    private void updateWeekHeader(LocalDate weekStart) {
        lblThu2.setText("Thứ 2\n" + weekStart.format(formatter));
        lblThu3.setText("Thứ 3\n" + weekStart.plusDays(1).format(formatter));
        lblThu4.setText("Thứ 4\n" + weekStart.plusDays(2).format(formatter));
        lblThu5.setText("Thứ 5\n" + weekStart.plusDays(3).format(formatter));
        lblThu6.setText("Thứ 6\n" + weekStart.plusDays(4).format(formatter));
        lblThu7.setText("Thứ 7\n" + weekStart.plusDays(5).format(formatter));
        lblCN.setText("Chủ nhật\n" + weekStart.plusDays(6).format(formatter));
    }
    private void loadScheduleToGrid(ThoiKhoaBieu thoiKhoaBieu) {
        clearOldSchedule();

        for (LopHocPhan lich : thoiKhoaBieu.getLopHocPhanList()) {
            int col = getColumnByThu(lich.getThu());
            int row = getRowByTime(lich.getGioBatDau());

            VBox card = createScheduleCard(lich);

            scheduleGrid.add(card, col, row);
        }
    }
    private void clearOldSchedule() {
        scheduleGrid.getChildren().removeIf(node ->
                "schedule-card".equals(node.getUserData())
        );
    }
    private int getRowByTime(LocalTime gioBatDau) {
        if(gioBatDau.getHour() < 9){
            return 1;
        }
        else if (gioBatDau.getHour() < 12) {
            return 2;
        }
        else if (gioBatDau.getHour() < 14) {
            return 3;
        }
        else {
            return 4;
        }
    }
    private int getColumnByThu(int thu) {
        return switch (thu) {
            case 2 -> 1;
            case 3 -> 2;
            case 4 -> 3;
            case 5 -> 4;
            case 6 -> 5;
            case 7 -> 6;
            case 8 -> 7;
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
        card.setMaxHeight(Double.MAX_VALUE);

        GridPane.setFillWidth(card, true);
        GridPane.setFillHeight(card, true);

        return card;
    }
    private LocalDate getStartOfWeek(LocalDate date) {
        return date.with(DayOfWeek.MONDAY);
    }
    private void setupDatePicker() {
        weekPicker.valueProperty().addListener((obs, oldDate, newDate) -> {
            if (newDate == null) return;

            currentWeekStart = getStartOfWeek(newDate);
            loadWeek(currentWeekStart);
        });
    }

}
