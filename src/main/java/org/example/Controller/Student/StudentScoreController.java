package org.example.Controller.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Config.AppSession;
import org.example.Model.BangDiem;

import org.example.Model.TongDiem;

import org.example.Service.DiemService;
import org.example.Util.SceneUtil;

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


    @FXML private LineChart<String, Number> gpaChart;
    @FXML private PieChart resultPieChart;

    @FXML private Button btnHome;
    @FXML private Button btnSchedule;
    @FXML private Button btnLogout;
    @FXML private Button btnSubject;
    @FXML private Button btnNote;

    @FXML private TableView<BangDiem> scoreTable;
    @FXML private TableColumn<BangDiem, String> colMaMonHoc;
    @FXML private TableColumn<BangDiem, Integer> colTenMonHoc;
    @FXML private TableColumn<BangDiem, Integer> colSoTinChi;
    @FXML private TableColumn<BangDiem, Integer> colDiemHe10;
    @FXML private TableColumn<BangDiem, String> colDiemChu;
    @FXML private TableColumn<BangDiem, String> colTrangThai;

    private final DiemService diemService = new DiemService();
    @FXML
    public void showHome() {
        SceneUtil.switchScene(btnHome, "/fxml/Home.fxml");
    }

    @FXML
    public void showSchedule() {
        SceneUtil.switchScene(btnSchedule, "/fxml/Schedule.fxml");
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
    public void initialize() {
        setupTable();
        loadListScoreData();
        loadScoreData();
    }

    private void loadScoreData() {
        try {
            List<BangDiem> scores = diemService.getMyScores();
            TongDiem summary = diemService.getMySummary();

            scoreTable.setItems(FXCollections.observableArrayList(scores));

            lblGPA.setText(String.format("%.2f", summary.getGpa()));
            lblTongTinChi.setText(String.valueOf(summary.getTongTin()));
            lblSoMon.setText(String.valueOf(summary.getTongMon()));
            lblXepLoai.setText(summary.getXepLoai() == null ? "Chưa có" : summary.getXepLoai());

            drawLineChart(scores);
            drawPieChart(scores);

        } catch (Exception e) {
            e.printStackTrace();
            lblGPA.setText("0.00");
            lblSoMon.setText("0");
            lblTongTinChi.setText("0");
            lblXepLoai.setText("Chưa có");
        }
    }

    private void drawLineChart(List<BangDiem> scores) {
        if (gpaChart == null) return;

        gpaChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Điểm hệ 4 từng môn");

        for (BangDiem score : scores) {
            double diemHe4 = score.getDiemHocPhan() / 10.0 * 4.0;
            String tenMon = score.getTenMon() == null ? score.getMaMon() : score.getTenMon();
            series.getData().add(new XYChart.Data<>(tenMon, diemHe4));
        }

        gpaChart.getData().add(series);
    }

    private void drawPieChart(List<BangDiem> scores) {
        if (resultPieChart == null) return;

        Map<String, Long> countByGrade = scores.stream()
                .collect(Collectors.groupingBy(
                        score -> score.getDiemChu() == null ? "Chưa có" : score.getDiemChu(),
                        Collectors.counting()
                ));

        resultPieChart.setData(FXCollections.observableArrayList(
                countByGrade.entrySet().stream()
                        .map(entry -> new PieChart.Data(entry.getKey(), entry.getValue()))
                        .toList()
        ));

        Map<String, Integer> gradeCount = countGradeLetters(scores);

        int soDiemA = gradeCount.get("A");
        int soDiemB = gradeCount.get("B");
        int soDiemC = gradeCount.get("C");
        int soDiemD = gradeCount.get("D");
        int soDiemF = gradeCount.get("F");

        lblA.setText(String.format(""+soDiemA));
        lblB.setText(String.format(""+soDiemB));
        lblC.setText(String.format(""+soDiemC));
        lblD.setText(String.format(""+soDiemD));
        lblF.setText(String.format(""+soDiemF));

    }
    private void setupTable() {
        colMaMonHoc.setCellValueFactory(new PropertyValueFactory<>("maMon"));
        colTenMonHoc.setCellValueFactory(new PropertyValueFactory<>("tenMon"));
        colSoTinChi.setCellValueFactory(new PropertyValueFactory<>("soTin"));
        colDiemHe10.setCellValueFactory(new PropertyValueFactory<>("diemHocPhan"));
        colDiemChu.setCellValueFactory(new PropertyValueFactory<>("diemChu"));
        colTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
    }
    private void setupTableStyle() {
        scoreTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colMaMonHoc.getStyleClass().add("center-cell");
        colTenMonHoc.getStyleClass().add("center-cell");
        colSoTinChi.getStyleClass().add("center-cell");
        colDiemHe10.getStyleClass().add("center-cell");
        colDiemChu.getStyleClass().add("center-cell");
        colTrangThai.getStyleClass().add("status-cell");
    }
    private void loadListScoreData() {
        ObservableList<BangDiem> diems = FXCollections.observableArrayList();
        List<BangDiem> scores = diemService.getMyScores();
        diems.addAll(scores);
        scoreTable.setItems(diems);
        setupTableStyle();
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

            if (diemChu != null) {
                diemChu = diemChu.toUpperCase();

                if (result.containsKey(diemChu)) {
                    result.put(diemChu, result.get(diemChu) + 1);
                }
            }
        }
        return result;
    }
}


