package org.example.Controller.Student;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.Config.AppSession;
import org.example.Model.*;
import org.example.Service.DiemService;
import org.example.Service.SinhVienService;
import org.example.Service.ThoiKhoaBieuService;
import org.example.Util.SceneUtil;

import java.time.LocalDate;
import java.util.List;


public class StudentHomeController {
    @FXML private Button btnScore;
    @FXML private Button btnSchedule;
    @FXML private Button btnSubject;
    @FXML private Button btnNote;
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
    @FXML private TextField txtMaSV;
    @FXML private TextField txtClass;
    @FXML private TextField txtGioiTinh;

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
    private void loadStudentInfo() {
        try {
            SinhVien sv = sinhVienService.getMyInfo();

            lblHoTenHeader.setText(sv.getHoTen()+" ");
            lblMaSvHeader.setText(sv.getMaSv()+" ");
            lblNganhHeader.setText(sv.getNganh()+" ");
            lblKhoaHeader.setText(sv.getKhoa()+" ");
            txtHoTen.setText(sv.getHoTen()+" ");
            txtNgaySinh.setText(sv.getNgaySinh().toString()+" ");
            txtID.setText(sv.getCccd()+" ");
            txtAddress.setText(sv.getDiaChi()+" ");
            txtEmail.setText(sv.getEmail()+" ");
            txtPhone.setText(sv.getSoDienThoai()+" ");
            txtMaSV.setText(sv.getMaSv()+" ");
            txtClass.setText(sv.getLop()+" ");
            txtGioiTinh.setText(
                    sv.getGioiTinh() ? "Nam" : "Nữ");
            if(sv.getNamNhapHoc() + 4 < LocalDate.now().getYear())
                lblStatus.setText("● Đã tốt nghiệp");

        } catch (Exception e) {
            e.printStackTrace();
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
        txtMaSV.setEditable(x);
        txtClass.setEditable(x);
        txtGioiTinh.setEditable(x);
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
    private void loadSchedule()
    {
        List<BuoiHoc> buoiHocList = thoiKhoaBieuService.getNearSchedule();

        if(buoiHocList.size() == 3)
        {
            LopHocPhan lopHocPhan1 = buoiHocList.get(1).getLopHocPhanDTO();
            LopHocPhan lopHocPhan2 = buoiHocList.get(2).getLopHocPhanDTO();
            LopHocPhan lopHocPhan3 = buoiHocList.get(3).getLopHocPhanDTO();

            date1.setText(buoiHocList.get(1).getNgayHoc().toString());
            date2.setText(buoiHocList.get(2).getNgayHoc().toString());
            date3.setText(buoiHocList.get(3).getNgayHoc().toString());

            time1.setText(lopHocPhan1.getGioBatDau() + "-" + lopHocPhan1.getGioKetThuc());
            time2.setText(lopHocPhan2.getGioBatDau() + "-" + lopHocPhan2.getGioKetThuc());
            time3.setText(lopHocPhan3.getGioBatDau() + "-" + lopHocPhan3.getGioKetThuc());

            address1.setText(lopHocPhan1.getPhongHoc());
            address2.setText(lopHocPhan2.getPhongHoc());
            address3.setText(lopHocPhan3.getPhongHoc());

            mon1.setText(lopHocPhan1.getTenMonHoc());
            mon2.setText(lopHocPhan2.getTenMonHoc());
            mon3.setText(lopHocPhan3.getTenMonHoc());
        }
        else if(buoiHocList.size() == 2)
        {
            LopHocPhan lopHocPhan1 = buoiHocList.get(1).getLopHocPhanDTO();
            LopHocPhan lopHocPhan2 = buoiHocList.get(2).getLopHocPhanDTO();

            date1.setText(buoiHocList.get(1).getNgayHoc().toString());
            date2.setText(buoiHocList.get(2).getNgayHoc().toString());
            date3.setText("");

            time1.setText(lopHocPhan1.getGioBatDau() + "-" + lopHocPhan1.getGioKetThuc());
            time2.setText(lopHocPhan2.getGioBatDau() + "-" + lopHocPhan2.getGioKetThuc());
            time3.setText("");

            address1.setText(lopHocPhan1.getPhongHoc());
            address2.setText(lopHocPhan2.getPhongHoc());
            address3.setText("");

            mon1.setText(lopHocPhan1.getTenMonHoc());
            mon2.setText(lopHocPhan2.getTenMonHoc());
            mon3.setText("");
        }
        if(buoiHocList.size() == 1)
        {
            LopHocPhan lopHocPhan1 = buoiHocList.get(1).getLopHocPhanDTO();

            date1.setText(buoiHocList.get(1).getNgayHoc().toString());
            date2.setText("");
            date3.setText("");

            time1.setText(lopHocPhan1.getGioBatDau() + "-" + lopHocPhan1.getGioKetThuc());
            time2.setText("");
            time3.setText("");

            address1.setText(lopHocPhan1.getPhongHoc());
            address2.setText("");
            address3.setText("");

            mon1.setText(lopHocPhan1.getTenMonHoc());
            mon2.setText("");
            mon3.setText("");
        }
        else
        {
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
        }


    }
    private String valueOrEmpty(String value) {
        return value == null ? "" : value;
    }
}
