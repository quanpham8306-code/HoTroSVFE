package org.example.Model;

public class Diem {

    private String mon;
    private String maSv;
    private String maLopHP;
    private double diemQuaTrinh;
    private double diemCuoiKy;
    private double diemHocPhan;
    private String trangThai;

    public Diem() {
    }

    public Diem(String mon, String maSv, String maLopHP, double diemQuaTrinh, double diemCuoiKy, double diemHocPhan, String trangThai) {
        this.mon = mon;
        this.maSv = maSv;
        this.maLopHP = maLopHP;
        this.diemQuaTrinh = diemQuaTrinh;
        this.diemCuoiKy = diemCuoiKy;
        this.diemHocPhan = diemHocPhan;
        this.trangThai = trangThai;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getMaLopHP() {
        return maLopHP;
    }

    public void setMaLopHP(String maLopHP) {
        this.maLopHP = maLopHP;
    }

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
