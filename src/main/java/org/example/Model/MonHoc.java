package org.example.Model;

public class MonHoc {
    private String tenMonHoc;
    private int soTinChi;
    private String MaMon;

    public MonHoc() {
    }

    public MonHoc(String tenMonHoc, int soTinChi, String maMon) {
        this.tenMonHoc = tenMonHoc;
        this.soTinChi = soTinChi;
        MaMon = maMon;
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

    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String maMon) {
        MaMon = maMon;
    }
}
