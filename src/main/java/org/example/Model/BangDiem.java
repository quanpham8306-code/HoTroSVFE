package org.example.Model;

public class BangDiem {
    private String maMon;
    private String tenMon;
    private int soTin;
    private double diemHocPhan;
    private String diemChu;
    private String trangThai;

    public BangDiem() {
    }

    public BangDiem(String maMon, String tenMon, int soTin, double diemHocPhan, String diemChu, String trangThai) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.soTin = soTin;
        this.diemHocPhan = diemHocPhan;
        this.diemChu = diemChu;
        this.trangThai = trangThai;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getSoTin() {
        return soTin;
    }

    public void setSoTin(int soTin) {
        this.soTin = soTin;
    }

    public double getDiemHocPhan() {
        return diemHocPhan;
    }

    public void setDiemHocPhan(double diemHocPhan) {
        this.diemHocPhan = diemHocPhan;
    }

    public String getDiemChu() {
        return diemChu;
    }

    public void setDiemChu(String diemChu) {
        this.diemChu = diemChu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
