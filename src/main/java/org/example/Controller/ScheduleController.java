package org.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
public class ScheduleController {
    // Khai báo các nút bấm Sidebar tương ứng với fx:id trong file FXML
    @FXML
    private Button btnHome;

    @FXML
    private Button btnScore;

    /**
     * Hàm tự động chạy khi giao diện Schedule.fxml được nạp lên thành công
     */
    @FXML
    public void initialize() {
        System.out.println("Màn hình Lịch học đã khởi tạo thành công.");
        // Sau này bạn có thể gọi API để lấy lịch học thực tế và đổ vào GridPane tại đây
    }

    /**
     * Sự kiện bấm vào nút "Thông tin cá nhân" -> Chuyển về màn hình Home
     */
    @FXML
    public void showHome() {
        try {
            // Tải file giao diện Home.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
            Parent root = loader.load();

            // Lấy Stage hiện tại thông qua nút bấm vừa click
            Stage stage = (Stage) btnHome.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Lỗi: Không thể tìm thấy hoặc nạp file /fxml/Home.fxml");
            e.printStackTrace();
        }
    }

    /**
     * Sự kiện bấm vào nút "Kết quả học tập" -> Chuyển sang màn hình Score
     */
    @FXML
    public void showScore() {
        try {
            // Tải file giao diện Score.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Score.fxml"));
            Parent root = loader.load();

            // Lấy Stage hiện tại thông qua nút bấm vừa click
            Stage stage = (Stage) btnScore.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Lỗi: Không thể tìm thấy hoặc nạp file /fxml/Score.fxml");
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
}
