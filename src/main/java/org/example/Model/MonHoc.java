package org.example.Model;

public class MonHoc {
    private String maMon;

    private String tenMonHoc;

    private int soTinChi;

    public MonHoc() {
    }

    public MonHoc(String maMon, String tenMonHoc, int soTinChi) {
        this.maMon = maMon;
        this.tenMonHoc = tenMonHoc;
        this.soTinChi = soTinChi;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
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
