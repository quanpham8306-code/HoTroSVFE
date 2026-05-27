package org.example.Model;

import java.sql.Time;

public class LopHocPhan {
    private String tenMonHoc;
    private String maLopHP;
    private int soLuongToiDa;
    private String hocKy;
    private String namHoc;

    public LopHocPhan() {
    }

    public LopHocPhan(String tenMonHoc, String maLopHP, int soLuongToiDa, String hocKy, String namHoc) {
        this.tenMonHoc = tenMonHoc;
        this.maLopHP = maLopHP;
        this.soLuongToiDa = soLuongToiDa;
        this.hocKy = hocKy;
        this.namHoc = namHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getMaLopHP() {
        return maLopHP;
    }

    public void setMaLopHP(String maLopHP) {
        this.maLopHP = maLopHP;
    }

    public int getSoLuongToiDa() {
        return soLuongToiDa;
    }

    public void setSoLuongToiDa(int soLuongToiDa) {
        this.soLuongToiDa = soLuongToiDa;
    }

    public String getHocKy() {
        return hocKy;
    }

    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }
}
