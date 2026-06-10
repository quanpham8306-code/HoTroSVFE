package org.example.Controller.Student;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.Config.AppSession;
import org.example.Model.SinhVien;
import org.example.Model.TongDiem;
import org.example.Service.DiemService;
import org.example.Service.SinhVienService;
import org.example.Util.SceneUtil;


public class StudentHomeController {
    @FXML private Button btnScore;
    @FXML private Button btnSchedule;
    @FXML private Button btnLogout;

    @FXML private Label lblHoTenHeader;
    @FXML private Label lblMaSvHeader;
    @FXML private Label lblNganhHeader;
    @FXML private Label lblKhoaHeader;

    @FXML private TextField txtHoTen;
    @FXML private TextField txtNgaySinh;
    @FXML private TextField txtID;
    @FXML private TextField txtAddress;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhone;
    @FXML private TextField txtMaSv;
    @FXML private TextField txtClass;
    @FXML private TextField txtGioiTinh;

    @FXML private Label lblMaSv;
    @FXML private Label lblGpa;
    @FXML private Label lblTongTin;
    @FXML private Label lblTongMon;
    @FXML private Label lblXepLoai;

    private final SinhVienService sinhVienService = new SinhVienService();
    private final DiemService diemService = new DiemService();

    @FXML
    public void initialize() {
        loadStudentInfo();
        loadScoreSummary();
        acceptEdit(false);
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
    public void handleLogout() {
        AppSession.clear();
        SceneUtil.switchScene(btnLogout, "/fxml/Login.fxml");
    }


    private void loadStudentInfo() {
        try {
            SinhVien sv = sinhVienService.getMyInfo();

            lblHoTenHeader.setText(sv.getMaSv());
            lblMaSvHeader.setText(sv.getMaSv());
            lblNganhHeader.setText(sv.getNganh());
            lblKhoaHeader.setText(sv.getNamNhapHoc()+"");

            txtHoTen.setText(sv.getHoTen());
            txtNgaySinh.setText(sv.getNgaySinh().toString());
            txtID.setText(sv.getCccd());
            txtAddress.setText(sv.getDiaChi());
            txtEmail.setText(sv.getEmail());
            txtPhone.setText(sv.getSoDienThoai());
            txtMaSv.setText(sv.getMaSv());
            txtClass.setText(sv.getLop());
            txtGioiTinh.setText(
                    sv.getGioiTinh() ? "Nam" : "Nữ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadScoreSummary() {
        try {
            TongDiem summary = diemService.getMySummary();
            lblGpa.setText( String.format("%.2f /4.0", summary.getGpa()));
            lblTongTin.setText(summary.getTongTin() + " TC");
            lblTongMon.setText( String.valueOf(summary.getTongMon()));
            lblXepLoai.setText(summary.getXepLoai());
        } catch (Exception e) {
            lblGpa.setText("0.0/4.0");
            lblTongTin.setText("0 TC");
            lblTongMon.setText("0");
            lblXepLoai.setText("Chưa có xếp hạng.");
        }
    }
    public void acceptEdit(boolean x)
    {
        txtHoTen.setEditable(x);
        txtNgaySinh.setEditable(x);
        txtID.setEditable(x);
        txtAddress.setEditable(x);
        txtEmail.setEditable(x);
        txtPhone.setEditable(x);
        txtMaSv.setEditable(x);
        txtClass.setEditable(x);
        txtGioiTinh.setEditable(x);
    }

    private String valueOrEmpty(String value) {
        return value == null ? "" : value;
    }
}
