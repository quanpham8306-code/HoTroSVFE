package org.example.Controller.Common;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.Service.TaiKhoanService;
import org.example.Util.AlertUtil;

public class ChangePwsController {
    TaiKhoanService taiKhoanService = new TaiKhoanService();

    @FXML private PasswordField oldPassword;
    @FXML private PasswordField newPassword;
    @FXML private PasswordField confirmPassword;

    @FXML private Button btnCancel;

    @FXML
    public void cancel() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void save() {
        String oldPass = oldPassword.getText().trim();
        String newPass = newPassword.getText().trim();
        String confirmPass = confirmPassword.getText().trim();

        if (oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
            AlertUtil.showError("Vui lòng nhập đầy đủ thông tin.");
            return;
        }
        if (!newPassword.getText().equals(confirmPassword.getText())) {
            AlertUtil.showError("Mật khẩu xác nhận không khớp.");
            return;
        }
        String respone = taiKhoanService.changePws(oldPass, newPass);
        if (respone.equals("SUCCESS")) {
            AlertUtil.showAlert("Đổi mật khẩu thành công");
        }
        else {
            AlertUtil.showAlert("Đổi mật khẩu thất bại : " + respone);
        }
    }
}
