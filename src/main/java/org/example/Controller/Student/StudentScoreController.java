package org.example.Controller.Student;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Config.AppSession;
import org.example.Model.BangDiem;

import org.example.Model.HocKy;
import org.example.Model.TongDiem;

import org.example.Service.Student.DiemService;
import org.example.Service.Student.SinhVienService;
import org.example.Util.SceneUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentScoreController {
    @FXML private Label lblGPA;
    @FXML private Label lblTongTinChi;
    @FXML private Label lblSoMon;
    @FXML private Label lblXepLoai;

    @FXML private Label lblA;
    @FXML private Label lblB;
    @FXML private Label lblC;
    @FXML private Label lblD;
    @FXML private Label lblF;
    @FXML private Label lblHocKy;


    @FXML private LineChart<String, Number> gpaChart;
    @FXML private PieChart resultPieChart;

    @FXML private Button btnHome;
    @FXML private Button btnSchedule;
    @FXML private Button btnLogout;
    @FXML private Button btnSubject;
    @FXML private Button btnSupport;
    @FXML private Button btnNote;

    @FXML private TableView<BangDiem> scoreTable;
    @FXML private TableColumn<BangDiem, String> colMaMonHoc;
    @FXML private TableColumn<BangDiem, Integer> colTenMonHoc;
    @FXML private TableColumn<BangDiem, Integer> colSoTinChi;
    @FXML private TableColumn<BangDiem, Integer> colDiemHe10;
    @FXML private TableColumn<BangDiem, String> colDiemChu;
    @FXML private TableColumn<BangDiem, String> colTrangThai;

    @FXML private ComboBox<HocKy> cBoxKyHoc;

    private final DiemService diemService = new DiemService();
    private final SinhVienService sinhVienService = new SinhVienService();
    @FXML
    public void showHome() throws IOException {
        SceneUtil.switchScene(btnHome, "/fxml/Student/Home.fxml");
    }

    @FXML
    public void showSchedule() throws IOException {
        SceneUtil.switchScene(btnSchedule, "/fxml/Student/Schedule.fxml");
    }
    @FXML
    public void showSubject() throws IOException {
        SceneUtil.switchScene(btnSubject, "/fxml/Student/VitualSchedule.fxml");
    }
    @FXML
    public void showNote() throws IOException {
        SceneUtil.switchScene(btnNote, "/fxml/Student/Note.fxml");
    }
    @FXML
    public void showSupport() throws IOException {
        SceneUtil.switchScene(btnSupport, "/fxml/Student/Support.fxml");
    }

    @FXML
    public void handleLogout() {
        AppSession.clear();
        SceneUtil.switchToLogin(btnLogout);
    }
    @FXML
    public void initialize() {
        setupTable();

        cBoxKyHoc.setOnAction(this::chonKyHoc);

        loadHocKyComboBox();

        if (!cBoxKyHoc.getItems().isEmpty()) {
            cBoxKyHoc.getSelectionModel().selectFirst();
        }

        loadAllScoreData();
    }
    private void chonKyHoc(ActionEvent event) {
        HocKy selected = cBoxKyHoc.getSelectionModel().getSelectedItem();

        if (selected == null || selected.getHocKy() == 0) {
            lblHocKy.setText("Bảng điểm toàn bộ các kỳ.");
            loadAllScoreData();
            return;
        }

        int hocKy = selected.getHocKy();
        String namHoc = selected.getNamHoc();

        lblHocKy.setText("Bảng điểm học kỳ " + hocKy + " năm học " + namHoc);
        loadScoreByKy(hocKy, namHoc);
    }
    private void loadScoreByKy(int ky, String nam) {
        try {
            List<BangDiem> scores = diemService.getMyScoresByKy(ky, nam);

            if (scores == null || scores.isEmpty()) {
                resetScoreView();
                return;
            }

            TongDiem summary = diemService.getMySummaryByKy(ky, nam);

            scoreTable.setItems(FXCollections.observableArrayList(scores));

            lblGPA.setText(String.format("%.2f", summary.getGpa()));
            lblTongTinChi.setText(String.valueOf(summary.getTongTin()));
            lblSoMon.setText(String.valueOf(summary.getTongMon()));
            lblXepLoai.setText(
                    summary.getXepLoai() == null ? "Chưa có" : summary.getXepLoai()
            );

            drawLineChart(scores);
            drawPieChart(scores);

        } catch (Exception e) {
            e.printStackTrace();
            resetScoreView();
        }
    }
    private void loadAllScoreData() {
        try {
            List<BangDiem> scores = diemService.getMyScores();

            if (scores == null || scores.isEmpty()) {
                resetScoreView();
                return;
            }

            TongDiem summary = diemService.getMySummary();

            scoreTable.setItems(FXCollections.observableArrayList(scores));

            lblGPA.setText(String.format("%.2f", summary.getGpa()));
            lblTongTinChi.setText(String.valueOf(summary.getTongTin()));
            lblSoMon.setText(String.valueOf(summary.getTongMon()));
            lblXepLoai.setText(
                    summary.getXepLoai() == null ? "Chưa có" : summary.getXepLoai()
            );

            drawLineChart(scores);
            drawPieChart(scores);

        } catch (Exception e) {
            e.printStackTrace();
            resetScoreView();
        }
    }
    private void resetScoreView() {
        scoreTable.setItems(FXCollections.observableArrayList());

        lblGPA.setText("0.00");
        lblTongTinChi.setText("0");
        lblSoMon.setText("0");
        lblXepLoai.setText("Chưa có");

        drawLineChart(List.of());
        drawPieChart(List.of());
    }
    private void loadHocKyComboBox() {
        if (cBoxKyHoc.getItems() == null) {
            cBoxKyHoc.setItems(FXCollections.observableArrayList());
        }
        int namNhapHoc = sinhVienService.getKhoa();

        cBoxKyHoc.getItems().clear();

        LocalDate now = LocalDate.now();
        int namHienTai = now.getYear();
        int thangHienTai = now.getMonthValue();

        for (int namHoc = namNhapHoc; namHoc <= namHienTai; namHoc++) {

            if (namHoc < namHienTai || thangHienTai >= 9) {
                cBoxKyHoc.getItems().add(
                        new HocKy(1,namHoc + "-" + (namHoc + 1))
                );
            }

            int namKy2 = namHoc + 1;

            if (namKy2 < namHienTai ||
                    (namKy2 == namHienTai && thangHienTai >= 3)) {
                cBoxKyHoc.getItems().add(
                        new HocKy(2,namHoc + "-" + (namHoc + 1))
                );
            }
        }

        cBoxKyHoc.getItems().addFirst(null);
        if (!cBoxKyHoc.getItems().isEmpty()) {
            cBoxKyHoc.getSelectionModel().selectLast();
        }
    }
    private void drawLineChart(List<BangDiem> scores) {
        if (gpaChart == null) return;

        gpaChart.getData().clear();
        gpaChart.setMinHeight(250);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Điểm hệ 4 từng môn");

        for (BangDiem score : scores) {
            double diemHe4 = score.getDiemHocPhan() / 10.0 * 4.0;
            String tenMon = score.getTenMon() == null ? score.getMaMon() : score.getTenMon();
            series.getData().add(new XYChart.Data<>(tenMon, diemHe4));
        }

        gpaChart.getData().add(series);
    }

    private void setupTable() {
        colMaMonHoc.setCellValueFactory(new PropertyValueFactory<>("maMon"));
        colTenMonHoc.setCellValueFactory(new PropertyValueFactory<>("tenMon"));
        colSoTinChi.setCellValueFactory(new PropertyValueFactory<>("soTin"));
        colDiemHe10.setCellValueFactory(new PropertyValueFactory<>("diemHocPhan"));
        colDiemChu.setCellValueFactory(new PropertyValueFactory<>("diemChu"));
        colTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
    }
private void drawPieChart(List<BangDiem> scores) {
    if (resultPieChart == null) return;

    Map<String, Integer> gradeCount = countGradeLetters(scores);

    int soDiemA = gradeCount.get("A");
    int soDiemB = gradeCount.get("B");
    int soDiemC = gradeCount.get("C");
    int soDiemD = gradeCount.get("D");
    int soDiemF = gradeCount.get("F");

    ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();

    if (soDiemA > 0) pieData.add(new PieChart.Data("A", soDiemA));
    if (soDiemB > 0) pieData.add(new PieChart.Data("B", soDiemB));
    if (soDiemC > 0) pieData.add(new PieChart.Data("C", soDiemC));
    if (soDiemD > 0) pieData.add(new PieChart.Data("D", soDiemD));
    if (soDiemF > 0) pieData.add(new PieChart.Data("F", soDiemF));

    resultPieChart.setData(pieData);

    Platform.runLater(() -> {
        for (PieChart.Data data : pieData) {
            switch (data.getName()) {
                case "A" ->
                        data.getNode().setStyle("-fx-pie-color: #16A34A;");
                case "B" ->
                        data.getNode().setStyle("-fx-pie-color: #2563EB;");
                case "C" ->
                        data.getNode().setStyle("-fx-pie-color: #D97706;");
                case "D" ->
                        data.getNode().setStyle("-fx-pie-color: #7C3AED;");
                case "F" ->
                        data.getNode().setStyle("-fx-pie-color: #DC2626;");
            }
        }
    });

    lblA.setText(soDiemA + " môn");
    lblB.setText(soDiemB + " môn");
    lblC.setText(soDiemC + " môn");
    lblD.setText(soDiemD + " môn");
    lblF.setText(soDiemF + " môn");
}

private Map<String, Integer> countGradeLetters(List<BangDiem> scores) {
    Map<String, Integer> result = new HashMap<>();

    result.put("A", 0);
    result.put("B", 0);
    result.put("C", 0);
    result.put("D", 0);
    result.put("F", 0);

    for (BangDiem score : scores) {
        String diemChu = score.getDiemChu();

        if (diemChu == null) continue;

        diemChu = diemChu.trim().toUpperCase();

        if (diemChu.startsWith("A")) {
            result.put("A", result.get("A") + 1);
        } else if (diemChu.startsWith("B")) {
            result.put("B", result.get("B") + 1);
        } else if (diemChu.startsWith("C")) {
            result.put("C", result.get("C") + 1);
        } else if (diemChu.startsWith("D")) {
            result.put("D", result.get("D") + 1);
        } else if (diemChu.startsWith("F")) {
            result.put("F", result.get("F") + 1);
        }
    }

    return result;
    }
}


