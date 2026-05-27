package org.example.Model;

import java.sql.Time;

public class LichHoc {
    private String tenMon;
    private String maLop;
    private String phongHoc;
    private int thu;
    private Time gioBatDau;
    private Time gioKetThuc;

    public LichHoc() {
    }

    public LichHoc(String tenMon, String maLop, String phongHoc, int thu, Time gioBatDau, Time gioKetThuc) {
        this.tenMon = tenMon;
        this.maLop = maLop;
        this.phongHoc = phongHoc;
        this.thu = thu;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }

    public int getThu() {
        return thu;
    }

    public void setThu(int thu) {
        this.thu = thu;
    }

    public Time getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(Time gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public Time getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(Time gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }
}
