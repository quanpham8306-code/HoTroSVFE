package org.example.Controller;
/*
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
*/
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.example.API.DiemAPI;
import org.example.Model.Diem;
import org.example.Model.TongDiem;
import org.example.Model.UserSession;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ScoreController {
    // Thống kê nhãn trên giao diện
    @FXML
    private Label lblGPA;
    @FXML
    private Label lblTongTinChi;
    @FXML
    private Label lblSoMon;
    @FXML
    private Label lblXepLoai;

    // Biểu đồ
    @FXML
    private LineChart<String, Number> gpaChart;
    @FXML
    private PieChart resultPieChart;

    // Điều hướng Sidebar
    @FXML
    private Button btnHome;
    @FXML
    private Button btnSchedule;

    TongDiem tongdiem;

    @FXML
    private TableView<Diem> tableDiem;

    @FXML
    private TableColumn<Diem, String> colMaMon;
    @FXML
    private TableColumn<Diem, String> colTenMon;
    @FXML
    private TableColumn<Diem, Integer> colTinChi;
    @FXML
    private TableColumn<Diem, Double> colDiem10;
    @FXML
    private TableColumn<Diem, String> colDiemChu;
    @FXML
    private TableColumn<Diem, String> colTrangThai;

    @FXML
    private TableColumn<Diem, Integer> colStt;

    @FXML
    public void initialize() {
        // Tải dữ liệu điểm từ hệ thống database qua API
        loadScoreData();
    }

    private void loadScoreData() {
        try {
            // 1. Kiểm tra session đăng nhập sinh viên
            if (UserSession.getInstance() != null) {
                String studentId = UserSession.getInstance().getStudentId();

                // Gọi API lấy chuỗi JSON điểm (Tham số lớp tạm thời truyền mặc định là 1)
                String jsonResponse = DiemAPI.getByIdSvIdlop(String.valueOf(studentId), 1);
                System.out.println("Kết quả trả về từ DiemAPI: " + jsonResponse);

                if (jsonResponse != null && !jsonResponse.trim().isEmpty()) {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Diem>>() {
                    }.getType();
                    List<Diem> listDiem = gson.fromJson(jsonResponse, listType);

                    if (listDiem != null && !listDiem.isEmpty()) {
                        // 2. Tính toán các chỉ số & Hiển thị nhãn số liệu thực tế
                        tongdiem.getListDiem();

                        // 3. Vẽ biểu đồ dựa trên dữ liệu thật
                        veBieuDoLineChart(listDiem);
                        veBieuDoPieChart(listDiem);
                        return;
                    }
                }
            }

            // Gán dữ liệu mặc định bằng 0 nếu không có kết quả từ Server
            lblGPA.setText("0.00");
            lblSoMon.setText("0");
            lblTongTinChi.setText("0");
            lblXepLoai.setText("Chưa có");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void veBieuDoLineChart(List<Diem> dsDiem) {
        gpaChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Điểm số môn học");

        // Đưa dữ liệu điểm học phần hệ 4 của từng môn vào đồ thị hình tuyến
        for (Diem d : dsDiem) {
            double diemHe4 = (d.getDiemHocPhan() / 10.0) * 4.0;
            series.getData().add(new XYChart.Data<>(d.getTenMon(), diemHe4));
        }
        gpaChart.getData().add(series);
    }

    private void veBieuDoPieChart(List<Diem> dsDiem) {
        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;

        for (Diem d : dsDiem) {
            double diem = d.getDiemHocPhan();
            if (diem >= 8.5) countA++;
            else if (diem >= 7.0) countB++;
            else if (diem >= 5.5) countC++;
            else if (diem >= 4.0) countD++;
            else countF++;
        }

        resultPieChart.getData().clear();
        if (countA > 0) resultPieChart.getData().add(new PieChart.Data("A", countA));
        if (countB > 0) resultPieChart.getData().add(new PieChart.Data("B", countB));
        if (countC > 0) resultPieChart.getData().add(new PieChart.Data("C", countC));
        if (countD > 0) resultPieChart.getData().add(new PieChart.Data("D", countD));
        if (countF > 0) resultPieChart.getData().add(new PieChart.Data("F", countF));
    }

    /**
     * Sự kiện click quay lại màn hình Thông tin cá nhân (Home.fxml)
     */
    @FXML
    public void showHome() {
        switchScene("/fxml/Home.fxml", btnHome);
    }

    /**
     * Sự kiện click chuyển tiếp sang màn hình Thời khóa biểu (Schedule.fxml)
     */
    @FXML
    public void showSchedule() {
        switchScene("/fxml/Schedule.fxml", btnSchedule);
    }

    private void switchScene(String fxmlPath, Button triggerButton) {
        try {
            if (triggerButton == null || triggerButton.getScene() == null) return;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) triggerButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button btnLogout; // Khai báo nút Đăng xuất ứng với fx:id

    /**
     * Sự kiện xử lý khi bấm nút Đăng xuất
     */
    @FXML
    public void handleLogout() {
        // 1. Xóa phiên làm việc hiện tại của người dùng
        org.example.Model.UserSession.cleanSession();
        System.out.println("Đã xóa phiên đăng nhập (UserSession).");

        // 2. Chuyển hướng quay trở lại màn hình Đăng nhập (Login.fxml)
        try {
            // Hãy kiểm tra chính xác đường dẫn file Login.fxml của bạn (ví dụ: /fxml/Login.fxml)
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            javafx.scene.Parent root = loader.load();

            javafx.stage.Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            stage.show();
        } catch (java.io.IOException e) {
            System.err.println("Không thể quay về màn hình Đăng nhập. Hãy kiểm tra đường dẫn FXML!");
            e.printStackTrace();
        }
    }

    public void datatable(List<Diem> listDiem) {

        colMaMon.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getMh().getMaMon())
        );

        colTenMon.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getTenMon())
        );

        colTinChi.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getMh().getSoTinChi()).asObject()
        );
        colDiem10.setCellValueFactory(data ->
                new SimpleDoubleProperty(data.getValue().getDiemHocPhan()).asObject());
        colDiemChu.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getDiemchu()));
        colTrangThai.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getTrangthai() != null
                                ? data.getValue().getTrangthai()
                                : ""
                )
        );


            // 🔥 STT (tự đánh số)
            colStt.setCellValueFactory(cellData ->
                    new SimpleIntegerProperty(
                            tableDiem.getItems().indexOf(cellData.getValue()) + 1
                    ).asObject()
            );

            // đổ dữ liệu
            ObservableList<Diem> data = FXCollections.observableArrayList(listDiem);

            tableDiem.setItems(data);
        }

}


