package org.example.Model;

public class Diem {

    private SinhVien sinhVien;
    private LopHocPhan lopHocPhan;
    private double diemQuaTrinh;
    private double diemCuoiKy;
    private double diemHocPhan;

    public Diem() {
    }

    public Diem(SinhVien sinhVien, LopHocPhan lopHocPhan, double diemQuaTrinh, double diemCuoiKy, double diemHocPhan) {
        this.sinhVien = sinhVien;
        this.lopHocPhan = lopHocPhan;
        this.diemQuaTrinh = diemQuaTrinh;
        this.diemCuoiKy = diemCuoiKy;
        this.diemHocPhan = diemHocPhan;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public LopHocPhan getLopHocPhan() {
        return lopHocPhan;
    }

    public void setLopHocPhan(LopHocPhan lopHocPhan) {
        this.lopHocPhan = lopHocPhan;
    }

    public double getDiemQuaTrinh() {
        return diemQuaTrinh;
    }

    public void setDiemQuaTrinh(double diemQuaTrinh) {
        this.diemQuaTrinh = diemQuaTrinh;
    }

    public double getDiemCuoiKy() {
        return diemCuoiKy;
    }

    public void setDiemCuoiKy(double diemCuoiKy) {
        this.diemCuoiKy = diemCuoiKy;
    }

    public double getDiemHocPhan() {
        return diemHocPhan;
    }

    public void setDiemHocPhan(double diemHocPhan) {
        this.diemHocPhan = diemHocPhan;
    }
}
