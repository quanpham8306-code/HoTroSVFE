package org.example.Model;

public class DangKyLopRequest {
        private String maSv;
        private String maLopHp;

    public DangKyLopRequest() {
    }

    public DangKyLopRequest(String maSv, String maLopHp) {
        this.maSv = maSv;
        this.maLopHp = maLopHp;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getMaLopHp() {
        return maLopHp;
    }

    public void setMaLopHp(String maLopHp) {
        this.maLopHp = maLopHp;
    }
}
