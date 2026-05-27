package org.example.Model;

import java.util.Date;

public class SinhVien {

    private String maSv;
    private String hoTen;
    private Date ngaySinh;
    private boolean gioiTinh;
    private String email;
    private String lop;
    private String soDth;
    private String cccd;
    private String diaChi;

    public SinhVien() {
    }

    public SinhVien(String maSv, String hoTen, Date ngaySinh, boolean gioiTinh, String email, String lop, String soDth, String cccd, String diaChi) {
        this.maSv = maSv;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.lop = lop;
        this.soDth = soDth;
        this.cccd = cccd;
        this.diaChi = diaChi;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getSoDth() {
        return soDth;
    }

    public void setSoDth(String soDth) {
        this.soDth = soDth;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
