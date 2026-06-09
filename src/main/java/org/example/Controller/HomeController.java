package org.example.Controller;
/*
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.API.SinhVienAPI;
import org.example.Model.SinhVien;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.API.SV.ProfileAPI;
import org.example.API.SinhVienAPI;
import org.example.Model.SinhVien;
import org.example.Model.TongDiem;
import org.example.Model.UserSession;

import java.io.IOException;
import java.text.SimpleDateFormat;


public class HomeController {

    // Nút điều hướng Sidebar
    @FXML
    private Button btnScore;
    @FXML
    private Button btnSchedule; // Thêm để bắt sự kiện lịch học

    // Nút chức năng Thao tác dữ liệu
    @FXML
    private Button EditButton;
    @FXML
    private Button LuuButton;

    // Các trường dữ liệu thông tin cá nhân
    @FXML
    private TextField txtNgaySinh;
    @FXML
    private TextField txtGioiTinh;
    @FXML
    private TextField txtHovaTen;
    @FXML
    private Label Labelmasv; // hiện ở chỗ bên trên
    @FXML
    private Label LabelHoten;//hiện ở chỗ bên trên
    @FXML
    private TextField txtSDT;
    @FXML
    private TextField txtDiaChi;
    @FXML
    private TextField txtMaSV;
    @FXML
    private TextField txtLop;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCccd;

    @FXML
    private Label LabelGPA;
    @FXML
    private Label LabelSTC;
    @FXML
    private Label LabelSoMon;
    @FXML
    private Label LabelXepLoai;

    private SinhVien sv = ProfileAPI.getMyProfile();
    // Đối tượng lưu trữ thông tin sinh viên hiện tại;
    TongDiem tongDiem;
   public void initialize() {
        System.out.println("--- KHỞI TẠO MÀN HÌNH HOME ---");

        try {
            /*
            // 3. Gọi API
            String jsonResponse = ProfileAPI.getMyProfile();

            System.out.println("-> JSON Sinh viên nhận được: " + jsonResponse);

            if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
                System.err.println("LỖI: API trả về rỗng!");
                return;
            }

            // 4. Parse JSON
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
                    .create();

            try {
                sv = gson.fromJson(jsonResponse, SinhVien.class);
            } catch (Exception e) {
                System.err.println("Parse lỗi ngày -> thử format yyyy-MM-dd");

                gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd")
                        .create();

                sv = gson.fromJson(jsonResponse, SinhVien.class);
            }
*/
            // 5. Đổ dữ liệu lên UI
            if (sv != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                txtHovaTen.setText(sv.getHoTen() != null ? sv.getHoTen() : "");
                txtNgaySinh.setText(sv.getNgaySinh() != null ? sdf.format(sv.getNgaySinh()) : "");
                txtGioiTinh.setText(sv.isGioiTinh() ? "Nam" : "Nữ");
                txtSDT.setText(sv.getSoDth() != null ? sv.getSoDth() : "");
                txtDiaChi.setText(sv.getDiaChi() != null ? sv.getDiaChi() : "");
                txtMaSV.setText(sv.getMaSv() != null ? sv.getMaSv() : "");
                txtLop.setText(sv.getLop() != null ? sv.getLop() : "");
                txtEmail.setText(sv.getEmail() != null ? sv.getEmail() : "");
                txtCccd.setText(sv.getCccd() != null ? sv.getCccd() : "");
                LabelHoten.setText(sv.getHoTen()!= null ? sv.getHoTen() : "");
                Labelmasv.setText(sv.getMaSv() != null ? sv.getMaSv() : "");
                LabelGPA.setText(String.valueOf(tongDiem.getGPA()));
                LabelSTC.setText(String.valueOf(tongDiem.getTongTin()));
                LabelSoMon.setText(String.valueOf(tongDiem.getGPA()));
                LabelXepLoai.setText(String.valueOf(tongDiem.getGPA()));




                System.out.println("✔ Đổ dữ liệu thành công");
            }

            // 6. khóa form
            setEditable(false);
            LuuButton.setDisable(true);
            EditButton.setDisable(false);

        } catch (Exception e) {
            System.err.println("Lỗi initialize Home:");
            e.printStackTrace();
        }
    }

    /**
     * Hàm bật/tắt chế độ chỉnh sửa (Editable) của các ô TextField thông tin lý lịch
     */
    private void setEditable(boolean value) {
        txtHovaTen.setEditable(value);
        txtNgaySinh.setEditable(value);
        txtGioiTinh.setEditable(value);
        txtSDT.setEditable(value);
        txtDiaChi.setEditable(value);
        txtEmail.setEditable(value);
        txtCccd.setEditable(value);

        // Mã SV và Lớp hành chính thường do nhà trường quản lý, nên để mặc định luôn khóa (false)
        txtMaSV.setEditable(false);
        txtLop.setEditable(false);
    }

    /**
     * Sự kiện khi người dùng nhấn nút "Sửa" (EditButton)
     */
    @FXML
    public void edit() {
        // Mở khóa các trường dữ liệu để người dùng nhập mới
        setEditable(true);

        // Điều chỉnh trạng thái ẩn/hiện kích hoạt của các nút bấm
        LuuButton.setDisable(false);
        EditButton.setDisable(true);
    }

    /**
     * Sự kiện khi người dùng nhấn nút "Lưu" (LuuButton)
     */
    @FXML
    public void save() {
        if (sv == null) return;

        try {
            // Cập nhật lại các thông tin mới từ màn hình vào đối tượng mẫu `sv`
            sv.setHoTen(txtHovaTen.getText().trim());
            sv.setSoDth(txtSDT.getText().trim());
            sv.setDiaChi(txtDiaChi.getText().trim());
            sv.setEmail(txtEmail.getText().trim());
            sv.setCccd(txtCccd.getText().trim());
            sv.setGioiTinh(txtGioiTinh.getText().equalsIgnoreCase("Nam"));

            // Xử lý chuyển đổi định dạng chuỗi ngày sinh thành Date để lưu trữ
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                sv.setNgaySinh(sdf.parse(txtNgaySinh.getText().trim()));
            } catch (Exception ex) {
                showNotification(Alert.AlertType.ERROR, "Lỗi định dạng", "Vui lòng nhập ngày sinh đúng định dạng dd/MM/yyyy!");
                return;
            }

            // Gửi dữ liệu cập nhật về Backend thông qua API Client
            SinhVienAPI.update(sv);
            System.out.println("Đã gửi yêu cầu cập nhật thông tin thành công lên Backend.");

            showNotification(Alert.AlertType.INFORMATION, "Thành công", "Cập nhật dữ liệu thông tin cá nhân thành công!");

            // Đưa giao diện về trạng thái khóa ban đầu
            setEditable(false);
            LuuButton.setDisable(true);
            EditButton.setDisable(false);

        } catch (Exception e) {
            e.printStackTrace();
            showNotification(Alert.AlertType.ERROR, "Thất bại", "Lỗi trong quá trình lưu dữ liệu!");
        }
    }

    /**
     * Sự kiện click Sidebar điều hướng sang màn hình Xem điểm (Score.fxml)
     */
    @FXML
    public void showScore() {
        switchScene("/fxml/Score.fxml", btnScore);
    }

    /**
     * Sự kiện click Sidebar điều hướng sang màn hình Thời khóa biểu (Schedule.fxml)
     */
    @FXML
    public void showSchedule() {
        switchScene("/fxml/Schedule.fxml", btnSchedule);
    }

    /**
     * Hàm dùng chung hỗ trợ chuyển đổi Scene giữa các tính năng
     */
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
            showNotification(Alert.AlertType.ERROR, "Lỗi chuyển cảnh", "Không thể tải giao diện: " + fxmlPath);
        }
    }

    /**
     * Hàm tiện ích hiển thị hộp thoại thông báo nhanh (Alert)
     */
    private void showNotification(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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
}

