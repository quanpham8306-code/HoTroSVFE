package org.example.Model;

public class MonHoc {
    private String tenMonHoc;
    private int soTinChi;

    public MonHoc() {
    }

    public MonHoc(String tenMonHoc, int soTinChi) {
        this.tenMonHoc = tenMonHoc;
        this.soTinChi = soTinChi;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }
}
