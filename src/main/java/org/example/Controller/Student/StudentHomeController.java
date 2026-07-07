package org.example.Controller.Student;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.Config.AppSession;
import org.example.Model.*;
import org.example.Service.Student.DiemService;
import org.example.Service.Student.SinhVienService;
import org.example.Service.Student.ThoiKhoaBieuService;
import org.example.Util.DateUtil;
import org.example.Util.SceneUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class StudentHomeController {
    @FXML private Button btnScore;
    @FXML private Button btnSchedule;
    @FXML private Button btnSubject;
    @FXML private Button btnNote;
    @FXML private Button btnSupport;
    @FXML private Button btnLogout;

    @FXML private Label lblHoTenHeader;
    @FXML private Label lblMaSvHeader;
    @FXML private Label lblNganhHeader;
    @FXML private Label lblKhoaHeader;

    @FXML private Label lblName;
    @FXML private Label lblDate;
    @FXML private Label lblId;
    @FXML private Label lblAd;
    @FXML private Label lblMail;
    @FXML private Label lblPhone;
    @FXML private Label lblMaSv;
    @FXML private Label lblClass;
    @FXML private Label lblSex;

    @FXML private Label lblGpa;
    @FXML private Label lblSoTinChi;
    @FXML private Label lblSoMon;
    @FXML private Label lblXepLoai;
    @FXML private Label lblStatus;

    @FXML private Label date1;
    @FXML private Label date2;
    @FXML private Label date3;
    @FXML private Label mon1;
    @FXML private Label mon2;
    @FXML private Label mon3;
    @FXML private Label time1;
    @FXML private Label time2;
    @FXML private Label time3;
    @FXML private Label address1;
    @FXML private Label address2;
    @FXML private Label address3;


    private final SinhVienService sinhVienService = new SinhVienService();
    private final DiemService diemService = new DiemService();
    private final ThoiKhoaBieuService thoiKhoaBieuService = new ThoiKhoaBieuService();

    @FXML
    public void initialize() {
        loadStudentInfo();
        loadScoreSummary();
        loadSchedule();
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
    private void loadStudentInfo() {
        try {
            SinhVien sv = sinhVienService.getMyInfo();

            lblHoTenHeader.setText(sv.getHoTen()+" ");
            lblMaSvHeader.setText(sv.getMaSv()+" ");
            lblNganhHeader.setText(sv.getNganh()+" ");
            lblKhoaHeader.setText(sv.getKhoa()+" ");
            lblName.setText(sv.getHoTen()+" ");
            lblDate.setText(DateUtil.format(sv.getNgaySinh()));
            lblId.setText(sv.getCccd()+" ");
            lblAd.setText(sv.getDiaChi()+" ");
            lblMail.setText(sv.getEmail()+" ");
            lblPhone.setText(sv.getSoDienThoai()+" ");
            lblMaSv.setText(sv.getMaSv()+" ");
            lblClass.setText(sv.getLop()+" ");
            lblSex.setText(
                    sv.getGioiTinh() ? "Nam" : "Nữ");
            if(sv.getNamNhapHoc() + 4 < LocalDate.now().getYear())
                lblStatus.setText("● Đã tốt nghiệp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadScoreSummary() {
        try {
            TongDiem summary = diemService.getMySummary();
            lblGpa.setText( String.format("%.2f /4.0", summary.getGpa()));
            lblSoTinChi.setText(summary.getTongTin() + " TC");
            lblSoMon.setText( String.valueOf(summary.getTongMon()));
            lblXepLoai.setText(summary.getXepLoai());
        } catch (Exception e) {
            lblGpa.setText("0.0/4.0");
            lblSoTinChi.setText("0 TC");
            lblSoMon.setText("0");
            lblXepLoai.setText("Chưa có xếp hạng.");
        }
    }
    private void loadSchedule() {
        List<BuoiHoc> buoiHocList = thoiKhoaBieuService.getNearSchedule();

        // Xóa hết trước
        date1.setText("");
        date2.setText("");
        date3.setText("");

        time1.setText("");
        time2.setText("");
        time3.setText("");

        address1.setText("");
        address2.setText("");
        address3.setText("");

        mon1.setText("");
        mon2.setText("");
        mon3.setText("");

        if (buoiHocList == null || buoiHocList.isEmpty()) {
            return;
        }

        if (buoiHocList.size() >= 1) {
            setScheduleItem(
                    buoiHocList.get(0),
                    date1,
                    time1,
                    address1,
                    mon1
            );
        }

        if (buoiHocList.size() >= 2) {
            setScheduleItem(
                    buoiHocList.get(1),
                    date2,
                    time2,
                    address2,
                    mon2
            );
        }

        if (buoiHocList.size() >= 3) {
            setScheduleItem(
                    buoiHocList.get(2),
                    date3,
                    time3,
                    address3,
                    mon3
            );
        }
    }
    private void setScheduleItem(BuoiHoc buoiHoc,
                                 Label date,
                                 Label time,
                                 Label address,
                                 Label mon) {

        if (buoiHoc == null || buoiHoc.getLopHocPhanDTO() == null) {
            return;
        }

        LopHocPhan lopHocPhan = buoiHoc.getLopHocPhanDTO();

        date.setText(buoiHoc.getNgayHoc() != null
                ? DateUtil.format(buoiHoc.getNgayHoc())
                : "");

        time.setText(
                lopHocPhan.getGioBatDau() + " - " + lopHocPhan.getGioKetThuc()
        );

        address.setText(lopHocPhan.getPhongHoc());
        mon.setText(lopHocPhan.getTenMonHoc());
    }
}
